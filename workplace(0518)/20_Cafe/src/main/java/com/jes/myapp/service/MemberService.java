package com.jes.myapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jes.myapp.dao.MemberDAO;
import com.jes.myapp.vo.MemberVO;

@Service
public class MemberService {

	
	@Autowired
	MemberDAO memberDAO;
	
	public void memberInsert(MemberVO m) throws Exception{
		memberDAO.memberInsert(m);
	}

	public String login(MemberVO m) {
		// TODO Auto-generated method stub
		return memberDAO.login(m);
	}

	public void deleteMember(MemberVO m) {
		memberDAO.deleteMember(m);
		
	}

}
