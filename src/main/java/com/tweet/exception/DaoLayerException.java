package com.tweet.exception;

public class DaoLayerException extends Exception {

	public DaoLayerException(String string, Exception e) {
		// TODO Auto-generated constructor stub
		System.out.println(string+" "+e);
	}
	
	public DaoLayerException(String string) {
		 System.out.println(string);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
