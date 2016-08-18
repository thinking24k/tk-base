package com.xxwl.tk.attchment.web.filter;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import net.coobird.thumbnailator.Thumbnails;

public class ImageFilter implements Filter{
	//图片压缩存放的位置/sy_msg_info/2431/compress/0/2016030311121043801485_40*40.jpg
	private final static String CLIPSTR="compress/";
	private final static String UNLINE="_";
	private final static String STAR="x";
	private final static String SUFFIX=".";
	private final static String DFS="/";
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		//转换为 HttpServletRequest
		HttpServletRequest req=(HttpServletRequest)request;
		//获取图片本地完整路径
		String path = req.getSession().getServletContext().getRealPath("")+req.getServletPath();
		try {
			if(doFilterImg(path)){//图片进行了处理，写出对应的图片
				if(writeFile(response, path)){//对第一次处理的图片写出
					return ;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		chain.doFilter(request, response);
	}
/*	@Test
	public void t1() throws IOException{
		String path="sy_msg_info/2431/compress/0/2016030311121043801485_40*40.jpg";
		System.out.println(sourceFile);
	}*/
	/**
	 * 
	 * @Title: writeFile 
	 * @Description: 写出文件，适用于第一次处理的图片
	 * @param response
	 * @param path
	 * @return
	 */
	public boolean writeFile(ServletResponse response,String path){
		FileInputStream fis = null;
		ServletOutputStream outputStream = null;
		try {
			fis = new FileInputStream(path);
			outputStream = response.getOutputStream();
			byte[] b = new byte[10*10240];  
			while(fis.read(b,0,10240) != -1){  
				outputStream.write(b,0,10240);  
			}
			outputStream.flush();
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}finally{
			if(null !=outputStream){
				try {
					outputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(null !=fis){
				try {
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return true;  
	}
	/**
	 * 
	 * @Title: doFilterImg 
	 * @Description: 按web.xml配置拦截并处理图片大小
	 * @param path
	 * @throws IOException 
	 */
	public boolean doFilterImg(String path) throws IOException{
		//String pathStr="sy_msg_info/2431/compress/0/2016030311121043801485_40*40.jpg";
		if(isNullOrBlank(path)){
			return false;
		}
		//不含_和* 和compress/不需要裁剪
		if(!(path.contains(UNLINE) && path.contains(STAR) && path.contains(CLIPSTR))){
			return false;
		}
		//判断裁剪的文件是否存在
		if(new File(path).isFile()){
			//存在直接返回
			return false;
		}
		//获取原文件路径(可能多个_和.)
		/*String[] tf = path.split(CLIPSTR);
		String[] tf2 = tf[1].split(UNLINE);
		String[] tf3 = tf2[1].split("\\"+SUFFIX);
		String sourceFile=tf[0]+tf2[0]+SUFFIX+tf3[1];*/
		String[] tf = path.split(CLIPSTR);
		int uindex=tf[1].lastIndexOf(UNLINE);
		String tf2=tf[1].substring(0, uindex);
		String tf3=tf[1].substring(uindex, tf[1].length());
		int lindex=tf3.lastIndexOf(SUFFIX);
		String tf5=tf3.substring(lindex, tf3.length());
		String sourceFile=tf[0]+tf2+tf5;
		//判断原文件是否存在
		if(!new File(sourceFile).isFile()){
			//不存在直接返回
			return false;
		}
		//创建文件夹
		File folder=new File(path.substring(0, path.lastIndexOf(DFS)));
		if(!folder.exists()){
			folder.mkdirs();
		}
		//获取参数进行裁剪
		String temppath=path.substring(path.lastIndexOf(UNLINE)+1, path.lastIndexOf(SUFFIX));
		String[] split = temppath.split(STAR);
		Integer w=Integer.parseInt(split[0]);
		Integer h=Integer.parseInt(split[1]);
		//校验
		if(h<=0 || w<=0){
			return false;
		}
		/*
		 * size(width,height) 若图片横比200小，高比300小，不变
		 * 若图片横比200小，高比300大，高缩小到300，图片比例不变 若图片横比200大，高比300小，横缩小到200，图片比例不变
		 * 若图片横比200大，高比300大，图片按比例缩小，横为200或高为300
		 */
		Thumbnails.of(sourceFile).size(w, h).keepAspectRatio(false).toFile(path);
		
		return true;
	}
	//空字符校验
	public static boolean isNullOrBlank(String s) {
        return s == null || "".equals(s.trim());
    }
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

}
