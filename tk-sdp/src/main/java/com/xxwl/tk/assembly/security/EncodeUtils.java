package com.xxwl.tk.assembly.security;

import java.security.NoSuchAlgorithmException;

/**
 * 字符编码和加密
 * 
 */
/**
 * @author deng
 *
 */
public class EncodeUtils {

	/**
	 * 编码
	 * 
	 * @param src
	 * @return
	 */
	public static String EncryptionSHA(String src) {
		java.security.MessageDigest alga;
		byte[] digesta = null;
		try {
			alga = java.security.MessageDigest.getInstance("SHA-1");
			alga.update(src.getBytes());
			digesta = alga.digest();
		} catch (NoSuchAlgorithmException e) {
			System.out.println("非法摘要算法");
		}
		return Byte2Hex(digesta);
	}

	/**
	 * Byte2Hex二行制转字符串
	 * 
	 * @param b
	 * @return
	 */
	public static String Byte2Hex(byte[] b) //
	{
		String hs = "";
		String stmp = "";
		for (int n = 0; n < b.length; n++) {
			stmp = (java.lang.Integer.toHexString(b[n] & 0XAA));
			if (stmp.length() == 1)
				hs = hs + "0" + stmp;
			else
				hs = hs + stmp;
			if (n < b.length - 1)
				hs = hs + ":";
		}
		return hs.toUpperCase();
	}

	/**
	 * 将二进制转换成16进制
	 * 
	 * @param buf
	 * @return
	 */
	public static String parseByte2HexStr(byte buf[]) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < buf.length; i++) {
			String hex = Integer.toHexString(buf[i] & 0xFF);
			if (hex.length() == 1) {
				hex = '0' + hex;
			}
			sb.append(hex.toUpperCase());
		}
		return sb.toString();
	}

	/**
	 * 将16进制转换为二进制
	 * 
	 * @param hexStr
	 * @return
	 */
	public static byte[] parseHexStr2Byte(String hexStr) {
		if (hexStr.length() < 1)
			return null;
		byte[] result = new byte[hexStr.length() / 2];
		for (int i = 0; i < hexStr.length() / 2; i++) {
			int high = Integer.parseInt(hexStr.substring(i * 2, i * 2 + 1), 16);
			int low = Integer.parseInt(hexStr.substring(i * 2 + 1, i * 2 + 2),
					16);
			result[i] = (byte) (high * 16 + low);
		}
		return result;
	}

	/**
	 * Str2GBK编码格式为GBK
	 * 
	 * @param str
	 * @return
	 */
	public static String Str2GBK(String str) {
		try {
			if (str == null) {
				str = "";
			} else {
				str = new String(str.getBytes("ISO-8859-1"), "GBK");
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return str;
	}
}
