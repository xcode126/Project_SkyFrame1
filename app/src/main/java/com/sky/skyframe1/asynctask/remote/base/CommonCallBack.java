package com.sky.skyframe1.asynctask.remote.base;

import com.lzy.okhttputils.callback.AbsCallback;

/**
 * 公共CallBack
 * 
 * @author WebUser
 * 
 * @param <T>
 */
public abstract class CommonCallBack<T> extends AbsCallback<T> {
	
	/**
	 * 超时（网络）异常
	 */
	public static final int TIMEOUT_ERROR = 600;
	/**
	 * 业务异常
	 */
	public static final int BUSSINESS_ERROR = 610;

}
