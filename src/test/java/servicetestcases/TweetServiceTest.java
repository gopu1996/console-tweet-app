package servicetestcases;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.util.List;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.tweet.dao.TweetDao;
import com.tweet.entity.Tweets;
import com.tweet.entity.User;
import com.tweet.exception.DaoLayerException;
import com.tweet.exception.ServiceException;
import com.tweet.service.TweetService;
import com.tweet.serviceimpl.TweetServiceImpl;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TweetServiceTest {
	
	private TweetService tweetService = null;
	private TweetDao tweetDao = null;

	
	@Before
	public void init() {
		tweetService = new TweetServiceImpl();
		tweetDao = new TweetDao();
	}
	
	 @Test 
	  public void TC1_addTweets() { 
		 try {
			    Tweets tweets = new Tweets();
			    tweets.setEmail("test12@gmail.com");
			    tweets.setSubject("My blog");
			    tweets.setDescription("This is my blog Test");
			    tweetService.postTweet(tweets); 
			    List<Tweets> tweetDetails = tweetDao.viewTweetbyEmail("test12@gmail.com");
			    assertEquals("Password must be equals", "test12@gmail.com", tweetDetails.get(0).getEmail());
	    
	   }catch (ServiceException | DaoLayerException e) { 
		  e.printStackTrace(); }
		  
	}
	 
	 @Test
	 public void TC2_viewMyTweets() {
		 try {
		  List<Tweets> tweets = tweetService.viewMyTweets("test12@gmail.com");
		  assertEquals("Subject must be equals", "My blog", tweets.get(0).getSubject());
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	 }
	 
	 @Test
	 public void TC3_viewAllTweets() {
		 try {
		  List<Tweets> tweets = tweetService.viewAllTweets();
		  assertFalse(tweets.isEmpty());
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	 }
	 
	 @Test
	 public void TC4_viewAllUserDetails() {
		 try {
		  List<User> user = tweetService.viewAllUserDetails();
		  assertFalse(user.isEmpty());
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	 }

}
