package com.sky.skyframe1.asynctask.remote.base;

import android.app.Activity;
import android.content.Context;

import com.lzy.okhttputils.request.BaseRequest;
import com.sky.skyframe1.asynctask.ApiUpLoadListener;
import com.sky.skyframe1.bean.BaseResponse;
import com.sky.util.LogUtils;

import java.net.UnknownHostException;

import okhttp3.Call;
import okhttp3.Request;
import okhttp3.Response;

public class UpLoadFileCallBack extends JsonCallBack<BaseResponse> {

	private ApiUpLoadListener apiUpLoadListener;
	private Context context;
	private String callMethod;

	public UpLoadFileCallBack(Context context, String method,
                              ApiUpLoadListener apiUpLoadListener) {
		super(BaseResponse.class);
		this.apiUpLoadListener = apiUpLoadListener;
		this.context = context;
		this.callMethod = method;
	}

	@Override
	public void showErrorWith(String method, int statusCode, String message) {
		apiUpLoadListener.onError(method, statusCode, message);
	}

	@Override
	public void onResponse(boolean isFromCache, BaseResponse data,
                           Request request, Response response) {
		// 完成是回调
		if (context instanceof Activity && ((Activity) context).isFinishing()) {
			// 页面已经被关闭或者页面没关闭，但是目前显示页面不是该接口页面，无须进行后续处理
			return;
		}
		if (apiUpLoadListener != null) {
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
					apiUpLoadListener.onSuccess(callMethod, "上传成功");
				}
			} else {
				showErrorWith(callMethod, BUSSINESS_ERROR, "上传失败");
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
		if (apiUpLoadListener != null) {
			// 回调返回数据
			if (e instanceof UnknownHostException) {
				if (LogUtils.isDebug) {
					e.printStackTrace();
				}
				showErrorWith(callMethod, TIMEOUT_ERROR, "请检查网络是否可用");
			} else {
				showErrorWith(callMethod, TIMEOUT_ERROR, "数据异常");
			}
		}
	}

	@Override
	public void onBefore(@SuppressWarnings("rawtypes") BaseRequest request) {
		super.onBefore(request);
		//  上传前提示
		if (apiUpLoadListener != null) {
			apiUpLoadListener.onUpLoadBefore();
		}
	}

	@Override
	public void upProgress(long currentSize, long totalSize, float progress,
			long networkSpeed) {
		super.upProgress(currentSize, totalSize, progress, networkSpeed);
		if (context instanceof Activity && ((Activity) context).isFinishing()) {
			// 页面已经被关闭或者页面没关闭，但是目前显示页面不是该接口页面，无须进行后续处理
			return;
		}
		// 上传进度
		if (apiUpLoadListener != null) {
			apiUpLoadListener.onProgress(currentSize, totalSize, progress,
					networkSpeed);
		}
	}

}
