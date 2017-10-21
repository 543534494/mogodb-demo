package com.zq.db.mongo.service.impl;

import org.springframework.stereotype.Service;

import com.zq.db.mongo.bean.User;
import com.zq.db.mongo.common.impl.MongoDBServiceImpl;
import com.zq.db.mongo.service.UserService;

@Service
public class UserServiceImpl extends MongoDBServiceImpl<User> implements UserService{

	public void add(){
	}
}
