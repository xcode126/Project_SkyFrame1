package com.sky.util;

import android.content.Context;
import android.widget.Toast;

/**
 * Author：sky on 2018/2/23 12:16
 * Email：xcode126@126.com
 * Toast工具类
 */
public class ToastUtils {

    private static Toast mToast;

    private ToastUtils() {
        throw new AssertionError();
    }

    public static void show(Context context, int resId) {
        show(context, context.getResources().getText(resId), Toast.LENGTH_SHORT);
    }

    public static void show(Context context, int resId, int duration) {
        show(context, context.getResources().getText(resId), duration);
    }

    public static void show(Context context, CharSequence text) {
        show(context, text, Toast.LENGTH_SHORT);
    }

    public static void show(Context context, CharSequence text, int duration) {
        CheckToas(context);
        mToast.setDuration(duration);
        mToast.setText(text);
        mToast.show();
    }

    public static void show(Context context, int resId, Object... args) {
        show(context,
                String.format(context.getResources().getString(resId), args),
                Toast.LENGTH_SHORT);
    }

    public static void show(Context context, String format, Object... args) {
        show(context, String.format(format, args), Toast.LENGTH_SHORT);
    }

    public static void show(Context context, int resId, int duration,
                            Object... args) {
        show(context,
                String.format(context.getResources().getString(resId), args),
                duration);
    }

    public static void show(Context context, String format, int duration,
                            Object... args) {
        show(context, String.format(format, args), duration);
    }

    private static void CheckToas(Context context) {
        if (mToast == null) {
            mToast = Toast.makeText(context, "", Toast.LENGTH_SHORT);
//			mToast.setGravity(17, 0, -30);// 居中显示
        }
    }

    public static void onCancelToas() {
        if (mToast != null) {
            mToast.cancel();
        }
    }
}
