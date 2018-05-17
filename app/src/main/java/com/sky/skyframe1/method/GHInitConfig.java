package com.sky.skyframe1.method;

import com.lzy.okhttputils.OkHttpUtils;
import com.lzy.okhttputils.cookie.store.MemoryCookieStore;
import com.sky.skyframe1.BaseApplication;
import com.sky.util.FileUtils;
import com.sky.util.LogUtils;

/**
 * Author：sky on 2018/5/8 0008 09:49.
 * Email：xcode126@126.com
 * Desc：APP启动初始化工具配置
 */
public class GHInitConfig {

	private static GHInitConfig initConfig;

	public static GHInitConfig getInstance() {
		if (initConfig == null) {
			initConfig = new GHInitConfig();
		}
		return initConfig;
	}

	/**
	 * 初始化网络、图片加载等框架
	 */
	public void initPlugIn() {
		initNetWork();
		FileUtils.initAppFileFolder(BaseApplication.getInstance());
		// 清空临时文件夹
		FileUtils.cleanFileWithTem();
	}

	/**
	 * 初始化网络控件
	 */
	private void initNetWork() {
		// 初始化网络请求框架
		OkHttpUtils.init(BaseApplication.getInstance());
		OkHttpUtils okHttpUtils = OkHttpUtils.getInstance();
		okHttpUtils.setConnectTimeout(OkHttpUtils.DEFAULT_MILLISECONDS);
		okHttpUtils.setReadTimeOut(OkHttpUtils.DEFAULT_MILLISECONDS);
		okHttpUtils.setWriteTimeOut(OkHttpUtils.DEFAULT_MILLISECONDS);
		okHttpUtils.setCookieStore(new MemoryCookieStore());
		okHttpUtils.setCacheTime(1000 * 60);
		if (!LogUtils.isDebug) {
			okHttpUtils.debug("Health");
		}
	}
}
