package com.zq.db.mongo.service.impl;

import org.springframework.stereotype.Service;

import com.zq.db.mongo.bean.Member;
import com.zq.db.mongo.common.impl.MongoDBServiceImpl;
import com.zq.db.mongo.service.MemberService;
@Service
public class MemberServiceImpl extends MongoDBServiceImpl<Member> implements MemberService{

}
