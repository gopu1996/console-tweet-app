package com.tweet.exception;

public class ConnectionFailedExcetion extends Exception {

	public ConnectionFailedExcetion(String string) {
		// TODO Auto-generated constructor stub
		 System.out.println(string);
	}

	public ConnectionFailedExcetion(String string, Exception e) {
		System.out.println(string + " "+ e);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
