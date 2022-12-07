package com.tweet.serviceimpl;

import java.util.Objects;
import java.util.Scanner;

import com.tweet.dao.UserDao;
import com.tweet.entity.User;
import com.tweet.exception.DaoLayerException;
import com.tweet.exception.ServiceException;
import com.tweet.service.UserService;


public class UserServiceImpl implements UserService {

	private static UserDao userDao = new UserDao();
	Scanner sc = new Scanner(System.in);
	
	@Override
	public String registerUser(User user) throws ServiceException {
		try {
		    return userDao.registerUserInDatabase(user);
		} catch (DaoLayerException e) {
			throw new ServiceException("Some this went worng in service",e); 
		}
	}

	@Override
	public String loginUser(String email, String password) throws ServiceException {
		String message = null;
		try {
			User details = userDao.getUserDetailsbyEmail(email);
			if(!Objects.isNull(details)) {
				 if(details.getEmail().equals(email) && details.getPassword().equals(password)) {
					 message = "Login Successfully";
				 }else {
					 throw new ServiceException("Failed ! Please Check Your Login Details  " + email +"  "+ password);
				 }
			}else {
				throw new ServiceException("No Such Email Exist  "+ email); 
			}
			
		} catch (DaoLayerException e) {
			throw new ServiceException("Some this went worng in service",e); 
		}
		return message;
	}

	@Override
	public boolean updateStatusForUser(boolean status,String email) throws ServiceException {
		boolean value;
		try {
			userDao.setUserStatus(status, email);
			User details = userDao.getUserDetailsbyEmail(email);
			   if(!Objects.isNull(details)) {
					if(details.isStatus()) {
						value = details.isStatus();
					}else {
						value = details.isStatus();
					}
			   }else {
				   throw new ServiceException("No Such Data Found"); 
			   }
		} catch (DaoLayerException e) {
			throw new ServiceException("Some this went worng in service",e); 
		}
		return value;
	}

	@SuppressWarnings("resource")
	@Override
	public String forgotPassword(String nickName,String email,String newPassword) throws ServiceException {
		String message = null;
		try {
		
			User details = userDao.getUserDetailsbyEmail(email);
		if(!Objects.isNull(details)) {
			if(details.getQuestion().equals(nickName)) {
			    message = userDao.setPasswordForUser(newPassword,email);
			}else {
				throw new ServiceException("Nick Name didn't match  "+nickName); 
			}
		}else {
			throw new ServiceException("No such details found regarding this email  "+email); 
		}
		} catch (DaoLayerException e) {
			throw new ServiceException("Some this went worng in service",e); 
		}
		return message;
	
	}

	@Override
	public String resetPassword(String email , String oldPassword , String newPassword) throws ServiceException {
		String message = null;
		try {
			User details = userDao.getUserDetailsbyEmail(email);
			if(!Objects.isNull(details)) {
				if(details.getPassword().equals(oldPassword)) {
					if(!newPassword.equals(details.getPassword())) {
					    message = userDao.setPasswordForUser(newPassword,email);
								}else {
					throw new ServiceException("Old Password and new Password couldn't not be same  "+oldPassword +"  "+ newPassword); 
				}
				}else {
					throw new ServiceException("Old Password didn't match  "+oldPassword); 
				}
			}else {
				throw new ServiceException("No such details found regarding this email  "+email); 
			}
		} catch (DaoLayerException e) {
			e.printStackTrace();
		}
		return message;
	}

	@Override
	 public boolean getStatusOfUser(String email) throws ServiceException {
		 
		 try {
			User details = userDao.getUserDetailsbyEmail(email);
			if(!Objects.isNull(details)) {
				return details.isStatus();
			}else {
				throw new ServiceException("No Such Email Exist  "+ email); 
			}
		} catch (DaoLayerException e) {
			throw new ServiceException("Some went wrong in service layer "+ e);
		}
	 }
	
}
