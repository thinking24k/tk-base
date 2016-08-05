package com.bqsolo.framework.domain.context;

import java.io.Serializable;

/**
 * 
 * @ClassName: TerminalContext 
 * @Description: 终端上下文
 * @company 
 * @author yixiang.deng
 * @Email 553067271@qq.com
 * @date 2016年8月5日 
 *
 */
public class TerminalContext implements Serializable {

	private static final long serialVersionUID = 5210905280921969199L;

	// 终端Code
	private String terminalCode;
	// 终端Version
	private String terminalVersion;
	// 保留字段
	private String accessToken;
	// 保留字段
	private String source;

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getTerminalCode() {
		return terminalCode;
	}

	public void setTerminalCode(String terminalCode) {
		this.terminalCode = terminalCode;
	}

	public String getTerminalVersion() {
		return terminalVersion;
	}

	public void setTerminalVersion(String terminalVersion) {
		this.terminalVersion = terminalVersion;
	}

	
}
