package my.jes.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import my.jes.web.service.MemberService;
import my.jes.web.vo.MemberVO;

@RestController // 아웃버퍼에 담길 내용
public class MemberController {

	@Autowired
	MemberService memberService;
	
	@PostMapping("login")
	public MemberVO login(@ModelAttribute MemberVO vo) { // 모델어트리뷰트 쓰면 생성자 안만들어도 됨, 자동
		try {
			System.out.println(vo);
			String name = memberService.login(vo);
			System.out.println(name);
			vo.setName(name);
		} catch (Exception e) {
			e.printStackTrace();
			vo.setMsg("로그인 실패");
		}
		vo.setPw(null);
		return vo;
	}
	
	@PostMapping("memberInsert")
	public MemberVO memberInsert(@ModelAttribute MemberVO vo) { // 모델어트리뷰트 쓰면 생성자 안만들어도 됨, 자동
		try {
			memberService.memberInsert(vo);
		} catch (Exception e) {
			e.printStackTrace();
			vo.setMsg("회원가입 실패");
		}
		vo.setPw(null);
		return vo;
	}
	
	
}
