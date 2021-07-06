package com.jes.web1.web.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.jes.web1.web.dao.MemberDAO;
import com.jes.web1.web.vo.MemberVO;



@Service
public class MemberService {
		@Autowired
	MemberDAO memberDAO;
	
	public void insertMember(MemberVO memberVO) throws DataAccessException {
		memberDAO.insertMember(memberVO);
	}
	public MemberVO loginById(MemberVO memberVO) throws DataAccessException{
		return memberDAO.loginById(memberVO);
	}

	public boolean loginCheck(MemberVO vo, HttpSession session) {
		boolean result =memberDAO.loginCheck(vo);
		if(result==true) {
			session.setAttribute("id", vo.getId());
		}
		return result;
	}
	public void logout(HttpSession session) {
		memberDAO.logout(session);
	}
	
	public void deleteMember(String id) throws DataAccessException{
		memberDAO.deleteMember(id);
	}

	public void updateMember(MemberVO memberVO) throws DataAccessException{
		memberDAO.updateMember(memberVO);
	}
	
	public List<MemberVO> selectAllMemberList() throws DataAccessException{
		return memberDAO.selectAllMemberList();
	}
}
