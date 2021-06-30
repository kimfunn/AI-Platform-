package exam1;

public class BookDTO {
	private int isbn;
	private String title;
	private String author;
	private String publisher;
	private int price;
	private String describe;
	
	
	public BookDTO(int i, String title, String author, String publisher, int j, String describe) {
		super();
		this.isbn = i;
		this.title = title;
		this.author = author;
		this.publisher = publisher;
		this.price = j;
		this.describe = describe;
	}
	
	public BookDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public int getLsbn() {
		return isbn;
	}
	public void setLsbn(int lsbn) {
		this.isbn = lsbn;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getDescribe() {
		
		return describe;
	}
	public void setDescribe(String describe) {
		this.describe = describe;
	}

	@Override
	public String toString() {
		return String.format("%-6d | %-10s	| %-10s | %-10s | %-10d | %-30s ", isbn, title, author, publisher, price, describe);
	}






	

}
