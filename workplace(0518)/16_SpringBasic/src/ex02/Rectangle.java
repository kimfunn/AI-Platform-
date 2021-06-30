package ex02;

public class Rectangle implements shape {

	private int width=4, height=5;
	
	public void area() {
		System.out.println("사각형의 넓이:"+(width*height));
	}
}
