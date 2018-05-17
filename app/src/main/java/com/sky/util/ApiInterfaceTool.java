package com.sky.util;


import com.sky.skyframe1.bean.BaseResponse;
import com.sky.skyframe1.bean.httpBean.HomeBannerResponse;
import com.sky.skyframe1.bean.httpBean.HomeListResponse;

/***
 * APP接口工具类
 */
public enum ApiInterfaceTool {

    /**
     * 加载主页数据
     */
    API_LoadHomeListData("loadHomeListData", HomeListResponse.class.getName()),
    /**
     * 加载主页banner数据
     */
    API_LoadHomeBannerData("loadHomeBannerData", HomeBannerResponse.class.getName()),
    /**
     * 提交手机号和人数叫车
     */
    API_LoadCarSubmit("LoadCarSubmit", BaseResponse.class.getName());

    private final String mId;
    private final Object mDefaultValue;

    private ApiInterfaceTool(String id, Object defaultValue) {
        this.mId = id;
        this.mDefaultValue = defaultValue;
    }

    public String getId() {
        return this.mId;
    }

    public Object getDefaultValue() {
        return this.mDefaultValue;
    }

    public static ApiInterfaceTool fromId(String id) {
        ApiInterfaceTool[] values = values();
        int cc = values.length;
        for (int i = 0; i < cc; i++) {
            if (values[i].mId == id) {
                return values[i];
            }
        }
        return null;
    }
}
