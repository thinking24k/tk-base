package com.xxwl.tk.framework.dao;

import java.util.Collection;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.springframework.data.redis.connection.DataType;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * 
 * @ClassName: RedisDao 
 * @Description: redis 操作接口
 * @company 
 * @author yixiang.deng
 * @Email 553067271@qq.com
 * @date 2015年8月25日 
 *
 */
public interface RedisDao {

	/**
	 * 
	 * @Title: getRedisTemplate 
	 * @Description: 获取RedisTemplate 
	 * @return RedisTemplate<String, Object>
	 */
	public RedisTemplate<String, Object> getRedisTemplate();
	/**
	 * 
	 * @Title: Hput 
	 * @Description: redis hash操作 存入map中一个值
	 * @param key hash的key
	 * @param field hash数据的key
	 * @param value hash数据的val
	 */
	public void hSet(String key,String field,Object value);
	/**
	 * 
	 * @Title: Hget 
	 * @Description: redis hash操作 取出map中一个值
	 * @param key hash的key
	 * @param field hash数据的key
	 * @return Object
	 */
	public Object hGet(String key,String field);
	/**
	 * 
	 * @Title: hDel 
	 * @Description: 从hash表中删除一条数据
	 * @param key hash的key
	 * @param field hash数据的key
	 */
	public void hDel(String key,String field);
	/**
	 * 
	 * @Title: del 
	 * @Description: 从redis db中删除指定key的数据
	 * @param key hash的key
	 */
	public void del(String key);
	/**
	 * 
	 * @Title: del 
	 * @Description: 从redis db中删除指定keys的数据
	 * @param keys  Collection<String>
	 */
	public void del(Collection<String> keys);
	
	/**
	 * @Title: clear 
	 * @Description: 清空整个redis
	 * @return Object
	 */
	public Object clear();
	/**
	 * 
	 * @Title: hLen 
	 * @Description: hash的size
	 * @param key hash的key
	 * @return
	 */
	public Long hSize(String key);
	 /**
	  * 
	  * @Title: expireBySeconds 
	  * @Description: 设置key的过期时间，以秒为单位
	  * @param key redis缓存key
	  * @param seconds 时间（秒）
	  * @return Boolean
	  */
   public Boolean expireBySeconds(String key, long seconds);
   /**
    * 
    * @Title: expire 
    * @Description: 设置key的过期时间，自定义时间类型及长度
    * @param key redis缓存key
    * @param timeout 时间
    * @param unit 时间类型 如:TimeUnit.SECONDS
    * @return Boolean
    */
   public Boolean expire(String key, long timeout,TimeUnit unit);
	/**
     * 
     * @Title: persist 
     * @Description: 取消key的过期时间设置
     * @param key redis缓存key
     * @return Boolean
     */
    public Boolean persist(String key);
    /**
     * 
     * @Title: getExpire 
     * @Description: 获取redis key的过期时间 
     * @param unit 时间类型 如:TimeUnit.SECONDS
     * @return
     */
    public Long getExpire(String key,TimeUnit unit);
    /**
     * 
     * @Title: type 
     * @Description: 获取key存储的类型
     * @param key
     * @return
     */
    public DataType type(String key);
    /**
     * 
     * @Title: keys 
     * @Description: TODO
     * @param pattern
     * @return
     */
    public Set<String> keys(String pattern);

    /**
	 * 
	 * @Title: setValForTimeout 
	 * @Description: 设置带有一个过期时间的key
	 * @param key  String
	 * @param value Object
	 * @param timeout long时间
	 * @param unit 用法 TimeUnit.
	 */
    public void setValForTimeout(String key,Object value,long timeout,TimeUnit unit);
    /**
     * 
     * @Title: getValForTimeout 
     * @Description: 获取对应key的val
     * @param key
     * @return
     */
    public Object getValForTimeout(String key);
}
