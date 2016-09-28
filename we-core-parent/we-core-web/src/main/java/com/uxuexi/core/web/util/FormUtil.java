/**
 * FormUtil.java
 * com.uxuexi.web.util
 * Copyright (c) 2014, 北京聚智未来科技有限公司版权所有.
*/

package com.uxuexi.core.web.util;

import static com.uxuexi.core.common.util.BeanUtil.*;
import static com.uxuexi.core.common.util.ExceptionUtil.*;
import static com.uxuexi.core.common.util.MapUtil.*;
import static com.uxuexi.core.db.util.EntityUtil.*;

import java.util.Map;

import org.nutz.dao.Condition;
import org.nutz.dao.SqlManager;
import org.nutz.dao.Sqls;
import org.nutz.dao.pager.Pager;
import org.nutz.dao.sql.Sql;

import com.uxuexi.core.db.dao.IDbDao;
import com.uxuexi.core.db.entity.impl.ITreeEntity;
import com.uxuexi.core.db.support.WeCnd;
import com.uxuexi.core.db.util.DbSqlUtil;
import com.uxuexi.core.db.util.EntityUtil;
import com.uxuexi.core.web.form.IAddForm;
import com.uxuexi.core.web.form.IModForm;
import com.uxuexi.core.web.form.IQueryForm;
import com.uxuexi.core.web.form.ISqlForm;

/**
 * 根form操作相关的工具类
 * <p>
 * 提供普通的form的增删改查功能
 * @author   庄君祥
 * @author   庄君祥


 * @Date	 2013-3-4 	 
 * @version  5.2.0
 */
public class FormUtil {
	private final static String TREE_ORDERNUM = "select max(orderNum) orderNum from $table  where parentId=@id";

	/**
	 * 该新增的实体对象 
	 * <p>
	 * 不支持addForm所有属性默认为空的判断<br/>
	 * 即：即使所有属性为默认空，依然会执行新增操作
	 * @param dbDao 数据库连接
	 * @param addForm 实现IAddForm接口的对象
	 * @param clazz 类型
	 * @return 该新增的实体对象
	*/
	public static <T> T add(final IDbDao dbDao, final IAddForm addForm, final Class<T> clazz) {
		checkNull(dbDao, "数据库链接不能为空!");
		checkNull(addForm, "表单信息不能为空!");
		addForm.check(dbDao);
		T entity = newInstance(clazz);
		copyProperties(addForm, entity);
		checkNull(entity, "对象不能为空!");
		dbDao.insert(entity);
		return entity;
	}

	/**
	 * 更新实体对象
	 * 
	 * @param dbDao 数据库连接
	 * @param modForm 实现IModForm接口的对象
	 * @param clazz 类型
	 * @return 更新的条数
	*/
	public static <T> int modify(final IDbDao dbDao, final IModForm modForm, final Class<T> clazz) {
		checkNull(dbDao, "数据库链接不能为空!");
		checkModForm(modForm, dbDao);
		T entity = dbDao.fetch(clazz, modForm.getId());
		copyProperties(modForm, entity);
		return dbDao.update(entity);
	}

	/**
	 * 验证修改表单的合法性
	 * <p>
	 * 不能为空，id不能为0
	 * @param modForm 需要验证的表单
	*/
	private static void checkModForm(final IModForm modForm, final IDbDao dbDao) {
		checkNull(modForm, "修改的表单不能为空");
		if (modForm.getId() == 0) {
			throw pEx("修改表单的主键不能为空");
		}
		modForm.check(dbDao);
	}

	/**
	 * 根据主键批量删除
	 *
	 * @param dbDao
	 * @param clazz 对象的类型 
	 * @param ids 主键集合
	 * @return 成功删除的个数
	 */
	public static <T> int delete(final IDbDao dbDao, final Class<T> clazz, final long... ids) {
		checkNull(dbDao, "dbDao对象不能为空");
		checkNull(clazz, "类型不能为空");
		checkEmpty(ids, "主键为空");
		Sql sql = Sqls.create("delete from $table $condition");
		String tableName = EntityUtil.getTableName(clazz);
		checkEmpty(tableName, "类型上面没有注解@Table");
		sql.vars().set("table", tableName).set("condition", WeCnd.where("id", "in", ids));
		dbDao.execute(sql);
		return sql.getUpdateCount();
	}

