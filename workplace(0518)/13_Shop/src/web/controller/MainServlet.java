package web.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import web.service.BoardService;
import web.service.MemberService;
import web.util.ShopException;
import web.vo.ArticleVO;
import web.vo.MemberVO;
import web.vo.ProductVO;
import web.util.Calculator;

@WebServlet("/main")
public class MainServlet extends HttpServlet {
	int count=1;
	MemberService m_service;
	BoardService b_service;
	
	@Override
	public void init() throws ServletException {
		 m_service=new MemberService();
		 b_service=new BoardService();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		myService(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		myService(request, response);
	}
	
	protected void myService(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		JSONObject json=new JSONObject();
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out=response.getWriter();
		
		
		String sign=request.getParameter("sign");
		if("memberInsert".equals(sign)) {//회원가입
			String name=request.getParameter("name");
			String id=request.getParameter("id");
			String pw=request.getParameter("pw");
			MemberVO vo=new MemberVO(id,pw,name);
			System.out.println(vo);
			
			try {
				m_service.insertMember(vo);
				out.append(name+"님 가입되셨습니다.");
			} catch (ShopException e) {
				out.append(e.getMessage());
			}	
			
		}else if(sign.equals("login")) {//login			
			String id=request.getParameter("id");
			String pw=request.getParameter("pw");
			MemberVO vo=new MemberVO(id,pw);
			System.out.println(vo);
			
			try {
				String name=m_service.selectMember(vo);
				if(name!=null) {//ok
					HttpSession session=request.getSession();
					session.setAttribute("id", id);				
					json.put("id", id);					
					out.append(json.toJSONString());//{"id":"a"}
				}else {//fail
					json.put("msg", "login 실패");
					out.append(json.toJSONString());
				}
				
			} catch (ShopException e) {
				json.put("msg", "login 실패");
				out.append(json.toJSONString());
			}	
			
		}else if(sign.equals("subject")){//체크박스 테스트
			String [] subject_value=request.getParameterValues(
					"subject_value[]");
			
			for(String value:subject_value) {
				System.out.println("선택한 과목:"+value);
			}
			
		}else if(sign.equals("calc")){//환율계산
			String won=request.getParameter("won");
			String operator=request.getParameter("operator");
			
			String result=Calculator.calculate(Float.parseFloat(won),operator);
			out.append(result);
			
		}else if(sign.equals("memberDelete")) {//회원 탈퇴
			HttpSession session=request.getSession();			
			String id=(String)session.getAttribute("id");
			System.out.println(session.getId()+":"+id);
			try {
				m_service.deleteMember(id);
				out.append("회원 탈퇴 되셨습니다.");
			} catch (ShopException e) {
				out.append(e.getMessage());
			}
		}else if(sign.equals("logout")) {//logout==>세션 무효화
			HttpSession session=request.getSession();
			session.invalidate();
			System.out.println("logout ok");
			out.append("logout ok");
		}else if(sign.equals("loginForm")) {//loginForm			
			String id=request.getParameter("id");
			String pw=request.getParameter("pw");
			MemberVO vo=new MemberVO(id,pw);
			System.out.println(vo);
			
			try {
				String name=m_service.selectMember(vo);
				if(name!=null) {//ok
					HttpSession session=request.getSession();
					session.setAttribute("id", id);
					
					RequestDispatcher disp=request.getRequestDispatcher("login_ok.jsp");
					request.setAttribute("name", name);
					disp.forward(request, response);
				}else {//fail
					RequestDispatcher disp=request.getRequestDispatcher("login_fail.jsp");
					disp.forward(request, response);
				}
				
			} catch (ShopException e) {
				RequestDispatcher disp=request.getRequestDispatcher("login_fail.jsp");
				disp.forward(request, response);
			}	
			
		}else if("basketInsert".equals(sign)) {//장바구니 넣기
			String product_value=request.getParameter("product_value");
			ProductVO vo=new ProductVO(product_value);
			System.out.println(vo);
			
			HttpSession session=request.getSession(false);
			if(session==null) {//로그인 필요
				out.append("먼저 로그인하세요");
			}else {
				ArrayList<ProductVO> basket= (ArrayList<ProductVO>) session.getAttribute("basket");
				if(basket==null) {//최초 장바구니
					basket=new ArrayList();
					session.setAttribute("basket", basket);
				}
				basket.add(vo);
				
				System.out.println(basket);
				out.append(product_value+"가 장바구니에 담겼습니다");
			}
					
		}else if("basketView".equals(sign)) {
			HttpSession session=request.getSession(false);
			if(session==null) {//로그인 필요
				out.append("먼저 로그인하세요");				
			}else {
				ArrayList<ProductVO> basket=(ArrayList<ProductVO>) session.getAttribute("basket");
				out.append("<ol>");
				for(ProductVO vo:basket) {
					out.append("<li>"+vo.getName()+"</li>");
				}
				out.append("</ol>");
			}
		}else if("cookie_basketInsert".equals(sign)) {
			String product_value=request.getParameter("product_value");
			Cookie c=new Cookie("product"+count++,product_value);
			response.addCookie(c);
			out.append(product_value+"가 장바구니에 담겼습니다");
		}else if("cookie_basketView".equals(sign)) {
			Cookie []all=request.getCookies();
			out.append("<ul>");
			for(Cookie c : all) {
				out.append("<li>"+c.getName()+":"+c.getValue()+"</li>");
			}
			out.append("</ul>");
		}else if("listArticles.do".equals(sign)) {//글목록 보기 처리
			
			 try {
				ArrayList<ArticleVO> articleList=b_service.listArticles();
				JSONArray jsonArray=new JSONArray();
				for(ArticleVO vo:articleList) {					
					JSONObject o=new JSONObject();
					o.put("level", vo.getLevel());
					o.put("articleNO", vo.getArticleNO());
					o.put("parentNO", vo.getParentNO());
					o.put("title", vo.getTitle());
					o.put("content", vo.getContent());
					o.put("id", vo.getId());
					o.put("writeDate", vo.getWriteDate().toString());
					jsonArray.add(o);
				}
				
				out.append(jsonArray.toJSONString());
			} catch (ShopException e) {
				
			}
		}else if("addArticle.do".equals(sign)) {
			HttpSession session=request.getSession(false);
			if(session==null) {				
				out.append("<body><script>alert('먼저 로그인 하세요');location.replace('html/login.html');</script></body>");
			}else {
				String title=request.getParameter("title");
				String content=request.getParameter("content");
				String imageFileName=request.getParameter("imageFileName");
				String id=(String) session.getAttribute("id");
				ArticleVO vo=new ArticleVO(1, 0, 0, title, content, imageFileName, id, null);
				System.out.println(vo);
				try {
					
					b_service.addArticle(vo);
					out.append("<body><script>alert('글이 등록되었습니다');location.replace('html/boardList.html');</script></body>");
				} catch (ShopException e) {
					out.append("<body><script>alert('"+e.getMessage()+"')</script></body>");
				}
			}
		}else if("viewArticle.do".equals(sign)) {
			int articleNO=Integer.parseInt(request.getParameter("articleNo"));
			try {
				ArticleVO vo=b_service.viewArticle(articleNO);
				if(vo != null) {
					RequestDispatcher disp=request.getRequestDispatcher("jsp/viewArticle.jsp");
					request.setAttribute("vo", vo);
					disp.forward(request, response);
				}else {
					out.append("<body><script>alert(\"해당글이 없습니다\")</script></body>");
				}
			} catch (ShopException e) {
				out.append("<body><script>alert(\""+e.getMessage()+"\")</script></body>");
			}
		}else if("replyInsert".equals(sign)) {
			String id=request.getParameter("id");
			
			HttpSession session= request.getSession(false);
			if(session==null) {
				out.append("침해");
			}else{
				if(session.getAttribute("id").equals(id)) {
					String title=request.getParameter("title");
					String content=request.getParameter("content");
					int parentNO=Integer.parseInt(request.getParameter("parentNO"));
					ArticleVO vo=new ArticleVO(0, 0, parentNO,title, content,null, id,null);
					System.out.println(vo); //데이터 제대로 넘어온건지 확인
					try {
						b_service.addReply(vo);
						out.append("댓글이 등록되었습니다");
					} catch (ShopException e) {
						out.append("e.getMessage");
					}
					
				}else {
					out.append("침해");
				}
			}
			
		}
		
	}//end service

}//end class
















