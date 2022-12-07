package com.tweet.service;

import com.tweet.entity.User;
import com.tweet.exception.ServiceException;

public interface UserService {

	  String registerUser(User user) throws ServiceException;

	  String loginUser(String email, String password) throws ServiceException;

	  boolean updateStatusForUser(boolean status, String email) throws ServiceException;

	  String forgotPassword(String nickName, String email, String newPassword) throws ServiceException;
	  
	 String resetPassword(String email , String oldPassword, String newPassword) throws ServiceException;
	
	 boolean getStatusOfUser(String email) throws ServiceException;
}
