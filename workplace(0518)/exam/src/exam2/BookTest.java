package exam2;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import exam2.BookDTO;
import oracle.jdbc.driver.OracleDriver;
import util.JDBCUtil;

public class BookTest{

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("**** JDBC Test Oracle 연동***");
		
		
		
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String  driver = "oracle.jdbc.OracleDriver";
		String user = "scott";
		String pw = "TIGER";
		
		String sql = "SELECT * FROM dept";
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		
		try {
			//1.드라이버 로딩
			DriverManager.registerDriver(new OracleDriver());
		
			//2,DB로 연결
			
			con = DriverManager.getConnection(url, user, pw);

			//3. SQL 구문을 관리해주는 Statement 체 생성
			st = con.createStatement();
			
			
			//4. sql 구문  실행
			
			rs = st.executeQuery(sql);
			System.out.println("*******************결과값 보기 **************8");
			while(rs.next()) {
				System.out.printf("%-15s| ", rs.getString("dname"));
				System.out.printf("%-15s | ", rs.getString("loc"));
				System.out.printf("%d | %n", rs.getInt("deptno"));
				
			}
			System.out.println("*****************************************");
			System.out.println(con);
		}catch(Exception e) {
			e.printStackTrace();
			
		}finally {
			try { if(con!=null) con.close();
				  if(rs!=null) rs.close();
				  if(st!=null) st.close();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		}
	}
}