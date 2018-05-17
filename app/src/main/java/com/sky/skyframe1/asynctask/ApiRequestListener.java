package com.sky.skyframe1.asynctask;

/**
 * API请求监听器 Created by amnice on 16/5/26.
 */
public interface ApiRequestListener {

	/**
	 * The CALLBACK for success API HTTP response
	 * 
	 * @param //(response--HTTP response)
	 */
	void onSuccess(String method, Object result);

	/**
	 * The CALLBACK for failure API HTTP response
	 * 
	 * @param (statusCode--HTTP response status code)
	 */
	void onError(String method, int statusCode, String message);
}
