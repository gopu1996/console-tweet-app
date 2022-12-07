package daotestcases;

import static org.junit.Assert.assertEquals;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.tweet.dao.UserDao;
import com.tweet.entity.User;
import com.tweet.exception.ConnectionFailedExcetion;
import com.tweet.exception.DaoLayerException;
import com.tweet.utility.DatabaseConnection;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UserDaoTest {

	private static UserDao dao = new UserDao();
	private static DatabaseConnection databaseConnection = new DatabaseConnection();

	@Before
	public void init() {
		Connection conn = null;
		try {
			conn = databaseConnection.getConnection();
			conn.setAutoCommit(false);
		} catch (ConnectionFailedExcetion e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@After
	public void teardown() {
		Connection conn;
		try {
			conn = databaseConnection.getConnection();
			conn.setAutoCommit(true);
		} catch (ConnectionFailedExcetion e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	
	 @Test public void addUserTest() { User user = new User();
	 user.setEmail("test@gmail.com"); user.setPassword("mypass");
	 user.setFirst_name("Test user"); user.setQuestion("Test");
	 user.setStatus(true); try { dao.registerUserInDatabase(user); User userFromDb
	  = dao.getUserDetailsbyEmail("test@gmail.com");
	  assertEquals("Password must be equals", "mypass", userFromDb.getPassword());
	  } catch (DaoLayerException e) { // TODO Auto-generated catch block
	  e.printStackTrace(); }
	  
	  }
	 
	@Test
	public void getUserDetailsbyEmailTest() {
		User user;
		try {
			user = dao.getUserDetailsbyEmail("test@gmail.com");
			assertEquals(user.getFirst_name(), "Test user");
		} catch (DaoLayerException e) {
			e.printStackTrace();
		}

	}
	
	@Test
	public void TC1_setUserStatus() {
		User user;
		try {
			dao.setUserStatus(true,"test@gmail.com");
			user = dao.getUserDetailsbyEmail("test@gmail.com");
			assertEquals(user.isStatus(), true);
		} catch (DaoLayerException e) {
			e.printStackTrace();
		}

	}
	
	@Test
	public void TC2_setPasswordForUser() {
		User user;
		try {
			dao.setPasswordForUser("newPassword","test@gmail.com");
			user = dao.getUserDetailsbyEmail("test@gmail.com");
			assertEquals(user.getPassword(), "newPassword");
		} catch (DaoLayerException e) {
			e.printStackTrace();
		}

	}

}
