/**
 * DbDao.java
 * com.uxuexi.core.db.dao.impl
 * Copyright (c) 2014, 北京聚智未来科技有限公司版权所有.
*/

package com.uxuexi.core.db.dao.impl;

import static com.uxuexi.core.common.util.ExceptionUtil.checkEmpty;
import static com.uxuexi.core.common.util.ExceptionUtil.checkNull;
import static com.uxuexi.core.common.util.ExceptionUtil.pEx;
import static com.uxuexi.core.common.util.Util.isEmpty;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.nutz.dao.Chain;
import org.nutz.dao.Cnd;
import org.nutz.dao.Condition;
import org.nutz.dao.Sqls;
import org.nutz.dao.entity.Record;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.impl.NutDao;
import org.nutz.dao.pager.Pager;
import org.nutz.dao.sql.Sql;
import org.nutz.lang.ContinueLoop;
import org.nutz.lang.Each;
import org.nutz.lang.ExitLoop;
import org.nutz.lang.Lang;
import org.nutz.lang.LoopException;
import org.nutz.lang.Mirror;

import com.uxuexi.core.common.util.ConvertUtil;
import com.uxuexi.core.common.util.StringUtil;
import com.uxuexi.core.common.util.Util;
import com.uxuexi.core.db.dao.IDbDao;
import com.uxuexi.core.db.dao.IIdGen;

/**
 * 纯属的基于nut的数据库持久
 *
 * @author   庄君祥
 * @author   朱晓川
 * @Date	 2013-12-6 	 
 */
public class DbDao implements IDbDao {
	/**
	 * 持有的NutDao
	 */
	protected NutDao nutDao;
	/**
	 * 主键生成策略
	 */
	protected IIdGen idGen;

	public DbDao(final NutDao nutDao) {
		this.nutDao = nutDao;
	}

	public DbDao(final NutDao nutDao, final IIdGen idGen) {
		this.nutDao = nutDao;
		this.idGen = idGen;
	}

	@Override
	public <T> T insert(final T obj) {
		checkEmpty(obj, "插入的数据不为空");
		Lang.each(obj, new Each<T>() {
			@Override
			public void invoke(final int index, final T ele, final int length) throws ExitLoop, ContinueLoop,
					LoopException {
				Mirror<T> me = Mirror.me(ele);
				Field[] fds = me.getFields();
				if (null == fds) {
					return;
				}
				for (Field field : fds) {
					if (field.isAnnotationPresent(Id.class)) {
						Id id = field.getAnnotation(Id.class);
						if (!id.auto()) {
							Object value = me.getValue(ele, field);
							long result = ConvertUtil.obj2long(value);
							if (result > 0) {
								break;
							}
							me.setValue(ele, field, idGen.getId());
						}
						break;
					}
				}
			}
		});
		return nutDao.insert(obj);

	}

	@Override
	public void insert(final String tableName, final Chain chain) {
		checkEmpty(tableName, "tableName不能为空");
		checkNull(chain, "chain不能为空");
		nutDao.insert(tableName, chain);
	}

	@Override
	public void insert(final Class<?> clazz, final Chain chain) {
		checkNull(clazz, "class不能为空");
		checkNull(chain, "chain不能为空");
		nutDao.insert(clazz, chain);
	}

	@Override
	public int update(final Object obj, final String... cols) {
		checkEmpty(obj, "更新的对象不允许为空");
		if (obj instanceof Map<?, ?>) {
			throw pEx("暂时不支持map更新操作");
		}
		if (isEmpty(cols)) {
			return nutDao.update(obj);
		}
		StringBuilder sb = new StringBuilder("^(").append(StringUtil.join("|", cols)).append(")$");
		return nutDao.update(obj, sb.toString());
	}

	@Override
	public int update(final String tableName, final Chain chain, final Condition cnd) {
		checkEmpty(tableName, "表名不能为空");
		checkNull(chain, "chain不能为空");
		return nutDao.update(tableName, chain, cnd);

	}

	@Override
	public int update(final Class<?> clazz, final Chain chain, final Condition cnd) {
		checkNull(clazz, "类型不能为空");
		checkNull(chain, "chain不能为空");
		return nutDao.update(clazz, chain, cnd);

	}

	@Override
	public int delete(final Object obj) {
		checkEmpty(obj, "要删除的对象不能为空");
		return nutDao.delete(obj);
	}

	@Override
	public int delete(final Class<?> clazz, final long id) {
		checkNull(clazz, "clazz不能为空");
		return nutDao.delete(clazz, id);
	}

	@Override
	public void execute(final Sql... sqls) {
		checkEmpty(sqls, "sql对象不允许为空");
		nutDao.execute(sqls);
	}

	@Override
	public <T> List<T> query(final Class<T> clazz, final Condition cnd, final Pager pager) {
		checkNull(clazz, "查询的类型不为空");
		//		adapterPager(pager);
		return nutDao.query(clazz, cnd, pager);
	}

	@Override
	public List<Record> query(final String tableName, final Condition cnd, final Pager pager) {
		checkEmpty(tableName, "查询的表不为空");
		return nutDao.query(tableName, cnd, pager);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Record> query(final Sql sql, final Condition cnd, final Pager pager) {
		checkNull(sql, "sql对象不允许为空");
		if (!sql.isSelect()) {
			throw pEx("执行查询sql时，sql不是select语句！");
		}
		if (null != cnd) {
			sql.setCondition(cnd);
		}
		if (null != pager) {
			sql.setPager(pager);
		}
		sql.setCallback(Sqls.callback.records());
		execute(sql);
		return (List<Record>) sql.getResult();
	}

	@Override
	public <T> T fetch(final Class<T> clazz, final long id) {
		checkNull(clazz, "class不能为空");
		return nutDao.fetch(clazz, id);

	}

	@Override
	public <T> T fetch(final Class<T> clazz, final Condition cnd) {
		checkNull(clazz, "class不能为空");
		return nutDao.fetch(clazz, cnd);

	}

	/**
	 * 根据条件获取一个 Record 对象
	 */
	@Override
	public Record fetch(final String tableName, final long id) {
		checkEmpty(tableName, "tableName不能为空");
		return fetch(tableName, Cnd.where("id", "=", id));
	}

	@Override
	public Record fetch(final String tableName, final Condition cnd) {
		checkEmpty(tableName, "tableName不能为空");
		return nutDao.fetch(tableName, cnd);
	}

	@Override
	public Record fetch(final Sql sql) {
		checkEmpty(sql, "sql不能为空");
		sql.setCallback(Sqls.callback.record());
		execute(sql);
		return sql.getObject(Record.class);
	}

	@Override
	public <T> void updateRelations(List<T> before, List<T> after) {
		if(Util.isEmpty(before) && !Util.isEmpty(after)) {
			//将之后的关系全部添加
			this.insert(after) ;
		}else{
			List<T> tobeAdd = new ArrayList<T>() ;
			if(!Util.isEmpty(after)){
				for(T t : after){
					if(!before.contains(t)){ 
						tobeAdd.add(t) ;
					}
				}
				
				//非空则执行添加
				if(!Util.isEmpty(tobeAdd))
					this.insert(tobeAdd) ;
			}
			
			
			//删除
			before.removeAll(after) ;
			if(!Util.isEmpty(before))
				this.delete(before);
		}
		
	}
}
