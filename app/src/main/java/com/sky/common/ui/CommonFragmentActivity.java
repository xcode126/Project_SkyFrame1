package com.sky.common.ui;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;

import com.sky.skyframe1.R;

/**
 * Author：sky on 2017/10/10 16:42
 * Email：xcode126@126.com
 * 最顶部的Activity
 */
public class CommonFragmentActivity extends FragmentActivity {

    public void startActivityWithAnimation(Intent intent) {
        startActivity(intent);
        overridePendingTransition(R.anim.slide_right_in, R.anim.slide_right_out);
    }

}
