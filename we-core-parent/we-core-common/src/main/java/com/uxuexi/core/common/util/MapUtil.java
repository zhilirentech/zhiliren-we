/**
 * MapUtil.java
 * com.uxuexi.common.util
 * Copyright (c) 2014, 北京聚智未来科技有限公司版权所有.
*/

package com.uxuexi.core.common.util;

import static com.uxuexi.core.common.util.ExceptionUtil.*;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 有关map处理的工具类
 * 
 * @author   庄君祥
 * @Date	 2014-4-3  	 
 */
public final class MapUtil {

	/**
	 * 
	 * 较方便的创建一个map
	 * <p>
	 * 注，这里的 Map，是 HashMap 的实例
	 * 
	 * @param <K> key的类型
	 * @param <V> value的类型
	 * @return map对象
	 */
	public static <K, V> Map<K, V> map() {
		return new HashMap<K, V>();
	}

	/**
	 * 
	 * 较方便的创建一个map
	 * <p>
	 * 注，这里的 Map线程安全，是 ConcurrentHashMap的实例
	 * 
	 * @param <K> key的类型
	 * @param <V> value的类型
	 * @return map对象
	 */
	public static <K, V> Map<K, V> concurrentMap() {
		return new ConcurrentHashMap<K, V>();
	}

	/**
	 * 
	 * 较方便的创建一个map
	 * <p>
	 * 注，这里的 Map，是 HashMap 的实例
	 * 
	 * @param <K> key的类型
	 * @param <V> value的类型
	 * @param key 键
	 * @param value 值
	 * @return map对象，包含key、value
	 */
	public static <K, V> Map<K, V> map(final K key, final V value) {
		Map<K, V> map = map();
		map.put(key, value);
		return map;
	}

	/**
	 * 较方便的创建一个map
	 * <p>
	 * 奇数个作为key，偶数个作为value 生成map对象
	 * 请注意数据的类型，要是不匹配，在获取时会提示 java.lang.ClassCastException
	 * @param args 参数
	 * @return map对象
	 * @throws DataException 如果参数为奇数个抛出该异常
	 */
	public static <K, V> Map<K, V> map(final Object... args) {
		Map<K, V> map = map();
		evenoddMap(map, args);
		return map;
	}

	/**
	 * 将数据奇数个作为key，偶数个作为value，填充到对象中
	 * 请注意数据的类型，要是不匹配，在获取时会提示 java.lang.ClassCastException
	 * @param <K> key的类型
	 * @param <V> value的类型
	 * @param map 填充的map
	 * @param args 参数列表
	 * @throws DataException 如果参数为奇数个抛出该异常
	 */
	@SuppressWarnings({ "unchecked" })
	private static <K, V> void evenoddMap(final Map<K, V> map, final Object... args) {
		if (args.length % 2 != 0) {
			throw pEx("生成map时,元素为单数!");
		}
		for (int i = 0; i < args.length; i += 2) {
			map.put((K) args[i], (V) args[i + 1]);
		}
	}

	/**
	 * 
	 * 较方便的创建一个map
	 * <p>
	 * 注，这里的 Map，是 LinkedHashMap 的实例
	 * 
	 * @param <K> key的类型
	 * @param <V> value的类型
	 * @return map对象
	 */
	public static <K, V> Map<K, V> linkedMap() {
		return new LinkedHashMap<K, V>();
	}

	/**
	 * 
	 * 较方便的创建一个map
	 * <p>
	 * 注，这里的 Map，是 LinkedHashMap 的实例
	 * 
	 * @param <K> key的类型
	 * @param <V> value的类型
	 * @param key 键
	 * @param value 值
	 * @return map对象，包含key、value
	 */
	public static <K, V> Map<K, V> linkedMap(final K key, final V value) {
		Map<K, V> map = linkedMap();
		map.put(key, value);
		return map;
	}

	/**
	 * 较方便的创建一个LinkedHashMap
	 * <p>
	 * 奇数个作为key，偶数个作为value 生成map对象
	 * @param args 参数
	 * @return map对象
	 * @throws DataException 如果参数为基数个抛出该异常
	 */
	public static <K, V> Map<K, V> linkedMap(final Object... args) {
		Map<K, V> map = linkedMap();
		evenoddMap(map, args);
		return map;
	}

	/**
	 * 向map的列表中添加对象
	 * <p>
	 * 如果map不存在
	 *
	 * @param <K> key类型
	 * @param <V> value类型
	 * @param map 要添加的map
	 * @param key 键
	 * @param value 值
	 * @throws DataException map为null，则提示DataException
	 */
	@SuppressWarnings("unchecked")
	public static <K, V> Map<K, List<V>> addMapList(final Map<K, List<V>> map, final K key, final V value) {
		checkNull(map, "填充map时,数据为空!");
		if (map.containsKey(key)) {
			map.get(key).add(value);
		} else {
			map.put(key, CollectionUtil.list(value));
		}
		return map;
	}

	/**
	 * 向map的列表中添加对象
	 * <p>
	 * 如果map不存在
	 *
	 * @param <K> key类型
	 * @param <V> value类型
	 * @param map 要添加的map
	 * @param key 键
	 * @param value 值
	 * @throws DataException map为null，则提示DataException
	 */
	@SuppressWarnings("unchecked")
	public static <K, V> Map<K, Set<V>> addMapSet(final Map<K, Set<V>> map, final K key, final V value) {
		checkNull(map, "填充map时,数据为空!");
		if (map.containsKey(key)) {
			map.get(key).add(value);
		} else {
			map.put(key, CollectionUtil.set(value));
		}
		return map;
	}
}
