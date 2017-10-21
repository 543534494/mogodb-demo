package com.zq.db.mongo;

import java.util.Date;

import org.bson.Document;
import org.bson.types.ObjectId;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;

public class MongoDBTest {

	private MongoClient client;
	private MongoDatabase database ;
	
	@Before
	public void init(){
		this.client = new MongoClient("192.169.7.8",27017);
		this.database = client.getDatabase("zq535");
	}
	
	@After
	public void close(){
		this.client.close();
	}
	@Test
	public void createCollection(){
		database.createCollection("test");
		client.close();
	}
	
	@Test
	public void insert(){
		MongoCollection<Document> collection = database.getCollection("test");
		Document document = new Document();
		document.put("name", "张三");
		document.put("age", 24);
		document.put("phone", 18701503529l);
		collection.insertOne(document);
	}
	
	@Test
	public void queryAll(){
		MongoCollection<Document> collection = database.getCollection("test");
		
		FindIterable<Document> iterable = collection.find();
		MongoCursor<Document> iterator = iterable.iterator();
		while(iterator.hasNext()){
			Document next = iterator.next();
			System.out.println(next);
		}
		
	}
	
	@Test
	public void update(){
		MongoCollection<Document> collection = database.getCollection("test");
		Document update = new Document();
		update.put("name", "李坏");
		update.put("updateTime", new Date());
		UpdateResult result = collection.updateOne(Filters.eq("age", 24), new Document("$set",update));
		System.out.println(result);
		System.out.println(result.getModifiedCount());
	}
	
	@Test
	public void get(){
		MongoCollection<Document> collection = database.getCollection("test");
		FindIterable<Document> find = collection.find(Filters.eq("_id", new ObjectId("587c5e54fb07e317a4fb2a64")),Document.class);
		MongoCursor<Document> iterator = find.iterator();
		while(iterator.hasNext()){
			Document next = iterator.next();
			System.out.println(next);
		}
	}
	
	@Test
	public void remove(){
		MongoCollection<Document> collection = database.getCollection("test");
		DeleteResult deleteOne = collection.deleteOne(Filters.eq("name","张三"));
		System.out.println(deleteOne.getDeletedCount());
	}
}
