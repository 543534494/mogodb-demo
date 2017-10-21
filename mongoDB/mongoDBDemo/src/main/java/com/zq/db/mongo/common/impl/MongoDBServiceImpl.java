package com.zq.db.mongo.common.impl;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import com.zq.db.mongo.bean.BasicBean;
import com.zq.db.mongo.common.MongoDBService;
import com.zq.db.mongo.common.MongoUpdateUtils;
import com.zq.db.mongo.common.ZqMongoTemplete;

/**
 * mongoDB通用的接口实现
 * <pre>
 * date: 2017年2月7日 下午4:53:52 
 * </pre>
 * @ClassName: MongoDBServiceImpl   
 * @author deyi
 * @version V1.0
 */
public class MongoDBServiceImpl<T extends BasicBean> extends ZqMongoTemplete implements MongoDBService<T> {

	@SuppressWarnings("unchecked")
	protected Class<T> entityClass = (Class<T>) ((ParameterizedType) this.getClass() //
			.getGenericSuperclass()).getActualTypeArguments()[0];

	public T get(String objectId) {
		
		Query query = new Query();
		
		Criteria criteria = new Criteria("_id");
		criteria.is(new ObjectId(objectId));
		
		query.addCriteria(criteria);
		
		return this.mongoTemplate.findOne(query, entityClass);
	}

	public List<T> query(Criteria criteria, Sort sort, int skip, int limit) {

		Query query = new Query(criteria);

		query.skip(skip).limit(limit).with(sort);

		return this.mongoTemplate.find(query, entityClass);
	}

	public long count(Criteria criteria) {

		Query query = new Query(criteria);

		return mongoTemplate.count(query, entityClass);
	}

	public void update(T t,String... fields) {
		
		Query query = new Query(Criteria.where("_id") //
				.is(new ObjectId(t.getId())));
		
		Update update = MongoUpdateUtils.buildInclude(t, fields);
		if (update.getUpdateObject().toMap().isEmpty()){
			return;
		}
		mongoTemplate.upsert(query, update, entityClass);
	}
	
	public void update(Criteria criteria,Update update) {
		
		Query query = new Query(criteria);
		if (update.getUpdateObject().toMap().isEmpty()){
			return;
		}
		mongoTemplate.upsert(query, update, entityClass);
	}


	public void remove(String objectId) {
		
		mongoTemplate.remove(new Query(Criteria.where("_id") //
				.is(new ObjectId(objectId))), entityClass);
	}

	public T insert(T t) {
		
		this.mongoTemplate.insert(t);
		
		return t;
	}

}
