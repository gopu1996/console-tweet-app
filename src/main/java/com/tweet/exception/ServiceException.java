package com.tweet.exception;

public class ServiceException extends Exception {

	public ServiceException(String string, DaoLayerException e) {
		// TODO Auto-generated constructor stub
		System.out.println(string+" "+e);
	}
	public ServiceException(String string) {
		// TODO Auto-generated constructor stub
		System.out.println(string);
	}


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
