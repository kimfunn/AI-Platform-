package web.controll;

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
import web.util.Calculator;
import web.util.ShopException;
import web.vo.ArticleVO;
import web.vo.MemberVO;
import web.vo.productVO;
import web.util.Calculator;
/**
 * Servlet implementation class MainServlet
 */
@WebServlet("/main")
public class MainServlet extends HttpServlet {
		int count=1;
		MemberService m_service;
		BoardService b_service;
	@Override
		public void init() throws ServletException {
			m_service = new MemberService();
			b_service = new BoardService();
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			myService(request,response);
		} catch (ServletException | IOException | ShopException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			myService(request,response);
		} catch (ServletException | IOException | ShopException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	protected void myService(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ShopException {
		JSONObject json = new JSONObject();
		request.setCharacterEncoding("utf-8");
		
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out=response.getWriter();
		

		String sign = request.getParameter("sign");
		
		if("memberInsert".equals(sign)) {//회원가입처리 
			String name = request.getParameter("name");
			String id = request.getParameter("id");
			String pw = request.getParameter("pw");
			
			MemberVO vo = new MemberVO(id,pw,name); // 얘는 한번만 하면 안됨 각각 다른 아이디 값들을 받아야하니까
			
			System.out.println(vo); //운영할떄 지워야함 1줄당 성능이 3배 나빠짐
			
			//db
			
			try {
				m_service.insertMember(vo);
				out.append(name+"님 가입되셨습니다.");
			} catch (ShopException e) {
				out.append(e.getMessage());
			}
		}else if(sign.equals("login")) {//login
			String id = request.getParameter("id");
			String pw = request.getParameter("pw");
			
			MemberVO vo = new MemberVO(id,pw); // 얘는 한번만 하면 안됨 각각 다른 아이디 값들을 받아야하니까
			
			System.out.println(vo); //운영할떄 지워야함 1줄당 성능이 3배 나빠짐
			
			//db
			
			try {
				String name = m_service.selectMember(vo);
				if(name!=null) {//ok
					HttpSession session=request.getSession(); //request야 너 세션받고 왔니/?
					session.setAttribute("id",id);
					//말하자면, response.setHeader("JESSIONID", session.getID()); 를 자동으로 해줌
					//System.out.println(session.getId());
					
					json.put("id", id); //json으로 id를ㄹ 만들어서
//					System.out.println(json.toJSONString());
//					System.out.println(json.toString());
					out.append(json.toJSONString()); //{"name":"전은수"} 보내려함 굳이 json 포맷으로 바꾸는 이유?
				}else {//fail
					json.put("msg", "login 실패");
					out.append(json.toJSONString());
				}
				
			} catch (ShopException e) {
				out.append(json.toJSONString());
			}
			
		}else if(sign.equals("subject")){//체크박스
			String [] subject_value=request.getParameterValues("subject_value[]");
			
			for(String value : subject_value) { //꺼낸 데이터의 타입과 값 : 꺼내려는 배열 이름
				System.out.println("선택한 과목:"+value);
			}
			
		}else if(sign.equals("calc")){//환율계산
			String won = request.getParameter("won");
			String operator = request.getParameter("operator");
			
			String result = Calculator.calculate(Float.parseFloat(won),operator);
			
			out.append(result);
			
		}else if(sign.equals("memberDelete")) {
			HttpSession session=request.getSession();
			
			String id=(String) session.getAttribute("id");
			System.out.println(session.getId()+":"+id);
			
			try{
				m_service.deleteMember(id);
				out.append("회원 탈퇴되셨습니다.");
				}catch(Exception e) {
					out.append(e.getMessage());
					}
		}else if(sign.equals("logout")) {//logout==> 세션무효화
				HttpSession session=request.getSession();
				session.invalidate();
				out.append("로그아웃 되셨습니다");
		}else if(sign.equals("loginForm")) {//loginForm	
			String id = request.getParameter("id");
			String pw = request.getParameter("pw");
			
			MemberVO vo = new MemberVO(id,pw); // 얘는 한번만 하면 안됨 각각 다른 아이디 값들을 받아야하니까	
			//db
			
			try {
				String name = m_service.selectMember(vo);
				if(name!=null) {//ok
					HttpSession session=request.getSession(); //request야 너 세션받고 왔니/?
					session.setAttribute("id",id);
					RequestDispatcher disp=request.getRequestDispatcher("login_ok.jsp"); 
					request.setAttribute("name", name);
					disp.forward(request,response);
					
				}else {//fail
					out.append("login 실패");
					RequestDispatcher disp=request.getRequestDispatcher("login_fail.jsp"); 
					disp.forward(request,response);
					
				}
				
			} catch (ShopException e) {
				out.append(e.getMessage());
				RequestDispatcher disp=request.getRequestDispatcher("login_fail.jsp"); 
				disp.forward(request,response);
			}

		

		}else if("basketInsert".equals(sign)) {//장바구니 넣기
			String product_value=request.getParameter("product_value");
			productVO vo=new productVO(product_value);
			System.out.println(vo);
			
			HttpSession session = request.getSession(false);
			if(session ==null) {//로그인 필요
				out.append("먼저 로그인 하세요");
				}else{
					ArrayList<productVO> basket = (ArrayList) session.getAttribute("basket"); //getAttribute 가 리턴타입 오브젝트
					if(basket==null) {
						// 최초 장바구니
						basket=new ArrayList();
						session.setAttribute("basket", basket);
					}
					basket.add(vo);
					System.out.println(basket);
					out.append(product_value+"가 장바구니에 담겼습니다");
				}
			
		}else if("basketView".equals(sign)) {
			HttpSession session = request.getSession(false);
			if(session==null) {//로그인 필요
				out.append("<ol>");
				
			}else {
				ArrayList<productVO> basket = (ArrayList<productVO>) session.getAttribute("basket");
				for(productVO vo:basket) {
					out.append("<li>"+vo.getName()+"</li>");
				}
				
				out.append("</ol>");
			}
		}else if("cookie_basketInsert".equals(sign)) {
			String product_value=request.getParameter("product_value");
			//쿠키는 객체가 안되고 텍스트만 됨 (헤더에 텍스트로 들어가기 대문에)
			Cookie c =new Cookie("product"+count++,product_value);
			//response.addCookie(c);
			out.append(product_value+"가 장바구니에 담겼습니다.");
			
		}else if("cookie_basketView".equals(sign)) {
			Cookie [] all=request.getCookies(); //cookies 쿠키를 하나만 가져갈 순 없다 (담겨있는 쿠키 다 움직임)
			out.append("<ul>");
			for(Cookie c : all) {
				out.append("<li>"+c.getName()+":"+c.getValue()+"</li>");
			}
			out.append("</ul>");
		
		}else if("listArticles.do".equals(sign)) {
			try {
			 ArrayList<ArticleVO> articleList= b_service.listArticles();
			 //out.append는 (text/html/json/xml..) 이런 텍스트를 보낼 수 있음 따라서 그냥 Arraylist를 보낼 수 없음
			 JSONArray jsonArray = new JSONArray();
			 for(ArticleVO vo : articleList) {
				 JSONObject o = new JSONObject();
				 o.put("level", vo.getLevel());
				 o.put("articleNO", vo.getArticleNO()); //o.put(이름, 값)
				 o.put("parentNO", vo.getParentNO());
				 o.put("title", vo.getTitle());
				 o.put("content", vo.getContent());
				 o.put("id", vo.getId());
				 o.put("writeDate", vo.getWriteDate().toString()); //json 에서 인식을 못해서
				 jsonArray.add(o);
			 }
			 out.append(jsonArray.toJSONString()); //json으로 인식을 못해서
			}catch(ShopException e) {
				
			}
		}else if("addArticle.do".equals(sign)) {
			
			//System.out.println("here"); //도착했는지 확인
			HttpSession session =request.getSession(false); // request야 너 session 갖고왔니? 아니라고 하면 따로 할당 X
			if(session ==null) { //로그인 필요
				json.put("msg", "먼저 로그인 하세요"); //html 한페이지 분량을 다 줘야함
				out.append("<body><script>alert('먼저 로그인 하세요');location.replace('html/login.html')</script></body>");

			}else {
				String title = request.getParameter("title");
				String content = request.getParameter("content");
				String imageFileName = request.getParameter("imageFileName"); //파라미터 변수를 받고
				String id = (String) session.getAttribute("id"); //아이디는 세션에 있는 것으로 받아서
				ArticleVO vo = new ArticleVO(1,0, 0, title, content, imageFileName, id ,null);
				System.out.print(vo);
				try {
				b_service.addArticle(vo);
				//절대경로
				out.append("<body><script>alert('글이 등록되었습니다.');location.replace('html/boardList.html');</script></body>");
				//out.append("<body><script>alert('글이 등록되었습니다.')</script><a href='html/boardList.html'>글목록</a></body>");

			}catch(ShopException e) {
				out.append("<body><script>alert('"+e.getMessage()+"')</script></body>");

			}
			}
		}else if("viewArticle.do".equals(sign)) {
				//파라미터로 얻어지면 String으로 얻어짐
				int articleNO=Integer.parseInt(request.getParameter("articleNo"));
				try{
					ArticleVO vo = b_service.viewArticle(articleNO);
					if(vo!=null) {
						RequestDispatcher disp=request.getRequestDispatcher("jsp/viewArticle.jsp");
						request.setAttribute("vo", vo);
						disp.forward(request, response);//articlevo를 줄테니까 가져가라
					}else {
						out.append("<body><script>alert(\"해당글이 없습니다.\")</script></body>");	
				}
				}catch(ShopException e) {
					out.append("<body><script>alert(\""+e.getMessage()+"\")</script></body>");
				}
		}
			
			
	}//end service
}//end class



