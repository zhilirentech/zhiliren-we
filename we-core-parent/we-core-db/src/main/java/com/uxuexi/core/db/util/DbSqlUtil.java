/**
 * DbUtil.java
 * com.uxuexi.core.db.util
 * Copyright (c) 2014, 北京聚智未来科技有限公司版权所有.
*/

package com.uxuexi.core.db.util;

import static com.uxuexi.core.common.util.CollectionUtil.*;
import static com.uxuexi.core.common.util.ConvertUtil.*;
import static com.uxuexi.core.common.util.ExceptionUtil.*;

import java.util.List;
import java.util.Map;

import org.nutz.dao.Condition;
import org.nutz.dao.Sqls;
import org.nutz.dao.entity.Record;
import org.nutz.dao.pager.Pager;
import org.nutz.dao.sql.Sql;
import org.nutz.dao.sql.SqlCallback;

import com.uxuexi.core.common.util.ArrayUtil;
import com.uxuexi.core.common.util.BeanUtil;
import com.uxuexi.core.common.util.Util;
import com.uxuexi.core.db.dao.IDbDao;

/**
 * 数据库sql工具类
 * <p>
 * 主要针对sql的类型转换封装
 *
 * @author   庄君祥
 * @Date	 2013-12-6 	 
 */
public class DbSqlUtil {
	/**
	 * 获取对象
	 *
	 * @param dbDao dbDao对象
	 * @param clazz 类型
	 * @param sql sql语句
	 * @return 对象 如果没有结果，则返回null
	 */
	public static <T> T fetchEntity(final IDbDao dbDao, final Class<T> clazz, final Sql sql) {
		Record record = dbDao.fetch(sql);
		if (Util.isEmpty(record)) {
			return null;
		}
		return BeanUtil.map2Object(record, clazz);
	}

	/**
	 * 获取数字型
	 *
	 * @param dbDao dbDao对象
	 * @param sql sql语句
	 * @return 数字型，如果没有结果，则返回-1
	 */
	public static int fetchInt(final IDbDao dbDao, final Sql sql) {
		return obj2int(fetchResult(dbDao, sql, Sqls.callback.integer()));
	}

	/**
	 * 获取数字型
	 *
	 * @param dbDao dbDao对象
	 * @param sqlStr 语句
	 * @return 数字型，如果没有结果，则返回-1
	 */
	public static int fetchInt(final IDbDao dbDao, final String sqlStr) {
		return fetchInt(dbDao, Sqls.create(sqlStr));
	}

	/**
	 * 获取浮点型
	 *
	 * @param dbDao dbDao对象
	 * @param sql sql语句
	 * @return 数字型，如果没有结果，则返回-1
	 */
	public static double fetchDouble(final IDbDao dbDao, final Sql sql) {
		return obj2double(fetchResult(dbDao, sql, Sqls.callback.doubleValue()));
	}

	/**
	 * 获取数字型
	 *
	 * @param dbDao dbDao对象
	 * @param sqlStr 语句
	 * @return 数字型，如果没有结果，则返回-1
	 */
	public static double fetchDouble(final IDbDao dbDao, final String sqlStr) {
		return fetchInt(dbDao, Sqls.create(sqlStr));
	}

	/**
	 * 获取String型
	 *
	 * @param dbDao
	 * @param sql
	 * @return String型数据
	 */
	public static String fetchString(final IDbDao dbDao, final Sql sql) {
		return obj2str(fetchResult(dbDao, sql, Sqls.callback.str()));
	}

	/**
	 * 获取String型
	 *
	 * @param dbDao
	 * @param sqlStr sql语句
	 * @return String型数据
	 */
	public static String fetchString(final IDbDao dbDao, final String sqlStr) {
		return fetchString(dbDao, Sqls.create(sqlStr));
	}

