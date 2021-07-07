package com.jes.myapp;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jes.myapp.service.MemberService;
import com.jes.myapp.vo.MemberVO;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {

		
		@Autowired
		MemberService memberService;
		@RequestMapping(value = "logout.jes", 
				method= {RequestMethod.POST},
				produces = "application/text; charset=utf8")			
		@ResponseBody
		public String logout(HttpServletRequest request,
				HttpServletResponse response){
			
				HttpSession session=request.getSession(false);
				session.invalidate();
				return "";
			
		}

	
		@RequestMapping(value = "login.jes", 
		method= {RequestMethod.POST},
		produces = "application/text; charset=utf8")			

		@ResponseBody
public String login(HttpServletRequest request,
		HttpServletResponse response){
	String id=request.getParameter("id");
	String pw=request.getParameter("pw");		
	
	try {
		MemberVO m=new MemberVO(id,pw); 
		String name=memberService.login(m);
		if(name!=null) {
			HttpSession session=request.getSession();
			session.setAttribute("member", m);
			return id+"님 접속중";
		}else {
			return "로그인 실패";
		}
	}catch(Exception e) {
		return e.getMessage();
	}		
}

		@ResponseBody
		public String memberInsert(HttpServletRequest request,
				HttpServletResponse response)throws Exception{
			String id=request.getParameter("id");
			String pw=request.getParameter("pw");
			String name=request.getParameter("name");
			
			MemberVO m=new MemberVO(id,pw,name);
			System.out.print(m);
			
			try {
			memberService.memberInsert(m);
			return name+"님 회원가입 되셨습니다.";
			}catch(Exception e) {
				return e.getMessage();
				
			}
			
			
			
		}		
	}


	

