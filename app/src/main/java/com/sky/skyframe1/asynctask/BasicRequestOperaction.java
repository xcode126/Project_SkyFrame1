package com.sky.skyframe1.asynctask;

import android.content.Context;

import com.sky.skyframe1.asynctask.remote.HomeListCallBack;
import com.sky.skyframe1.bean.httpBean.HomeListResponse;
import com.sky.skyframe1.util.UrlAggregate;
import com.sky.util.ApiInterfaceTool;

import java.util.HashMap;
import java.util.Map;

/**
 * Author：sky on 2018/5/8 0008 10:03.
 * Email：xcode126@126.com
 * Desc：网络请求集合工具
 */

public class BasicRequestOperaction {
    private static BasicRequestOperaction requestOperaction;

    public static BasicRequestOperaction getInstance() {
        if (requestOperaction == null) {
            requestOperaction = new BasicRequestOperaction();
        }
        return requestOperaction;
    }

    /**
     * 拼接请求URL
     *
     * @param partUrl
     * @return
     */
    public String getAbsoluteApiUrl(String partUrl) {
        String url = String.format(UrlAggregate.BaseRootUrl, partUrl);
        return url;
    }

    /**
     *  取消当前请求,所有Activity的onDestroy方法统一调用
     *
     * @param context
     */
    public void onCancelTask(Context context) {
        ApiAsyncTask.getInstance().onCancelRequest(context);
    }
    // ==============================================

    /**
     * 加载主页数据
     *
     * @param context
     * @param page
     * @param apiRequestListener
     */
    public void loadHomeListData(Context context, int page,
                                 ApiRequestListener apiRequestListener) {
        String url = getAbsoluteApiUrl("/house/pageQuery");
        Map<String, Object> requestParam = new HashMap<String, Object>();
        requestParam.put("page", page);
        requestParam.put("page_size", UrlAggregate.PAGE_SIZE);
        HomeListCallBack<HomeListResponse> callback = new HomeListCallBack<>(
                context, HomeListResponse.class, apiRequestListener);
        ApiAsyncTask.getInstance().doRequestWithMethod(HttpRequestMethod.Get,
                context, url, requestParam, null, callback);
    }

    /***
     * 加载主页轮播图片数据
     *
     * @param context
     * @param apiRequestListener
     */
    public void loadHomeBinnerData(Context context,
                                   ApiRequestListener apiRequestListener) {
        String url = getAbsoluteApiUrl("/house/getSlide");
        Map<String, Object> requestParam = new HashMap<String, Object>();
//        requestParam.put("page", page);
//        requestParam.put("page_size", UrlAggregate.PAGE_SIZE);
        ApiAsyncTask.RequestParamsModel paramsModel = ApiAsyncTask.getInstance().getRequestParamsModel();
        paramsModel.setApiInterfaceTool(ApiInterfaceTool.API_LoadHomeBannerData);
        paramsModel.setApiRequestListener(apiRequestListener);
        paramsModel.setBodyParamters(requestParam);
        paramsModel.setContext(context);
        ApiAsyncTask.getInstance().doRequestWithMethod(HttpRequestMethod.Get, url);
    }
}
