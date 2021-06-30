package web.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web.dao.UserDAO;
import web.vo.UserVO;

/**
 * Servlet implementation class MainServlet
 */
@WebServlet("/main")
public class MainServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MyService(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MyService(request, response);
	}

	protected void MyService(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String id=request.getParameter("id");
		String pw=request.getParameter("pw");
		
		//db 호출
		UserDAO dao = new UserDAO();
		UserVO vo = dao.login(id, pw);
		
		//응답
		if(vo!=null) {
			RequestDispatcher disp = request.getRequestDispatcher("login_ok.jsp");
			disp.forward(request, response); //이걸해줘야 jsp로 넘어감
		}else {		
			RequestDispatcher disp = request.getRequestDispatcher("login_fail.jsp");
			disp.forward(request, response); //이걸해줘야 jsp로 넘어감
			
		
		}
	}
}
