package com.sky.util;

/**
 * 对象工具类 Created by Administrator on 2016/5/23.
 */
public class ObjectUtils {

	private ObjectUtils() {
		throw new AssertionError();
	}

	/**
	 * 比较两个对象
	 * 
	 * @param actual
	 * @param expected
	 * @return <ul>
	 *         <li>if both are null, return true</li>
	 *         <li>return actual.{@link Object#equals(Object)}</li>
	 *         </ul>
	 */
	public static boolean isEquals(Object actual, Object expected) {
		return actual == expected
				|| (actual == null ? expected == null : actual.equals(expected));
	}

	/**
	 * 空对象为空字符串
	 * <p/>
	 * 
	 * <pre>
	 * nullStrToEmpty(null) = &quot;&quot;;
	 * nullStrToEmpty(&quot;&quot;) = &quot;&quot;;
	 * nullStrToEmpty(&quot;aa&quot;) = &quot;aa&quot;;
	 * </pre>
	 * 
	 * @param str
	 * @return
	 */
	public static String nullStrToEmpty(Object str) {
		return (str == null ? "" : (str instanceof String ? (String) str : str
				.toString()));
	}

	/**
	 * 多头排列转化为多头排列
	 * 
	 * @param source
	 * @return
	 */
	public static Long[] transformLongArray(long[] source) {
		Long[] destin = new Long[source.length];
		for (int i = 0; i < source.length; i++) {
			destin[i] = source[i];
		}
		return destin;
	}

	/**
	 * 长数组转换成多头排列
	 * 
	 * @param source
	 * @return
	 */
	public static long[] transformLongArray(Long[] source) {
		long[] destin = new long[source.length];
		for (int i = 0; i < source.length; i++) {
			destin[i] = source[i];
		}
		return destin;
	}

	/**
	 * int数组转换为整数数组
	 * 
	 * @param source
	 * @return
	 */
	public static Integer[] transformIntArray(int[] source) {
		Integer[] destin = new Integer[source.length];
		for (int i = 0; i < source.length; i++) {
			destin[i] = source[i];
		}
		return destin;
	}

	/**
	 * 转换成整数数组int数组
	 * 
	 * @param source
	 * @return
	 */
	public static int[] transformIntArray(Integer[] source) {
		int[] destin = new int[source.length];
		for (int i = 0; i < source.length; i++) {
			destin[i] = source[i];
		}
		return destin;
	}

	/**
	 * 比较两个对象
	 * <ul>
	 * <strong>About result</strong>
	 * <li>if v1 > v2, return 1</li>
	 * <li>if v1 = v2, return 0</li>
	 * <li>if v1 < v2, return -1</li>
	 * </ul>
	 * <ul>
	 * <strong>About rule</strong>
	 * <li>if v1 is null, v2 is null, then return 0</li>
	 * <li>if v1 is null, v2 is not null, then return -1</li>
	 * <li>if v1 is not null, v2 is null, then return 1</li>
	 * <li>return v1.{@link Comparable#compareTo(Object)}</li>
	 * </ul>
	 * 
	 * @param v1
	 * @param v2
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static <V> int compare(V v1, V v2) {
		return v1 == null ? (v2 == null ? 0 : -1) : (v2 == null ? 1
				: ((Comparable) v1).compareTo(v2));
	}
}
