package web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/test")
public class MainController {
	
	@RequestMapping(value="/main1.do", method= {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView main1(@RequestParam String id, 
			@RequestParam(value="pw",required=false) String pw, HttpServletRequest request, HttpServletResponse response) throws Exception{
		/* 위의 @RequestParam 덕에 필요없음 근데 성능은 안좋음
		 * @RequestParam(value="pw",required=true) 비밀번호 null 안돼!
		 * String id=request.getParameter("id"); String pw=request.getParameter("pw");
		 */	
		//ModelAndView 데이터를 생성하고 보여줄 수 있는 모델을 만들 수 있는 아이
		ModelAndView mav=new ModelAndView();
		mav.addObject("msg","main1");
		mav.addObject("id",id);
		mav.addObject("pw",pw);
		mav.setViewName("main");
		return mav;
		
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
