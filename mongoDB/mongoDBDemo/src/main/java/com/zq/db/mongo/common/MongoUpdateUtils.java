package com.zq.db.mongo.common;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Array;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.data.mongodb.core.query.Update;

/**
 * MongoUpdateUtils工具类
 * <pre>
 * date: 2017年2月7日 下午4:25:05 
 * </pre>
 * @ClassName: MongoDBUtils   
 * @author deyi
 * @version V1.0
 */
public class MongoUpdateUtils {

	
	/**
	 * 封装Update对象排除字段
	 * @author	deyi
	 * @date 2017年2月7日 下午4:49:40   
	 * @version V1.0
	 * @throws
	 */
	public  static <T> Update buildExclude(T t,String ...objFields ){
		Update update = new Update();
		toUpdate(update, t, 3, 1,null, objFields);
		return update;
	}
	
	/**
	 * 封装Update对象包含字段
	 * @author	deyi
	 * @date 2017年2月7日 下午4:49:40   
	 * @version V1.0
	 * @throws
	 */
	public  static <T> Update buildInclude(T t,String ...objFields ){
		Update update = new Update();
		toUpdate(update, t, 3, 0,null, objFields);
		return update;
	}
	
	protected static <T>  void toUpdate(Update update ,T t,int depth,int optType,String prefix,String ... objFields){
		
		if (depth < 0 || null == t) {
			return;
		}
		
		List<String> fields = null;
		try {
			
			if (null != objFields && objFields.length > 0) {
				
				fields = Arrays.asList(objFields);
			}
			BeanInfo beanInfo = Introspector.getBeanInfo(t.getClass());
			
			PropertyDescriptor[] descriptors = beanInfo.getPropertyDescriptors();
			
			for (PropertyDescriptor p:descriptors) {
				String name = p.getName();
				if (name.equals("class")){
					continue;
				}
				if (optType == 1) {
					if (null != fields && fields.contains(name)) {//包含字段过滤
						continue;
					}
				}else {
					if (null != fields && !fields.contains(name)) {//包含字段显示
						continue;
					}
				}
				Method method = p.getReadMethod();
				if (null == method) {
					continue;
				}
				Object invoke = method.invoke(t);
				if (null == fields && null == invoke) {
					continue;
				}
				if (null == invoke) {
					//删除字段
					update.unset(getPrefix(prefix) + name);
				} else {
					Class<?> type = p.getPropertyType();
					if (type.isPrimitive()		 	||
							invoke instanceof String 	||
							invoke instanceof Number 	||
							invoke instanceof Date 		||
							invoke instanceof Collection||
							invoke instanceof Array) {
						
						update.set(getPrefix(prefix) + name, invoke);
					}else {
						
						toUpdate(update,invoke,depth-1, optType ,getPrefix(prefix) + name,getField(fields, name));
					}
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static String getPrefix(String prefix){
		if(null == prefix){
			return "";
		}
		return prefix +".";
	}
	
	private static String[] getField(List<String> fields,String name){
		List<String> f2 =new ArrayList<>();
		if (null != fields) {
			for(String f:fields){
				if (f.startsWith(name+".")) {
					f2.add(f.substring(name.length()+1));
				}
			}
		}
		return f2.toArray(new String[]{});
	}
	
}
