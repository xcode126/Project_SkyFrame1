package com.sky.skyframe1.asynctask;

import android.content.Context;

import com.lzy.okhttputils.OkHttpUtils;
import com.lzy.okhttputils.callback.AbsCallback;
import com.lzy.okhttputils.model.HttpHeaders;
import com.lzy.okhttputils.model.HttpParams;
import com.sky.skyframe1.asynctask.remote.base.CommonCallBack;
import com.sky.skyframe1.asynctask.remote.base.DefaultCallBack;
import com.sky.skyframe1.asynctask.remote.base.JsonCallBack;
import com.sky.util.ApiInterfaceTool;
import com.sky.util.LogUtils;
import com.sky.util.NetWorkUtils;

import java.io.Serializable;
import java.net.UnknownHostException;
import java.util.Map;

/**
 * 请求格式
 * 
 * @author WebUser
 * 
 */
enum HttpRequestMethod {
	/** Get请求 */
	Get,
	/** Post请求 */
	Post
}

/**
 * 网络请求工具类 2016/5/12
 */
public class ApiAsyncTask {

	private static ApiAsyncTask apiAsyncTask;

	/** 请求相关设置 */
	private RequestParamsModel requestParamsModel;

	public static ApiAsyncTask getInstance() {
		if (apiAsyncTask == null) {
			apiAsyncTask = new ApiAsyncTask();
		}
		return apiAsyncTask;
	}

	/***
	 *  取消请求
	 * 
	 * @param context
	 */
	public void onCancelRequest(Context context) {
		OkHttpUtils.getInstance().cancelTag(context);
	}

	/**
	 * HTTP请求集合(请求前必须设置请求体、请求头(可选)、回调、解析Tool)
	 * 
	 * @param method
	 * @param url
	 */
	public void doRequestWithMethod(HttpRequestMethod method, String url) {
		switch (method) {
		case Get:
			// Get请求
			try {
				onGet(url);
			} catch (ClassNotFoundException e) {
				if (LogUtils.isDebug) {
					e.printStackTrace();
				}
				CheckCallbackClass();
			}
			break;
		case Post:
			// Post请求
			try {
				onPost(url);
			} catch (ClassNotFoundException e) {
				if (LogUtils.isDebug) {
					e.printStackTrace();
				}
				CheckCallbackClass();
			}
			break;
		}
	}

	/**
	 * HTTP请求集合
	 * 
	 * @param method
	 * @param context
	 * @param url
	 * @param (requestParam -- 请求体)
	 * @param (requestHeaders -- 添加头部信息)
	 * @param (callback-- 回调)
	 */
	public void doRequestWithMethod(HttpRequestMethod method, Context context,
			String url, Map<String, Object> requestParam,
			Map<String, String> requestHeaders, AbsCallback<?> callback) {
		if (getRequestParamsModel() == null) {
			requestParamsModel = new RequestParamsModel();
		}
		requestParamsModel.setContext(context);
		requestParamsModel.setBodyParamters(requestParam);
		requestParamsModel.setRequestHeaders(requestHeaders);
		requestParamsModel.setCallback(callback);
		switch (method) {
		case Get:
			// Get请求
			try {
				onGet(url);
			} catch (ClassNotFoundException e) {
				if (LogUtils.isDebug) {
					e.printStackTrace();
				}
				CheckCallbackClass();
			}
			break;
		case Post:
			// Post请求
			try {
				onPost(url);
			} catch (ClassNotFoundException e) {
				if (LogUtils.isDebug) {
					e.printStackTrace();
				}
				CheckCallbackClass();
			}
			break;
		}
	}

	/**
	 * Post请求
	 * 
	 * @param url
	 */
	private void onPost(String url) throws ClassNotFoundException {
		if (checkNetWork()) {
			return;
		}
		HttpParams requestParam = ApiRequestFactory
				.getRequestParams(requestParamsModel.getBodyParamters());
		HttpHeaders requestHeader = ApiRequestFactory
				.setRequestHeader(requestParamsModel.getRequestHeaders());
		if (requestHeader != null && requestParam != null) {
			OkHttpUtils.post(url).tag(requestParamsModel.getContext())
					.headers(requestHeader).params(requestParam)
					.execute(checkCallBackParams());
		} else if (requestHeader == null && requestParam != null) {
			OkHttpUtils.post(url).tag(requestParamsModel.getContext())
					.params(requestParam).execute(checkCallBackParams());
		} else {
			OkHttpUtils.post(url).tag(requestParamsModel.getContext())
					.execute(checkCallBackParams());
		}
	}

