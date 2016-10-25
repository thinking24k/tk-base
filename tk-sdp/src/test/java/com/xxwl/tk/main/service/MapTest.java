package com.xxwl.tk.main.service;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

import org.junit.Test;

import com.xxwl.tk.attachment.utils.FileUtil;
import com.xxwl.tk.framework.attribute.CommonAttribute;
import com.xxwl.tk.framework.utils.DateUtil;
import com.xxwl.tk.framework.utils.MapUtil;
import com.xxwl.tk.framework.utils.StringUtil;
import com.xxwl.tk.main.entity.UserEntity;

public class MapTest {
	@Test
	public final void testDoMap() {
		 Map<String, Object> temp = MapUtil.getValue(new UserEntity(null, "AA1", null, "123456", "123456", null, 6D, 0, null) );
		for (String s : temp.keySet()) {
			System.out.println(temp.get(s));
		}
		
	}
	
	@Test
	public void t1(){
		String filename="sadasdas.jpg";
		//获取文件保存路径
		String today = DateUtil.paseString(DateUtil.DATE_PATTERN, new Date());
		//数据库存储相对位置
		String savePath="pic"+CommonAttribute.PATH_DEFAULT_SPLIT_CHAR+today+CommonAttribute.PATH_DEFAULT_SPLIT_CHAR;
		//保存文件位置
		String filePath="E://test/"+savePath;
		FileUtil.checkPath(filePath);
		FileUtil.createFilePath(filePath);
		//获取文件名
		String suffix = filename.substring(filename.lastIndexOf("."),filename.length());
		String uuid = UUID.randomUUID().toString();
		String fileName=uuid+suffix;
		//保存文件
		File save=new File(filePath+fileName);
		System.out.println(savePath+fileName);
		
	}
}
