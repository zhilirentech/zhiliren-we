/**
 * TestDbSqlUtil.java
 * com.uxuexi.core.db.util
 * Copyright (c) 2014, 北京聚智未来科技有限公司版权所有.
*/

package com.uxuexi.core.db.util;

import static com.uxuexi.core.common.util.CollectionUtil.*;
import static com.uxuexi.core.common.util.Util.*;
import static org.testng.Assert.*;

import java.util.List;

import org.easymock.EasyMock;
import org.nutz.dao.DaoException;
import org.nutz.dao.Sqls;
import org.nutz.dao.impl.NutDao;
import org.nutz.dao.pager.Pager;
import org.nutz.dao.sql.Sql;
import org.nutz.ioc.Ioc;
import org.nutz.ioc.impl.NutIoc;
import org.nutz.ioc.loader.json.JsonLoader;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.uxuexi.core.db.dao.IDbDao;
import com.uxuexi.core.db.dao.IIdGen;
import com.uxuexi.core.db.dao.impl.DbDao;
import com.uxuexi.core.db.util.DbSqlUtil;

import com.uxuexi.core.db.mock.entity.MockEntity;
import com.uxuexi.core.db.mock.entity.MockEntity4ExtendQuery;

/**
 * DbSqlUtil测试类
 *
 * @author   庄君祥
 * @Date	 2013-12-10 	 
 */
public class TestDbSqlUtil {
	private IDbDao dbDao;
	private IIdGen idGen;
	private NutDao nutDao;
	private Ioc ioc;
	private int totalQuerySize = 100;

	@BeforeClass
	public void init() {
		ioc = new NutIoc(new JsonLoader("db/config/dao.js"));
		nutDao = ioc.get(NutDao.class, "nutDao");
		mockIIdGen();
		dbDao = new DbDao(nutDao, idGen);
		nutDao.create(MockEntity.class, true);
		nutDao.create(MockEntity4ExtendQuery.class, true);
		initQuery();
	}

	private void initQuery() {
		List<MockEntity4ExtendQuery> entites = list();
		for (int i = 0; i < totalQuerySize; i++) {
			entites.add(new MockEntity4ExtendQuery("测试" + i, i));
		}
		dbDao.insert(entites);
	}

	/**
	 * mock IIdGen的接口
	*/
	private void mockIIdGen() {
		idGen = EasyMock.createMock(IIdGen.class);
		long currentTimeMillis = System.currentTimeMillis();
		for (int i = 0; i < 200; i++) {
			idGen.getId();
			EasyMock.expectLastCall().andReturn(currentTimeMillis++);
		}
		EasyMock.replay(idGen);
	}

	@Test
	public void testFetchSignleNormal() {
		int type = 2;
		String name = "测试的啊";
		MockEntity entity = new MockEntity(name, type);
		dbDao.insert(entity);
		Sql typeSql = Sqls.create(" select type from mock_entity where id=@id");
		typeSql.params().set("id", entity.getId());
		assertEquals(DbSqlUtil.fetchInt(dbDao, typeSql), type);
		assertEquals(DbSqlUtil.fetchInt(dbDao, "select type from mock_entity where id=" + entity.getId()), type);

		Sql nameSql = Sqls.create(" select name from mock_entity where id=@id");
		nameSql.params().set("id", entity.getId());
		assertEquals(DbSqlUtil.fetchString(dbDao, nameSql), name);
		assertEquals(DbSqlUtil.fetchString(dbDao, "select name from mock_entity where id=" + entity.getId()), name);

		Sql idSql = Sqls.create(" select id from mock_entity where id=@id");
		idSql.params().set("id", entity.getId());
		assertEquals(DbSqlUtil.fetchLong(dbDao, idSql), entity.getId());
		assertEquals(DbSqlUtil.fetchLong(dbDao, "select id from mock_entity where id=" + entity.getId()),
				entity.getId());
	}

	@Test
	public void testFetchIntIll() {
		assertEquals(-1, DbSqlUtil.fetchInt(dbDao, Sqls.create("select type from mock_entity where id =-1")));
	}

