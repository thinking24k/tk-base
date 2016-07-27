package com.scrt.mi.generator.test;

import java.io.FileInputStream;
import java.io.InputStream;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.scrt.mi.generator.attribute.CommonAttribute;
import com.scrt.mi.generator.service.BuilderService;

/**
 * 
 * @ClassName: TestClass 
 * @Description: 主要测试类
 * @company 
 * @author yixiang.deng
 * @Email 553067271@qq.com
 * @date 2015年7月15日 
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:spring/spring-*.xml")
public class TestClass {
	//注入代码构建service
	@Autowired
	private BuilderService builderService;
	/**
	 * 
	 * @Title: builderAllBySqlTest 
	 * @Description: 测试直接生成所有表格并放入项目目录中【测试其使用是test/resources下面的配置】
	 * @throws Exception
	 */
//	@Test
//	public void builderAllBySqlTest() throws Exception{
//		Integer builderBySql = builderService.builderAllBySql();
//		Assert.assertTrue(builderBySql.equals(1));
//	}
//	/**
//	 * 
//	 * @Title: builderAllByPdmTest 
//	 * @Description: 测试通过pdm文件直接生成所有表格并放入项目目录中【测试其使用是test/resources下面的配置】
//	 * @throws Exception
//	 */
//	@Test
//	public void builderAllByPdmTest() throws Exception{
//		String pdmFileLocalPath=CommonAttribute.ROOT_PATH+"/src/test/resources/pdm/Mi-Base.pdm";
//		//String pdmFileLocalPath="F:/tempCode/Mi-Base.pdm";
//		//String pdmFileLocalPath="E:/rt_workSpaces/R2D/doc/公共文档/R&D/设计文档/数据库设计/Mi-平台数据库设计.pdm";
//		InputStream inputStream=new FileInputStream(pdmFileLocalPath);
//		Integer builderBySql = builderService.builderAllByPdm(inputStream);
//		Assert.assertTrue(builderBySql.equals(1));
//	}
	/**
	 * 
	 * @Title: builderAllBySqlTest 
	 * @Description: 测试直接生成部分表格并放入项目目录中【测试其使用是test/resources下面的配置】
	 * @throws Exception
	 */
	@Test
	public void builderSmomeBySqlTest() throws Exception{
		Integer builderBySql = builderService.builderAllBySql("sy_msg_nav".split(","));
		Assert.assertTrue(builderBySql.equals(1));
	}
//	@Test
//	public void builderSmomeByPdmTest() throws Exception{
//		String pdmFileLocalPath=CommonAttribute.ROOT_PATH+"/src/test/resources/pdm/Mi-Base.pdm";
//		//String pdmFileLocalPath="F:/tempCode/Mi-Base.pdm";
//		//String pdmFileLocalPath="E:/rt_workSpaces/R2D/doc/公共文档/R&D/设计文档/数据库设计/Mi-平台数据库设计.pdm";
//		InputStream inputStream=new FileInputStream(pdmFileLocalPath);
//		Integer builderBySql = builderService.builderAllByPdm(inputStream,"mi_attachment");
//		Assert.assertTrue(builderBySql.equals(1));
//	}
}
