package com.sky.common.ui;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import com.sky.common.widget.TopBarView;
import com.sky.skyframe1.R;
import com.sky.util.ActivityManager;

/**
 * Author：sky on 2017/10/10 16:42
 * Email：xcode126@126.com
 * 公共activity 所有Activity需继承此CommonSuperActivity
 */
public abstract class CommonSuperActivity extends CommonFragmentActivity {

	/**
	 * 初始化应用ActionBar
	 */
	public CommonBaseActivity mBaseActivity;
	protected Context context;
	protected ProgressDialog progressDialog;

	@SuppressLint("InlinedApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		context = this;
		mBaseActivity = new CommonActivityImpl(this);
		mBaseActivity.init(getBaseContext(), this);
		// // 判断手机SDK版本如果大于或者等于19 就透明状态栏
		// if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
		// Window window = getWindow();
		// // 透明状态栏
		// window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
		// }
		progressDialog = new ProgressDialog(this);
		progressDialog.setMessage(getString(R.string.str_pd_cont));
		// 使其点击对话框以外的地方不起作用
		progressDialog.setCanceledOnTouchOutside(false);
		// 当前Activity入栈,自助管理栈
		ActivityManager.getInstance().pushActivity(this);
		initViews();
		initEvent();
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		// 当前Activity出栈
		ActivityManager.getInstance().popActivity(this);
	}

	/**
	 * The sub Activity implement, set the UI Layout
	 * 
	 * @return
	 */
	protected abstract int getLayoutId();

	/**
	 * 子类重载该方法自定义标题布局文件
	 * 
	 * @return
	 */
	public int getTitleLayout() {
		return R.layout.widght_base_title_view;
	}

	public void onBaseContentViewAttach(View contentView) {
		setContentView(contentView);
	}

	public FragmentActivity getActionBarActivityContext() {
		return mBaseActivity.getFragmentActivity();
	}

	public TopBarView getTopBarView() {
		return mBaseActivity.getTopBarView();
	}

	/**
	 * 设置ActionBar标题
	 * 
	 * @param resid
	 */
	public void setActionBarTitle(int resid) {
		mBaseActivity.setActionBarTitle(getString(resid));
	}

	/**
	 * 设置ActionBar标题
	 * 
	 * @param text
	 */
	public void setActionBarTitle(CharSequence text) {
		mBaseActivity.setActionBarTitle(text);
	}

	/**
	 * 返回ActionBar 标题
	 * 
	 * @return
	 */
	public final CharSequence getActionBarTitle() {
		return mBaseActivity.getActionBarTitle();
	}

	/**
	 * #getLayoutId()
	 * 
	 * @return
	 */
	public View getActivityLayoutView() {
		return mBaseActivity.getActivityLayoutView();
	}

	/**
	 * 显示导航栏
	 */
	public final void showTitleView() {
		mBaseActivity.showTitleView();
	}

	/**
	 * 隐藏导航栏
	 */
	public final void hideTitleView() {
		mBaseActivity.hideTitleView();
	}

	/**
	 * 设置导航栏背景颜色
	 * 
	 * @param colorId
	 */
	public void setTopBarBackgroundColor(int colorId) {
		mBaseActivity.getTopBarView().setBackgroundColorWithCustom(colorId);
	}

	/**
	 * 设置导航栏背景背景
	 * 
	 * @param drawId
	 */
	public void setTopBarBackgroundDraw(int drawId) {
		mBaseActivity.getTopBarView().setBackgroundDrawWithCustom(drawId);
	}

	/**
	 * 设置导航栏左边文字颜色
	 * 
	 * @param colorId
	 */
	public void setTopBarLeftTextColor(int colorId) {
		mBaseActivity.getTopBarView().setLeftTextColor(colorId);
	}

	/**
	 * 设置导航栏标题颜色
	 * 
	 * @param colorId
	 */
	public void setTopBarTitleTextColor(int colorId) {
		mBaseActivity.getTopBarView().setTitleTextColor(colorId);
	}

	/**
	 * 设置导航栏右边文字颜色
	 * 
	 * @param colorId
	 */
	public void setTopBarRightTextColor(int colorId) {
		mBaseActivity.getTopBarView().setRightTextColor(colorId);
	}

	/**
	 * 公用初始化方法
	 */
	protected abstract void initViews();

	/**
	 * 设置事件
	 */
	protected abstract void initEvent();

	/**
	 * 展示加载数据Loading
	 */
	public void showWaittingDialog() {
		if (progressDialog != null) {
			progressDialog.show();
		}
	}

	/**
	 * 隐藏加载数据Loading
	 * 
	 * @return
	 */
	public boolean dismissWaittingDialog() {
		if (progressDialog != null && progressDialog.isShowing()) {
			progressDialog.dismiss();
			return true;
		}
		return false;
	}
}
