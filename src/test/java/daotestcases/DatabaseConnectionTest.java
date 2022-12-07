package daotestcases;

import static org.junit.Assert.assertNotNull;

import java.sql.Connection;

import org.junit.Test;

import com.tweet.exception.ConnectionFailedExcetion;
import com.tweet.utility.DatabaseConnection;

public class DatabaseConnectionTest {

	private DatabaseConnection databaseConnection = new DatabaseConnection();
	
	@Test
	public void getConnection() {
		 try {
			Connection dbConnection  = databaseConnection.getConnection();
			assertNotNull("connection should be successfull." , dbConnection);
		} catch (ConnectionFailedExcetion e) {
			e.printStackTrace();
		}
		
	}
	
	
}
