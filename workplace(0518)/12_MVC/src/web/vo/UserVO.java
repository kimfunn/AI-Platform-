package web.vo;

public class UserVO {
	
	private String id,password,name,role;
	
	

	public UserVO(String id, String password, String name, String role) {
		super();
		setId(id);
		setPassword(password);
		setName(name);
		setRole(role);
	}

	public UserVO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		if(id!=null) {
			this.id = id;
		}else {
			System.out.println("id를 입력하세요");
		}
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "UserVO [id=" + id + ", password=" + password + ", name=" + name + ", role=" + role + "]";
	}
	
	

}
