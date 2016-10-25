package com.xxwl.tk.attachment.utils;

import java.io.File;

import com.xxwl.tk.framework.attribute.CommonAttribute;
import com.xxwl.tk.framework.utils.StringUtil;


/**
 * 
 * @ClassName: FileUtil 
 * @Description: 文件操作工具类
 * @company 
 * @author yixiang.deng
 * @Email 553067271@qq.com
 * @date 2015年7月24日 
 *
 */
public class FileUtil {
	/**
	 *  根据路径删除指定的目录或文件，无论存在与否
	 *@param sPath  要删除的目录或文件
	 *@return 删除成功返回 true，否则返回 false。
	 */
	public static boolean deleteFolder(String sPath) {
		boolean flag = false;
		File  file = new File(sPath);
		// 判断目录或文件是否存在
		if (!file.exists()) {  // 不存在返回 false
			return flag;
		} else {
			// 判断是否为文件
			if (file.isFile()) {  // 为文件时调用删除文件方法
				return deleteFile(sPath);
			} else {  // 为目录时调用删除目录方法
				return deleteDirectory(sPath);
			}
		}
	}
	/**
	 * 删除单个文件
	 * @param   sPath    被删除文件的文件名
	 * @return 单个文件删除成功返回true，否则返回false
	 */
	public static boolean deleteFile(String sPath) {
		boolean flag = false;
		File  file = new File(sPath);
		// 路径为文件且不为空则进行删除
		if (file.isFile() && file.exists()) {
			file.delete();
			flag = true;
		}
		return flag;
	}
	//校验文件路径[去除不必要的文件]
	public static String checkPath(String path){
		if(StringUtil.isEmpty(path)){
			return path;
		}
		do {
			//替换文件路径中多余的 杠
			path=path.replace("\\", "/");
			path=path.replace("//", "/");
			path=path.replace("\\/", "/");
			path=path.replace("/\\", "/");
		} while (path.contains("//"));
		return path;
	}
	//校验文件路径[去除不必要的文件]

	/**
	 * 删除目录（文件夹）以及目录下的文件
	 * @param   sPath 被删除目录的文件路径
	 * @return  目录删除成功返回true，否则返回false
	 */
	public static boolean deleteDirectory(String sPath) {
		//如果sPath不以文件分隔符结尾，自动添加文件分隔符
		if (!sPath.endsWith(File.separator)) {
			sPath = sPath + File.separator;
		}
		File dirFile = new File(sPath);
		//如果dir对应的文件不存在，或者不是一个目录，则退出
		if (!dirFile.exists() || !dirFile.isDirectory()) {
			return false;
		}
		boolean flag = false;
		//删除文件夹下的所有文件(包括子目录)
		File[] files = dirFile.listFiles();
		for (int i = 0; i < files.length; i++) {
			//删除子文件
			if (files[i].isFile()) {
				flag = deleteFile(files[i].getAbsolutePath());
				if (!flag) break;
			} //删除子目录
			else {
				flag = deleteDirectory(files[i].getAbsolutePath());
				if (!flag) break;
			}
		}
		if (!flag) return false;
		//删除当前目录
		if (dirFile.delete()) {
			return true;
		} else {
			return false;
		}
	}
	/**
	 * 
	 * @Title: createFilePath 
	 * @Description: 创建文件路径
	 * @param path
	 */
	public static void createFilePath(String path){
		//判断空
		if(StringUtil.isEmpty(path)){
			return ;
		}
		//判断是否有/
		if(path.contains(CommonAttribute.PATH_DEFAULT_SPLIT_CHAR)){
			path=path.substring(0, path.lastIndexOf(CommonAttribute.PATH_DEFAULT_SPLIT_CHAR));
		}
		//new file 
		File f=new File(path);
		//不存在创建文件夹
		if(!f.exists()){
			f.mkdirs();
		}
	}
}
