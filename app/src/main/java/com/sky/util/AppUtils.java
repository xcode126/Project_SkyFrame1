package com.sky.util;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import com.sky.skyframe1.BaseApplication;

import java.util.Iterator;
import java.util.List;

/**
 * APP相关的辅助类 Created by Administrator on 2016/5/23.
 */
public class AppUtils {

	private AppUtils() {
		/* cannot be instantiated */
		throw new UnsupportedOperationException("cannot be instantiated");

	}

	/**
	 * 获取应用程序名称
	 */
	public static String getAppName(Context context) {
		PackageManager packageManager = context.getPackageManager();
		String appname = (String) packageManager.getApplicationLabel(context
				.getApplicationInfo());
		return appname;
	}

	/**
	 * [获取应用程序版本名称信息]
	 * 
	 * @param context
	 * @return 当前应用的版本名称
	 */
	public static String getVersionName(Context context) {
		try {
			PackageManager packageManager = context.getPackageManager();
			PackageInfo packageInfo = packageManager.getPackageInfo(
					context.getPackageName(), 0);
			return packageInfo.versionName;

		} catch (PackageManager.NameNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 获取软件的版本号
	 * 
	 * @return
	 */
	public String getVersion(Context context) {
		String version = "";
		try {
			PackageManager manager = context.getPackageManager();
			PackageInfo info = manager.getPackageInfo(context.getPackageName(),
					0);
			version = info.versionName;

		} catch (Exception e) {
			if (LogUtils.isDebug) {
				e.printStackTrace();
			}
			version = "";
		}
		return version;
	}

	/**
	 * 检查服务是否运行
	 * 
	 * @param context
	 * @param serviceName
	 * @return
	 */
	public static boolean isServiceRunning(Context context, String serviceName) {
		boolean isRunning = false;
		ActivityManager activityManager = (ActivityManager) context
				.getSystemService(Context.ACTIVITY_SERVICE);
		List<ActivityManager.RunningServiceInfo> serviceList = activityManager
				.getRunningServices(Integer.MAX_VALUE);
		if (serviceList == null || serviceList.size() == 0) {
			return false;
		}
		for (int i = 0; i < serviceList.size(); i++) {
			if (serviceList.get(i).service.getClassName().equals(serviceName)) {
				isRunning = true;
				break;
			}
		}
		return isRunning;
	}

	/**
	 * 根据PID获取应用名称
	 * 
	 * @param pID
	 * @return
	 */
	public static String getAppName(int pID) {
		String processName = null;
		ActivityManager am = (ActivityManager) BaseApplication.getInstance()
				.getSystemService(Context.ACTIVITY_SERVICE);
		List<RunningAppProcessInfo> l = am.getRunningAppProcesses();
		Iterator<RunningAppProcessInfo> i = l.iterator();
		while (i.hasNext()) {
			RunningAppProcessInfo info = (RunningAppProcessInfo) (i
					.next());
			try {
				if (info.pid == pID) {

					processName = info.processName;
					return processName;
				}
			} catch (Exception e) {
			}
		}
		return processName;
	}

	/**
	 * 检测某Activity是否在当前Task的栈顶
	 * 
	 * @param context
	 * @return
	 */
	public static boolean isTopActivity(Context context) {
		String packageName = context.getPackageName();
		ActivityManager activityManager = (ActivityManager) context
				.getSystemService(Context.ACTIVITY_SERVICE);
		List<ActivityManager.RunningTaskInfo> tasksInfo = activityManager
				.getRunningTasks(1);
		if (tasksInfo.size() > 0) {
			// 应用程序位于堆栈的顶层
			if (packageName.equals(tasksInfo.get(0).topActivity
					.getPackageName())) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 获取APP PackageNam
	 * 
	 * @param context
	 * @return
	 */
	public static String queryAppPackageName(Context context) {
		if (context == null) {
			context = BaseApplication.getInstance().getApplicationContext();
		}
		return context.getApplicationInfo().packageName;
	}
}