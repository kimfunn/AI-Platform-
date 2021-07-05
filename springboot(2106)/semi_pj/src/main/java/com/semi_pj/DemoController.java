package com.semi_pj;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class DemoController {
	
	
	@RequestMapping("/")
	public String home() {
		System.out.println("home()호출됨");
		return "index3.html";
	}
	
	@RequestMapping("/hello.do")
	public String hello() {
		System.out.println("hello()호출됨");
		return "html/hello.html";
	}
}
