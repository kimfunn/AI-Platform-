package com.semi_pj;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

public class ServletInitializer extends SpringBootServletInitializer {
//ServletInitializer.java에 생성된 SErvletInitializer 클래스는 Spring BOotServletInitializer 클래스 상속 ( 이는 스프링
	//애플리케이션을 web.xml없이 톰캣에서 실행
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(semi_pj.class);
	}

}
