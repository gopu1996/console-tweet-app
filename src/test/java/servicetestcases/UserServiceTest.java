package servicetestcases;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.tweet.dao.UserDao;
import com.tweet.entity.User;
import com.tweet.exception.DaoLayerException;
import com.tweet.exception.ServiceException;
import com.tweet.service.UserService;
import com.tweet.serviceimpl.UserServiceImpl;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UserServiceTest {
	
	private UserService userService = null;
	private UserDao userDao = null;
	
	@Before
	public void init() {
		userService = new UserServiceImpl();
		userDao = new UserDao();
	}
	
	
	@Test
	public void TC1_saveUser() {
		 try {
		 User user = new User();
	     user.setEmail("test12@gmail.com"); 
	     user.setPassword("mypass");
		 user.setFirst_name("Test user");
		 user.setQuestion("Test");
		 user.setStatus(true);
		 userService.registerUser(user);
		 User details = userDao.getUserDetailsbyEmail("test12@gmail.com");
		 assertEquals("Password must be equals", "mypass", details.getPassword());
		} catch (ServiceException | DaoLayerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test()
	public void TC2_loginUser() {
		 try {
		 String message = userService.loginUser("test12@gmail.com","mypass@011");
		 assertEquals("Password must be equals", message, "Login Successfully");
		} catch (ServiceException  e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void TC3_getStatusOfUser() {
		 try {
		 boolean status = userService.getStatusOfUser("test1@gmail.com");
	     assertTrue(status);
		} catch (ServiceException  e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void TC4_forgotPassword() {
		 try {
	    String message = userService.forgotPassword("Test","test12@gmail.com","my@pass");	
	    assertEquals("Password must be equals", message, "Password change suceesfully");
		} catch (ServiceException   e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void TC5_restPasswordTest() {
		 try {
	    String message = userService.resetPassword("test12@gmail.com","my@pass","pass");	
	    userDao.setPasswordForUser("mypass@011", "test12@gmail.com");
	    assertEquals("Password must be equals", message, "Password change suceesfully");
		} catch (ServiceException | DaoLayerException  e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void TC6_updateStatusForUserTest() {
		 try {
	     boolean userStatus = userService.updateStatusForUser(false, "test12@gmail.com");		 
	     assertFalse(userStatus);
		} catch (ServiceException  e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	

}
