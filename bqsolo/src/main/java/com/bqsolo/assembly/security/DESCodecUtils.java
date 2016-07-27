package com.bqsolo.assembly.security;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

/**
 * DES加密解码
 * 
 */
/**
 * @author deng
 *
 */
public class DESCodecUtils {

	public static final String KEY = "HK6Jy6Orflio9sg6WD/rQIZfNeFn+nrrXQCTfN7Cv3cAADoAAAAAAOPCv3cAAAAAAM35CFzU/wsIAAAAVNT/C8LWWgwUdkfiyelksa+GrWgzEwXwDlFzAd3AgvncAAAAAZAYAAK8nVQwUwXwDZ00o0OIl1aBn0/8LXNT/C9PAWgwUwXwDQAAAAATBfAMJTRN4SLuBCnzBfAMAAAAAEAAAAAAAAAAAAAAAAAAAAAAAAABEWnGJorzX8xAuTW1+kKO3zOL5ESpEX3uYttX1BhgrP1RqgZmyzOcDID5dfY6gs8fc8gkhOlRvi6jG5QUWKDtPO7f8CyC1YQxQvmEMAAAAAASCwBckn0F6gAAAAGjT/ws=";

	/**
	 * 加密方法
	 * 
	 * @param rawKeyData
	 * @param str
	 * @return
	 * @throws InvalidKeyException
	 * @throws NoSuchAlgorithmException
	 * @throws IllegalBlockSizeException
	 * @throws BadPaddingException
	 * @throws NoSuchPaddingException
	 * @throws InvalidKeySpecException
	 */
	public static byte[] encrypt(String str) throws InvalidKeyException,
			NoSuchAlgorithmException, IllegalBlockSizeException,
			BadPaddingException, NoSuchPaddingException,
			InvalidKeySpecException {
		// DES算法要求有一个可信任的随机数源
		SecureRandom sr = new SecureRandom();
		// 从原始密匙数据创建一个DESKeySpec对象
		DESKeySpec dks = new DESKeySpec(KEY.getBytes());
		// 创建一个密匙工厂，然后用它把DESKeySpec转换成一个SecretKey对象
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
		SecretKey key = keyFactory.generateSecret(dks);
		// Cipher对象实际完成加密操作
		Cipher cipher = Cipher.getInstance("DES");
		// 用密匙初始化Cipher对象
		cipher.init(Cipher.ENCRYPT_MODE, key, sr);
		// 现在，获取数据并加密
		byte data[] = str.getBytes();
		// 正式执行加密操作
		byte[] encryptedData = cipher.doFinal(data);

		//System.out.println("加密后===>" + encryptedData);
		return encryptedData;
	}
	
	/**
	 * 加密方法
	 * 
	 * @param rawKeyData
	 * @param str
	 * @return
	 * @throws InvalidKeyException
	 * @throws NoSuchAlgorithmException
	 * @throws IllegalBlockSizeException
	 * @throws BadPaddingException
	 * @throws NoSuchPaddingException
	 * @throws InvalidKeySpecException
	 */
	public static byte[] encrypt(String str, String encoding) throws InvalidKeyException,
			NoSuchAlgorithmException, IllegalBlockSizeException,
			BadPaddingException, NoSuchPaddingException,
			InvalidKeySpecException,  UnsupportedEncodingException {
		// DES算法要求有一个可信任的随机数源
		SecureRandom sr = new SecureRandom();
		// 从原始密匙数据创建一个DESKeySpec对象
		DESKeySpec dks = new DESKeySpec(KEY.getBytes());
		// 创建一个密匙工厂，然后用它把DESKeySpec转换成一个SecretKey对象
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
		SecretKey key = keyFactory.generateSecret(dks);
		// Cipher对象实际完成加密操作
		Cipher cipher = Cipher.getInstance("DES");
		// 用密匙初始化Cipher对象
		cipher.init(Cipher.ENCRYPT_MODE, key, sr);
		// 现在，获取数据并加密
		byte data[] = str.getBytes(encoding);
		// 正式执行加密操作
		byte[] encryptedData = cipher.doFinal(data);

		//System.out.println("加密后===>" + encryptedData);
		return encryptedData;
	}

	/**
	 * 解密方法
	 * 
	 * @param rawKeyData
	 * @param encryptedData
	 * @throws IllegalBlockSizeException
	 * @throws BadPaddingException
	 * @throws InvalidKeyException
	 * @throws NoSuchAlgorithmException
	 * @throws NoSuchPaddingException
	 * @throws InvalidKeySpecException
	 */
	public static byte[] decrypt(byte[] encryptedData)
			throws IllegalBlockSizeException, BadPaddingException,
			InvalidKeyException, NoSuchAlgorithmException,
			NoSuchPaddingException, InvalidKeySpecException {
		// DES算法要求有一个可信任的随机数源
		SecureRandom sr = new SecureRandom();
		// 从原始密匙数据创建一个DESKeySpec对象
		DESKeySpec dks = new DESKeySpec(KEY.getBytes());
		// 创建一个密匙工厂，然后用它把DESKeySpec对象转换成一个SecretKey对象
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
		SecretKey key = keyFactory.generateSecret(dks);
		// Cipher对象实际完成解密操作
		Cipher cipher = Cipher.getInstance("DES");
		// 用密匙初始化Cipher对象
		cipher.init(Cipher.DECRYPT_MODE, key, sr);
		// 正式执行解密操作
		byte decryptedData[] = cipher.doFinal(encryptedData);
		//System.out.println("解密后===>" + new String(decryptedData));
		return decryptedData;// new String(decryptedData);
	}

}
