/**
 * AnnotationUtil.java
 * com.uxuexi.common.util
 * Copyright (c) 2014, 北京聚智未来科技有限公司版权所有.
*/

package com.uxuexi.core.db.util;

import static com.uxuexi.core.common.util.ExceptionUtil.*;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

import org.nutz.dao.Cnd;
import org.nutz.dao.entity.annotation.Comment;
import org.nutz.dao.entity.annotation.Table;
import org.nutz.lang.Mirror;

import com.google.common.base.Function;
import com.google.common.collect.Lists;
import com.uxuexi.core.common.util.CollectionUtil;
import com.uxuexi.core.common.util.ConvertUtil;
import com.uxuexi.core.common.util.MapUtil;
import com.uxuexi.core.common.util.StringUtil;
import com.uxuexi.core.common.util.Util;
import com.uxuexi.core.db.dao.IDbDao;

/**
 * 实体相关的工具类
 * <p>
 * 根据类取得数据库表名
 * 创建实体等
 * @author   庄君祥

 * @Date	 2013-4-9 
 */
public class EntityUtil {

	/**
	 * 根据Table注解获取数据表名
	 * <p>
	 * 如果没有写的默认为：首字母小写，其他的大写字母用下划线替代
	 * @param clazz 类
	 * @return 对应的数据库表名
	 * @exception DataException 参数为null
	*/
	public static String getTableName(final Class<?> clazz) {
		checkNull(clazz, "类型不能为空");
		if (!clazz.isAnnotationPresent(Table.class)) {
			return eName2TName(clazz.getName());
		}
		return clazz.getAnnotation(Table.class).value();
	}

	/**
	 * 根据Table注解获取数据表名
	 * <p>
	 * 如果没有写的默认为：首字母小写，其他的大写字母用下划线替代
	 * @param clazz 类
	 * @return 对应的数据库表名
	 * @exception DataException 参数为null
	*/
	public static String getComment(final Class<?> clazz) {
		checkNull(clazz, "类型不能为空");
		if (!clazz.isAnnotationPresent(Comment.class)) {
			return eName2TName(clazz.getName());
		}
		return clazz.getAnnotation(Comment.class).value();
	}

	/**
	 * 根据实体名自动转成表名
	 * <p>
	 * 有大写的转成_拼上小写
	 * 如：
	 * <pre>
	 * RoleUser  -->role_user
	 * </pre>
	 * @param entityName
	 * @return 根据实体名自动转成表名
	*/
	public static String eName2TName(final String entityName) {
		StringBuilder sb = new StringBuilder();
		boolean isFirst = true;
		for (char c : entityName.toCharArray()) {
			if (isFirst) {
				sb.append(Character.toLowerCase(c));
				isFirst = false;
				continue;
			}
			if (Character.isUpperCase(c)) {
				sb.append("_").append(Character.toLowerCase(c));
			} else {
				sb.append(c);
			}
		}
		return sb.toString();
	}

	/**
	 * 根据类型获取实体的对象 
	 * 
	 * @param clazz 类型
	 * @return 实体对象
	*/
	public static final <T> T newInstance(final Class<T> clazz) {
		try {
			return clazz.newInstance();
		} catch (Throwable e) {
			throw pEx("创建泛型对象失败，请检查", e);
		}
	}

	/**
	 * 判断某列是否存在相同的数据
	 *
	 * @param dao 数据库
	 * @param clazz 实体
	 * @param id 自己的id
	 * @param col 列名
	 * @param value 值
	 * @return 是否存在相同的数据
	 */
	public static <T> boolean checkSame(final IDbDao dao, final Class<T> clazz, final long id, final String col,
			final Object value) {
		checkNull(dao, "数据库链接为空!");
		checkNull(clazz, "实体对象为空!");
		checkEmpty(col, "列名为空!");
		int same = DbExtendNewUtil.count(dao, clazz, Cnd.where("id", "<>", id).and(col, "=", value));
		if (same > 0) {
			return true;
		}
		return false;
	}

	public static <T> List<Long> tranIds(final List<T> entities) {
		return tranIds(entities, "id");
	}

	public static <T> List<Long> tranIds(final List<T> entities, final String fieldName) {
		if (Util.isEmpty(entities)) {
			return CollectionUtil.list();
		}
		List<Long> ids = CollectionUtil.list();
		for (T entity : entities) {
			long id = ConvertUtil.obj2long(Mirror.me(entity).getValue(entity, fieldName));
			if (id <= 0) {
				continue;
			}
			ids.add(id);
		}
		return ids;
	}

	public static <T> List<String> tranKeys(final List<T> entities, final String fieldName) {
		if (Util.isEmpty(entities)) {
			return CollectionUtil.list();
		}
		List<String> keys = CollectionUtil.list();
		for (T entity : entities) {
			String key = ConvertUtil.obj2str(Mirror.me(entity).getValue(entity, fieldName));
			if (Util.isEmpty(key)) {
				continue;
			}
			keys.add(key);
		}
		return keys;
	}

	public static <T> Map<Long, T> tranIdsMap(final List<T> entities) {
		return tranIdsMap(entities, "id");
	}

	public static <T> Map<Long, T> tranIdsMap(final List<T> entities, final String fieldName) {
		if (Util.isEmpty(entities)) {
			return MapUtil.map();
		}
		Map<Long, T> map = MapUtil.map();
		for (T entity : entities) {
			long id = ConvertUtil.obj2long(Mirror.me(entity).getValue(entity, fieldName));
			if (id <= 0) {
				continue;
			}
			map.put(id, entity);
		}
		return map;
	}

	/**
	 * TODO(这里用一句话描述这个方法的作用)
	 * <p>
	 * TODO(这里描述这个方法详情– 可选)
	 *
	 * @param entities
	 * @param fieldName
	 * @return TODO(这里描述每个参数,如果有返回值描述返回值,如果有异常描述异常)
	*/
	public static <T> Map<String, T> tranKeysMap(final List<T> entities, final String fieldName) {
		if (Util.isEmpty(entities)) {
			return MapUtil.map();
		}
		Map<String, T> map = MapUtil.map();
		for (T entity : entities) {
			String key = ConvertUtil.obj2str(Mirror.me(entity).getValue(entity, fieldName));
			if (Util.isEmpty(key)) {
				continue;
			}
			map.put(key, entity);
		}
		return map;
	}

	/**
	 * 从实体类得到单表查询该实体的sql
	 */
	public static String entityCndSql(final Class<?> entityClass) {
		Mirror<?> mirror = Mirror.me(entityClass);
		Field[] fields = mirror.getFields();
		List<String> fieldNames = Lists.transform(CollectionUtil.list(fields), new Function<Field, String>() {
			@Override
			public String apply(Field f) {
				return f.getName();
			}
		});

		StringBuffer sb = new StringBuffer("SELECT ");
		sb.append(StringUtil.join(",", fieldNames));
		sb.append(" FROM " + getTableName(entityClass));
		sb.append(" T $condition ");
		return sb.toString();
	}

}
