package ex02;

public class Triangle implements shape{
	private int width=4, height=5;
	
	public void area() {
		System.out.println("삼각형의 넓이:"+(width*height/2));
	}
}
