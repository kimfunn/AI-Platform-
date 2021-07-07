package web.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import web.service.MemberService;
import web.vo.MemberVO;

@Controller
@RequestMapping("/test")
public class MainController {
	
	@Autowired
	MemberService memberService;
	
	@RequestMapping(value="/main1.do", method= {RequestMethod.GET, RequestMethod.POST})
	//public ModelAndView main1(@RequestParam Map<String, String> info, HttpServletRequest request, HttpServletResponse response) throws Exception{
	//public ModelAndView main1(@ModelAttribute("info") MemberVO memberVO, HttpServletRequest request, HttpServletResponse response) throws Exception{
	public String main1(Model model, HttpServletRequest request, HttpServletResponse response) throws Exception{
		System.out.println("MainController의main() 호출됨");
		model.addAttribute("id",request.getParameter("id"));
		model.addAttribute("pw",request.getParameter("pw"));
		memberService.login(request.getParameter("id"),request.getParameter("pw"));
		/* 위의 @RequestParam 덕에 필요없음 근데 성능은 안좋음
		 * @RequestParam(value="pw",required=true) 비밀번호 null 안돼!
		 * String id=request.getParameter("id"); String pw=request.getParameter("pw");
		 */	
		//ModelAndView 데이터를 생성하고 보여줄 수 있는 모델을 만들 수 있는 아이
		/*
		 * ModelAndView mav=new ModelAndView(); mav.addObject("msg","main1");
		 * //mav.addObject("id",memberVO.getId());
		 * //mav.addObject("pw",memberVO.getPw()); mav.addObject("info",memberVO);
		 * mav.setViewName("main");
		 */
		return "main";
		
	}
	
	@RequestMapping("/main2.do")
	public ModelAndView main2(HttpServletRequest request, HttpServletResponse response) {
		
		//ModelAndView 데이터를 생성하고 보여줄 수 있는 모델을 만들 수 있는 아이
		ModelAndView mav=new ModelAndView();
		mav.addObject("msg","main2");
		mav.setViewName("main");
		return mav;
		
	}
	

}
