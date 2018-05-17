package com.sky.skyframe1.asynctask.remote.base;

import android.text.TextUtils;

import com.alibaba.fastjson.JSON;

import okhttp3.Response;

/**
 * JSON格式解析
 * 
 * @author WebUser
 * 
 * @param <T>
 */
public abstract class JsonCallBack<T> extends CommonCallBack<T> {

	private Class<?> clazz;

	public JsonCallBack(Class<?> clazz) {
		this.clazz = clazz;
	}

	@SuppressWarnings("unchecked")
	@Override
	public T parseNetworkResponse(Response response) throws Exception {
		String responseData = response.body().string();
		if (TextUtils.isEmpty(responseData)) {
			return null;
		}
		if (clazz != null) {
			return (T) JSON.parseObject(responseData, clazz);
		}
		return null;
	}

	/***
	 * 错误描述
	 * 
	 * @param method
	 * @param statusCode
	 * @param message
	 */
	abstract public void showErrorWith(String method, int statusCode,
			String message);
}
