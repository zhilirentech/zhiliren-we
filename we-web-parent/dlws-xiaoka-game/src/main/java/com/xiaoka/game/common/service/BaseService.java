package com.xiaoka.game.common.service;

import static com.uxuexi.core.common.util.CollectionUtil.*;
import static com.uxuexi.core.common.util.ExceptionUtil.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.nutz.dao.Chain;
import org.nutz.dao.Cnd;
import org.nutz.dao.Condition;
import org.nutz.dao.SqlManager;
import org.nutz.dao.Sqls;
import org.nutz.dao.entity.Record;
import org.nutz.dao.impl.NutDao;
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
import com.xiaoka.game.common.form.SQLParamForm;
import com.xiaoka.game.common.page.Pagination;

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
	protected NutDao nutDao;

	/**
	 * 注入容器中的sqlManager对象，用于获取sql
	 */
	@Inject
	protected SqlManager sqlManager;

	protected Logger logger = LoggerFactory.getLogger(BaseService.class);

	public BaseService() {
		try {
			@SuppressWarnings("unchecked")
			Class<T> entryClass = (Class<T>) Mirror.getTypeParam(getClass(), 0);
			mirror = Mirror.me(entryClass);
			if (logger.isDebugEnabled())
				logger.debug("获取服务相关entity类型: %s", entryClass.getName());
		} catch (Throwable e) {
			if (logger.isDebugEnabled())
				logger.warn("!!!获取服务相关entity异常 !", e);
		}
	}

	public Class<T> getEntityClass() {
		return mirror.getType();
	}

	public T fetch(long id) {
		return nutDao.fetch(this.getEntityClass(), id);
	}

	/**获取当前表的最大id*/
	public int getMaxId() {
		return nutDao.getMaxId(this.getEntityClass());
	}

	/**添加一个对象*/
	public T insert(T t) {
		return dbDao.insert(t);
	}

	/**添加一条数据，设置部分值*/
	public void insert(String tableName, Chain chain) {
		nutDao.insert(tableName, chain);
	}

	/**快速添加一个对象。 对象的 '@Prev' 以及 '@Next' 在这个函数里不起作用。*/
	public T fastInsert(T t) {
		return nutDao.fastInsert(t);
	}

	/**覆盖更新，表必须有主键*/
	public int update(Object obj) {
		return nutDao.update(obj);
	}

	/**
	 * 忽略值为null的字段,更新
	 */
	public int updateIgnoreNull(Object obj) {
		return nutDao.updateIgnoreNull(obj);
	}

	/**按照id删除数据*/
	public int deleteById(long id) {
		return nutDao.delete(this.getEntityClass(), id);
	}

	/**按照id删除数据*/
	public int deleteById(int id) {
		return nutDao.delete(this.getEntityClass(), id);
	}

	/**
	 * 批量删除
	 *
	 * @param ids
	 */
	public void delete(Integer[] ids) {
		nutDao.clear(getEntityClass(), Cnd.where("id", "in", ids));
	}

	/**
	 * 批量删除
	 *
	 * @param ids
	 */
	public void delete(Long[] ids) {
		nutDao.clear(getEntityClass(), Cnd.where("id", "in", ids));
	}

	/**
	 * 批量删除
	 *
	 * @param ids
	 */
	public void delete(String[] ids) {
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
