package com.sky.skyframe1.adapter;

import android.content.Context;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.sky.skyframe1.R;
import com.sky.skyframe1.bean.HousesListModel;
import com.sky.util.BitmapUtils;

/**
 * Author：sky on 2018/5/9 0009 14:31.
 * Email：xcode126@126.com
 * Desc：首页列表
 */

public class HomeAdapter extends BaseQuickAdapter<HousesListModel, BaseViewHolder> {

    private Context mContext;

    public HomeAdapter(Context mContext) {
        super(R.layout.item_home);
        this.mContext = mContext;
    }

    @Override
    protected void convert(BaseViewHolder helper, HousesListModel item) {
        BitmapUtils.getInstance().loadImage(mContext, (ImageView) helper.getView(R.id.iv_image), item.getHouse_thumb_src(),
                R.drawable.ic_placeholder_2, R.drawable.ic_placeholder_2);
        helper.setText(R.id.tv_title, item.getHouse_name());
        helper.setText(R.id.tv_sign, item.getHouse_address());
        helper.setText(R.id.tv_browse, item.getHouse_max_area());
        helper.setText(R.id.tv_time, item.getHouse_average_price());
    }
}
