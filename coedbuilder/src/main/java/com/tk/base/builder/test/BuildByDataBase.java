package com.tk.base.builder.test;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.tk.base.builder.service.BuilderService;


/**
 * 
 * @ClassName: TestClass 
 * @Description: 独立通过数据库生成代码，本类只存在一个test方法放置误操作启动其它test方法
 * @company 
 * @author yixiang.deng
 * @Email 553067271@qq.com
 * @date 2015年7月15日 
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:spring/spring-*.xml")
public class BuildByDataBase {
	//注入代码构建service
	@Autowired
	private BuilderService builderService;
	
	/**
	 * 
	 * @Title: builderAllBySqlTest 
	 * @Description: 测试直接生成部分表格并放入项目目录中【测试其使用是test/resources下面的配置】
	 * @throws Exception
	 */
	@Test
	public void builderSmomeBySqlTest() throws Exception{
		Integer builderBySql = builderService.builderAllBySql("admin");
		Assert.assertTrue(builderBySql.equals(1));
	}

}
