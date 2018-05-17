package com.sky.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telephony.TelephonyManager;
import android.text.TextUtils;

/**
 * 网络连接工具类 Created by Administrator on 2016/5/23.
 */
public class NetWorkUtils {

	public static final String NETWORK_TYPE_WIFI = "wifi";
	public static final String NETWORK_TYPE_3G = "eg";
	public static final String NETWORK_TYPE_2G = "2g";
	public static final String NETWORK_TYPE_WAP = "wap";
	public static final String NETWORK_TYPE_UNKNOWN = "unknown";
	public static final String NETWORK_TYPE_DISCONNECT = "disconnect";

	/**
	 * 获取连接网络类型
	 * 
	 * @param context
	 * @return
	 */
	public static int getNetworkType(Context context) {
		ConnectivityManager connectivityManager = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo networkInfo = connectivityManager == null ? null
				: connectivityManager.getActiveNetworkInfo();
		return networkInfo == null ? -1 : networkInfo.getType();
	}

	/**
	 * 获取连接网络名称
	 * 
	 * @param context
	 * @return
	 */
	public static String getNetworkTypeName(Context context) {
		ConnectivityManager manager = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo networkInfo;
		String type = NETWORK_TYPE_DISCONNECT;
		if (manager == null
				|| (networkInfo = manager.getActiveNetworkInfo()) == null) {
			return type;
		}
		;

		if (networkInfo.isConnected()) {
			String typeName = networkInfo.getTypeName();
			if ("WIFI".equalsIgnoreCase(typeName)) {
				type = NETWORK_TYPE_WIFI;
			} else if ("MOBILE".equalsIgnoreCase(typeName)) {
				String proxyHost = android.net.Proxy.getDefaultHost();
				type = TextUtils.isEmpty(proxyHost) ? (isFastMobileNetwork(context) ? NETWORK_TYPE_3G
						: NETWORK_TYPE_2G)
						: NETWORK_TYPE_WAP;
			} else {
				type = NETWORK_TYPE_UNKNOWN;
			}
		}
		return type;
	}

	/**
	 * Whether is fast mobile network
	 * 
	 * @param context
	 * @return
	 */
	private static boolean isFastMobileNetwork(Context context) {
		TelephonyManager telephonyManager = (TelephonyManager) context
				.getSystemService(Context.TELEPHONY_SERVICE);
		if (telephonyManager == null) {
			return false;
		}
		switch (telephonyManager.getNetworkType()) {
		case TelephonyManager.NETWORK_TYPE_1xRTT:
			return false;
		case TelephonyManager.NETWORK_TYPE_CDMA:
			return false;
		case TelephonyManager.NETWORK_TYPE_EDGE:
			return false;
		case TelephonyManager.NETWORK_TYPE_EVDO_0:
			return true;
		case TelephonyManager.NETWORK_TYPE_EVDO_A:
			return true;
		case TelephonyManager.NETWORK_TYPE_GPRS:
			return false;
		case TelephonyManager.NETWORK_TYPE_HSDPA:
			return true;
		case TelephonyManager.NETWORK_TYPE_HSPA:
			return true;
		case TelephonyManager.NETWORK_TYPE_HSUPA:
			return true;
		case TelephonyManager.NETWORK_TYPE_UMTS:
			return true;
		case TelephonyManager.NETWORK_TYPE_EHRPD:
			return true;
		case TelephonyManager.NETWORK_TYPE_EVDO_B:
			return true;
		case TelephonyManager.NETWORK_TYPE_HSPAP:
			return true;
		case TelephonyManager.NETWORK_TYPE_IDEN:
			return false;
		case TelephonyManager.NETWORK_TYPE_LTE:
			return true;
		case TelephonyManager.NETWORK_TYPE_UNKNOWN:
			return false;
		default:
			return false;
		}
	}

	/**
	 * 检查网络是否连通
	 * 
	 * @return boolean
	 * @since V1.0
	 */
	public static boolean isNetworkAvailable(Context context) {
		// 创建并初始化连接对象
		ConnectivityManager connMan = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		// 判断初始化是否成功并作出相应处理
		if (connMan != null) {
			// 调用getActiveNetworkInfo方法创建对象,如果不为空则表明网络连通，否则没连通
			NetworkInfo info = connMan.getActiveNetworkInfo();
			if (info != null) {
				return info.isAvailable();
			}
		}
		return false;
	}

	/**
	 * 判断网络连接情况
	 * 
	 * @param context
	 * @return
	 */
	public static boolean isNetWorkConnected(Context context) {
		if (context != null) {
			ConnectivityManager mConnectivityManager = (ConnectivityManager) context
					.getSystemService(Context.CONNECTIVITY_SERVICE);
			NetworkInfo mNetworkInfo = mConnectivityManager
					.getActiveNetworkInfo();
			if (mNetworkInfo != null) {
				return mNetworkInfo.isAvailable() && mNetworkInfo.isConnected();
			}
		}

		return false;
	}

}