	/**
	 * 返回查询对象列表
	 * <p>
	 * 主要是为了配合前端sp显示
	 * <p>
	 * 列表包括：<br/>
	 * 对象列表，分页对象，查询表单
	 * @param dbDao
	 * @param clazz 对象的类型
	 * @param cnd 查询条件
	 * @param pager 分页
	 * @return map对象
	 */
	public static <T> Map<String, Object> query(final IDbDao dbDao, final Class<T> clazz, final Condition cnd,
			final Pager pager) {
		checkNull(dbDao, "dbDao对象不能为空");
		checkNull(clazz, "参数clazz不能为空");
		Map<String, Object> map = map();
		map.put("list", DbSqlUtil.query(dbDao, clazz, cnd, pager));
		map.put("pager", pager);
		return map;
	}

	/**
	 * 查询对象列表
	 * <p>
	 * 主要是为了配合前端jsp显示
	 * <p>
	 * 列表包括：<br/>
	 * record列表，分页对象，查询表单
	 * @param dbDao dbDao对象
	 * @param clazz 实体对象类
	 * @param queryForm sql表单
	 * @param pager 分页对象
	 * @return map对象
	 */
	public static <T> Map<String, Object> query(final IDbDao dbDao, final Class<T> clazz, final IQueryForm queryForm,
			final Pager pager) {
		checkNull(queryForm, "queryForm不能为空");
		Map<String, Object> map = query(dbDao, clazz, queryForm.createCnd(), pager);
		map.put("queryForm", queryForm);
		return map;
	}

	/**
	 * 查询对象列表
	 * <p>
	 * 主要是为了配合前端jsp显示
	 * <p>
	 * 列表包括：<br/>
	 * record列表，分页对象
	 * @param dbDao dbDao对象
	 * @param pagerSql 分页的sql
	 * @param countSql 总数的sql
	 * @param pager 分页对象
	 * @return map对象
	 */
	public static Map<String, Object> query(final IDbDao dbDao, final Sql pagerSql, final Sql countSql,
			final Pager pager) {
		checkNull(dbDao, "dbDao对象不能为空");
		checkNull(pagerSql, "参数sql不能为空");
		Map<String, Object> map = map();
		map.put("list", DbSqlUtil.query(dbDao, pagerSql, countSql, pager));
		map.put("pager", pager);
		return map;
	}

	/**
	 * 查询对象列表
	 * <p>
	 * 主要是为了配合前端jsp显示
	 * <p>
	 * 列表包括：<br/>
	 * record列表，分页对象
	 * @param dbDao dbDao对象
	 * @param pagerSqlStr 分页的sql
	 * @param countSqlStr 总数的sql
	 * @param pager 分页对象
	 * @return map对象
	 */
	public static Map<String, Object> query(final IDbDao dbDao, final String pagerSqlStr, final String countSqlStr,
			final Pager pager) {
		return query(dbDao, Sqls.create(pagerSqlStr), Sqls.create(countSqlStr), pager);
	}

	/**
	 * 查询对象列表
	 * <p>
	 * 主要是为了配合前端jsp显示
	 * <p>
	 * 列表包括：<br/>
	 * record列表，分页对象，查询表单
	 * @param dbDao dbDao对象
	 * @param sqlForm sql表单
	 * @param pager 分页对象
	 * @return map对象
	 */
	public static Map<String, Object> query(final IDbDao dbDao, final SqlManager sqlManager, final ISqlForm sqlForm,
			final Pager pager) {
		checkNull(sqlForm, "sqlForm不能为空");
		Map<String, Object> map = query(dbDao, sqlForm.createPagerSql(dbDao, sqlManager),
				sqlForm.createCountSql(dbDao, sqlManager), pager);
		map.put("queryForm", sqlForm);
		return map;
	}

	/**
	 * 取树节点的父节点
	 * <p>
	 * 这个方法即将废弃，请不要使用这个方法了
	 *
	 * @param dbDao 数据库链接
	 * @param clazz 对象类型
	 * @param id 对象id 
	 * @return 节点和顺序号的map
	 */
	@Deprecated
	public static <T extends ITreeEntity<?>> Map<String, Object> fetchParent(final IDbDao dbDao, final Class<T> clazz,
			final long id) {
		checkNull(dbDao, "dbDao对象不能为空");
		checkNull(clazz, "对象类别不能为空");
		Map<String, Object> map = map();
		map.put("parent", dbDao.fetch(clazz, id));
		Sql sql = Sqls.create(TREE_ORDERNUM);
		sql.vars().set("table", getTableName(clazz));
		sql.params().set("id", id);
		int orderNum = DbSqlUtil.fetchInt(dbDao, sql);
		if (orderNum == -1) {
			orderNum = 1;
		} else {
			orderNum++;
		}
		map.put("orderNum", orderNum);
		return map;
	}
}