	/**
	 * Get请求
	 * 
	 * @param url
	 */
	private void onGet(String url) throws ClassNotFoundException {
		if (checkNetWork()) {
			return;
		}
		HttpParams requestParam = ApiRequestFactory
				.getRequestParams(requestParamsModel.getBodyParamters());
		HttpHeaders requestHeader = ApiRequestFactory
				.setRequestHeader(requestParamsModel.getRequestHeaders());
		if (requestHeader != null && requestParam != null) {
			OkHttpUtils.get(url).tag(requestParamsModel.getContext())
					.headers(requestHeader).params(requestParam)
					.execute(checkCallBackParams());
		} else if (requestHeader == null && requestParam != null) {
			OkHttpUtils.get(url).tag(requestParamsModel.getContext())
					.params(requestParam).execute(checkCallBackParams());
		} else {
			OkHttpUtils.get(url).tag(requestParamsModel.getContext())
					.execute(checkCallBackParams());
		}
	}

	/**
	 * 请求返回参数检测
	 * 
	 * @return
	 */
	private AbsCallback<?> checkCallBackParams() throws ClassNotFoundException {
		if (requestParamsModel.getCallback() == null) {
			Class<?> clazz = Class.forName(requestParamsModel
					.getApiInterfaceTool().getDefaultValue().toString());
			requestParamsModel.setCallback(new DefaultCallBack(
					requestParamsModel.getContext(), clazz, requestParamsModel
							.getApiInterfaceTool().getId(), requestParamsModel
							.getApiRequestListener()));
		}
		return requestParamsModel.getCallback();
	}

	/**
	 * 检测回调类
	 */
	private void CheckCallbackClass() {
		JsonCallBack<?> jsonCallBack = (JsonCallBack<?>) requestParamsModel
				.getCallback();
		if (jsonCallBack == null) {
			requestParamsModel.getApiRequestListener().onError(null,
					CommonCallBack.BUSSINESS_ERROR,
					"设置回调类错误,检查ApiInterfaceTool对应的设置");
		} else {
			jsonCallBack.showErrorWith(null, CommonCallBack.BUSSINESS_ERROR,
					"设置回调类错误,检查ApiInterfaceTool对应的设置");
		}

	}

	/**
	 * 检查网络状态
	 */
	private boolean checkNetWork() {
		if (!NetWorkUtils.isNetworkAvailable(requestParamsModel.getContext())) {
			JsonCallBack<?> jsonCallBack = (JsonCallBack<?>) requestParamsModel
					.getCallback();
			if (jsonCallBack == null) {
				try {
					jsonCallBack = (JsonCallBack<?>) checkCallBackParams();
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
			}
			jsonCallBack.onError(false, null, null, new UnknownHostException());
			return true;
		}
		return false;
	}

	// ============Getter、 Setter========================================
	public RequestParamsModel getRequestParamsModel() {
		if (requestParamsModel == null) {
			requestParamsModel = new RequestParamsModel();
		}
		requestParamsModel.setCallback(null);
		return requestParamsModel;
	}

	public void setRequestParamsModel(RequestParamsModel requestParamsModel) {
		this.requestParamsModel = requestParamsModel;
	}

	// ===========请求参数回调Model==============================================
	class RequestParamsModel implements Serializable {

		private static final long serialVersionUID = 1L;
		private Context context;
		/** 回调解析 */
		private AbsCallback<?> callback;
		/** 请求接口类型、解析Model设置 */
		private ApiInterfaceTool apiInterfaceTool;
		/** 回调 */
		private ApiRequestListener apiRequestListener;
		/** 请求参数 */
		private Map<String, Object> bodyParamters;
		/** 请求头部 */
		private Map<String, String> requestHeaders;

		public Context getContext() {
			return context;
		}

		public void setContext(Context context) {
			this.context = context;
		}

		public AbsCallback<?> getCallback() {
			return callback;
		}

		public void setCallback(AbsCallback<?> callback) {
			this.callback = callback;
		}

		public ApiInterfaceTool getApiInterfaceTool() {
			return apiInterfaceTool;
		}

		public void setApiInterfaceTool(ApiInterfaceTool apiInterfaceTool) {
			this.apiInterfaceTool = apiInterfaceTool;
		}

		public ApiRequestListener getApiRequestListener() {
			return apiRequestListener;
		}

		public void setApiRequestListener(ApiRequestListener apiRequestListener) {
			this.apiRequestListener = apiRequestListener;
		}

		public Map<String, Object> getBodyParamters() {
			return bodyParamters;
		}

		public void setBodyParamters(Map<String, Object> bodyParamters) {
			this.bodyParamters = bodyParamters;
		}

		public Map<String, String> getRequestHeaders() {
			return requestHeaders;
		}

		public void setRequestHeaders(Map<String, String> requestHeaders) {
			this.requestHeaders = requestHeaders;
		}
	}
}
