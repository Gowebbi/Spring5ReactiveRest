package com.zohaib.model;

public class User {

	private String userId;

	private String name;

	private int age;
	
	

	public static User newUser(final String userId, final String name, final int age) {
		final User user = new User();
		
		user.setUserId(userId);
		user.setName(name);
		user.setAge(age);
		
		return user;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(final String userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(final int age) {
		this.age = age;
	}

}
