package ex01;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.FileSystemResource;

public class HelloTest {

	public static void main(String[] args) {
		
		//Helloh = new Hello(); //제어는 spring
		//spring 객체를 만들어야함 (객체를 만들어주는 공장)
		BeanFactory factory = new XmlBeanFactory(new FileSystemResource("beans.xml"));
		Hello h= (Hello) factory.getBean("h"); // DI = I.C(제억의 역전) 디펜던시 인젝션 제어는 Spring
		Hello h2= (Hello) factory.getBean("h2");
		System.out.println(h.getMsg());
		System.out.println(h2.getMsg());
		System.out.println(h==h2);
		//https://docs.spring.io/spring-framework/docs/current/javadoc-api/
	}

}
