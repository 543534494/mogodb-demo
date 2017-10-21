package com.zq.db.mongo.bean;

import java.io.Serializable;

import org.springframework.data.annotation.Id;


/**
 * 基础的Bean对象 123
 * <pre>
 * date: 2017年2月7日 下午4:55:03 
 * </pre>
 * @ClassName: BasicBean   
 * @author deyi
 * @version V1.0
 */
public class BasicBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	protected String id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
}
