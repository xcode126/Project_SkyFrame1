package com.sky.skyframe1.bean.httpBean;

import com.sky.skyframe1.bean.BinnerImageModel;

import java.util.List;

/**
 * Author：sky on 2018/5/11 0011 16:06.
 * Email：xcode126@126.com
 * Desc：
 */

public class HomeBannerResponse {
    private static final long serialVersionUID = 1L;

    private List<BinnerImageModel> data;

    public List<BinnerImageModel> getData() {
        return data;
    }

    public void setData(List<BinnerImageModel> data) {
        this.data = data;
    }
}
