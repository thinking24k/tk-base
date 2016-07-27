package com.scrt.mi.generator.web.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.scrt.mi.generator.attribute.CommonAttribute;
import com.scrt.mi.generator.entity.Level;
import com.scrt.mi.generator.entity.TableEntity;
import com.scrt.mi.generator.service.BuilderService;
import com.scrt.mi.generator.util.ConfigUtil;

/**
 * 
 * @ClassName: BuilderController 
 * @Description: 代码生成器controller
 * @company 
 * @author yixiang.deng
 * @Email 553067271@qq.com
 * @date 2015年7月6日 
 *
 */
@Controller
@RequestMapping("builderController/")
public class BuilderController {
	@Autowired
	private BuilderService builderService;

	@RequestMapping("toPdmUpload")
	public String toPdmUpload(HttpServletRequest req) {
		req.setAttribute("select", 1);//nav标示选中 从0开始
		return "pdmUpLoad/pdmUpload";
	}
	/**
	 * 
	 * @Title: getAllTableBySql 
	 * @Description: 通过数据库获取所有的表格
	 * @return
	 */
	@RequestMapping("getAllTableBySql")
	public @ResponseBody List<TableEntity> getAllTableBySql() {
		try {
			return builderService.getAllTableBySql();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 
	 * @Title: getAllTableByPdm 
	 * @Description: 通过数据库获取所有的表格
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("getAllTableByPdm")
	public @ResponseBody List<TableEntity> getAllTableByPdm(HttpSession session) {
		try {
			return (List<TableEntity>) session.getAttribute("pdmTables");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 
	 * @Title: getAllTableBySql 
	 * @Description: 通过数据库获取所有的表格
	 * @return
	 */
	@RequestMapping("getlevels")
	public @ResponseBody List<Level> getlevels() {
		try {
			return builderService.getlevels();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 
	 * @Title: builderBySql 
	 * @Description: 根据sql生成
	 * @param tables
	 * @return
	 */
	@RequestMapping("builderBySql")
	public @ResponseBody Integer builderBySql(String tables) {
		try {
			return builderService.builderBySql(tables);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}
	/**
	 * 
	 * @Title: builderByPdm 
	 * @Description: 通过pdm文件导入生成
	 * @param tables
	 * @return
	 */
	@RequestMapping("builderByPdm")
	public @ResponseBody Integer builderByPdm(String tables,HttpSession session) {
		try {
			@SuppressWarnings("unchecked")
			List<TableEntity> tableEntities=(List<TableEntity>) session.getAttribute("pdmTables");
			return builderService.builderByPdm(tables,tableEntities);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}
	/*
	 * SpringMVC中的文件上传
	 * @第一步：由于SpringMVC使用的是commons-fileupload实现，故将其组件引入项目中
	 * @这里用到的是commons-fileupload-1.2.1.jar和commons-io-1.3.2.jar
	 * @第二步：spring-mvx中配置MultipartResolver处理器。可在此加入对上传文件的属性限制
	 *  <bean id="multipartResolver"  
	 *  class="org.springframework.web.multipart.commons.CommonsMultipartResolver">
	 *	 <!-- 设置上传文件的最大尺寸为10MB -->  
	 *		<property name="maxUploadSize">  
	 *			<value>10000000</value>  
	 *		 </property>  
	 * </bean> 
	 * 第三步：在Controller的方法中添加MultipartFile参数。该参数用于接收表单中file组件的内容
	 *第四步：编写前台表单。注意enctype="multipart/form-data"以及<input type="file" name="****"/>
	 *  如果是单个文件 直接使用MultipartFile 即可
	 */ 
	/**
	 * 
	 * @Title: upload 
	 * @Description: 文件上传
	 * @param file 文件
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping("upload")
	public String upload(@RequestParam("file") MultipartFile file,
			HttpServletRequest req,HttpSession session){
			System.out.println("文件类型："+file.getContentType());
			System.out.println("文件名称："+file.getOriginalFilename());
			System.out.println("文件大小:"+file.getSize());
			InputStream inputStream=null;
			try {
				//获取文件的输入流
				inputStream = file.getInputStream();
				List<TableEntity> allTableByPdm = builderService.getAllTableByPdm(inputStream);
				//保存解析的pad到session
				session.setAttribute("pdmTables", allTableByPdm);
				//nav标示选中 从0开始
				req.setAttribute("select", 1);
				return "index";
			} catch (IOException e) {
				e.printStackTrace();
			}finally{//关闭流
				if(inputStream!=null){
					try {
						inputStream.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		return "redirect:builderController/toPdmUpload.do";
		//获取文件 存储位置
		//String realPath = request.getSession().getServletContext().getRealPath("/uploadFile");

		//File pathFile = new File(realPath);

		//if (!pathFile.exists()) {
		//文件夹不存 创建文件
		//	pathFile.mkdirs();
		//}
		//for (MultipartFile f : file) {

		//System.out.println("文件类型："+f.getContentType());
		//System.out.println("文件名称："+f.getOriginalFilename());
		//	System.out.println("文件大小:"+f.getSize());
		//	InputStream inputStream = f.getInputStream();
		//builderService.getAllTableByPdm(inputStream);

		//将文件copy上传到服务器
		//f.transferTo(new File(realPath + "/" + f.getOriginalFilename()));
		//FileUtils.copy
		//}
		//获取modelandview对象
		//ModelAndView view = new ModelAndView();
		//view.setViewName("redirect:index.jsp");
		//return "";
	}


	@RequestMapping("download")  
	public ModelAndView download(HttpServletRequest request,  
			HttpServletResponse response){  

		//String storeName = "Spring3.xAPI_zh.chm";  
		// String storeName="房地.txt";
		//String contentType = "application/octet-stream";  
		download(request, response, ConfigUtil.getByKey(CommonAttribute.ZIP_PATH));
		return null;  
	}  


	//文件下载 主要方法
	public void download(HttpServletRequest request,  
			HttpServletResponse response, String filePath) {  


		BufferedInputStream bis = null;  
		BufferedOutputStream bos = null;  

		//获取项目根目录
		//String ctxPath = request.getSession().getServletContext().getRealPath("");  

		//获取下载文件露肩
		//String downLoadPath = ctxPath+"/uploadFile/"+ storeName;  
		try {
			File file = new File(filePath);
			//获取文件的长度
			long fileLength =file.length();  
			request.setCharacterEncoding("UTF-8");  
			//设置文件输出类型
			response.setContentType("application/octet-stream");  
			response.setHeader("Content-disposition", "attachment; filename="  
					+ new String(file.getName().getBytes("utf-8"), "ISO8859-1")); 
			//设置输出长度
			response.setHeader("Content-Length", String.valueOf(fileLength));  
			//获取输入流
			bis = new BufferedInputStream(new FileInputStream(filePath));  
			//输出流
			bos = new BufferedOutputStream(response.getOutputStream());  
			byte[] buff = new byte[2048];  
			int bytesRead;  
			while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {  
				bos.write(buff, 0, bytesRead);  
			}
			bos.flush();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}  
		finally{
			//关闭流
			try {
				if(bis!=null){
					bis.close();
				}
			} catch (IOException e1) {
				e1.printStackTrace();
			}  
			try {
				if(bos!=null){
					bos.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}  
		}
	}  


}
