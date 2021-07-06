package com.jes.web1.web.vo;

public class MemberVO {
	private String id, pw,username;

	
	
	public MemberVO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public MemberVO( String id, String pw, String username) {
		super();
		this.id = id;
		this.pw = pw;
		this.username = username;
		
	}

	public String getPw() {
		return pw;
	}
	public void setPassword(String pw) {
		this.pw= pw;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "MemberVO [password=" + pw + ", username=" + username + ", id=" + id + "]";
	}


	
}
