package exam2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import oracle.jdbc.driver.OracleDriver;

public class StudentTest {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("**** JDBC Test Oracle ����***");
		
		
		
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String  driver = "oracle.jdbc.OracleDriver";
		String user = "scott";
		String pw = "TIGER";
		
		String sql = "SELECT * FROM book";
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		
		try {
			//1.����̹� �ε�
			DriverManager.registerDriver(new OracleDriver());
		
			//2,DB�� ����
			
			con = DriverManager.getConnection(url, user, pw);

			//3. SQL ������ �������ִ� Statement ü ����
			st = con.createStatement();
			
			
			//4. sql ����  ����
			
			rs = st.executeQuery(sql);
			System.out.println("*******************����� ���� **************8");
			while(rs.next()) {
				System.out.printf("|%-15d| ", rs.getInt("isbn"));
				System.out.printf("%-15s	| ", rs.getString("title"));
				System.out.printf("%-15s| ", rs.getString("author"));
				System.out.printf("%-15s | ", rs.getString("publisher"));
				System.out.printf("%-15s | ", rs.getInt("price"));
				System.out.printf("%-15s |  %n", rs.getString("describe"));
				
			}
			System.out.println("*****************************************");
			
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


