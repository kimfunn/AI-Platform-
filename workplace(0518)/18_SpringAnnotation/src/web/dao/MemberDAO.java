package web.dao;

import org.springframework.stereotype.Repository;

@Repository
public class MemberDAO {

	public void login(String id, String pw) {
		System.out.println("MemberDAO의 login 호출됨");
	}
}