	/**
	 * 获取long型
	 *
	 * @param dbDao dbDao对象
	 * @param sql sql语句
	 * @return long型，如果没有结果，则返回-1
	 */
	public static long fetchLong(final IDbDao dbDao, final Sql sql) {
		return obj2long(fetchResult(dbDao, sql, Sqls.callback.longValue()));
	}

	/**
	 * 获取long型
	 *
	 * @param dbDao dbDao对象
	 * @param sqlStr 语句
	 * @return long型，如果没有结果，则返回-1
	 */
	public static long fetchLong(final IDbDao dbDao, final String sqlStr) {
		return fetchLong(dbDao, Sqls.create(sqlStr));
	}

	/**
	 * 查询某类型的数据
	 *
	 * @param dbDao dbDao对象
	 * @param sql sql语句
	 * @param countSql 计数语句 可以为空，为空则不统计条数
	 * @param pager 分页对象，可以为空，为空则不分页，也不统计条数
	 * @param callback 返回值类型
	 * @return 数据
	 */
	private static Object getResult(final IDbDao dbDao, final Sql sql, final Sql countSql, final Pager pager,
			final SqlCallback callback) {
		checkSql(sql);
		if (pager != null) {
			if (pager.getPageNumber() <= 0) {
				pager.setPageNumber(1);
			}
			sql.setPager(pager);
			if (countSql != null && countSql.isSelect()) {
				pager.setRecordCount(fetchInt(dbDao, countSql));
			}
		}
		sql.setCallback(callback);
		dbDao.execute(sql);
		return sql.getResult();
	}

	/**
	 * 校验sql是否合法
	 *
	 * @param sql sql对象
	 */
	private static void checkSql(final Sql sql) {
		checkNull(sql, "sql不允许不为空");
		if (!sql.isSelect()) {
			throw pEx("执行查询sql时，sql不是select语句！");
		}
	}

	/**
	 * 查询某类型的数据,最多返回一行
	 *
	 * @param dbDao
	 * @param sql
	 * @param callback 获取的数据类型
	 */
	private static Object fetchResult(final IDbDao dbDao, final Sql sql, final SqlCallback callback) {
		checkSql(sql);
		boolean isLimit = false;
		if (sql.toString().toLowerCase().indexOf("limit") == -1) {
			isLimit = true;
			sql.setPager(new Pager().setPageSize(1));
		}
		Object obj = getResult(dbDao, sql, null, null, callback);
		if (isLimit) {
			sql.setPager(null);
		}
		return obj;
	}

	/**
	 * 获取数字[]型
	 *
	 * @see getResult
	 */
	public static int[] queryInt(final IDbDao dbDao, final Sql sql) {
		return queryInt(dbDao, sql, null);
	}

	/**
	 * 获取数字[]型
	 *
	 * @see getResult
	 */
	public static int[] queryInt(final IDbDao dbDao, final Sql sql, final Pager pager) {
		return queryInt(dbDao, sql, null, pager);
	}

	/**
	 * 获取数字[]型
	 *
	 * @see getResult
	 */
	public static int[] queryInt(final IDbDao dbDao, final Sql sql, final Sql countSql, final Pager pager) {
		return (int[]) getResult(dbDao, sql, countSql, pager, Sqls.callback.ints());
	}

	/**
	 * 获取数字[]型
	 *
	 * @see getResult
	 */
	public static int[] queryInt(final IDbDao dbDao, final String sqlStr, final String countSqlStr, final Pager pager) {
		return queryInt(dbDao, Sqls.create(sqlStr), Sqls.create(countSqlStr), pager);
	}

	/**
	 * 获取long[]型
	 *
	 * @see getResult
	 */
	public static long[] queryLong(final IDbDao dbDao, final Sql sql) {
		return queryLong(dbDao, sql, null);
	}

	/**
	 * 获取long[]型
	 *
	 * @see getResult
	 */
	public static long[] queryLong(final IDbDao dbDao, final Sql sql, final Pager pager) {
		return queryLong(dbDao, sql, null, pager);
	}

