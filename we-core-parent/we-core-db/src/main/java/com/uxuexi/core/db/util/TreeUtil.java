/**
 * TreeUtil.java
 * cn.vko.util
 * Copyright (c) 2012, 北京微课创景教育科技有限公司版权所有.
*/

package com.uxuexi.core.db.util;

import static org.nutz.lang.Lang.*;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.nutz.dao.Sqls;
import org.nutz.dao.sql.Sql;

import com.uxuexi.core.common.util.CollectionUtil;
import com.uxuexi.core.common.util.MapUtil;
import com.uxuexi.core.common.util.Util;
import com.uxuexi.core.db.dao.IDbDao;
import com.uxuexi.core.db.entity.impl.ITreeEntity;
import com.uxuexi.core.db.entity.impl.TreeEntity;
import com.uxuexi.core.db.support.WeCnd;

/**
 * 用于处理树状结构的工具类
 * @author   彭文杰
 * @Date	 2012-5-5 	 
 */
public class TreeUtil {
	/**
	 * 把列表的树节点构造为树状，只返回最根节点列表
	 *
	 * @param allNodes 所有树的节点
	 * @return 根节点
	 */

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static <T extends ITreeEntity> List<T> buildTree(final Collection<T> allNodes) {
		List<T> result = list();
		if (Util.isEmpty(allNodes)) {
			return result;
		}
		Map<Long, T> ids = MapUtil.map();
		for (T te : allNodes) {
			ids.put(te.getId(), te);
			te.clearSub();
		}
		for (T te : allNodes) {
			if (te.isFirst() || !ids.containsKey(te.getParentId())) {
				result.add(te);
			} else {
				if (ids.containsKey(te.getParentId())) {
					ids.get(te.getParentId()).addSub(te);
				}
			}
		}
		Collections.sort(result);
		return result;
	}

	/**
	 * 根据父节点查询所有的子节点
	 * @param dbDao 数据库操作对象
	 * @param parentId 父节点id
	 * @param clazz 该类必须是继承了TreeEntity 
	 * @return 
	*/
	private static List<Long> querySub(final IDbDao dbDao, final List<Long> parentIds,
			final Class<? extends TreeEntity> clazz) {
		List<Long> allIds = CollectionUtil.list();
		List<Long> subIds = CollectionUtil.list();
		Sql sql = Sqls.create("select id from $table $condition");
		sql.setCondition(WeCnd.where("parentId", "in", parentIds));
		sql.vars().set("table", EntityUtil.getTableName(clazz));
		subIds = DbSqlUtil.queryLongList(dbDao, sql);
		allIds.addAll(parentIds);
		if (Util.isEmpty(subIds)) {
			return allIds;
		}
		allIds.addAll(querySub(dbDao, subIds, clazz));
		return allIds;
	}

	/**
	 * 根据父节点查询所有的子节点
	 * @param dbDao 数据库操作对象
	 * @param parentId 父节点id
	 * @param clazz 该类必须是继承了TreeEntity 
	 * @return 
	*/
	public static List<Long> querySub(final IDbDao dbDao, final long parentId, final Class<? extends TreeEntity> clazz) {
		return querySub(dbDao, CollectionUtil.list(parentId), clazz);
	}
}
