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
public class LoginController {
	@Autowired
	MemberService memberService;
	@ResponseBody
	@RequestMapping("/loginById")
	public String loginById(HttpServletRequest request, HttpServletResponse response) {
		try {
			String id= request.getParameter("id");
			String pw= request.getParameter("pw");
			MemberVO memberVO=new MemberVO(id,pw, null);
			
			memberVO= memberService.loginById(memberVO);
			JSONObject json=new JSONObject();
			json.put("id",id);
			return json.toJSONString();
	}catch(DataAccessException e) {
		e.printStackTrace(); //debug 유리
		return "로그인 실패";
	}
	}
}
