package ex02;

public class Circle  implements shape{
	private int radius=3;
	public void area() {
	
			System.out.println("원의 넓이:"+(radius*radius*Math.PI));
		
	}
}
