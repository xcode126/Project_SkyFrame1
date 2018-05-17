package com.sky.common.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/***
 * 公共Fragment
 * 
 * @author WebUser
 * 
 */
public abstract class CommonSuperFragment extends FragmentWithCustom {

	protected View view;
	protected LayoutInflater inflater;
	protected boolean isCreated = false;
	protected boolean isPrepared;
	protected View LayoutView;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		isCreated = true;
	}

	@Override
	public void setUserVisibleHint(boolean isVisibleToUser) {
		super.setUserVisibleHint(isVisibleToUser);
		if (!isCreated && !isPrepared) {
			return;
		}
		if (isVisibleToUser) {
			isVisible = true;
			lazyLoad();
		} else {
			isVisible = true;
		}
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		this.inflater = inflater;
		if (view == null) {
			view = inflater.inflate(getLayoutContId(), container, false);
		}
		ViewGroup parent = (ViewGroup) view.getParent();
		if (parent != null) {
			parent.removeView(view);// 先移除
		}
		isPrepared = true;
		// 初始化View的各控件 完成
		initViews(savedInstanceState);
		initEvent();
		return view;
	}

	public abstract int getLayoutContId();

	protected abstract void initViews(Bundle savedInstanceState);

	protected abstract void initEvent();

}
