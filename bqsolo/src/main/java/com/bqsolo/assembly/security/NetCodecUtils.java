package com.bqsolo.assembly.security;

import com.google.gson.Gson;

/**
 * 网络传输编码
 * 
 */
/**
 * @author deng
 * 
 */
public class NetCodecUtils {

	/**
	 * 传输加密
	 * 
	 * @param src
	 *            待加密对象
	 * @return 加密之后的字符串
	 */
	public static String encrypt(Object src) {
		try {
			Gson gson = new Gson();
			String str = gson.toJson(src);
			byte[] encryptResult = DESCodecUtils.encrypt(str, "utf-8");
			String encryptResultStr = EncodeUtils
					.parseByte2HexStr(encryptResult);
			// System.out.println("加密后===>" + encryptResultStr);
			return encryptResultStr;
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return "";
	}

	/**
	 * 传输解码
	 * 
	 * @param src
	 *            json格式
	 * @param c
	 *            转换的类
	 * @return 类对象
	 */
	public static Object decryption(String src, Class<?> c) {

		try {
			byte[] decryptFrom = EncodeUtils.parseHexStr2Byte(src);
			byte[] decryptResult = DESCodecUtils.decrypt(decryptFrom);
			Gson gson = new Gson();
			String outputGBK = new String(decryptResult, "utf-8");
			// LogUtils.d("outputGBK=" + outputGBK);
			// System.out.println("解密后===>" + outputGBK);
			return gson.fromJson(outputGBK, c);
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return "";
	}

	/**
	 * 传输解码
	 * 
	 * @param src
	 *            json格式
	 * @param c
	 *            转换的类
	 * @return 类对象
	 */
	public static Object decryption(String src, Class<?> c, String encoding) {

		try {
			byte[] decryptFrom = EncodeUtils.parseHexStr2Byte(src);
			byte[] decryptResult = DESCodecUtils.decrypt(decryptFrom);
			Gson gson = new Gson();
			String outputGBK = new String(decryptResult, encoding);
			// LogUtils.d("outputGBK=" + outputGBK);
			// System.out.println("解密后===>" + outputGBK);
			return gson.fromJson(outputGBK, c);
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return "";
	}

	public static void main(String[] args) {
		try {
			String result = "2E99560689D7B8592D8C37678E515F6BBCA4BFE1DA8CA45C043FDB4C200F56A74AD60F0F731C0FCD6A39BCB098722AA817EDCEE4CD51F5976CA71A86ACF07DC8745E1793DD8B6DC0037F73BB4FDA7B0AC0AC245D4C2D2299A12180B2B7B86409C9328612052C11A2B6CEE80F56D8622A13E058ECC3A3F8813FA0B0426F30716BBE39BD2C469E41DF912617584E84F0666D446F0F742278BEF697A38B3FF41BBCF755DF315F65FD97F3E7AFC83B09EB0B4C616BD4D11DE5D9A90466FEB3A4B8C41F61BE30AAC912A19B6DEFEDB90358E0E8AD3D0A2BB52E3D10DBB9A42EE5EC47D4EFB1FADE4F09C465F41B129442175BF87AB1669608277B8B97CA9B23568B4601CC4487DB2EE28FE0322F6B8785A4A49BFFB8ADEF3437C3886C775248A259B90357E8C138A29BD801765F3460BCE78A572EFAA86EB2B2BF0F4A72178DD568BD97456524C7CB74C58D77F25AFC65F69067FFF6E18D4313243FED8BD6C4E7491CF2AFB4D8BFD0F855D485F977D0F2036A78CD4916B6DDEDCBFBF3D82DC344D9F72437F2B948F72937D06FC48CA945A1CE6E53CB4B9C2658AEADC8DC78D78FE58A88A026DE0EAA3C53E6442003615C34D660F8656C7AA4CA370709EC9A7B0EAC2B5E902F7843C7A3E729DDE3199D2A16E4C9718176543B61B71384C572FA6C7FCBF3853BE321A54C5EF87BC99A2050D7D40C59B46D331569F4A14F2FBCFC93187CC333298AA6CD0216429F9310F8BD8E6C1384C572FA6C7FCBCFB781101D525A3303698E70191BC661F377BDF22847D6D7BA48EFEEB205FCE3EA1F467A7F8D0DD468FA1A4F9B0D124E0B78348508ACA3651B51964BCD7622094A640DFA1B61EA3DD02FCDF7721C1D28B24D6E03025097A21AD368425AB1745D1BA71CEAC27101B29508E593868758227AFE21B24ACD75C25F0F146EE45D63ADCDC6B751796255F08E77C77C842B5E6F";
			byte[] decryptFrom = EncodeUtils.parseHexStr2Byte(result);
			byte[] decryptResult = DESCodecUtils.decrypt(decryptFrom);
			String outputGBK = new String(decryptResult, "utf-8");
			System.out.println(outputGBK);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}
