package com.xxwl.tk.attachment.service;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.UUID;

import org.springframework.web.multipart.MultipartFile;

import com.xxwl.tk.framework.attribute.CommonAttribute;
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

public interface AttachmentService {
	/**
	 * 
	 * @Title: saveFile 
	 * @Description: 保存文件
	 * @param beginSavePath
	 * @param module
	 * @param file
	 * @return
	 */
	public String saveFile(String beginSavePath,String module,MultipartFile file);
	/**
	 * 
	 * @Title: getRelativePathFile 
	 * @Description: 获取相对位置包含文件名
	 * @param module
	 * @param fileName
	 * @return
	 */
	public String getRelativePathFile(String module, String fileName);
	/**
	 * 
	 * @Title: getRelativePath 
	 * @Description: 获取相对位置
	 * @param module
	 * @return
	 */
	public String getRelativePath(String module);
	/**
	 * 
	 * @Title: getFileLogicName 
	 * @Description: 获取文件逻辑名称
	 * @param fileName
	 * @return
	 */
	public String getFileLogicName(String fileName);
}
