package kr.or.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DbQueryTest {

	public static void main(String[] args) {
		//Connection connection = dataSource.getConnection();
				Connection connection = null;
				Statement stmt = null;
				ResultSet rs = null;
				try {
					connection = DriverManager.getConnection("jdbc:hsqldb:file:c:/egov/workspace/embeded/hsql_file.db");
					//직접 쿼리를 날립니다.(아래)
					stmt = connection.createStatement();
					
					/*insert query실행(아래) */
					for(int cnt=0;cnt<=0;cnt++) {
					stmt.executeQuery("INSERT INTO tbl_board VALUES(" 
					+ "(select count(*) from tbl_board)+1"
					+ ", '강제 수정된 글입니다.', '수정 테스트', 'user00', now(),now(), 0, 0)");
					}
					//select query실행(아래)
					rs = stmt.executeQuery("select * from tbl_board");
					System.out.println("번호\t\t제목\t\t내용\t\t작성자");
					while(rs.next()) {
						System.out.print(rs.getString("bno"));
						System.out.println(rs.getString("title"));
						System.out.println(rs.getString("content"));
						System.out.println(rs.getString("writer"));
						System.out.println();
					}
					
				} catch (SQLException e) {
					
						try {
							if(rs != null)rs.close();
							if(stmt != null)stmt.close();
							if(connection !=null)connection.close();
						} catch (SQLException e1) {
							
						}
					
				}
				
	}

}