	/**
	 * 获取long[]型
	 *
	 * @see getResult
	 */
	public static long[] queryLong(final IDbDao dbDao, final Sql sql, final Sql countSql, final Pager pager) {
		return (long[]) getResult(dbDao, sql, countSql, pager, Sqls.callback.longs());
	}

	/**
	 * 获取long[]型
	 *
	 * @see getResult
	 */
	public static long[] queryLong(final IDbDao dbDao, final String sqlStr, final String countSqlStr, final Pager pager) {
		return queryLong(dbDao, Sqls.create(sqlStr), Sqls.create(countSqlStr), pager);
	}

	/**
	 * 获取结果为long类型列表
	 *
	 * @param dbDao dbDao
	 * @param sql sql
	 * @return 结果
	*/
	public static List<Long> queryLongList(final IDbDao dbDao, final Sql sql) {
		return queryLongList(dbDao, sql, null);
	}

	/**
	 * 获取结果为long类型列表
	 *
	 * @param dbDao
	 * @param sql
	 * @param pager 分页。如果为null，则不用分页
	 * @return 结果
	 */
	public static List<Long> queryLongList(final IDbDao dbDao, final Sql sql, final Pager pager) {
		return queryLongList(dbDao, sql, null, pager);
	}

	/**
	 * 获取结果为long类型列表
	 *
	 * @param dbDao
	 * @param sql
	 * @param countSql 统计总个数的语句。如果为null，则无需统计总个数
	 * @param pager 分页。如果为null，则不用分页
	 * @return 结果
	 */
	public static List<Long> queryLongList(final IDbDao dbDao, final Sql sql, final Sql countSql, final Pager pager) {
		return ArrayUtil.array2List((long[]) getResult(dbDao, sql, countSql, pager, Sqls.callback.longs()));
	}

	/**
	 * 获取结果为long类型列表
	 *
	 * @param dbDao
	 * @param sqlStr sql语句
	 * @param countSqlStr 统计总个数的语句。如果为null，则无需统计总个数
	 * @param pager 分页。如果为null，则不用分页
	 * @return 结果
	 */
	public static List<Long> queryLongList(final IDbDao dbDao, final String sqlStr, final String countSqlStr,
			final Pager pager) {
		return queryLongList(dbDao, Sqls.create(sqlStr), Sqls.create(countSqlStr), pager);
	}

	/**
	 * 获取结果为String类型列表
	 *
	 * @param dbDao dbDao
	 * @param sql sql
	 * @return String类型列表
	*/
	public static List<String> queryStringList(final IDbDao dbDao, final Sql sql) {
		return queryStringList(dbDao, sql, null);
	}

	/**
	 * 获取结果为String类型列表
	 *
	 * @param dbDao
	 * @param sql
	 * @param pager 分页。如果为null，则不用分页
	 * @return 结果
	 */
	public static List<String> queryStringList(final IDbDao dbDao, final Sql sql, final Pager pager) {
		return queryStringList(dbDao, sql, null, pager);
	}

	/**
	 * 获取结果为String类型列表
	 *
	 * @param dbDao
	 * @param sql
	 * @param countSql 统计总个数的语句。如果为null，则无需统计总个数
	 * @param pager 分页。如果为null，则不用分页
	 * @return 结果
	 */
	@SuppressWarnings("unchecked")
	public static List<String> queryStringList(final IDbDao dbDao, final Sql sql, final Sql countSql, final Pager pager) {
		return (List<String>) getResult(dbDao, sql, countSql, pager, Sqls.callback.strList());
	}

	/**
	 * 获取结果为String类型列表
	 *
	 * @param dbDao
	 * @param sqlStr sql语句
	 * @param countSqlStr 统计总个数的语句。如果为null，则无需统计总个数
	 * @param pager 分页。如果为null，则不用分页
	 * @return 结果
	 */
	public static List<String> queryStringList(final IDbDao dbDao, final String sqlStr, final String countSqlStr,
			final Pager pager) {
		return queryStringList(dbDao, Sqls.create(sqlStr), Sqls.create(countSqlStr), pager);
	}

