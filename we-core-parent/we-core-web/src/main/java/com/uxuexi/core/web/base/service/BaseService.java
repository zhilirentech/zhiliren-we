package com.uxuexi.core.web.base.service;

import static com.uxuexi.core.common.util.CollectionUtil.*;
import static com.uxuexi.core.common.util.ExceptionUtil.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.nutz.dao.Chain;
import org.nutz.dao.Cnd;
import org.nutz.dao.Condition;
import org.nutz.dao.Dao;
import org.nutz.dao.SqlManager;
import org.nutz.dao.Sqls;
import org.nutz.dao.entity.Record;
import org.nutz.dao.pager.Pager;
import org.nutz.dao.sql.Sql;
import org.nutz.dao.sql.SqlCallback;
import org.nutz.dao.util.Daos;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.json.JsonFormat;
import org.nutz.lang.Lang;
import org.nutz.lang.Mirror;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.uxuexi.core.common.util.BeanUtil;
import com.uxuexi.core.common.util.Util;
import com.uxuexi.core.db.dao.IDbDao;
import com.uxuexi.core.web.base.page.Pagination;
import com.uxuexi.core.web.form.AddForm;
import com.uxuexi.core.web.form.ModForm;
import com.uxuexi.core.web.form.SQLParamForm;
import com.uxuexi.core.web.util.FormUtil;

/**
 * 基础业务类，提供通用的业务处理。
 * 使用你的类继承它即可:)
 * 注意:
 * 1,请确保nutz-ioc容器中有以下实例:dbDao,nutDao,sqlManager
 * 2,继承的时候,请提供一个service默认关联的entity泛型T，否则公共方法中的单表操作可能要出问题。。。
 * <p>
 * @author   朱晓川
 * @Date	 2016年8月30日
 */
@IocBean
public abstract class BaseService<T> {

	protected final static int DEFAULT_PAGE_NUMBER = 10;
	protected final static JsonFormat jsonFormat = new JsonFormat().setIgnoreNull(false);

	private Mirror<T> mirror;

	/**
	 * 注入容器中的dbDao对象，用于数据库查询、持久操作 
	 */
	@Inject
	protected IDbDao dbDao;

	/**nutz dao*/
	@Inject
	protected Dao nutDao;

	/**
	 * 注入容器中的sqlManager对象，用于获取sql
	 */
	@Inject
	protected SqlManager sqlManager;

	private Logger logger = LoggerFactory.getLogger(BaseService.class);

	public BaseService() {
		try {
			@SuppressWarnings("unchecked")
			Class<T> entityClass = (Class<T>) Mirror.getTypeParam(getClass(), 0);
			mirror = Mirror.me(entityClass);
			if (logger.isDebugEnabled())
				logger.debug("获取服务相关entity类型: %s", entityClass.getName());
		} catch (Throwable e) {
			if (logger.isDebugEnabled())
				logger.warn("!!!获取服务相关entity异常 !", e);
		}
	}

	public Class<T> getEntityClass() {
		return mirror.getType();
	}

	public T fetch(long id) {
		return dbDao.fetch(this.getEntityClass(), id);
	}

	/**获取当前表的最大id*/
	public int getMaxId() {
		return nutDao.getMaxId(this.getEntityClass());
	}

	/**添加一个对象*/
	public T add(AddForm addForm) {
		@SuppressWarnings("unchecked")
		Class<T> entryClass = (Class<T>) Mirror.getTypeParam(getClass(), 0);
		return FormUtil.add(dbDao, addForm, entryClass);
	}

	/**添加一条数据，设置部分值*/
	public void add(String tableName, Chain chain) {
		dbDao.insert(tableName, chain);
	}

	/**快速添加一个对象。 对象的 '@Prev' 以及 '@Next' 在这个函数里不起作用。*/
	public T fastInsert(T t) {
		return nutDao.fastInsert(t);
	}

	/**覆盖更新，表必须有主键*/
	public int update(ModForm modForm) {
		@SuppressWarnings("unchecked")
		Class<T> entryClass = (Class<T>) Mirror.getTypeParam(getClass(), 0);
		return FormUtil.modify(dbDao, modForm, entryClass);
	}

	/**
	 * 更新关系表数据。比如，更新角色对应的功能:
	 * 1，前台传递角色最后应该拥有的全部功能，组装成after
	 * 2，查询出角色现在拥有的功能 before
	 * <p>
	 * 
	 * 泛型R必须是一个多对多关系Entity类型
	 * 
	 * @param before   (按照某种条件查询出的)关系表更新前的数据集合
	 * @param after    (更新后应处于的状态)关系表欲更新的数据集合
	 */
	public <R> void updateRelations(List<R> before, List<R> after) {
		dbDao.updateRelations(before, after);
	}

	/**
	 * 忽略值为null的字段,更新
	 */
	public int updateIgnoreNull(Object obj) {
		return nutDao.updateIgnoreNull(obj);
	}

	/**按照id删除数据*/
	public int deleteById(long id) {
		return dbDao.delete(this.getEntityClass(), id);
	}

