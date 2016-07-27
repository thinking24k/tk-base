package com.scrt.mi.generator.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import freemarker.template.Template;
import freemarker.template.TemplateException;
/**
 * 
 * @ClassName: FreemarkUtil 
 * @Description: Freemark操作工具类，依靠spring
 * @company 
 * @author yixiang.deng
 * @Email 553067271@qq.com
 * @date 2015年7月2日 
 *
 */
@Component
public class FreemarkUtil {
	@Resource(name="freeMarker")
	private FreeMarkerConfigurer freeMarkerConfigurer;
	// log4j
	private static Logger log = Logger.getLogger(FreemarkUtil.class);
	FreemarkUtil(){
		//System.out.println("init......");
	}

	/**
	 * 
	 * @Title: parperTemplate 
	 * @Description: 根据文件名获取文件，并填充数据
	 * @param templateName 模板文件的名称 如:entityTemplate.ftl
	 * @param map 模板内需要填充的数据
	 * @return 模板+数据 字符数据
	 * @throws IOException
	 * @throws TemplateException
	 */
	public  String parperTemplate(String templateName, Map<String, Object> map) throws IOException, TemplateException{
		//log.info(freeMarkerConfigurer.getConfiguration().getTemplateLoader());
		// 通过指定模板名获取FreeMarker模板实例
		Template tpl = freeMarkerConfigurer.getConfiguration().getTemplate(templateName);  
		//tpl.process(dataModel, out);
		// Map<String, Object> map = new HashMap<String, Object>(); 
		// FreeMarker通过Map传递动态数据
		// map.put("LOGINNAME", content); // 注意动态数据的key和模板标签中指定的属性相匹配
		// 解析模板并替换动态数据，最终content将替换模板文件中的${content}标签。
		return  FreeMarkerTemplateUtils.processTemplateIntoString(tpl, map);
	}
	/**
	 * 
	 * @Title: saveFilebyStr 
	 * @Description: 保存模板
	 * @param templateName
	 * @param tempMap
	 * @param outFilePath
	 * @param fileName
	 */
	public void saveFilebyStr(String templateName, Map<String, Object> tempMap, String outFilePath,String fileName)throws Exception{
		String savePath=cheackFilPath(outFilePath);
		OutputStreamWriter out=null;
		FileOutputStream fileOutputStream = null;
		try {
			String parperTemplate = parperTemplate(templateName, tempMap);
			File folder = new File(savePath);
			if (!folder.exists()) {
				folder.mkdirs();
			}
			savePath=savePath+"/"+fileName;//保存文件全路径名
			fileOutputStream = new FileOutputStream(savePath);
			out = new OutputStreamWriter(fileOutputStream,"UTF-8");
			out.write(parperTemplate);
			out.flush();
			fileOutputStream.flush();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (TemplateException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} 
		finally {
			try {
				if (out != null)
					out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				if (fileOutputStream != null)
					fileOutputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}


	}
	//校验path
	private String cheackFilPath(String path){
		if(StringUtil.isEmpty(path)){
			return path;
		}
		if(path.contains(".")){//如果有.
			path= path.replace(".", "/");
			path= path.replace("//", "/");
		}
		return path;
	}


	/*public void saveFile(String templateName, Map<String, Object> root, String outFilePath) {
		OutputStreamWriter out = null;
		try {
			String folderPath = outFilePath.substring(0,outFilePath.lastIndexOf("/"));
			File folder = new File(folderPath);
			if (!folder.exists()) {
				folder.mkdirs();
			}			

			// 通过一个文件输出流，就可以写到相应的文件中，此处用的是绝对路径
			File file = new File(outFilePath);
			if (!file.exists()) {
				file.createNewFile();
			}			
			out = new OutputStreamWriter(new FileOutputStream(file),"UTF-8"); 
			Template temp =freeMarkerConfigurer.getConfiguration().getTemplate(templateName);
			temp.process(root, out);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (TemplateException e) {
			e.printStackTrace();
		} finally {
			try {
				if (out != null)
					out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}*/

}
