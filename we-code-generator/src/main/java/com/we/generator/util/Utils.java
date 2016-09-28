package com.we.generator.util;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Map;

import org.nutz.lang.Strings;

import com.google.common.base.Preconditions;

/**
 * 工具类
 */
public class Utils {

	private static final String SEPARATOR = "_";

	private Utils() {
	}

	public static String getPath4Pkg(String pkg) {
		Preconditions.checkNotNull(pkg, "包名不能为空");
		return pkg.replace('.', '/');
	}

	/**
	 * 将以“_”分割的单词转换为首字母小写驼峰格式
	 * @param src
	 * @return
	 */
	public static String LOWER_CAMEL(String src) {
		Preconditions.checkNotNull(src);
		if (!src.contains(SEPARATOR)) {
			return Strings.lowerFirst(src);
		}

		String lowerSrc = src.toLowerCase();
		StringBuilder result = new StringBuilder();
		for (String item : lowerSrc.split(SEPARATOR)) {
			if (result.toString().length() == 0) {
				result.append(item);
			} else {
				result.append(upperFirst(item));
			}
		}
		return result.toString();
	}

	/**
	 * 以“_”分割的单词转换为首字母大写驼峰格式
	 * @param src
	 * @return
	 */
	public static String UPPER_CAMEL(String src) {
		Preconditions.checkNotNull(src);
		if (!src.contains(SEPARATOR)) {
			return upperFirst(src);
		}

		String lowerSrc = src.toLowerCase();
		StringBuilder result = new StringBuilder();
		for (String sitem : lowerSrc.split(SEPARATOR)) {
			if (result.toString().length() == 0) {
				result.append(upperFirst(sitem));
			} else {
				result.append(upperFirst(sitem));
			}
		}
		return result.toString();
	}

	/**
	 * 判断一个对象是否为空。它支持如下对象类型：
	 * <ul>
	 * <li>null : 一定为空
	 * <li>字符串     : ""为空,多个空格也为空
	 * <li>数组
	 * <li>集合
	 * <li>Map
	 * <li>其他对象 : 一定不为空
	 * </ul>
	 * 
	 * @param obj
	 *            任意对象
	 * @return 是否为空
	 */
	public final static boolean isEmpty(final Object obj) {
		if (obj == null) {
			return true;
		}
		if (obj instanceof String) {
			return "".equals(String.valueOf(obj).trim());
		}
		if (obj.getClass().isArray()) {
			return Array.getLength(obj) == 0;
		}
		if (obj instanceof Collection<?>) {
			return ((Collection<?>) obj).isEmpty();
		}
		if (obj instanceof Map<?, ?>) {
			return ((Map<?, ?>) obj).isEmpty();
		}
		return false;
	}

	/**
	* 将字符串首字母大写
	* 
	* @param s
	*            字符串
	* @return 首字母大写后的新字符串
	*/
	public static String upperFirst(CharSequence s) {
		if (null == s)
			return null;
		int len = s.length();
		if (len == 0)
			return "";
		char c = s.charAt(0);
		if (Character.isUpperCase(c))
			return s.toString();
		return new StringBuilder(len).append(Character.toUpperCase(c)).append(s.subSequence(1, len)).toString();
	}

	/**
	* 将字符串首字母小写
	* 
	* @param s
	*            字符串
	* @return 首字母小写后的新字符串
	*/
	public static String lowerFirst(CharSequence s) {
		if (null == s)
			return null;
		int len = s.length();
		if (len == 0)
			return "";
		char c = s.charAt(0);
		if (Character.isLowerCase(c))
			return s.toString();
		return new StringBuilder(len).append(Character.toLowerCase(c)).append(s.subSequence(1, len)).toString();
	}

}
