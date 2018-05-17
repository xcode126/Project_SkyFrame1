package com.sky.skyframe1.asynctask.remote.base;

import android.app.Activity;
import android.content.Context;

import com.sky.skyframe1.asynctask.ApiRequestListener;
import com.sky.skyframe1.bean.BaseResponse;
import com.sky.util.LogUtils;

import java.net.UnknownHostException;

import okhttp3.Call;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 默认解析CallBack
 * 
 * @author WebUser
 * 
 */
public class DefaultCallBack extends JsonCallBack<Object> {

	private ApiRequestListener apiRequestListener;
	private Context context;
	private String callMethod;

	public DefaultCallBack(Context context, Class<?> clazz, String method,
                           ApiRequestListener apiRequestListener) {
		super(clazz);
		this.context = context;
		this.callMethod = method;
		this.apiRequestListener = apiRequestListener;
	}

	@Override
	public void showErrorWith(String method, int statusCode, String message) {
		apiRequestListener.onError(method, statusCode, message);
	}

	@Override
	public void onResponse(boolean isFromCache, Object data, Request request,
			Response response) {
		if (context instanceof Activity && ((Activity) context).isFinishing()) {
			// 页面已经被关闭或者页面没关闭，但是目前显示页面不是该接口页面，无须进行后续处理
			return;
		}
		if (apiRequestListener != null) {
			// 数据解析
			if (data != null) {
				BaseResponse model = (BaseResponse) data;
				if (!model.isStatus()) {
					if (model.isShow()) {
						showErrorWith(callMethod, BUSSINESS_ERROR,
								model.getErrorMsg());
					} else {
						showErrorWith(callMethod, BUSSINESS_ERROR, "暂无数据");
					}
				} else {
					apiRequestListener.onSuccess(callMethod, data);
				}
			} else {
				if (LogUtils.isDebug) {
					showErrorWith(callMethod, BUSSINESS_ERROR, "数据异常,请联系管理员");
				} else {
					showErrorWith(callMethod, BUSSINESS_ERROR, "数据异常");
				}
			}
		}
	}

	@Override
	public void onError(boolean isFromCache, Call call, Response response,
                        Exception e) {
		super.onError(isFromCache, call, response, e);
		if (context instanceof Activity && ((Activity) context).isFinishing()) {
			// 页面已经被关闭或者页面没关闭，但是目前显示页面不是该接口页面，无须进行后续处理
			return;
		}
		if (apiRequestListener != null) {
			// 回调返回数据
			if (e instanceof UnknownHostException) {
				if (LogUtils.isDebug) {
					e.printStackTrace();
				}
				showErrorWith(callMethod, TIMEOUT_ERROR, "请检查网络是否可用");
			} else {
				if (LogUtils.isDebug) {
					showErrorWith(callMethod, TIMEOUT_ERROR, e.getMessage());
				} else {
					showErrorWith(callMethod, TIMEOUT_ERROR, "数据异常");
				}
			}
		}
	}

}
