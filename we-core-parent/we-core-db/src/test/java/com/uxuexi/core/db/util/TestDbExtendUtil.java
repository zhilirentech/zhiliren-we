/**
 * TestDbExendUtil.java
 * com.uxuexi.core.db.util
 * Copyright (c) 2014, 北京聚智未来科技有限公司版权所有.
*/

package com.uxuexi.core.db.util;

import static com.uxuexi.core.common.util.CollectionUtil.*;
import static org.testng.Assert.*;

import java.util.List;

import org.easymock.EasyMock;
import org.nutz.dao.Cnd;
import org.nutz.dao.impl.NutDao;
import org.nutz.ioc.Ioc;
import org.nutz.ioc.impl.NutIoc;
import org.nutz.ioc.loader.json.JsonLoader;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.uxuexi.core.common.exception.impl.ParamException;
import com.uxuexi.core.db.dao.IDbDao;
import com.uxuexi.core.db.dao.IIdGen;
import com.uxuexi.core.db.dao.impl.DbDao;
import com.uxuexi.core.db.util.DbExtendUtil;

import com.uxuexi.core.db.mock.entity.MockEntity4ExtendCount;

/**
 * DbExendUtil测试类
 *
 * @author   庄君祥
 * @Date	 2013-12-10 	 
 */
public class TestDbExtendUtil {
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
		nutDao.create(MockEntity4ExtendCount.class, true);
		initQuery();
	}

	private void initQuery() {
		List<MockEntity4ExtendCount> entites = list();
		for (int i = 0; i < totalQuerySize; i++) {
			entites.add(new MockEntity4ExtendCount("测试" + i));
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
	public void testCount() throws Exception {
		assertEquals(totalQuerySize, DbExtendUtil.count(dbDao, MockEntity4ExtendCount.class));
	}

	@Test
	public void testCountCnd() {
		int size = 50;
		assertEquals(size, DbExtendUtil.count(dbDao, MockEntity4ExtendCount.class, Cnd.where("id", "<=", size)));
	}

	@Test(expectedExceptions = ParamException.class)
	public void testCountNull() throws Exception {
		Class<?> clazz = null;
		assertNull(clazz);
		DbExtendUtil.count(dbDao, clazz);
	}

}
