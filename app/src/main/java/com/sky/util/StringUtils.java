package com.sky.util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 字符处理工具类 Created by Administrator on 2016/5/23.
 */
public class StringUtils {

	private StringUtils() {
		throw new AssertionError();
	}

	/**
	 * 判断字符为空或它的长度为0
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isBlank(String str) {
		return (str == null || str.trim().length() == 0);
	}

	/**
	 * 判断字符为null或长度为0
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isEmpty(CharSequence str) {
		return (str == null || str.length() == 0);
	}

	/**
	 * 比较两个字符串
	 * 
	 * @param actual
	 * @param expected
	 * @return
	 */
	public static boolean isEquals(String actual, String expected) {
		return ObjectUtils.isEquals(actual, expected);
	}

	/**
	 * 获得CharSequence的长度
	 * 
	 * @param str
	 * @return
	 */
	public static int length(CharSequence str) {
		return str == null ? 0 : str.length();
	}

	/**
	 * 空对象为空字符串
	 * 
	 * @param str
	 * @return
	 */
	public static String nullStrToEmpty(Object str) {
		return (str == null ? "" : (str instanceof String ? (String) str : str
				.toString()));
	}

	/**
	 * 大写首字母
	 * 
	 * @param str
	 * @return
	 */
	public static String capitalizeFirstLetter(String str) {
		if (isEmpty(str)) {
			return str;
		}

		char c = str.charAt(0);
		return (!Character.isLetter(c) || Character.isUpperCase(c)) ? str
				: new StringBuilder(str.length())
						.append(Character.toUpperCase(c))
						.append(str.substring(1)).toString();
	}

	/**
	 * UTF-8编码
	 * 
	 * @param str
	 * @return
	 */
	public static String utf8Encode(String str) {
		if (!isEmpty(str) && str.getBytes().length != str.length()) {
			try {
				return URLEncoder.encode(str, "UTF-8");
			} catch (UnsupportedEncodingException e) {
				throw new RuntimeException(
						"UnsupportedEncodingException occurred. ", e);
			}
		}
		return str;
	}

	/**
	 * 编码为UTF-8，如果异常，返回默认返回
	 * 
	 * @param str
	 * @param defultReturn
	 * @return
	 */
	public static String utf8Encode(String str, String defultReturn) {
		if (!isEmpty(str) && str.getBytes().length != str.length()) {
			try {
				return URLEncoder.encode(str, "UTF-8");
			} catch (UnsupportedEncodingException e) {
				return defultReturn;
			}
		}
		return str;
	}

	/***
	 * 从HREF得到的innerHTML
	 */
	public static String getHrefInnerHtml(String href) {
		if (isEmpty(href)) {
			return "";
		}
		String hrefReg = ".*<[\\s]*a[\\s]*.*>(.+?)<[\\s]*/a[\\s]*>.*";
		Pattern hrefPattern = Pattern
				.compile(hrefReg, Pattern.CASE_INSENSITIVE);
		Matcher hrefMatcher = hrefPattern.matcher(href);
		if (hrefMatcher.matches()) {
			return hrefMatcher.group(1);
		}
		return href;
	}

	/**
	 * 处理HTML特殊字符
	 * 
	 * @param source
	 * @return
	 */
	public static String htmlEscapeCharsToString(String source) {
		return StringUtils.isEmpty(source) ? source : source
				.replaceAll("&lt;", "<").replaceAll("&gt;", ">")
				.replaceAll("&amp;", "&").replaceAll("&quot;", "\"");
	}

	/**
	 * 转换半角字符为全角字符
	 * 
	 * @param s
	 * @return
	 */
	public static String fullWidthToHalfWidth(String s) {
		if (isEmpty(s)) {
			return s;
		}

		char[] source = s.toCharArray();
		for (int i = 0; i < source.length; i++) {
			if (source[i] == 12288) {
				source[i] = ' ';
				// } else if (source[i] == 12290) {
				// source[i] = '.';
			} else if (source[i] >= 65281 && source[i] <= 65374) {
				source[i] = (char) (source[i] - 65248);
			} else {
				source[i] = source[i];
			}
		}
		return new String(source);
	}

	/**
	 * 改造全宽字符到半角字符
	 * 
	 * @param s
	 * @return
	 */
	public static String halfWidthToFullWidth(String s) {
		if (isEmpty(s)) {
			return s;
		}

		char[] source = s.toCharArray();
		for (int i = 0; i < source.length; i++) {
			if (source[i] == ' ') {
				source[i] = (char) 12288;
				// } else if (source[i] == '.') {
				// source[i] = (char)12290;
			} else if (source[i] >= 33 && source[i] <= 126) {
				source[i] = (char) (source[i] + 65248);
			} else {
				source[i] = source[i];
			}
		}
		return new String(source);
	}

	/**
	 * 判断字符串str1是否包含字符串str2
	 * 
	 * @param str1
	 *            源字符串
	 * @param str2
	 *            指定字符串
	 * @return true源字符串包含指定字符串，false源字符串不包含指定字符串
	 */
	public static boolean contains(String str1, String str2) {
		return str1 != null && str1.contains(str2);
	}

	/**
	 * 判断email格式是否正确
	 * 
	 * @param email
	 * @return
	 */
	public boolean isEmail(String email) {
		String str = "^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$";
		Pattern p = Pattern.compile(str);
		Matcher m = p.matcher(email);
		return m.matches();
	}
}
