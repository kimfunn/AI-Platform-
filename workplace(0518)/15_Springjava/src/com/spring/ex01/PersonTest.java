package com.spring.ex01;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.FileSystemResource;

public class PersonTest {

	public static void main(String[] args) {
		BeanFactory factory = new XmlBeanFactory(
				new FileSystemResource("person.xml")
				);
				
		PersonService person=(PersonService) factory.getBean("personService"); //getBean 이 xml에서 id를 불러오는 것
		person.sayHello();
	}

}
