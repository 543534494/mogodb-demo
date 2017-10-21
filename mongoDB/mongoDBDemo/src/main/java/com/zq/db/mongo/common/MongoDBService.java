package com.zq.db.mongo.common;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Update;

import com.zq.db.mongo.bean.BasicBean;

/**
 * mongoDB通用接口
 * <pre>
 * date: 2017年2月7日 下午4:54:31 
 * </pre>
 * @ClassName: MongoDBService   
 * @author deyi
 * @version V1.0
 */
public interface MongoDBService<T extends BasicBean> {

	T get(String objectId);

	List<T> query(Criteria criteria, Sort sort, int skip, int limit);

	long count(Criteria criteria);

	T insert(T t);

	void update(T t,String... fields);
	
	void update(Criteria criteria,Update update);

	void remove(String objectId);

}
