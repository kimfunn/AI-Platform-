package my.jes.web;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import my.jes.web.service.MemberService;
import my.jes.web.vo.MemberVO;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {

	@Autowired
	MemberService memberService;
	@RequestMapping(value = "memberDelete.jes", 
			method= {RequestMethod.POST},
			produces = "application/text; charset=utf8")			
	@ResponseBody
	public String memberDelete(@RequestParam("id") String id,
			@RequestParam("pw") String pw, @RequestParam(value="name", required = false) String name, 
			HttpServletRequest request,
			HttpServletResponse response){
		
			HttpSession session=request.getSession(false);
			MemberVO m = (MemberVO) session.getAttribute("member");
			
			memberService.deleteMember(m);
			
			return "";
		
	}
	
		
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
public String login(@ModelAttribute("info") MemberVO m, HttpServletRequest request,
		HttpServletResponse response){
	/*
	 * String id=request.getParameter("id"); String pw=request.getParameter("pw");
	 */
	JSONObject json=new JSONObject();
	
	try {
		System.out.println(m);
		//MemberVO m=new MemberVO(id,pw); 
		String name=memberService.login(m);
		if(name!=null) {
			HttpSession session=request.getSession();
			session.setAttribute("member", m);
			json.put("name", name);
		}else {
			json.put("msg", "로그인실패");
		}
	}catch(Exception e) {
		json.put("msg",e.getMessage());
	}		
	return json.toJSONString();
}

		@ResponseBody
		public String memberInsert(@RequestParam("id") String id,
				@RequestParam("pw") String pw, @RequestParam(value="name", required = false) String name,
				HttpServletRequest request,
				HttpServletResponse response)throws Exception{
			/*
			 * String id=request.getParameter("id"); String pw=request.getParameter("pw");
			 * String name=request.getParameter("name");
			 */
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


	

