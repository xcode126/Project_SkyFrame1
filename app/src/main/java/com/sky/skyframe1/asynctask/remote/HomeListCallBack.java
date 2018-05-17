package com.sky.skyframe1.asynctask.remote;

import android.app.Activity;
import android.content.Context;

import com.sky.skyframe1.asynctask.ApiRequestListener;
import com.sky.skyframe1.asynctask.remote.base.JsonCallBack;
import com.sky.skyframe1.bean.httpBean.HomeListResponse;
import com.sky.util.ApiInterfaceTool;
import com.sky.util.LogUtils;

import java.net.UnknownHostException;

import okhttp3.Call;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Author：sky on 2018/5/11 0011 15:05.
 * Email：xcode126@126.com
 * Desc：主页数据解析CallBack
 */

public class HomeListCallBack<T> extends JsonCallBack<T> {

    private ApiRequestListener apiRequestListener;
    private Context context;

    public HomeListCallBack(Context context, Class<T> clazz,
                                ApiRequestListener apiRequestListener) {
        super(clazz);
        this.context = context;
        this.apiRequestListener = apiRequestListener;
    }

    @Override
    public void onResponse(boolean isFromCache, T data, Request request,
                           Response response) {
        if (context instanceof Activity && ((Activity) context).isFinishing()) {
            // 页面已经被关闭或者页面没关闭，但是目前显示页面不是该接口页面，无须进行后续处理
            return;
        }
        if (apiRequestListener != null) {
            // 数据解析
            if (data != null) {
                HomeListResponse model = (HomeListResponse) data;
                if (!model.isStatus()) {
                    if (model.isShow()) {
                        showErrorWith(
                                ApiInterfaceTool.API_LoadHomeListData.getId(),
                                BUSSINESS_ERROR, model.getErrorMsg());
                    } else {
                        showErrorWith(
                                ApiInterfaceTool.API_LoadHomeListData.getId(),
                                BUSSINESS_ERROR, "暂无数据");
                    }
                } else {
                    apiRequestListener.onSuccess(
                            ApiInterfaceTool.API_LoadHomeListData.getId(),
                            model.getData());
                }
            } else {
                showErrorWith(ApiInterfaceTool.API_LoadHomeListData.getId(),
                        BUSSINESS_ERROR, "暂无数据");
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
                showErrorWith(ApiInterfaceTool.API_LoadHomeListData.getId(),
                        TIMEOUT_ERROR, "请检查网络是否可用");
            } else {
                showErrorWith(ApiInterfaceTool.API_LoadHomeListData.getId(),
                        TIMEOUT_ERROR, "数据异常");
            }
        }
    }

    @Override
    public void showErrorWith(String method, int statusCode, String message) {
        apiRequestListener.onError(method, statusCode, message);
    }
}
