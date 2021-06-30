package web.controller;


import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class HiServlet
 */
public class HiServlet3 extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	response.setContentType("text/html;charset=utf-8");
	PrintWriter out = response.getWriter();
	
	out.append("<a href='index.html'><img src='img/home.png' width='100px'></a><hr>");
	
	out.append("<img src='img/fox.jpg' width='300px'><br>");
	out.append("<h2>귀여운 여우</h2>");
	out.append("<a href='hello.jsp'> 이쁜 다람쥐가 보고싶으면?</a>");
	}

}
