package com.tweet.entity;

public class User {
	
	private String firstName , email , password , question;
	private boolean status;
	
	
	
	public User(String firstName, String email, String password, String question, boolean status) {
		super();
		this.firstName = firstName;
		this.email = email;
		this.password = password;
		this.question = question;
		this.status = status;
	}

	public User() {
		super();
		
	}

	public String getFirst_name() {
		return firstName;
	}
	public void setFirst_name(String firstName) {
		this.firstName = firstName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "User [first_name=" + firstName + ", email=" + email + ", password=" + password + ", question="
				+ question + ", status=" + status + "]";
	}
	
	

}