	/**
	* 获取实体对象
	*
	* @see getResult
	*/
	public static <T> List<T> query(final IDbDao dbDao, final Class<T> clazz, final Sql sql) {
		return query(dbDao, clazz, sql, null);
	}

	/**
	 * 获取实体对象
	 *
	 * @see DbExtendUtil
	 */
	public static <T> List<T> query(final IDbDao dbDao, final Class<T> clazz, final Condition cnd, final Pager pager) {
		if (!Util.isEmpty(pager)) {
			pager.setRecordCount(DbExtendNewUtil.count(dbDao, clazz, cnd));
		}
		return dbDao.query(clazz, cnd, pager);
	}

	/**
	 * 获取实体对象
	 *
	 * @see getResult
	 */
	public static <T> List<T> query(final IDbDao dbDao, final Class<T> clazz, final Sql sql, final Pager pager) {
		return query(dbDao, clazz, sql, null, pager);
	}

	/**
	 * 获取实体对象
	 *
	 * @see getResult
	 */
	@SuppressWarnings("unchecked")
	public static <T> List<T> query(final IDbDao dbDao, final Class<T> clazz, final Sql sql, final Sql countSql,
			final Pager pager) {
		List<Record> result = query(dbDao, sql, countSql, pager);
		if (Util.isEmpty(result)) {
			return list();
		}
		List<T> list = list();
		for (Record record : result) {
			list.add(BeanUtil.map2Object(record, clazz));
		}
		return list;
	}

	/**
	 * 获取实体对象
	 *
	 * @see getResult
	 */
	public static <T> List<T> query(final IDbDao dbDao, final Class<T> clazz, final String sqlStr,
			final String countSqlStr, final Pager pager) {
		return query(dbDao, clazz, Sqls.create(sqlStr), Sqls.create(countSqlStr), pager);
	}

	/**
	 * 获取record
	 *
	 * @see getResult
	 */
	@SuppressWarnings("unchecked")
	public static List<Record> query(final IDbDao dbDao, final Sql sql, final Sql countSql, final Pager pager) {
		return (List<Record>) getResult(dbDao, sql, countSql, pager, Sqls.callback.records());
	}

	/**
	 * 获取record
	 *
	 * @see getResult
	 */
	public static List<Record> query(final IDbDao dbDao, final String sqlStr, final String countSqlStr,
			final Pager pager) {
		return query(dbDao, Sqls.create(sqlStr), Sqls.create(countSqlStr), pager);
	}

	/**
	 * 获取R类型
	 * 
	 * @param dbDao
	 * @param sql
	 * @param param
	 * @return
	 */
	public static Record fetchRecord(final IDbDao dbDao, final String sqlStr, final Map<String, Object> param) {
		Sql sql = Sqls.create(sqlStr);
		if (!Util.isEmpty(param)) {
			sql.params().putAll(param);
		}
		sql.setCallback(Sqls.callback.record());
		dbDao.execute(sql);
		return sql.getObject(Record.class);
	}

	/**
	 * 获取R类型
	 * 
	 * @param dbDao
	 * @param sql
	 * @param param
	 * @param pager
	 * @return
	 */
	public static List<Record> fetchRecords(final IDbDao dbDao, final String sqlStr, final Map<String, Object> param,
			final Pager pager) {
		return fetchRecords(dbDao, sqlStr, "", param, pager);
	}

