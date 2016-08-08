package com.xxwl.tk.assembly.md5andsha;

import java.security.MessageDigest;

/**
 * MD5加密 
 * @author deng
 *
 */
public class MD5Util {
	/*** 
	 * MD5加密 生成32位md5码
	 * @param 待加密字符串
	 * @return 返回32位md5码
	 */
	public static String md5Encode(String inStr)  {
		inStr=SHAUtil.strMove(inStr);
		MessageDigest md5 = null;
		byte[] byteArray=null;
		try {
			md5 = MessageDigest.getInstance("MD5");
			byteArray = inStr.getBytes("UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
		byte[] md5Bytes = md5.digest(byteArray);
		StringBuffer hexValue = new StringBuffer();
		for (int i = 0; i < md5Bytes.length; i++) {
			int val = ((int) md5Bytes[i]) & 0xff;
			if (val < 16) {
				hexValue.append("0");
			}
			hexValue.append(Integer.toHexString(val));
		}
		return hexValue.toString();
	}

	/**
	 * 测试主函数
	 * @param args
	 * @throws Exception
	 */
	public static void main(String args[]) throws Exception {
		String str = new String("dendsfsfsfdsfspuipdfsdddddddpdddddgfgfiuoddddddfsdfdddddddddddddfsfsdddddddddddd");
		System.out.println("原始：" + str);
		System.out.println("MD5后：" + md5Encode(str));
		System.out.println("移动:"+SHAUtil.strMove(str));
		System.out.println("计算:"+SHAUtil.strAdd(str));
	}
}