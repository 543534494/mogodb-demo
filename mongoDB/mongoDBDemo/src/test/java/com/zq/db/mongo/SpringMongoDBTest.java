package com.zq.db.mongo;


import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.zq.db.mongo.bean.Member;
import com.zq.db.mongo.bean.User;
import com.zq.db.mongo.service.MemberService;
import com.zq.db.mongo.service.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring.xml"})
public class SpringMongoDBTest {

	
	@Resource
	private UserService userService;
	
	@Resource
	private MemberService memberService;
	
	@Test
	public void insert(){
		User user = new User();
		user.setAge(12);
		user.setName("历史");
		user.setBirthDate(new Date());
		User insert = userService.insert(user);
		System.out.println(insert);
	}
	
	@Test
	public void query(){
		List<User> query = userService.query(Criteria.where("name").is("历史").and("age").gt(17),new Sort(Direction.DESC,"age"),0,1);
		
		System.out.println(query);
	}
	
	
	@Test
	public void saveMember(){
		
		Member m= new Member();
		
		m.setName("zhangsan");
		
		User u= new User();
		u.setAge(12);
		u.setBirthDate(new Date());
		u.setName("zhangsan");
		m.setUser(u);
		
		Member insert = memberService.insert(m);
		System.out.println(insert);
	}
	
	
	@Test
	public void getMember(){
		Member member = memberService.get("58995bed7baead1bec7b138d");
		System.out.println(member);
		
	}
	
	@Test
	public void updateMember(){
		Member member = new Member();
		member.setId("58995bed7baead1bec7b138d");
		member.setName("李四2");
		User u = new User();
		u.setName("王五");
		member.setUser(u);
		memberService.update(member,"user","user.age");
	}
}
