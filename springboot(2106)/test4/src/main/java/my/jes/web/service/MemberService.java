package my.jes.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import my.jes.web.dao.MemberDAO;
import my.jes.web.vo.MemberVO;

@Service
public class MemberService {
	
	@Autowired
	MemberDAO memberDAO;

	public void memberInsert(MemberVO vo) {
		memberDAO.memberInsert(vo);
		
	}

	public String login(MemberVO vo) {
		return memberDAO.login(vo);
		
	}
	
}
