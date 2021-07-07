# 1. 가장 수동적으로 스프링(spring)만들기

build 설정해서 Libraries 에서 Classpath 설정하기



web.xml을 통해 servlet 설정

```xml
<?xml version="1.0" encoding="UTF-8"?>
<web-app xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns="http://xmlns.jcp.org/xml/ns/javaee" xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee http://xmlns.jcp.org/xml/ns/javaee/web-app_4_0.xsd" id="WebApp_ID" version="4.0">
  <!--  web app이 원래 디폴트 web.xml 경로 설정 -->
  <servlet>
  	<servlet-name>action</servlet-name>
  	<servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
  	
  
  </servlet>
  
  <servlet-mapping>
  
  	<servlet-name>action</servlet-name>
  	<url-pattern>*.do</url-pattern> <!--  .do 라고 들어오면 jes 라고 불를 것이고 servlet jes야 -->
  </servlet-mapping>
</web-app>
```

MainController로 수행

```java
package web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/test")
public class MainController {
	
	@RequestMapping("/main1.do")
	public ModelAndView main1(HttpServletRequest request, HttpServletResponse response) {
		
		//ModelAndView 데이터를 생성하고 보여줄 수 있는 모델을 만들 수 있는 아이
		ModelAndView mav=new ModelAndView();
		mav.addObject("msg","main1");
		mav.setViewName("main");
		return mav;
		
	}
	
	@RequestMapping("/main2.do")
	public ModelAndView main2(HttpServletRequest request, HttpServletResponse response) {
		
		//ModelAndView 데이터를 생성하고 보여줄 수 있는 모델을 만들 수 있는 아이
		ModelAndView mav=new ModelAndView();
		mav.addObject("msg","main2");
		mav.setViewName("main");
		return mav;
		
	}
	

}

```



main.jsp

```jsp
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>${msg} 페이지 입니다!!</h1>
</body>
</html>
```



이제 html이 열어지게 해보자!

html을 생성

**index.html**

```html
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="test/main1.do" method="post">
	<!--  submit type 이여야만 데이터를 전송할 수 있음 -->
		<input type="submit" value="post 요청 보내기">
	</form>
	
</body>
</html>
```

