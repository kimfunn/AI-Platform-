package ex03_member;

public class MemberService implements Service {
	// MemberDAO dao = new MemberDAO(); // 할당받으려고 new 삭제
	
	 DAO dao;
	 
	 public void setDao(DAO dao) { 
		 this.dao = dao; 
		 }
	 
	public void selectAll() {
		dao.selectAll();
		
	}

}
