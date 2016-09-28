/**
 * BeanUtil.java
 * com.uxuexi.util
 * Copyright (c) 2012, 北京聚智未来科技有限公司版权所有.
*/

package com.uxuexi.core.common.util;

import static com.uxuexi.core.common.util.ExceptionUtil.*;
import static com.uxuexi.core.common.util.Util.*;

import java.lang.reflect.Field;
import java.util.Map;

import org.nutz.castor.Castors;
import org.nutz.castor.FailToCastObjectException;
import org.nutz.lang.Lang;
import org.nutz.lang.Mirror;

/**
 * 封装了bean的操作
 * <p>
 * 从一个bean copy到另一个bean
 * <p>
 * 依赖了nutz 的Mirror
 * @author   庄君祥

 * @Date	 2014-4-3 
 * @see Mirror 	
 */
public final class BeanUtil {

	/**
	 * 拷贝一个对象属性到另一个对象
	 * <p>
	 * 依赖Mirror的功能
	 * @param target  目标对象
	 * @param source 源对象 
	 * @see Mirror
	*/
	public static void copyProperties(final Object source, final Object target) {
		Mirror<?> sourceMirror = Mirror.me(source);
		Field[] sourceFds = sourceMirror.getFields();

		Mirror<?> targetMirror = Mirror.me(target);
		Field[] targetFds = targetMirror.getFields();

		for (Field sourceFd : sourceFds) {
			String fieldName = sourceFd.getName();
			if (!contain(targetFds, fieldName)) {
				continue;
			}
			Object value = null;
			try {
				value = sourceMirror.getValue(source, sourceFd);
				targetMirror.setValue(target, fieldName, value);
			} catch (Exception e) {
				throw pEx("对象拷贝时出现异常", e);
			}
		}
	}

	/**
	 * 判断是否存在某属性
	 *
	 * @param fds 属性列表
	 * @param name 属性名
	 * @return 是否存在
	 */
	private static boolean contain(final Field[] fds, final String name) {
		if (isEmpty(fds) || isEmpty(name)) {
			return false;
		}
		for (Field fd : fds) {
			if (fd == null) {
				continue;
			}
			if (name.equals(fd.getName())) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 对象是否包含某个属性
	 * @param obj 需要判断的对象
	 * @param propertyName 属性名称
	 * @return 如果对象包含该属性返回true,否则返回false
	*/
	public static boolean contain(final Object obj, final String propertyName) {
		Mirror<?> mirror = Mirror.me(obj);
		try {
			Field fd = mirror.getField(propertyName);
			return fd != null;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * 直接获取某属性
	 *
	 * @param obj 对象
	 * @param propertyName 属性名
	 * @return 属性值
	 */
	public static Object get(final Object obj, final String propertyName) {
		Mirror<?> mirror = Mirror.me(obj);
		try {
			return mirror.getValue(obj, propertyName);
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 直接获取某属性
	 *
	 * @param obj 对象
	 * @param propertyName 属性名
	 * @return 属性值
	 */
	public static String getString(final Object obj, final String propertyName) {
		Mirror<?> mirror = Mirror.me(obj);
		return Castors.me().castToString(mirror.getValue(obj, propertyName));
	}

	/**
	 * 判断属性是否为空
	 * <p>
	 * <ul>
	 * <li>如果是int或者long时，值为0时，则为empty
	 * <li>对象为null的时候，为empty
	 * </ul>
	 * <p>
	 * 其他的参见Util.weIsEmpty()方法
	 * @param fieldName 属性名
	 * @return 是否为空
	*/
	public static boolean isFieldEmpty(final Object source, final String fieldName) {
		if (source == null || isEmpty(fieldName)) {
			return true;
		}
		Field fd = null;
		try {
			//fd = source.getClass().getDeclaredField(fieldName);
			for (Class<?> clazzz = source.getClass(); clazzz != Object.class; clazzz = clazzz.getSuperclass()) {

				Field[] tempArr = clazzz.getDeclaredFields();
				for (int i = tempArr.length - 1; i >= 0; i--) {
					if (fieldName.equals(tempArr[i].getName())) {
						fd = tempArr[i];
						break;
					}
				}
				if (fd != null) {
					break;
				}
			}
			if (fd == null) {
				return true;
			}
		} catch (SecurityException e) {
			return true;
		} catch (Exception e) {
			return true;
		}
		Object value = Mirror.me(source).getValue(source, fd);
		return isEmpty(value);
	}

	/**
	 * 把map转为POJO对象
	 *
	 * @param map map
	 * @param toType 目标类型
	 * @return 目标类型的POJO对象
	 * @throws FailToCastObjectException 无法转为对象异常
	*/
	public static <T> T map2Object(final Map<?, ?> map, final Class<T> toType) {
		return Lang.map2Object(map, toType);
	}

	/**
	* 将对象转换成 Map
	* 
	* @param obj  POJO对象
	* @return Map 对象
	*/
	public static Map<String, Object> obj2Map(final Object obj) {
		return Lang.obj2map(obj);
	}
}
