package com.xxwl.tk.attachment.service;

import org.springframework.web.multipart.MultipartFile;
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
}
