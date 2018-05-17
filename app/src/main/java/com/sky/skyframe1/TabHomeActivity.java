package com.sky.skyframe1;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.RadioButton;
import android.widget.TextView;

import com.sky.skyframe1.adapter.FragmentTabAdapter;
import com.sky.skyframe1.asynctask.BasicRequestOperaction;
import com.sky.skyframe1.navigate.FragmentHome;
import com.sky.skyframe1.navigate.FragmentIndex2;
import com.sky.skyframe1.navigate.FragmentIndex3;
import com.sky.skyframe1.util.ConfigUtil;
import com.sky.skyframe1.widget.TabRadioGroup;

import java.util.ArrayList;
/**
 * Author：sky on 2017/10/10 16:42
 * Email：xcode126@126.com
 * project-frame
 */
public class TabHomeActivity extends AppCompatActivity {

    private FragmentTabAdapter tabAdapter;
    private ArrayList<Fragment> fragments;
    private int currPageid = 0;
    //child index
    private FragmentHome index1;
    private FragmentIndex2 index2;
    private FragmentIndex3 index3;
    //The bottom frame switches control.
    private TabRadioGroup rgHomeBootom;
    private RadioButton rb1;
    private RadioButton rb2;
    private RadioButton rb3;
    private Bundle savedInstanceState;
    private FragmentManager fManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_home);
        initViews();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        this.savedInstanceState = savedInstanceState;
    }

    private void initViews() {
        currPageid = R.id.rb1;//default is first
        rgHomeBootom = (TabRadioGroup) findViewById(R.id.rg_home_bottom);
//        tvMsgCount = (TextView) findViewById(R.id.tv_rb_message_count);
        rb1 = (RadioButton) findViewById(R.id.rb1);
        rb2 = (RadioButton) findViewById(R.id.rb2);
        rb3 = (RadioButton) findViewById(R.id.rb3);

        fManager = getSupportFragmentManager();
        if (savedInstanceState != null) {
            index1 = (FragmentHome) fManager.findFragmentByTag("0");
            index2 = (FragmentIndex2) fManager.findFragmentByTag("1");
            index3 = (FragmentIndex3) fManager.findFragmentByTag("2");

        }
        //make data
        index1 = new FragmentHome();
        index2 = new FragmentIndex2();
        index3 = new FragmentIndex3();

        fragments = new ArrayList<>();
        fragments.add(index1);
        fragments.add(index2);
        fragments.add(index3);
        tabAdapter = new FragmentTabAdapter(TabHomeActivity.this, fragments, R.id.fl_home_content, rgHomeBootom);
        tabAdapter.setOnRgsExtraCheckedChangedListener(checkedChangedListener);
    }

    private FragmentTabAdapter.OnRgsExtraCheckedChangedListener checkedChangedListener = new FragmentTabAdapter.OnRgsExtraCheckedChangedListener() {
        @Override
        public void OnRgsExtraCheckedChanged(TabRadioGroup radioGroup, int checkedId, int index) {
            super.OnRgsExtraCheckedChanged(radioGroup, checkedId, index);
            setRadioButtonIcon();
            int drawableid = -1;
            switch (checkedId) {
                case R.id.rb1:
                    ConfigUtil.isClicked1 = true;
                    drawableid = R.drawable.icon_home_selected;
                    break;
                case R.id.rb2:
                    ConfigUtil.isClicked2 = true;
                    drawableid = R.drawable.icon_find_selected;
                    break;
                case R.id.rb3:
                    ConfigUtil.isClicked3 = true;
                    drawableid = R.drawable.icon_shop_selected;
                    break;
            }
            RadioButton rg = (RadioButton) findViewById(checkedId);
            currPageid = checkedId;
            setClickChRadioButtonIcon(rg, drawableid);
        }
    };

    /**
     * Set each button icon.
     */
    private void setRadioButtonIcon() {
        // Restore all Tab click states.
        ConfigUtil.isClicked1 = false;
        ConfigUtil.isClicked2 = false;
        ConfigUtil.isClicked3 = false;
        setClickChRadioButtonIcon(rb1, R.drawable.icon_home_unselect);
        setClickChRadioButtonIcon(rb2, R.drawable.icon_find_unselect);
        setClickChRadioButtonIcon(rb3, R.drawable.icon_shop_unselect);
        rb1.setTextColor(ContextCompat.getColor(this, R.color.tab_text_background_unselect));
        rb2.setTextColor(ContextCompat.getColor(this, R.color.tab_text_background_unselect));
        rb3.setTextColor(ContextCompat.getColor(this, R.color.tab_text_background_unselect));
    }

    /**
     * Set the button icon in the dot.
     */
    private void setClickChRadioButtonIcon(TextView textview, int drawableid) {
        setCompoundDrawables(textview, null, getResources().getDrawable(drawableid), null, null, 60, 60, true);
        textview.setTextColor(ContextCompat.getColor(TabHomeActivity.this, R.color.tab_text_background_selected));
    }

    @Override
    protected void onPause() {
        super.onPause();
        ConfigUtil.currentHomePage = false;
    }

    @Override
    protected void onResume() {
        super.onResume();
        ConfigUtil.currentHomePage = true;
        if (tabAdapter != null) {
            tabAdapter.onCheckedChanged(rgHomeBootom, currPageid);
        }
    }

    /**
     * Set the image size and location.
     *
     * @param textView
     * @param left
     * @param top
     * @param right
     * @param bottom
     * @param width
     * @param height
     * @param isDefault Whether to use the image itself long width.
     */
    public void setCompoundDrawables(TextView textView, Drawable left,
                                     Drawable top, Drawable right, Drawable bottom, int width,
                                     int height, boolean isDefault) {
        // 调用setCompoundDrawables时，必须调用Drawable.setBounds()方法,否则图片不显示
        int w = width, h = height;
        if (left != null) {
            if (isDefault) {
                w = left.getMinimumWidth();
                h = left.getMinimumHeight();
            }
            left.setBounds(0, 0, w, h);
        }
        if (top != null) {
            if (isDefault) {
                w = top.getMinimumWidth();
                h = top.getMinimumHeight();
            }
            top.setBounds(0, 0, w, h);
        }
        if (right != null) {
            if (isDefault) {
                w = right.getMinimumWidth();
                h = right.getMinimumHeight();
            }
            right.setBounds(0, 0, w, h);
        }
        if (bottom != null) {
            if (isDefault) {
                w = bottom.getMinimumWidth();
                h = bottom.getMinimumHeight();
            }
            bottom.setBounds(0, 0, w, h);
        }
        textView.setCompoundDrawables(left, top, right, bottom); // 设置图标
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        BasicRequestOperaction.getInstance().onCancelTask(this);
    }
}
