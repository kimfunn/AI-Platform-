package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import web.dao.MemberDAO;

@Service
public class MemberService {
	
	@Autowired
	MemberDAO dao;
	
	public void login(String id, String pw) {
		
		dao.login(id,pw);
	}
}
