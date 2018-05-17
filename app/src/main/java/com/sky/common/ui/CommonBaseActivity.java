package com.sky.common.ui;

import android.content.Context;
import android.os.IBinder;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.sky.common.widget.TopBarView;
import com.sky.skyframe1.R;

/**
 * Author：sky on 2017/10/10 16:42
 * Email：xcode126@126.com
 * 界面处理
 */
public abstract class CommonBaseActivity {

    private FragmentActivity mActionBarActivity;

    /**
     * root view
     */
    private View mContentView;

    private LayoutInflater mLayoutInflater;


    /**
     * root View container
     */
    private FrameLayout mContentFrameLayout;

    public View mBaseLayoutView;

    public CharSequence mTitleText;

    /**
     * 标题
     */
    public View mTopBarView;

    public void init(Context context, FragmentActivity activity) {
        mActionBarActivity = activity;
        onInit();
        int layoutId = getLayoutId();
        mLayoutInflater = LayoutInflater.from(mActionBarActivity);
        mBaseLayoutView = mLayoutInflater.inflate(R.layout.activity_common, null);
        LinearLayout mRootView = (LinearLayout) mBaseLayoutView.findViewById(R.id.common_root_view);
        mContentFrameLayout = (FrameLayout) mBaseLayoutView.findViewById(R.id.common_content);


        if (getTitleLayout() != -1) {
            mTopBarView = mLayoutInflater.inflate(getTitleLayout(), null);
            mRootView.addView(mTopBarView,
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT);
        }

        if (layoutId != -1) {

            mContentView = getContentLayoutView();
            if (mContentView == null) {
                mContentView = mLayoutInflater.inflate(getLayoutId(), null);
            }
            mRootView.addView(mContentView, LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.MATCH_PARENT);
        }

        dealContentView(mBaseLayoutView);

        CommonLayoutListenerView listenerView = (CommonLayoutListenerView) mActionBarActivity.findViewById(R.id.common_content);
        if (listenerView != null && mActionBarActivity.getWindow().getAttributes().softInputMode != WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE) {
            listenerView.setOnSizeChangedListener(new CommonLayoutListenerView.OnCCPViewSizeChangedListener() {

                @Override
                public void onSizeChanged(int w, int h, int oldw, int oldh) {
                }
            });

        }
    }


    /**
     * hideTitleView
     */
    public final void hideTitleView() {
        if (mTopBarView != null) {
            mTopBarView.setVisibility(View.GONE);
        }
    }

    /**
     * showTitleView
     */
    public final void showTitleView() {
        if (mTopBarView != null) {
            mTopBarView.setVisibility(View.VISIBLE);
        }
    }


    /**
     * isTitleShowing
     *
     * @return
     */
    public final boolean isTitleShowing() {
        if (mTopBarView == null) {
            return false;
        }

        return mTopBarView.getVisibility() == View.VISIBLE;
    }

    /**
     * The height of acitonBar
     *
     * @return
     */
    public final int getActionBarHeight() {
        if (mTopBarView == null) {
            return 0;
        }

        return mTopBarView.getHeight();
    }

    /**
     * @return
     */
    public View getActivityLayoutView() {
        return mContentView;
    }

    public View getContentView() {
        return mBaseLayoutView;
    }

    /**
     * @param visiable
     */
    public void setActionBarVisiable(int visiable) {
        if (mTopBarView == null) {
            return;
        }
        if (visiable == View.VISIBLE) {
            showTitleView();
            return;
        }
        hideTitleView();
    }

    /**
     * @return
     */
    public FragmentActivity getFragmentActivity() {
        return mActionBarActivity;
    }


    /**
     * @param contentDescription
     */
    public final void setActionContentDescription(CharSequence contentDescription) {
        if (TextUtils.isEmpty(contentDescription)) {
            return;
        }
        String description = mActionBarActivity.getString(R.string.common_enter_activity) + contentDescription;
        mActionBarActivity.getWindow().getDecorView().setContentDescription(description);
    }


    /**
     *
     */
    public void toggleSoftInput() {
        final FragmentActivity activity = mActionBarActivity;
        // Display the soft keyboard
        InputMethodManager inputMethodManager = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (inputMethodManager != null) {
            View localView = activity.getCurrentFocus();
            if (localView != null && localView.getWindowToken() != null) {
                inputMethodManager.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
            }
        }
    }

    /**
     * hide input method.
     */
    public void hideSoftKeyboard(View view) {
        if (view == null) {
            return;
        }

        InputMethodManager inputMethodManager = (InputMethodManager) mActionBarActivity.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (inputMethodManager != null) {
            IBinder localIBinder = view.getWindowToken();
            if (localIBinder != null)
                inputMethodManager.hideSoftInputFromWindow(localIBinder, 0);
        }
    }

    /**
     * hide inputMethod
     */
    public void hideSoftKeyboard() {
        InputMethodManager inputMethodManager = (InputMethodManager) mActionBarActivity.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (inputMethodManager != null) {
            View localView = mActionBarActivity.getCurrentFocus();
            if (localView != null && localView.getWindowToken() != null) {
                IBinder windowToken = localView.getWindowToken();
                inputMethodManager.hideSoftInputFromWindow(windowToken, 0);
            }
        }
    }

    public final void setRootConsumeWatcher(CommonLayoutListenerView layoutListenerView) {
        if (!(this.mContentFrameLayout instanceof CommonLayoutListenerView)) {
            return;
        }
        ((CommonLayoutListenerView) this.mContentFrameLayout).setRootConsumeWatcher();
    }


    /**
     * 设置ActionBar标题
     *
     * @param title
     */
    public final void setActionBarTitle(CharSequence title) {
        if (mTopBarView == null) {
            return;
        }

        mTitleText = title;
        if (mTopBarView instanceof TopBarView) {
            ((TopBarView) mTopBarView).setTitle(title != null ? title.toString() : "");
        }
        setActionContentDescription(title);
    }

    /**
     * @return
     */
    public final CharSequence getActionBarTitle() {
        return mTitleText;
    }

    /**
     * @return
     */
    public final TopBarView getTitleBar() {
        return (TopBarView) mTopBarView;
    }


    /**
     * @param keyCode
     * @param event
     * @return
     */
    public boolean onKeyUp(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_MENU && event.getAction() == KeyEvent.ACTION_UP) {
            /*if(mOverFlowAction != null && mOverFlowAction.isEnabled()) {
                callMenuCallback(mOverFlowMenuItem, mOverFlowAction);
				return true;
			}*/
        }
        return false;
    }

    public void onResume() {

    }

    public void onPause() {

    }

    /**
     *
     */
    public void onDestroy() {
        mTopBarView = null;
    }


    /**
     * 子类重载该方法自定义标题布局文件
     *
     * @return
     */
    public int getTitleLayout() {
        return R.layout.widght_base_title_view;
    }

    public TopBarView getTopBarView() {
        if (mTopBarView instanceof TopBarView) {
            return (TopBarView) mTopBarView;
        }
        return null;
    }

    protected abstract void onInit();

    /**
     * The sub Activity implement, set the Ui Layout
     *
     * @return
     */
    protected abstract int getLayoutId();

    protected abstract View getContentLayoutView();

    protected abstract String getClassName();

    /**
     *
     */
    protected abstract void dealContentView(View contentView);


    public final void invalidateActionMenu() {
        mActionBarActivity.supportInvalidateOptionsMenu();
    }

    public void setScreenEnable(boolean screenEnable) {

    }

    public void onStart() {

    }


}
