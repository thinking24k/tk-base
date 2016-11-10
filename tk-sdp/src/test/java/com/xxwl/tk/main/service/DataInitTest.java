package com.xxwl.tk.main.service;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.xxwl.tk.attachment.service.AttachmentService;
import com.xxwl.tk.attachment.utils.FileUtil;
import com.xxwl.tk.main.entity.PicEntity;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:spring/*.xml")
public class DataInitTest {
	@Resource
	private PicService  picService;
	@Resource
	private AttachmentService  attachmentService;
	
	@Test
	public final void InitTest() {
		String filepath="F:/bqsolo/Extracted",localSave="E:/apache-tomcat-7.0.54/webapps/tk-attachment/attachment/";
		Set<String> inImgsSet=new HashSet<String>(Arrays.asList("jpg","jepg","png","bmp","gif","JPG","JEPG","PNG","BMP","GIF"));
		
		List<PicEntity> lists=new ArrayList<PicEntity>();
		File file=new File(filepath);
		if(file.isFile()){
			//读取
			PicEntity e = saveFile(localSave, inImgsSet, lists, file);
			lists.add(e);
		}else{
			File[] listFiles = file.listFiles();
			for (int i = 0; i < listFiles.length; i++) {
				if(i>20){//
					break ;
				}
				File file2 = listFiles[i];
				PicEntity e = saveFile(localSave, inImgsSet, lists, file2);
				e.setGroupid(i%5<=0?1:i%5);
				System.out.println(e);
				lists.add(e);
			}
		}
		System.out.println(lists.size());
		picService.doAddBatch(lists);
		
	}

	private PicEntity saveFile(String localSave, Set<String> inImgsSet,
			List<PicEntity> lists, File file){
		try{
			String fileSuffix=file.getName().substring(file.getName().lastIndexOf(".")+1, file.getName().length());
			if(!inImgsSet.contains(fileSuffix)){
				return null;
			}
			String relativePath = attachmentService.getRelativePath("pic");
			String fileName=attachmentService.getFileLogicName(file.getName());
			String pathsave=localSave+relativePath;
			//获取文件名
			FileUtil.checkPath(pathsave);
			FileUtil.createFilePath(pathsave);
			FileUtil.copyFileUsingFileChannels(file, new File(pathsave+fileName));
			PicEntity e=new PicEntity();
			e.setPath(relativePath+fileName);
			//文件MD5
			String md5Hex = DigestUtils.md5Hex(new FileInputStream(file));
			e.setMD5(md5Hex);
			return e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	

	
}
