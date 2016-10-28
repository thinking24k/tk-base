package com.xxwl.tk.attachment.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.xxwl.tk.attachment.service.AttachmentService;
import com.xxwl.tk.attachment.utils.FileUtil;
import com.xxwl.tk.framework.attribute.CommonAttribute;
import com.xxwl.tk.framework.utils.StringUtil;
/**
 * 
 * @ClassName: AttachmentService 
 * @Description: TODO
 * @company 
 * @author yixiang.deng
 * @Email 553067271@qq.com
 * @date 2016年10月25日 
 *
 */
@Service
public class AttachmentServiceImpl implements AttachmentService{
	
	private static Logger logger = LoggerFactory.getLogger(AttachmentServiceImpl.class);
	
	@Override
	public String saveFile(String beginSavePath,String module, MultipartFile file) {
		if(StringUtil.isEmpty(beginSavePath) || null == file){
			return null;
		}
		String savePath = getRelativePath(module);
		String fileName=getFileLogicName(file.getOriginalFilename());
		//保存文件位置
		String filePath=beginSavePath+savePath;
		//获取文件名
		FileUtil.checkPath(filePath);
		FileUtil.createFilePath(filePath);
		//保存文件
		File save=new File(filePath+fileName);
		try {
			file.transferTo(save);
			return savePath+fileName;
		} catch (IllegalStateException | IOException e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}
		return null;
	}
	//获取相对位置包含文件名
	public String getRelativePathFile(String module, String fileName) {
		//数据库存储相对位置
		return getRelativePath(module)+getFileLogicName(fileName);
	}
	//获取相对位置
	public String getRelativePath(String module) {
		//获取文件保存路径
		Calendar now = new GregorianCalendar();
		int year=now.get(Calendar.YEAR);
		int month=now.get(Calendar.MONTH);
		int day=now.get(Calendar.DAY_OF_MONTH);
		String today =year+CommonAttribute.PATH_DEFAULT_SPLIT_CHAR+month+CommonAttribute.PATH_DEFAULT_SPLIT_CHAR+day; //DateUtil.paseString(DateUtil.DATE_PATTERN, new Date());
		//数据库存储相对位置
		return module+CommonAttribute.PATH_DEFAULT_SPLIT_CHAR+today+CommonAttribute.PATH_DEFAULT_SPLIT_CHAR;
	}
	//获取文件逻辑名称
	public String getFileLogicName(String fileName) {
		//获取文件名
		String suffix = fileName.substring(fileName.lastIndexOf("."),fileName.length());
		String uuid = UUID.randomUUID().toString();
		return uuid+suffix;
	}
		
	
	
	
	
}
