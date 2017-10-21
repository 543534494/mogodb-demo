package com.zq.db.mongo.bean;

import java.util.Date;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class User extends BasicBean  {

	private static final long serialVersionUID = -3498592452352104227L;

	private String name;
	
	private Integer age;
	
	private Date birthDate;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}
	
	
}
