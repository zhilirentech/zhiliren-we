/**
 * DbExendUtil.java
 * com.uxuexi.core.db.util
 * Copyright (c) 2014, 北京聚智未来科技有限公司版权所有.
*/

package com.uxuexi.core.db.util;

import org.nutz.dao.Chain;
import org.nutz.dao.Cnd;
import org.nutz.dao.Condition;
import org.nutz.dao.Sqls;
import org.nutz.dao.sql.Sql;

import com.uxuexi.core.common.util.ExceptionUtil;
import com.uxuexi.core.db.dao.IDbDao;

/**
 * 数据库功能扩展工具类
 *
 * @author   庄君祥
 * @Date	 2013-12-9 	 
 */
public class DbExtendNewUtil {

	/**
	 * 查询数量
	 *
	 * @param dbDao dao对象
	 * @param clazz 类型
	 * @return 数量
	*/
	public static int count(final IDbDao dbDao, final Class<?> clazz) {
		return count(dbDao, clazz, null);
	}

	/**
	 * 查询数量
	 *
	 * @param dbDao dao对象
	 * @param clazz 类型
	 * @param cnd 条件
	 * @return 数量
	*/
	public static int count(final IDbDao dbDao, final Class<?> clazz, final Condition cnd) {
		return count(dbDao, EntityUtil.getTableName(clazz), cnd);
	}

	/**
	 * 查询数量
	 *
	 * @param dbDao dao对象
	 * @param tableName 表名
	 * @return 数量
	*/
	public static int count(final IDbDao dbDao, final String tableName) {
		return count(dbDao, tableName, null);
	}

	/**
	 * 查询数量
	 *
	 * @param dbDao dao对象
	 * @param tableName 表名
	 * @param cnd 条件
	 * @return 数量
	*/
	public static int count(final IDbDao dbDao, final String tableName, final Condition cnd) {
		Sql sql = Sqls.create("select count(*) from $tableName $condition");
		sql.vars().set("tableName", tableName).set("condition", cnd);
		return DbSqlUtil.fetchInt(dbDao, sql);
	}

	/**
	 * 假删
	 *
	 * @param dbDao 数据库
	 * @param classOfT 对象
	 * @param ids 对象ids
	 * @return 禁用条数
	 */
	public static int disable(final IDbDao dbDao, final Class<?> clazz, final long... ids) {
		return disable(dbDao, EntityUtil.getTableName(clazz), ids);
	}

	/**
	 * 禁用
	 *
	 * @param dbDao
	 * @param tableName 表名
	 * @param ids 筛选主键集合
	 * @return 成功的条数
	 */
	public static int disable(final IDbDao dbDao, final String tableName, final long... ids) {
		ExceptionUtil.checkEmpty(ids, "要操作的主键不允许为空");
		return disable(dbDao, tableName, Cnd.where("id", "in", ids));
	}

	/**
	 * 禁用
	 *
	 * @param dbDao
	 * @param tableName 表名
	 * @param cnd 筛选条件
	 * @return 成功的条数
	 */
	public static int disable(final IDbDao dbDao, final String tableName, final Condition cnd) {
		return updateStatus(dbDao, tableName, false, cnd);
	}

	/**
	 * 禁用
	 *
	 * @param dbDao
	 * @param clazz
	 * @param cnd
	 * @return 禁用的条数
	*/
	public static int disable(final IDbDao dbDao, final Class<?> clazz, final Condition cnd) {
		return disable(dbDao, EntityUtil.getTableName(clazz), cnd);
	}

	/**
	 * 启用statusEntity
	 *
	 * @param dbDao 数据库
	 * @param clazz 对象
	 * @param ids 对象ids
	 * @return 启用条数
	 */
	public static int enable(final IDbDao dbDao, final Class<?> clazz, final long... ids) {
		return enable(dbDao, EntityUtil.getTableName(clazz), ids);
	}

	/**
	 * 启用
	 *
	 * @param dbDao
	 * @param tableName 表名
	 * @param ids 筛选主键集合
	 * @return 成功的条数
	 */
	public static int enable(final IDbDao dbDao, final String tableName, final long... ids) {
		ExceptionUtil.checkEmpty(ids, "要操作的主键不允许为空");
		return enable(dbDao, tableName, Cnd.where("id", "in", ids));
	}

	/**
	 * 启用
	 *
	 * @param dbDao
	 * @param tableName 表名
	 * @param cnd 筛选条件
	 * @return 成功的条数
	 */
	protected static int enable(final IDbDao dbDao, final String tableName, final Condition cnd) {
		return updateStatus(dbDao, tableName, true, cnd);
	}

	/**
	 * 更新状态
	 *
	 * @param dbDao dao对象
	 * @param tableName 表名
	 * @param valid 状态值
	 * @param cnd 条件
	 * @return 更新的行数
	*/
	private static <T> int updateStatus(final IDbDao dbDao, final String tableName, final boolean valid,
			final Condition cnd) {
		ExceptionUtil.checkEmpty(tableName, "表不允许为空");
		return dbDao.update(tableName, Chain.make("isValid", valid), cnd);
	}
}
