package my.jes.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import my.jes.web.service.MemberService;
import my.jes.web.vo.MemberVO;

@RestController
public class MemberController {
	
	@Autowired
	MemberService memberService;
	
	@PostMapping("login")
	public MemberVO login(@ModelAttribute MemberVO vo) {
		try {
			System.out.println(vo);
			String name=memberService.login(vo);
			vo.setName(name);			
		}catch(Exception e) {
			e.printStackTrace();
			vo.setMsg("로그인 실패");
		}
		vo.setPw(null);
		return vo;
	}
	
	@PostMapping("memberInsert")
	public MemberVO memberInsert(@ModelAttribute MemberVO vo) {
		try {
			memberService.memberInsert(vo);
		}catch(Exception e) {
			e.printStackTrace();
			vo.setMsg("회원가입실패");
		}
		vo.setPw(null);
		return vo;
	}

}
