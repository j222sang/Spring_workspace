package www.dream.com;

import static org.junit.Assert.assertNotNull;

import java.sql.Connection;
import java.sql.DriverManager;

import org.junit.Test;

public class JDBCTest {
	private static final String URL = "jdbc:oracle:thin:@localhost:1521:orcl";
	private static final String USER_ID = "system";
	private static final String PASSWORD = "as94946007";
	static {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		} 
	}
	
	
	
	@Test
	public void test() {
		try(Connection connection = DriverManager.getConnection(URL, USER_ID, PASSWORD);){
			assertNotNull(connection);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}