	@Test
	public void testFetchLongIll() {
		assertEquals(-1L, DbSqlUtil.fetchLong(dbDao, Sqls.create("select id from mock_entity where id =-1")));
	}

	@Test
	public void testFetchStringIll() {
		assertTrue(isEmpty(DbSqlUtil.fetchString(dbDao, Sqls.create("select id from mock_entity where id =-1"))));
	}

	@Test
	public void testQueryIntIll() throws Exception {
		int[] result = DbSqlUtil
				.queryInt(dbDao, Sqls.create("select type from mock_entity_4_extend_query where id=-1"));
		assertTrue(isEmpty(result));
	}

	@Test
	public void testQueryIntAll() throws Exception {
		int[] result = DbSqlUtil.queryInt(dbDao, Sqls.create("select type from mock_entity_4_extend_query"));
		assertEquals(result.length, totalQuerySize);
	}

	@Test
	public void testQueryIntPager() throws Exception {
		Pager pager = new Pager().setPageSize(23);
		int[] result = DbSqlUtil.queryInt(dbDao, Sqls.create("select type from mock_entity_4_extend_query"), pager);
		assertEquals(result.length, pager.getPageSize());
		int zero = 0;
		assertEquals(zero, pager.getRecordCount());
	}

	@Test
	public void testQueryIntPagerRecordCount() throws Exception {
		Pager pager = new Pager().setPageSize(23);
		int[] result = DbSqlUtil.queryInt(dbDao, Sqls.create("select type from mock_entity_4_extend_query"),
				Sqls.create("select count(*) from mock_entity_4_extend_query"), pager);
		assertEquals(result.length, pager.getPageSize());
		assertEquals(totalQuerySize, pager.getRecordCount());
	}

	@Test(expectedExceptions = DaoException.class)
	public void testQueryIntCastIll() throws Exception {
		DbSqlUtil.queryInt(dbDao, Sqls.create("select name from mock_entity_4_extend_query"));
	}

	@Test
	public void testQueryEntity() throws Exception {
		List<MockEntity4ExtendQuery> list = DbSqlUtil.query(dbDao, MockEntity4ExtendQuery.class,
				Sqls.create("select * from mock_entity_4_extend_query"), null);
		assertEquals(list.size(), totalQuerySize);
	}

	@Test
	public void testQueryEntityIll() throws Exception {
		List<MockEntity4ExtendQuery> list = DbSqlUtil.query(dbDao, MockEntity4ExtendQuery.class,
				Sqls.create("select *,3 from mock_entity_4_extend_query"), null);
		assertEquals(list.size(), totalQuerySize);
	}

	@Test
	public void testQueryEntityPager() throws Exception {
		Pager pager = new Pager().setPageSize(23);
		List<MockEntity4ExtendQuery> result = DbSqlUtil.query(dbDao, MockEntity4ExtendQuery.class,
				Sqls.create("select * from mock_entity_4_extend_query"), pager);
		assertEquals(result.size(), pager.getPageSize());
		int zero = 0;
		assertEquals(zero, pager.getRecordCount());
	}

	@Test
	public void testQueryEntityPagerRecordCount() throws Exception {
		Pager pager = new Pager().setPageSize(23);
		List<MockEntity4ExtendQuery> result = DbSqlUtil.query(dbDao, MockEntity4ExtendQuery.class,
				Sqls.create("select * from mock_entity_4_extend_query"),
				Sqls.create("select count(*) from mock_entity_4_extend_query"), pager);
		assertEquals(result.size(), pager.getPageSize());
		assertEquals(totalQuerySize, pager.getRecordCount());
	}

	@Test
	public void testFetchEntity() throws Exception {
		MockEntity entity = new MockEntity("a", 1);
		dbDao.insert(entity);
		Sql sql = Sqls.create(" select * from mock_entity where id =@id");
		sql.params().set("id", entity.getId());
		assertEquals(entity, DbSqlUtil.fetchEntity(dbDao, MockEntity.class, sql));
	}
}
