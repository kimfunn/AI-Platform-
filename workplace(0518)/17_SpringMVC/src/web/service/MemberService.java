package web.service;

import java.util.List;

import web.dao.MemberDAO;
import web.vo.MemberVO;

public class MemberService {

	private MemberDAO dao;

	public void setMemberDAO(MemberDAO dao) {
		this.dao = dao;
	}


	public List<MemberVO> listMembers() {
		
		return dao.listMembers();
	}

}
