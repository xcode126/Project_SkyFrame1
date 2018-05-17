package com.sky.skyframe1.util;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.sky.skyframe1.R;
import com.sky.util.BitmapUtils;
import com.youth.banner.loader.ImageLoader;

/**
 * Author：sky on 2018/5/11 0011 16:51.
 * Email：xcode126@126.com
 * Desc：
 */

public class GlideImageLoader extends ImageLoader {
    @Override
    public void displayImage(Context context, Object path, ImageView imageView) {
        /**
         注意：
         1.图片加载器由自己选择，这里不限制，只是提供几种使用方法
         2.返回的图片路径为Object类型，由于不能确定你到底使用的那种图片加载器，
         传输的到的是什么格式，那么这种就使用Object接收和返回，你只需要强转成你传输的类型就行，
         切记不要胡乱强转！
         */

        Glide.with(context).load(path).into(imageView);
        BitmapUtils.getInstance().loadImage(context, imageView, path, R.drawable.ic_placeholder_2, R.drawable.ic_placeholder_2);
    }
}
