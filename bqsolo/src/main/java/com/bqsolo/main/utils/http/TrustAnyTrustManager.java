/**
* <p>Title: TrustAnyTrustManager.java</p>
* <p>Description: </p>
* <p>Copyright: Copyright (c) 2014</p>
* <p>Company: </p>
* @author xuefeng.gao
* @date 2014年12月30日
* @version 1.0
*/
package com.bqsolo.main.utils.http;
/**
 * 这个是一个证书管理器和下面的MyX509TrustManager类是一样的
 * @author credream
 *
 */
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import javax.net.ssl.X509TrustManager;

public class TrustAnyTrustManager implements X509TrustManager{

 public void checkClientTrusted(X509Certificate[] chain, String authType)
   throws CertificateException {
  
 }

 public void checkServerTrusted(X509Certificate[] chain, String authType)
   throws CertificateException {
  
 }

 public X509Certificate[] getAcceptedIssuers() {
  return null;
 }

} 
