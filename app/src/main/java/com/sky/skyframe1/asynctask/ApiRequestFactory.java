package com.sky.skyframe1.asynctask;

import com.lzy.okhttputils.model.HttpHeaders;
import com.lzy.okhttputils.model.HttpParams;
import com.sky.util.LogUtils;

import java.io.File;
import java.util.Map;

/***
 * 请求参数设置工具类
 * 
 * @author WebUser
 * 
 */
public class ApiRequestFactory {

	/**
	 * 获取API HTTP 请求内容
	 * 
	 * @param (params-- 请求参数)
	 * @return 处理完成的请求内容
	 */
	public static HttpParams getRequestParams(Map<String, Object> params) {
		if (params == null) {
			return null;
		}
		HttpParams requestParams = new HttpParams();
		try {
			for (String key : params.keySet()) {
				Object object = params.get(key);
				if (object instanceof File) {
					File file = (File) object;
					requestParams.put(key, file);
				} else {
					String valuse = String.valueOf(params.get(key));
					requestParams.put(key, valuse);
				}

			}
		} catch (Exception e) {
			if (LogUtils.isDebug) {
				e.printStackTrace();
			}
		}
		return requestParams;

	}

	/**
	 * 设置header信息
	 * 
	 * @param headerValues
	 * @return
	 */
	public static HttpHeaders setRequestHeader(Map<String, String> headerValues) {
		HttpHeaders headers = null;
		if (headerValues != null && !headerValues.isEmpty()) {
			headers = new HttpHeaders();
			for (String headerKey : headerValues.keySet()) {
				headers.put(headerKey, headerValues.get(headerKey));
			}
		}
		return headers;
	}

}
