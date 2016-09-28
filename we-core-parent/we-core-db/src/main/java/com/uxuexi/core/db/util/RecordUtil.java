/**
 * RecordUtil.java
 * com.uxuexi.util
 * Copyright (c) 2012, 北京聚智未来科技有限公司版权所有.
*/

package com.uxuexi.core.db.util;

import static org.nutz.castor.Castors.*;

import java.sql.Date;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.nutz.castor.Castors;
import org.nutz.dao.entity.Record;

import com.uxuexi.core.common.util.CollectionUtil;
import com.uxuexi.core.common.util.MapUtil;

/**
 * 用来处理record工具类
 * <p>
 * 参见Record的getXxx()方法
 * 
 * @author   庄君祥

 * @Date	 2013-3-27 	 
 * @version  0.0.0
 * @see Record
 */
public class RecordUtil {
	/**
	 * 获取Long型数据
	 * <p>
	 * 如果取不到值返回0
	 * 
	 * @param r 记录
	 * @param name 数据键值
	 * @return 数据
	 */
	public static long getLong(final Record r, final String name) {
		if (r == null) {
			return 0L;
		}
		if (name == null) {
			return 0L;
		}
		Object value = r.get(name);
		if (value == null) {
			return 0L;
		}
		Long result = 0L;
		try {
			result = me().castTo(value, Long.class);
		} catch (Exception e) {
			result = 0L;
		}
		if (result == null) {
			return 0L;
		}
		return result;
	}

	/**
	 * 获取java.sql.Date数据
	 * 
	 * @param r 记录
	 * @param name 数据的键值
	 * @return 数据
	 */
	public static Date getDate(final Record r, final String name) {
		if (r == null) {
			return null;
		}
		if (name == null) {
			return null;
		}
		Object value = r.get(name);
		if (value == null) {
			return null;
		}
		return me().castTo(value, Date.class);
	}

	/**
	 * 获取布尔数据
	 * 
	 * @param r 记录
	 * @param name 数据的键值
	 * @return 数据
	 */
	public static Boolean getBoolean(final Record r, final String name) {
		if (r == null) {
			return false;
		}
		if (name == null) {
			return false;
		}
		Object value = r.get(name);
		if (value == null) {
			return false;
		}
		return me().castTo(value, Boolean.class);
	}

	/**
	 * 获取Double数据
	 * 
	 * @param r 记录
	 * @param name 数据的键值
	 * @return 数据
	 */
	public static double getDouble(final Record r, final String name) {
		if (r == null) {
			return 0;
		}
		if (name == null) {
			return 0;
		}
		Object value = r.get(name);
		if (value == null) {
			return 0;
		}
		Double result = 0.0;
		try {
			result = me().castTo(value, Double.class);
		} catch (Exception e) {
			result = 0.0;
		}
		if (result == null) {
			return 0;
		}
		return result;
	}

	/**
	 * 获取List数据
	 * @param r 记录
	 * @param name 数据的键值
	 * @return 数据
	 */
	public static List<?> getList(final Record r, final String name) {
		if (r == null) {
			return CollectionUtil.list();
		}
		if (name == null) {
			return CollectionUtil.list();
		}
		Object value = r.get(name);
		if (value == null) {
			return CollectionUtil.list();
		}
		return me().castTo(value, List.class);
	}

	public static <T> Map<T, Record> list2map(final Class<T> keyClass, final Collection<Record> coll,
			final String keyFieldName) {
		Map<T, Record> map = MapUtil.map();
		for (Record one : coll) {
			map.put(Castors.me().castTo(one.get(keyFieldName), keyClass), one);
		}
		return map;
	}
}
