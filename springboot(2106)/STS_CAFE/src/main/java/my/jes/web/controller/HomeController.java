package my.jes.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import my.jes.web.service.MemberService;
import my.jes.web.vo.MemberVO;


@RestController
public class HomeController {
	
	@Autowired MemberService memberService;
	
	@RequestMapping(value = "memberDelete.jes", 
			method= {RequestMethod.POST},
			produces = "application/text; charset=utf8")			
	//@ResponseBody
	public String memberDelete(HttpServletRequest request,
			HttpServletResponse response){
		
			HttpSession session=request.getSession(false);
			MemberVO m=(MemberVO) session.getAttribute("member");
			
			memberService.deleteMember(m);
			session.invalidate();
			return "";		
	}

	
	@RequestMapping(value = "logout.jes", 
			method= {RequestMethod.POST},
			produces = "application/text; charset=utf8")			
	//@ResponseBody
	public String logout(HttpServletRequest request,
			HttpServletResponse response){
		
			HttpSession session=request.getSession(false);
			session.invalidate();
			return "";		
	}

	
	@RequestMapping(value = "login.jes"
			)			
	//@ResponseBody
	public MemberVO login(@ModelAttribute("info") MemberVO m, HttpServletRequest request,
			HttpServletResponse response){
		
	//	JSONObject json=new JSONObject();
	
		try {
			
			System.out.println(m);
			
			String name=memberService.login(m);
			if(name!=null) {//ok
				HttpSession session=request.getSession();
				session.setAttribute("member", m);
				m.setName(name);
	//			json.put("name", name);
			}else {
		//		json.put("msg", "로그인 실패");
				m=new MemberVO();
			}
		}catch(Exception e) {
		//	json.put("msg", e.getMessage());
			m=new MemberVO();
		}		
		
		return m;

	}

	
	@RequestMapping(value = "memberInsert.jes", 
			method= {RequestMethod.POST},
			produces = "application/text; charset=utf8")			
	//@ResponseBody
	public String memberInsert(@RequestParam("id") String id,
			@RequestParam("pw") String pw, @RequestParam(value="name", required = false) String name,
HttpServletRequest request,
			HttpServletResponse response)throws Exception{
		
		MemberVO m=new MemberVO(id, pw, name);		
		System.out.println(m);
		
		try {
			memberService.memberInsert(m);
			return name+"님 회원가입 되셨습니다.";
		}catch(Exception e) {
			return e.getMessage();
		}
		
		
	}		
	
}
