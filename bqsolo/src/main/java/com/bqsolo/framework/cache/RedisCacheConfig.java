package com.bqsolo.framework.cache;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
  

/** 
* @ClassName: RedisCacheConfig 
* @Description: 啟用spring的cache,納入redis支持
* @company 
* @author xuefeng.gao
* @Email xuefeng.gao@rato360.com
* @date 2016年5月31日 
*  
*/ 
/*@Configuration  
@EnableCaching
public class RedisCacheConfig extends CachingConfigurerSupport {  

	//(spring-base-datasource.xml文件中已定义redisTemplate,
	//使用jedisConnectionFactory直接初始化会导致redisTemplate全部使用默认只初始化)
	@Autowired
	private RedisTemplate<String, Object> redisTemplate;	

	

    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory cf) {

        return redisTemplate;
    }  
  
    @Bean(name="springCacheManager")
    public CacheManager cacheManager(RedisTemplate redisTemplate) {  
        RedisCacheManager cacheManager = new RedisCacheManager(redisTemplate);  
        cacheManager.setDefaultExpiration(0); 
        return cacheManager;  
    }  
      
}  */