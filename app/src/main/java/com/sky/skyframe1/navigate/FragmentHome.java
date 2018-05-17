package com.sky.skyframe1.navigate;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;

import com.sky.common.ui.CommonSuperFragment;
import com.sky.skyframe1.R;
import com.sky.skyframe1.adapter.HomeAdapter;
import com.sky.skyframe1.adapter.HomeHeaderAdapter;
import com.sky.skyframe1.asynctask.ApiRequestListener;
import com.sky.skyframe1.asynctask.BasicRequestOperaction;
import com.sky.skyframe1.bean.BinnerImageModel;
import com.sky.skyframe1.bean.HousesListModel;
import com.sky.skyframe1.util.GlideImageLoader;
import com.sky.util.ApiInterfaceTool;
import com.sky.util.StringUtils;
import com.sky.util.ToastUtils;
import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.List;

/**
 * Author：sky on 2018/2/23 12:16
 * Email：xcode126@126.com
 * 首页
 */

public class FragmentHome extends CommonSuperFragment implements ApiRequestListener {

    private RecyclerView mRecyclerView;
    private HomeAdapter adapter;

    private Banner banner;

    @Override
    public int getLayoutContId() {
        return R.layout.common_recycler;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        banner = view.findViewById(R.id.banner);
        mRecyclerView = view.findViewById(R.id.mRecyclerView);
        adapter = new HomeAdapter(getActivity());
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setAdapter(adapter);
    }

    @Override
    protected void initEvent() {

    }

    @Override
    protected void lazyLoad() {
//        BasicRequestOperaction.getInstance().loadHomeBinnerData(getActivity(), this);
        BasicRequestOperaction.getInstance().loadHomeListData(getActivity(), 1, this);
    }

    @Override
    public void onSuccess(String method, Object result) {
        if (result == null) {
            return;
        }
        if (StringUtils.isEquals(method,
                ApiInterfaceTool.API_LoadHomeListData.getId())) {
            processHomeListData((List<HousesListModel>) result);
        } else if (StringUtils.isEquals(method, ApiInterfaceTool.API_LoadHomeBannerData.getId())) {
            processHomeBannerData((List<BinnerImageModel>) result);
        }
    }

    @Override
    public void onError(String method, int statusCode, String message) {
        if (StringUtils.isEquals(method,
                ApiInterfaceTool.API_LoadHomeListData.getId())) {
            //该接口请求失败
            ToastUtils.show(getActivity(), message);
            dismissWaittingDialog();
        }
    }

    /**
     * 处理请求返回的数据
     *
     * @param list
     */
    private void processHomeListData(List<HousesListModel> list) {
        if (adapter.getHeaderLayoutCount() > 0) {
            adapter.removeAllHeaderView();
            adapter.notifyDataSetChanged();
        }
        View headerView = LayoutInflater.from(getActivity()).inflate(R.layout.header_home_top, null);
        RecyclerView recyclerView = headerView.findViewById(R.id.rv_home);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        HomeHeaderAdapter homeHeaderAdapter = new HomeHeaderAdapter(getActivity());
        recyclerView.setAdapter(homeHeaderAdapter);
        homeHeaderAdapter.setNewData(list);
        adapter.addHeaderView(headerView);
        adapter.setNewData(list);
    }

    /**
     * 处理首页banner数据
     *
     * @param list
     */
    private void processHomeBannerData(List<BinnerImageModel> list) {
        List imgList = new ArrayList();
        if (list != null && list.size() > 0) {
            for (BinnerImageModel model : list) {
                imgList.add(model.getImage_source_url());
            }
        }
        //设置图片加载器
        banner.setImageLoader(new GlideImageLoader());
        //设置图片集合
        banner.setImages(imgList);
        //banner设置方法全部调用完毕时最后调用
        banner.start();
    }

//    @Override
//    public void onHiddenChanged(boolean hidden) {
//        super.onHiddenChanged(hidden);
//        if (hidden) {
//            //结束轮播
//            banner.stopAutoPlay();
//        } else {
//            //开始轮播
//            banner.startAutoPlay();
//        }
//    }
}
