package com.xxwl.tk.framework.cache;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.cache.Cache;

import com.xxwl.tk.framework.dao.RedisDao;

/** 
 *  
 * @ClassName: RedisCache 
 * @Description: TODO(使用第三方缓存服务器redis，处理二级缓存) 
 * @author LiuYi 
 * @date 2014年6月9日 下午1:37:46 
 * 
 */  
/*public class RedisCache   implements Cache {  
	private static Log log = LogFactory.getLog(RedisCache.class);  
	*//** The ReadWriteLock. *//*  
	private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();  

	private String id;  
	public RedisCache(final String id) {  
		if (id == null) {  
			throw new IllegalArgumentException("必须传入ID");  
		}  
		log.debug("MybatisRedisCache:id=" + id);  
		this.id=id;
	}  
	@Override  
	public String getId() {  
		return this.id;  
	}

	private RedisDao rd;
	//获取redisDao spring 注入对象
	private RedisDao getRD(){
		if(null==rd){
			Object bean =SpringContext.getContext().getBean("redisDaoImpl");
			return (RedisDao)bean;
		}
		return rd;
	}
	@Override  
	public int getSize() {  
		return Integer.parseInt(getRD().hSize(id).toString()); 
	}  

	@Override  
	public void putObject(Object key, Object value) {
		getRD().hSet(id, key.toString(), value);
	}  

	@Override  
	public Object getObject(Object key) {  
		return getRD().hGet(id, key.toString());
	}  

	@Override  
	public Object removeObject(Object key) {
		Object hGet = getRD().hGet(id, key.toString());
		getRD().hDel(id, key.toString());
		return hGet;
	}  

	@Override  
	public void clear() {
		getRD().del(id);
	}  

	@Override  
	public ReadWriteLock getReadWriteLock() {  
		return readWriteLock;  
	}  
	

}  */