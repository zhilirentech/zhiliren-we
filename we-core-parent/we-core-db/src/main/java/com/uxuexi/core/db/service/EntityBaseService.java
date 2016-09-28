/**
 * BaseService.java
 * com.uxuexi.core.db.service
 * Copyright (c) 2015, 北京聚智未来科技有限公司版权所有.
*/

package com.uxuexi.core.db.service;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import com.uxuexi.core.common.util.ExceptionUtil;
import com.uxuexi.core.db.dao.IDbDao;
import com.uxuexi.core.db.util.EntityUtil;

/**
 * 基础服务
 *
 * @author   庄君祥
 * @Date	 2015年10月14日 	 
 */
public class EntityBaseService<T> {
	protected IDbDao dbDao;

	/**
	 * 获取对象
	 * 
	 *
	 * @param id
	 * @return 对象。如果取不到返回null
	*/
	@SuppressWarnings("unchecked")
	public T get(long id) {
		ExceptionUtil.checkId(id, EntityUtil.getComment(getEntityClass()));
		Class<?> entityClass = getEntityClass();
		return (T) dbDao.fetch(entityClass, id);
	}

	/**
	 * 获取对象
	 *
	 * @param id id
	 * 
	 * @return 对象
	 * throws ParamException 如果没有取到对象会抛出异常
	*/
	public T getWithEx(long id) {
		T entity = get(id);
		ExceptionUtil.checkEmpty(entity, EntityUtil.getComment(getEntityClass()) + ":{0}没有对应的对象", String.valueOf(id));
		return entity;
	}

	/**
	 * 获取实体的class
	 *
	 * @return 实体的class
	*/
	private Class<?> getEntityClass() {
		Type genType = getClass().getGenericSuperclass();
		if (!(genType instanceof ParameterizedType)) {
			return Object.class;
		}
		Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
		return (Class<?>) params[0];
	}
}
