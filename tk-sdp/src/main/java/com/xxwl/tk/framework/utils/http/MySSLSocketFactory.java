/**
* <p>Title: MySSLSocketFactory.java</p>
* <p>Description: </p>
* <p>Copyright: Copyright (c) 2014</p>
* <p>Company: </p>
* @author xuefeng.gao
* @date 2014年12月30日
* @version 1.0
*/
package com.xxwl.tk.framework.utils.http;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;

import org.apache.http.conn.ssl.SSLSocketFactory;

/**
 * <p>Title: MySSLSocketFactory</p>
 * <p>Description: </p>
 * <p>Company:</p>
 * @author xuefeng.gao
 * @date 2014年12月30日
 */
public class MySSLSocketFactory extends SSLSocketFactory{
	 static{
	  mySSLSocketFactory = new MySSLSocketFactory(createSContext());
	 }
	 private static MySSLSocketFactory mySSLSocketFactory = null;
	 private static SSLContext createSContext(){
	  SSLContext sslcontext = null;
	  try {
	   sslcontext = SSLContext.getInstance("SSL");
	  } catch (NoSuchAlgorithmException e) {
	   e.printStackTrace();
	  }
	  try {
	   sslcontext.init(null, new TrustManager[]{new TrustAnyTrustManager()}, null);
	  } catch (KeyManagementException e) {
	   e.printStackTrace();
	   return null;
	  }
	  return sslcontext;
	 }
	 
	 @SuppressWarnings("deprecation")
	 private MySSLSocketFactory(SSLContext sslContext) {
	  super(sslContext);
	  this.setHostnameVerifier(ALLOW_ALL_HOSTNAME_VERIFIER);
	 }
	 
	 public static MySSLSocketFactory getInstance(){
	  if(mySSLSocketFactory != null){
	   return mySSLSocketFactory;
	  }else{
	   return mySSLSocketFactory = new MySSLSocketFactory(createSContext());
	  }
	 }
	 
	} 