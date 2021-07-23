package my.jes.web.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import my.jes.web.vo.MemberVO;

@Repository
public class MemberDAO {
	
	@Autowired
	SqlSession sqlSession;
	
	public void memberInsert(MemberVO vo) {
		System.out.println(vo);
		sqlSession.insert("mapper.member.memberInsert", vo);
		
	}

	public String login(MemberVO vo) {
		return sqlSession.selectOne("mapper.member.login",vo);
		
	}

}
