package com.sky.common.ui;

import android.view.View;

/**
 *2016/5/25
 */
public class CommonActivityImpl extends CommonBaseActivity{

    final private CommonSuperActivity mActivity;

    public CommonActivityImpl(CommonSuperActivity activity) {
        mActivity  = activity;
    }

    @Override
    protected void onInit() {
    }

    @Override
    protected int getLayoutId() {
        return mActivity.getLayoutId();
    }

    public int getTitleLayout() {
        return mActivity.getTitleLayout();
    }

    @Override
    protected View getContentLayoutView() {
        return null;
    }

    @Override
    protected String getClassName() {
        return mActivity.getClass().getName();
    }

    @Override
    protected void dealContentView(View contentView) {
        mActivity.onBaseContentViewAttach(contentView);
    }


}
