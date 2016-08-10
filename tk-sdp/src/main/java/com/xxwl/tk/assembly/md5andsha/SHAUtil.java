package com.xxwl.tk.assembly.md5andsha;

import java.security.MessageDigest;

/**
 * 采用SHAA加密
 * @author deng
 *
 */
public class SHAUtil {
	
	public static String strMove(String inStr){
		if(inStr==null ||inStr.trim().length()<4){
			return inStr;
		}
		char[] charArray = inStr.toCharArray();
		StringBuilder sb=new StringBuilder(charArray[1]);
		sb.append(charArray[charArray.length-1]);
		StringBuilder sbtemp=new StringBuilder();
		for (int i = 1; i < charArray.length-1; i++) {
			if(i%5==0){
				sbtemp.append(charArray[i]);
				continue;
			}
			sb.append(charArray[i]);
		}
		sb.append(charArray[0]);
		sb.append(sbtemp);
		return sb.toString();
	}
	
	public static String strAdd(String inStr){
		if(inStr==null ||inStr.trim().length()<4){
			return inStr;
		}
		char[] charArray = inStr.toCharArray();
		int num=0;
		for (int i = 0; i < charArray.length-1; i++) {
			if(i%2==0){
				num+=charArray[i]-charArray[i+1];
			}else{
				num+=charArray[i]+charArray[i+1];
			}
		}
		return String.valueOf(num);
	}
    /*** 
     * SHA加密 生成40位SHA码
     * @param 待加密字符串
     * @return 返回40位SHA码
     */
    public static String shaEncode(String inStr) throws Exception {
    	inStr=SHAUtil.strMove(inStr);
        MessageDigest sha = null;
        try {
            sha = MessageDigest.getInstance("SHA");
        } catch (Exception e) {
            System.out.println(e.toString());
            e.printStackTrace();
            return "";
        }

        byte[] byteArray = inStr.getBytes("UTF-8");
        byte[] md5Bytes = sha.digest(byteArray);
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
        String str = new String("amigoxiexiexingxing");
        System.out.println("原始：" + str);
        System.out.println("SHA后：" + shaEncode(str));
    }
}