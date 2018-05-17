package com.sky.skyframe1.adapter;

import android.content.Context;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.sky.skyframe1.R;
import com.sky.skyframe1.bean.HousesListModel;

/**
 * Author：sky on 2018/5/9 0009 16:10.
 * Email：xcode126@126.com
 * Desc：
 */

public class HomeHeaderAdapter extends BaseQuickAdapter<HousesListModel, BaseViewHolder> {
    private Context mContext;

    public HomeHeaderAdapter(Context mContext) {
        super(R.layout.item_home_header);
        this.mContext = mContext;
    }

    @Override
    protected void convert(BaseViewHolder helper, HousesListModel item) {

    }
}
