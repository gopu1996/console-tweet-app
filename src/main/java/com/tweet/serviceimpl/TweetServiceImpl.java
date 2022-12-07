package com.tweet.serviceimpl;

import java.util.List;
import java.util.Objects;

import com.tweet.dao.TweetDao;
import com.tweet.entity.Tweets;
import com.tweet.entity.User;
import com.tweet.exception.DaoLayerException;
import com.tweet.exception.ServiceException;
import com.tweet.service.TweetService;

public class TweetServiceImpl implements TweetService{

	private static TweetDao tweetDao = new TweetDao();
	
	@Override
	public String postTweet(Tweets tweet) throws ServiceException {
		 String message = null;
			try {
				message = tweetDao.postTweet(tweet);
			} catch (DaoLayerException e) {
				throw new ServiceException("Some this went worng in service",e); 
			}
			
			return message;
	}

	@Override
	public List<Tweets> viewMyTweets(String email) throws ServiceException {
	   
		try {
			List<Tweets> details = tweetDao.viewTweetbyEmail(email);
			if(!Objects.isNull(details)) {
				return details;
			}else {
				throw new ServiceException("Email doesn't exsits");
			}
		} catch (DaoLayerException e) {
			throw new ServiceException("Some this went worng in service",e); 
		}
	}

	@Override
	public List<Tweets> viewAllTweets() throws ServiceException {
		try {
			List<Tweets> details = tweetDao.viewAllTweets();
			if(!Objects.isNull(details)) {
				return details;
			}else {
				throw new ServiceException("No Records Are There");
			}
		} catch (DaoLayerException e) {
			throw new ServiceException("Some this went worng in service",e); 
		}
	}

	@Override
	public List<User> viewAllUserDetails() throws ServiceException {
		try {
			List<User> details = tweetDao.getAllUserDetails();
			if(!Objects.isNull(details)) {
				return details;
			}else {
				throw new ServiceException("No User Details Are Found");
			}
		} catch (DaoLayerException e) {
			throw new ServiceException("Some this went worng in service",e); 
		}
	}

	

}
