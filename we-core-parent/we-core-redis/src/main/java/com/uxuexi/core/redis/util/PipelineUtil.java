/**
 * PipelineUtil.java
 * com.uxuexi.dao.redis.util
 * Copyright (c) 2014, 北京聚智未来科技有限公司版权所有.
*/

package com.uxuexi.core.redis.util;

import static com.uxuexi.core.common.util.StringUtil.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.uxuexi.core.common.util.CollectionUtil;
import com.uxuexi.core.common.util.ConvertUtil;
import com.uxuexi.core.common.util.MapUtil;
import com.uxuexi.core.common.util.Util;

import redis.clients.jedis.Tuple;

/**
 * 处理pipeline返回结果
 *

 * @Date	 2013-4-10 	
 * @version  5.0.0 
 */
public final class PipelineUtil {
	private PipelineUtil() {

	}

	/**
	 * 将返回的数据进行类型转换，并排序
	 *
	 * @param re pipeline返回的sortedset结果
	 * @return 排序后的字符串
	 */
	@SuppressWarnings("unchecked")
	public static Set<String> unionSet(final List<Object> re) {
		List<Tuple> l = CollectionUtil.list();
		for (Object o : re) {
			if (o == null) {
				continue;
			}
			if (o instanceof Set) {
				l.addAll((Set<Tuple>) o);
			}
		}
		Collections.sort(l);
		Set<String> result = new LinkedHashSet<String>();
		//将list中的结果从最后一个开始往hashset中添加值
		if (Util.isEmpty(l)) {
			return new LinkedHashSet<String>();
		}
		for (int i = l.size() - 1; i >= 0; i--) {
			Tuple t = l.get(i);
			result.add(t.getElement());
		}
		return result;
	}

	/**
	 * 将pipeline返回的结果进行map转换
	 *
	 * @param re 返回值
	 * @return map的列表
	*/
	@SuppressWarnings("unchecked")
	public static List<Map<String, String>> hash(final List<Object> re) {
		if (Util.isEmpty(re)) {
			return CollectionUtil.list();
		}
		List<Map<String, String>> l = CollectionUtil.list();
		for (Object o : re) {
			if (Util.isEmpty(o)) {
				l.add(new HashMap<String, String>());
				continue;
			}
			if (o instanceof Map) {
				l.add((Map<String, String>) o);
			}
		}
		return l;
	}

	/**
	 * 将pipeline返回的结果进行类型转换
	 *
	 * @param re 返回值
	 * @return map的列表
	*/
	@SuppressWarnings("unchecked")
	public static <T> List<T> hash(final List<Object> re, final Class<T> clazz) {
		if (Util.isEmpty(re)) {
			return CollectionUtil.list();
		}
		List<T> l = CollectionUtil.list();
		for (Object o : re) {
			if (Util.isEmpty(o)) {
				l.add(null);
				continue;
			}
			if (o instanceof Map) {
				l.add(ConvertUtil.map2Object((Map<String, String>) o, clazz));
			}
		}
		return l;
	}

	/**
	 * 将pipeline返回的List<String>进行map转换
	 *
	 * @param re 返回值
	 * @param keys 键列表
	 * @return map的列表
	*/
	@SuppressWarnings("unchecked")
	public static List<Map<String, String>> hash(final List<Object> re, final List<String> keys) {
		List<Map<String, String>> l = CollectionUtil.list();
		for (Object o : re) {
			if (o == null) {
				l.add(new HashMap<String, String>());
			}
			if (o instanceof List) {
				l.add(toMap((List<String>) o, keys));
			}
		}
		return l;
	}

	/**
	 * 将2个list转换为map
	 *
	 * @param re 值列表
	 * @param keys 键列表
	 * @return map对象
	 */
	public static Map<String, String> toMap(final List<String> re, final List<String> keys) {
		if (Util.isEmpty(keys)) {
			return MapUtil.map();
		}
		int i = 0;
		Map<String, String> result = MapUtil.map();
		for (String key : keys) {
			result.put(key, get(re, i++));
		}
		return result;
	}

	/**
	 * 从列表中获取值
	 *
	 * @param l 列表
	 * @param i pos
	 * @return 返回值
	*/
	private static String get(final List<String> l, final int i) {
		if (l == null) {
			return "";
		}
		if (l.size() > i) {
			return nvl(l.get(i), "");
		}
		return "";
	}
}
