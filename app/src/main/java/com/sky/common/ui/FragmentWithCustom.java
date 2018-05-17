package com.sky.common.ui;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.sky.skyframe1.R;
import com.sky.util.ToastUtils;

/**
 * Author：sky on 2018/2/23 12:16
 * Email：xcode126@126.com
 * 公共Fragment(子类加载数据请在lazy方法中加载)
 */
public abstract class FragmentWithCustom extends Fragment {

	protected ProgressDialog progressDialog;

	/**
	 * 是否显示
	 */
	protected boolean isVisible;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		progressDialog = new ProgressDialog(getActivity());
		progressDialog.setMessage(getString(R.string.str_pd_cont));
		// 使其点击对话框以外的地方不起作用
		progressDialog.setCanceledOnTouchOutside(false);
	}

	public void showWaittingDialog() {
		if (progressDialog != null) {
			progressDialog.show();
		}
	}

	public boolean dismissWaittingDialog() {
		if (progressDialog != null && progressDialog.isShowing()) {
			progressDialog.dismiss();
			return true;
		}
		return false;
	}

	public void showToast(String message) {
		ToastUtils.show(getActivity(), message);
	}

	public void showToast(int resId) {
		ToastUtils.show(getActivity(), resId);
	}

	@Override
	public void onResume() {
		super.onResume();
		if (getUserVisibleHint()) {
			isVisible = true;
			onVisible();
		} else {
			isVisible = false;
		}
	}

	protected void onVisible() {
		lazyLoad();
	}

	/**
	 * 懒加载数据
	 */
	protected abstract void lazyLoad();

}
