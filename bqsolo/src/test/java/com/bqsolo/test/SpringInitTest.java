package com.bqsolo.test;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.bqsolo.framework.attribute.CommonAttribute;
import com.bqsolo.main.utils.MailUtil;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:spring/*.xml")
public class SpringInitTest {
	@Resource(name="mailUtil")
	private MailUtil  mailUtil;
	
	@Test
	public void sendMail(){
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("name", "邓");
		map.put("regIp", "127.0.0.1");
		mailUtil.sendhtmlMail("dengyixiang_jy@126.com", "553067271@qq.com", "test", CommonAttribute.REGISTERFTL, map);
	} 
}
