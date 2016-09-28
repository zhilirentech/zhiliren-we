/**
 * ArrayUtil.java
 * com.uxuexi.common.util
 * Copyright (c) 2014, 北京聚智未来科技有限公司版权所有.
*/

package com.uxuexi.core.common.util;

import static com.uxuexi.core.common.util.CollectionUtil.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/**
 * 数组工具类

 * @author   庄君祥
 * @Date	 2014-4-3 	 
 */
public final class ArrayUtil {

	/**
	  * 将数组转换成一个列表
	  * 
	  * @param array 原始数组
	  * @param <T> 表示任何对象
	  * @return 新列表
	  */
	public static <T> List<T> array2List(final T[] array) {
		return collection2list(array2Set(array));
	}

	/**
	  * 将数组转换成一个列表
	  * 
	  * @param array 原始数组
	  * @param <T> 表示任何对象
	  * @return 新列表
	  */
	public static List<Long> array2List(final long[] array) {
		if (null == array) {
			return null;
		}
		List<Long> re = new ArrayList<Long>(array.length);
		for (long obj : array) {
			re.add(obj);
		}
		return re;
	}

	/**
	  * 将数组转换成一个列表
	  * 
	  * @param array 原始数组
	  * @param <T> 表示任何对象
	  * @return 新列表
	  */
	public static <T> Set<T> array2Set(final T[] array) {
		if (null == array) {
			return null;
		}
		Set<T> re = new LinkedHashSet<T>(array.length);
		for (T obj : array) {
			re.add(obj);
		}
		return re;
	}

	/**
	 * 将long数组转换为set
	 *
	 * @param array 数组
	 * @return set
	 */
	public static Set<Long> array2Set(final long[] array) {
		if (null == array) {
			return null;
		}
		Set<Long> re = new LinkedHashSet<Long>(array.length);
		for (long obj : array) {
			re.add(obj);
		}
		return re;
	}

	/**
	 * 生成array的简便方法
	 * <p>
	 * 只支持对象，基本数据类型不支持
	 *
	 * @param arr 参数
	 * @param <T> 表示任何对象
	 * @return 数组
	 */
	public static <T> T[] array(final T... arr) {
		return arr;
	}

	/**
	 * 将对象数组转为字符串数组
	 * @param obj 对象
	 */
	public static String[] arrayObjectString(final Object obj) {

		String[] result = null;
		if (Util.isEmpty(obj)) {
			return null;
		}

		if (obj instanceof String) {
			result = new String[] { obj.toString() };
		}

		if (obj instanceof Object[]) {
			Object[] arr = (Object[]) obj;
			result = new String[arr.length];
			for (int i = 0; i < arr.length; i++) {
				result[i] = arr[i].toString();
			}
			return result;
		}

		if (obj instanceof Collection<?>) {
			Collection<?> coll = (Collection<?>) obj;
			Object[] arr = coll.toArray();
			result = new String[arr.length];
			for (int i = 0; i < arr.length; i++) {
				result[i] = arr[i].toString();
			}
			return result;
		}
		return result;
	}
}