	/**按照id删除数据*/
	public int deleteById(int id) {
		return dbDao.delete(this.getEntityClass(), id);
	}

	/**
	 * 批量删除
	 *
	 * @param ids
	 */
	public void batchDelete(Integer[] ids) {
		nutDao.clear(getEntityClass(), Cnd.where("id", "in", ids));
	}

	/**
	 * 批量删除
	 *
	 * @param ids
	 */
	public void batchDelete(Long[] ids) {
		nutDao.clear(getEntityClass(), Cnd.where("id", "in", ids));
	}

	/**
	 * 批量删除
	 *
	 * @param ids
	 */
	public void batchDelete(String[] ids) {
		nutDao.clear(getEntityClass(), Cnd.where("id", "in", ids));
	}

	/**
	 * 自定义SQL统计
	 *
	 * @param sql
	 * @return
	 */
	public int count(Sql sql) {
		sql.setCallback(new SqlCallback() {
			@Override
			public Object invoke(Connection conn, ResultSet rs, Sql sqlV) throws SQLException {
				int rsvalue = 0;
				if (rs != null && rs.next()) {
					rsvalue = rs.getInt(1);
				}
				return rsvalue;
			}
		});
		nutDao.execute(sql);
		return sql.getInt();
	}

	/**
	 * 获取实体对象(sqlParamForm)
	 */
	public <E> List<E> listPageBean(final Class<E> clazz, final SQLParamForm sqlParamForm, final Pager pager) {
		checkNull(sqlParamForm, "sqlParamForm不能为空");
		checkNull(pager, "分页对象不能为空");
		Sql sql = sqlParamForm.sql(sqlManager);
		return listPageBean(clazz, sql, pager);
	}

	/**
	 * 获取实体对象(sql)
	 */
	@SuppressWarnings("unchecked")
	public <E> List<E> listPageBean(final Class<E> clazz, final Sql sql, final Pager pager) {
		List<Record> result = (List<Record>) queryResult(sql, pager, Sqls.callback.records());
		if (Util.isEmpty(result)) {
			return list();
		}
		List<E> list = list();
		for (Record record : result) {
			list.add(BeanUtil.map2Object(record, clazz));
		}
		return list;
	}

	/**
	 * 单表分页查询
	 */
	public Pagination listPage(Integer pageNumber, Condition cnd) {
		return listPageCnd(pageNumber, DEFAULT_PAGE_NUMBER, cnd);
	}

	/**
	 * 单表分页查询,查询当前service关联的表
	 */
	public Pagination listPageCnd(int pageNumber, int pageSize, Condition cnd) {
		int pn = pageNumber(pageNumber);
		int ps = pageSize(pageSize);
		Pager pager = nutDao.createPager(pn, ps);
		List<T> list = nutDao.query(getEntityClass(), cnd, pager);
		pager.setRecordCount(nutDao.count(getEntityClass(), cnd));
		return new Pagination(pn, ps, pager.getRecordCount(), list);
	}

	/**
	 * 使用sql进行分页查询
	 */
	public Pagination listPage(Integer pageNumber, Sql sql) {
		return listPage(pageNumber, DEFAULT_PAGE_NUMBER, sql);
	}

	/**
	 * 使用sql进行分页查询,返回分页对象
	 */
	public Pagination listPage(Integer pageNumber, int pageSize, Sql sql) {
		int pn = pageNumber(pageNumber);
		int ps = pageSize(pageSize);
		Pager pager = nutDao.createPager(pn, ps);
		@SuppressWarnings("unchecked")
		List<Record> list = (List<Record>) queryResult(sql, pager, Sqls.callback.records());
		return new Pagination(pn, ps, pager.getRecordCount(), list);
	}

	/**
	 * 使用sqlParamForm封装复杂参数sql的分页查询,返回分页对象
	 * <p>
	 * pager为null则查询全部
	 */
	public Pagination listPage(final SQLParamForm sqlParamForm, final Pager pager) {
		checkNull(sqlParamForm, "sqlParamForm不能为空");
		checkNull(pager, "分页对象不能为空");
		Sql sql = sqlParamForm.sql(sqlManager);
		return listPage(pager.getPageNumber(), pager.getPageSize(), sql);
	}

	/**
	 * 默认页码
	 *
	 * @param pageNumber
	 * @return
	 */
	protected int pageNumber(Integer pageNumber) {
		return Lang.isEmpty(pageNumber) ? 1 : pageNumber;
	}

	/**
	 * 默认页大小
	 *
	 * @param pageSize
	 * @return
	 */
	protected int pageSize(int pageSize) {
		return pageSize == 0 ? DEFAULT_PAGE_NUMBER : pageSize;
	}

	private Object queryResult(final Sql sql, final Pager pager, final SqlCallback callback) {
		checkSql(sql);
		if (pager != null) {
			if (pager.getPageNumber() <= 0) {
				pager.setPageNumber(1);
			}
			sql.setPager(pager);
			// 设置记录数
			pager.setRecordCount((int) Daos.queryCount(nutDao, sql.toString()));
		}
		sql.setCallback(callback);
		nutDao.execute(sql);
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

}
