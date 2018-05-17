package com.sky.util;

import android.content.Context;
import android.text.TextUtils;

import java.io.File;

/**
 * 文件保存工具类 2016/5/23
 */
public class FileUtils {

	/**
	 * 临时文件夹名称
	 */
	private static String tempFileName = "temFile";
	/**
	 * 缓存文件夹名称
	 */
	private static String cacheFileName = "cacheFile";
	/**
	 * 异常捕捉保存文件夹名称
	 */
	private static String crashFileName = " crashFile";

	/**
	 * 临时保存图片文件夹
	 */
	private static String tempImgName = "tempImg";

	/**
	 * 缓存图片文件夹
	 */
	private static String cacheImgName = "cacheImg";
	

	/**
	 * APP主目录路径
	 */
	private static String filePath;

	/**
	 * 初始化文件保存位置
	 * 
	 * @param context
	 */
	public static void initAppFileFolder(Context context) {
		filePath = SDCardUtils.getSDCardPath() + AppUtils.getAppName(context);
		File file = new File(filePath);
		if (!file.exists()) {
			file.mkdir();
		}
	}

	/**
	 * 获取临时文件夹路径(APP重启时删除所有内容)
	 * 
	 * @return
	 */
	public static String getTemFilePath() {
		String temFilePath = filePath + File.separator + tempFileName;
		File file = new File(temFilePath);
		if (!file.exists()) {
			file.mkdir();
		}
		return file.getAbsolutePath();
	}

	/**
	 * 获取临时图片文件夹路径(APP重启时删除所有内容)
	 * 
	 * @return
	 */
	public static String getTemImgPath() {
		String temImgPath = getTemFilePath() + File.separator + tempImgName;
		File file = new File(temImgPath);
		if (!file.exists()) {
			file.mkdir();
		}
		return file.getAbsolutePath();
	}

	/**
	 * 获取临时文件夹路径(APP重启时删除所有内容)
	 * 
	 * @return
	 */
	public static File getTemFile() {
		String temFilePath = filePath + File.separator + tempFileName;
		File file = new File(temFilePath);
		if (!file.exists()) {
			file.mkdir();
		}
		return file;
	}

	/**
	 * 获取临时图片文件夹路径(APP重启时删除所有内容)
	 * 
	 * @return
	 */
	public static File getTemImgFile() {
		String temImgPath = getTemFilePath() + File.separator + tempImgName;
		File file = new File(temImgPath);
		if (!file.exists()) {
			file.mkdir();
		}
		return file;
	}

	/**
	 * 获取缓存文件夹路径(需要手动删除文件)
	 * 
	 * @return
	 */
	public static String getCacheFilePath() {
		String cacheFilePath = filePath + File.separator + cacheFileName;
		File file = new File(cacheFilePath);
		if (!file.exists()) {
			file.mkdir();
		}
		return file.getAbsolutePath();
	}

	/**
	 * 获取缓存图片文件夹路径(需要手动删除文件)
	 * 
	 * @return
	 */
	public static String getCacheImgPath() {
		String cacheImgPath = getCacheFilePath() + File.separator
				+ cacheImgName;
		File file = new File(cacheImgPath);
		if (!file.exists()) {
			file.mkdir();
		}
		return file.getAbsolutePath();
	}

	/**
	 * 获取缓存文件夹路径(需要手动删除文件)
	 * 
	 * @return
	 */
	public static File getCacheFile() {
		String cacheImgPath = filePath + File.separator + cacheFileName;
		File file = new File(cacheImgPath);
		if (!file.exists()) {
			file.mkdir();
		}
		return file;
	}

	/**
	 * 获取缓存图片文件夹路径(需要手动删除文件)
	 * 
	 * @return
	 */
	public static File getCacheImgFile() {
		String cacheImgPath = getCacheFilePath() + File.separator
				+ cacheImgName;
		File file = new File(cacheImgPath);
		if (!file.exists()) {
			file.mkdir();
		}
		return file;
	}

	/**
	 * 获取异常缓存路径
	 * 
	 * @return
	 */
	public static String getCrashFilePath() {
		String temFilePath = filePath + File.separator + crashFileName;
		File file = new File(temFilePath);
		if (!file.exists()) {
			file.mkdir();
		}
		return file.getAbsolutePath();
	}

	/**
	 * 删除文件
	 * 
	 * @param path
	 */
	public static void delImageWithPath(String path) {
		if (TextUtils.isEmpty(path)) {
			return;
		}
		File file = new File(path);
		if (file.exists()) {
			file.delete();
		}
	}

	/**
	 * 从磁盘清除缓存文件夹所有文件
	 */
	public void cleanFileWithCache(Context context) {
		File cacheDirectory = getCacheFile();
		cleaFile(cacheDirectory);
	}

	/**
	 * 删除临时文件夹所有文件
	 */
	public static void cleanFileWithTem() {
		File temDirectory = getTemFile();
		cleaFile(temDirectory);
	}

	/**
	 * 删除文件夹内所有文件
	 * 
	 * @param fileDirectory
	 */
	private static void cleaFile(File fileDirectory) {
		if (fileDirectory.exists()) {
			File[] files = fileDirectory.listFiles();
			for (File file : files) {
				file.delete();
			}
		}
	}

	/**
	 * 检查File是否存在
	 * 
	 * @param filePath
	 * @return
	 */
	public static boolean checkFile(String filePath) {
		if (TextUtils.isEmpty(filePath) || !(new File(filePath).exists())) {
			return false;
		}
		return true;
	}

	/**
	 * 根据文件全路径查找File
	 * 
	 * @param fileFullPath
	 * @return
	 */
	public static File queryFileByFullFilePath(String fileFullPath) {
		if (checkFile(fileFullPath)) {
			// 存在
			File file = new File(fileFullPath);
			return file;
		}
		return null;
	}

}
