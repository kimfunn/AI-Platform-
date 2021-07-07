package web.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web.service.MemberService;

/**
 * Servlet implementation class MainServlet
 */
@WebServlet("/main")
public class MainServlet extends HttpServlet {
	MemberService memberService =new MemberService();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	myService(request,response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	myService(request,response);
	
	}
	
	protected void myService(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	String id=request.getParameter("id");
	String pw=request.getParameter("pw");
	
	//MemberService memberService=new MemberServiece(); //나쁜코드 request 건당 생성되기 때문에 
	memberService.login(id,pw);
	RequestDispatcher disp= request.getRequestDispatcher("main.jsp");
	request.setAttribute("id",id);
	request.setAttribute("pw",pw);
	disp.forward(request, response);
	
	}


}
