package com.tweet.entity;

public class Tweets {
	
	private String email,subject,description;

	public Tweets(String email, String subject, String description) {
		super();
		this.email = email;
		this.subject = subject;
		this.description = description;
	}

	public Tweets() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "Tweets [email=" + email + ", subject=" + subject + ", description=" + description + "]";
	}
	
	

}
