package my.jes.web.vo;

public class MessageVO {
	private String msg,tempname;

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	@Override
	public String toString() {
		return "MessageVO [msg=" + msg + "]";
	}

	public String getTempname() {
		return tempname;
	}

	public void setTempname(String tempname) {
		this.tempname = tempname;
	}
	
	
}
