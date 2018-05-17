package com.sky.skyframe1.util;

/**
 * Author：sky on 2017/10/10 16:42
 * Email：xcode126@126.com
 * 全局配置类
 */

public class ConfigUtil {

    private static ConfigUtil configUtil;
    // =======判断选择Tab(适用于懒加载数据时判断)begin=============
    public static boolean isClicked1 = false;
    public static boolean isClicked2 = false;
    public static boolean isClicked3 = false;
    /**是否处于TabHomePage*/
    public static boolean currentHomePage = false;
    // =======判断选择Tab(适用于懒加载数据时判断)end=============
    public synchronized static ConfigUtil getInstate() {
        if (configUtil == null) {
            configUtil = new ConfigUtil();
        }
        return configUtil;
    }
}
