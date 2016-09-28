/**
 * EnumUtil.java
 * com.uxuexi.core.common.util
 * Copyright (c) 2014, 北京聚智未来科技有限公司版权所有.
*/

package com.uxuexi.core.common.util;

import java.util.LinkedHashMap;
import java.util.Map;

import com.uxuexi.core.common.enums.IEnum;

/**
 * 自定义枚举的工具类
 * 
 * @author   庄君祥
 * @Date	 2014-4-3 	
 */
public final class EnumUtil {

	/**    
	 * 将enum转换为map对象
	 * TODO 修改一个方法名enum2map
	 * <p>
	 * key是对应的key,value是显示的值
	 * @param en enum的类型
	 * @return map对象
	 */
	public static <T extends IEnum> Map<String, String> enum2(final Class<T> en) {
		Map<String, String> re = new LinkedHashMap<String, String>();
		if (en == null) {
			return re;
		}
		if (!en.isEnum()) {
			return re;
		}
		T[] arr = en.getEnumConstants();
		for (T one : arr) {
			if (one == null) {
				continue;
			}
			re.put(one.key(), one.value());
		}
		return re;
	}

	/**
	 * 获取枚举
	 *
	 * @param en 枚举类别
	 * @param key 值
	 * @return 枚举
	 */
	public static <T extends IEnum> T get(final Class<T> en, final String key) {
		if (en == null) {
			return null;
		}
		T[] arr = en.getEnumConstants();
		for (T one : arr) {
			if (Util.eq(key, one.key())) {
				return one;
			}
		}
		return null;
	}

	/**
	 * 通过枚举的名称获取
	 *
	 * @param en 枚举类别
	 * @param name 枚举名称
	 * @return 枚举
	 */
	public static <T extends IEnum> T getByEnumName(final Class<T> en, final String name) {
		if (en == null) {
			return null;
		}
		T[] arr = en.getEnumConstants();
		for (T one : arr) {
			if (one.toString().equalsIgnoreCase(name)) {
				return one;
			}
		}
		return null;
	}

	/**
	 * 判断枚举是否存在
	 *
	 * @param en 枚举类别
	 * @param key 值
	 * @return 枚举
	 */
	public static <T extends IEnum> boolean isContain(final Class<T> en, final String key) {
		return get(en, key) != null;
	}

	/**
	 * 获取枚举
	 *
	 * @param en 枚举类别
	 * @param key 值
	 * @return 枚举
	 */
	public static <T extends IEnum> T get(final Class<T> en, final int key) {
		return get(en, String.valueOf(key));
	}

	/**
	 * 获取枚举
	 *
	 * @param en 枚举类别
	 * @param key 值
	 * @return 枚举
	 */
	public static <T extends IEnum> String getValue(final Class<T> en, final int key) {
		T t = get(en, String.valueOf(key));
		return Util.isEmpty(t) ? "" : t.value();
	}

	/**
	 * 获取枚举
	 *
	 * @param en 枚举类别
	 * @param key 值
	 * @return 枚举
	 */
	public static <T extends IEnum> String getValue(final Class<T> en, final String key) {
		T t = get(en, key);
		return Util.isEmpty(t) ? "" : t.value();
	}

	/**
	 * 获取字符形键值
	 *
	 * @param en 枚举类别
	 * @param ele 枚举
	 * @return 键值
	 */
	public static <T extends IEnum> String getKey(final T ele) {
		return Util.isEmpty(ele) ? "-1" : ele.key();
	}

	/**
	 * 获取整形键值
	 *
	 * @param en 枚举类别
	 * @param ele 枚举
	 * @return 键值
	 */
	public static <T extends IEnum> int getKeyInt(final T ele) {
		return Util.isEmpty(ele) ? -1 : Integer.valueOf(ele.key());
	}
}
