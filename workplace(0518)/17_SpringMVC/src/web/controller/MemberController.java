package web.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import web.service.MemberService;
import web.vo.MemberVO;

public class MemberController extends MultiActionController{

		private MemberService memberService;

		public void setMemberService(MemberService memberService) {
			this.memberService = memberService;
		}
		
		public ModelAndView listMembers(HttpServletRequest request,
				HttpServletResponse response) throws Exception{
			
			List<MemberVO> membersList=memberService.listMembers();
			
			ModelAndView mav=new ModelAndView("listMembers"); //생성하지않고 view name 바로 줄 수 있음
			// /WEB-INF/views/listMembers.jsp 로 바뀌어야함
			
			mav.addObject("membersList",membersList);
			return mav;
			
		}
}
