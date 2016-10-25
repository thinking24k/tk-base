package com.xxwl.tk.attachment.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.UUID;

import org.apache.log4j.Logger;
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
	
	private static Logger logger = Logger.getLogger(AttachmentServiceImpl.class);
	
	@Override
	public String saveFile(String beginSavePath,String module, MultipartFile file) {
		if(StringUtil.isEmpty(beginSavePath) || null == file){
			return null;
		}
		//获取文件保存路径
		GregorianCalendar now = new GregorianCalendar();
		int year=now.get(Calendar.YEAR);
		int month=now.get(Calendar.MONTH);
		int day=now.get(Calendar.DAY_OF_MONTH);
		String today =year+CommonAttribute.PATH_DEFAULT_SPLIT_CHAR+month+CommonAttribute.PATH_DEFAULT_SPLIT_CHAR+day; //DateUtil.paseString(DateUtil.DATE_PATTERN, new Date());
		//数据库存储相对位置
		String savePath=module+CommonAttribute.PATH_DEFAULT_SPLIT_CHAR+today+CommonAttribute.PATH_DEFAULT_SPLIT_CHAR;
		//保存文件位置
		String filePath=beginSavePath+savePath;
		FileUtil.checkPath(filePath);
		FileUtil.createFilePath(filePath);
		
		//获取文件名
		String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."),file.getOriginalFilename().length());
		String uuid = UUID.randomUUID().toString();
		String fileName=uuid+suffix;
		//保存文件
		File save=new File(filePath+fileName);
		try {
			file.transferTo(save);
			return savePath+fileName;
		} catch (IllegalStateException | IOException e) {
			logger.error(e);
			e.printStackTrace();
		}
		return null;
	}
}
