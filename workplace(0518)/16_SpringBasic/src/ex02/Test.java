package ex02;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.FileSystemResource;

public class Test {

		public static void main(String[] args) {
			/*
			 * shape c= new Circle(); c.area(); //override 기법
			 * 
			 * 
			 * shape r = new Rectangle(); r.area();
			 * 
			 * shape t=new Triangle(); t.area();
			 */
			BeanFactory factory = new XmlBeanFactory(new FileSystemResource("beans.xml"));
			
			/*
			 * shape s=(shape) factory.getBean("c"); s.area();
			 * 
			 * s=(shape) factory.getBean("r"); s.area();
			 * 
			 * s=(shape) factory.getBean("t"); s.area();
			 */
			
			shape s = (shape) factory.getBean(args[0]); //어떤 shape 인지 알 수 없음 (이것이 루즈 커플드) 
			// 걔네들의 인터페이스(shape)을 부르겠다.
			s.area();
		}
}
