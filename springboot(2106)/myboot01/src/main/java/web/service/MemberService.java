package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import web.dao.MemberDAO;
import web.vo.MemberVO;

@Service
public class MemberService {
	
	@Autowired //@repository memberDAO 와 매핑됨
	MemberDAO memberDAO;
	
	public void insertMember(MemberVO memberVO) {
		memberDAO.insertMember(memberVO); //interface인데 함수 불러올 수 있음 자동으로 불러와서 주입해줌
	}
	

}
