package com.zq.db.mongo.common;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.data.mongodb.core.MongoTemplate;

public abstract class ZqMongoTemplete  implements  
				ApplicationContextAware { 

	protected MongoTemplate mongoTemplate;

	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		
		   this.mongoTemplate = applicationContext.getBean(  
	                "mongoTemplate", MongoTemplate.class);  
	}
	
}
