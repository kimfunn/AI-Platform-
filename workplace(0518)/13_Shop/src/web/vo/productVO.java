package web.vo;

public class productVO {

	private String name, discription;
	private int price;
	
	
	public productVO() {
		super();
		
	}
	public productVO(String name) {
		super();
		this.name = name;
	}
	public productVO(String name, String discription, int price) {
		super();
		this.name = name;
		this.discription = discription;
		this.price = price;
	}
	//유효성 검사 필수
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDiscription() {
		return discription;
	}
	public void setDiscription(String discription) {
		this.discription = discription;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	@Override
	public String toString() {
		return "productVO [name=" + name + ", discription=" + discription + ", price=" + price + "]";
	}
	
}
