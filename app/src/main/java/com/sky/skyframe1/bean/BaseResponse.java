package com.sky.skyframe1.bean;

import java.io.Serializable;

/**
 * 公共服务器返回Model
 * 
 * @author WebUser
 * 
 */
public class BaseResponse implements Serializable {

	private static final long serialVersionUID = 1L;
	/** 请求状态 */
	private boolean status;
	/** 错误标识 */
	private int errorCode;
	/** 错误信息 */
	private String errorMsg;
	/** 是否展示信息给用户查看 */
	private boolean isShow;

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	public boolean isShow() {
		return isShow;
	}

	public void setShow(boolean isShow) {
		this.isShow = isShow;
	}
	
}