	/**
	 * 获取R类型
	 * <li>
	 * TODO 有没有办法不传countSqlStr。。。与上一个方法合并
	 *
	 * @param dbDao
	 * @param sqlStr
	 * @param countSqlStr
	 * @param param
	 * @param pager
	 * @return 
	*/
	public static List<Record> fetchRecords(final IDbDao dbDao, final String sqlStr, final String countSqlStr,
			final Map<String, Object> param, final Pager pager) {
		Sql sql = Sqls.create(sqlStr);
		if (!Util.isEmpty(param)) {
			sql.params().putAll(param);
		}
		if (!Util.isEmpty(pager)) {
			sql.setPager(pager);
			if (!Util.isEmpty(countSqlStr)) {
				Sql countSql = Sqls.create(countSqlStr);
				if (countSql != null && countSql.isSelect()) {
					countSql.params().putAll(param);
					pager.setRecordCount(fetchInt(dbDao, countSql));
				}
			}
		}
		sql.setCallback(Sqls.callback.records());
		dbDao.execute(sql);
		return sql.getList(Record.class);
	}

	/**
	 * 获取String类型
	 * 
	 * @param dbDao
	 * @param sql
	 * @param param
	 * @return
	 */
	public static List<String> fetchString(final IDbDao dbDao, final String sqlStr, final Map<String, Object> param,
			final Pager pager) {
		Sql sql = Sqls.create(sqlStr);
		if (!Util.isEmpty(param)) {
			sql.params().putAll(param);
		}
		if (!Util.isEmpty(param)) {
			sql.setPager(pager);
		}
		sql.setCallback(Sqls.callback.strList());
		dbDao.execute(sql);
		return sql.getList(String.class);
	}

	/**
	 * 获取int类型
	 * 
	 * @param dbDao
	 * @param sql
	 * @param param
	 * @return
	 */
	public static int fetchInt(final IDbDao dbDao, final String sqlStr, final Map<String, Object> param) {
		Sql sql = Sqls.create(sqlStr);
		if (!Util.isEmpty(param)) {
			sql.params().putAll(param);
		}
		sql.setCallback(Sqls.callback.integer());
		dbDao.execute(sql);
		return sql.getInt();
	}

	/**
	 * 获取String类型
	 * 
	 * @param dbDao
	 * @param sql
	 * @param param
	 * @return
	 */
	public static String fetchString(final IDbDao dbDao, final String sqlStr, final Map<String, Object> param) {
		Sql sql = Sqls.create(sqlStr);
		if (!Util.isEmpty(param)) {
			sql.params().putAll(param);
		}
		sql.setCallback(Sqls.callback.str());
		dbDao.execute(sql);
		return sql.getString();
	}

	/**
	 * 获取Long类型
	 * 
	 * @param dbDao
	 * @param sql
	 * @param param
	 * @return
	 */
	public static Long fetchLong(final IDbDao dbDao, final String sqlStr, final Map<String, Object> param) {
		Sql sql = Sqls.create(sqlStr);
		if (!Util.isEmpty(param)) {
			sql.params().putAll(param);
		}
		sql.setCallback(Sqls.callback.longValue());
		dbDao.execute(sql);
		Long longObj = sql.getObject(Long.class);
		if (longObj == null) {
			return 0l;
		}
		return longObj.longValue();
	}

	/**
	 * 获取Long类型
	 * 
	 * @param dbDao
	 * @param sql
	 * @param param
	 * @return
	 */
	public static Double fetchDouble(final IDbDao dbDao, final String sqlStr, final Map<String, Object> param) {
		Sql sql = Sqls.create(sqlStr);
		if (!Util.isEmpty(param)) {
			sql.params().putAll(param);
		}
		sql.setCallback(Sqls.callback.doubleValue());
		dbDao.execute(sql);
		return sql.getObject(Double.class);
	}

	/**
	 * 执行语句
	 * 
	 * @param dbDao
	 * @param sql
	 * @param param
	 * @auther xushichao
	 * @date 2013年10月28日
	 */
	public static void execute(final IDbDao dbDao, final String sqlStr, final Map<String, Object> param) {
		Sql sql = Sqls.create(sqlStr);
		if (!Util.isEmpty(param)) {
			sql.params().putAll(param);
		}
		dbDao.execute(sql);
	}

}
