package com.zq.db.mongo.bean;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Member extends BasicBean {

	private static final long serialVersionUID = 4895962651360018628L;

	private User user;
	
	private String name;
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}
