package com.sky.skyframe1.asynctask;

/**
 * 上传文件进度回调
 * 
 * @author WebUser
 * 
 */
public interface ApiUpLoadListener {

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

	/**
	 * The CALLBACK for Progress API HTTP response
	 * 
	 * @param currentSize
	 * @param totalSize
	 * @param progress
	 * @param networkSpeed
	 */
	void onProgress(long currentSize, long totalSize, float progress,
                    long networkSpeed);
	
	/**
	 * 上传前回调(适用于弹出或设置进度提示预处理)
	 */
	void onUpLoadBefore();

}
