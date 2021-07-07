package web.vo;

public class MemberVO {
	private String id,pw;

	public MemberVO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public MemberVO(String id, String pw) {
		super();
	setId(id);
	setPw(pw);
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		//유형성 검사 필수
		this.id = id;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	@Override
	public String toString() {
		return "MemberVO [id=" + id + ", pw=" + pw + "]";
	}
	
}
