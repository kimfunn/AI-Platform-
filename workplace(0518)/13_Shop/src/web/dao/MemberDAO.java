package web.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


import javax.naming.*;
import javax.sql.DataSource;

import web.util.ShopException;
import web.vo.MemberVO;


public class MemberDAO {
	DataSource ds;
	//동시성 문제 
	//C
	public MemberDAO() {
		//1. 드라이버 등록 //한번만 해야 좋은일 (계속 반복하면 request 건수 만큼 수행됨)
		// connection full 찾기
		try {
			Context initCtx = new InitialContext();
			Context envCtx = (Context) initCtx.lookup("java:comp/env");
			ds = (DataSource) envCtx.lookup("jdbc/MyDB"); //connectionPool
			
			
		} catch (Exception e) {
			System.out.println("드라이버 등록 실패");
			}
	}


	public void insertMember(MemberVO vo) throws ShopException{
		Connection con = null;
		PreparedStatement st = null;
				
		try {
		
		
		//2. 연결 (Connection 얻기)
		 con=ds.getConnection();//대여
	
	
		//3. Statement 생성
		 st = con.prepareStatement("insert into Member(id,pw,name,address,age) values (?,?,?,?,?)");//sql문 들어가야함
		 
		//4. SQL 전송
		st.setString(1, vo.getId());
		st.setString(2, vo.getPw());
		st.setString(3, vo.getName());
		st.setString(4, vo.getAddress());
		st.setInt(5, vo.getAge());
		
		int i=st.executeUpdate();
		
		
		//5. 결과얻기
		System.out.println(i+"행이 insert 되었습니다.");
		
		//6. 종료
		
		}catch(Exception e) {
			e.printStackTrace(); //디버깅을 위한 실행시에는 빼야함 보안을 위해 
			throw new ShopException("회원가입 실패");
			
		}finally {
			//6. 종료
			try {
				
				st.close(); 
				con.close(); //반납
			}catch(Exception e) {
				
			}
		}
	}
		
		
		//R
	public String selectMember(MemberVO vo) throws ShopException {
		Connection con = null;
		PreparedStatement st = null;
		ResultSet rs=null;
		try {
		
		
		//2. 연결 (Connection 얻기)
		 con= ds.getConnection();//대여
	
	
		//3. Statement 생성
		 st = con.prepareStatement("select name from Member where id=? and pw=?");//sql문 들어가야함
		 
		//4. SQL 전송
		st.setString(1, vo.getId());
		st.setString(2, vo.getPw());
	
		rs=st.executeQuery();
		
		
		//5. 결과얻기

		if(rs.next()) {
			return rs.getString("name");
			
		}
		return null;
		//6. 종료
		
		}catch(Exception e) {
			e.printStackTrace(); //디버깅을 위한 실행시에는 빼야함 보안을 위해 
			throw new ShopException("로그인실패");
			
		}finally {
			//6. 종료
			try {
				
				st.close(); 
				con.close(); //반납
			}catch(Exception e) {
				
			}
		}
		
	}
	//U
	public void updateMember() {
		
	}
	//D
	public void deleteMember(String id) throws ShopException {
	
			Connection con = null;
			PreparedStatement st = null;
					
			try {
			
			
			//2. 연결 (Connection 얻기)
			 con=ds.getConnection();//대여
		
		
			//3. Statement 생성
			 st = con.prepareStatement("delete from Member where id=?");//sql문 들어가야함
			 
			//4. SQL 전송
			st.setString(1, id);
		
			int i=st.executeUpdate();
			
			
			//5. 결과얻기
			System.out.println(i+"행이 delete 되었습니다.");
			
			//6. 종료
			
			}catch(Exception e) {
				e.printStackTrace(); //디버깅을 위한 실행시에는 빼야함 보안을 위해 
				throw new ShopException("회원탈퇴 실패");
				
			}finally {
				//6. 종료
				try {
					
					st.close(); 
					con.close(); //반납
				}catch(Exception e) {
					
				}
			}
		
	}
}
