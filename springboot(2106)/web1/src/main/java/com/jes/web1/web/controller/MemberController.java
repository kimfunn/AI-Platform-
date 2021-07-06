package com.jes.web1.web.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jes.web1.web.service.MemberService;
import com.jes.web1.web.vo.MemberVO;

@Controller
public class MemberController {
	
	@Autowired
	MemberService memberService;
	
	@ResponseBody
	@RequestMapping("/memberInsert")
	public String memberInsert(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("memberInsert호출됨");
		String id=request.getParameter("id");
		String password=request.getParameter("password");
		String username=request.getParameter("username");
		
		// 서블릿 / jse shop Exception 처리
		MemberVO vo = new MemberVO(id, password, username);
		System.out.println(vo);
		
		try {
		memberService.insertMember(vo);
		return"회원가입 ok";
		}catch(DataAccessException e) {
			return "회원 가입에 실패하셨습니다";
		}
		
	}
	

	@ResponseBody
	@RequestMapping("/deleteMember")
	public String deleteMember(HttpServletRequest request, HttpServletResponse response) {
		try {
			String id= request.getParameter("id");
			memberService.deleteMember(id);
			return "회원 삭제 완료";
	}catch(DataAccessException e) {
		e.printStackTrace(); //debug 유리
		return "회원 삭제 실패";
	}
	}

	@ResponseBody
	@RequestMapping("/updateMember")
	public String updateMember(HttpServletRequest request, HttpServletResponse response) {
		try {
			String id=request.getParameter("id");
			String password= request.getParameter("password");
			String username= request.getParameter("username");
		
			
			MemberVO memberVO=new MemberVO(id,password,username);
			
			memberVO= memberService.loginById(memberVO);
			memberService.updateMember(memberVO);
			return "회원 정보 수정 완료";
	}catch(DataAccessException e) {
		e.printStackTrace(); //debug 유리
		return "회원 정보 수정 실패";
	}
	}
	//@ResponseBody 부분이미지만 주는거라서 // 지우면 전체를 주는것
	@RequestMapping("/selectAllMemberList")
	public String selectAllMemberList(HttpServletRequest request, HttpServletResponse response) {
		try {
			
		List<MemberVO> list=memberService.selectAllMemberList();
		return "ok.jsp";
	}catch(DataAccessException e) {
		e.printStackTrace(); //debug 유리
		return "fail.jsp";
	}
	}
} //end class
