/**
 * TestOperateDbDao.java
 * com.uxuexi.core.db.dao.impl
 * Copyright (c) 2014, 北京聚智未来科技有限公司版权所有.
*/

package com.uxuexi.core.db.dao.impl;

import static org.testng.Assert.*;

import java.sql.Timestamp;
import java.util.Collection;

import org.easymock.EasyMock;
import org.nutz.dao.impl.NutDao;
import org.nutz.ioc.Ioc;
import org.nutz.ioc.impl.NutIoc;
import org.nutz.ioc.loader.json.JsonLoader;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.uxuexi.core.common.exception.impl.ParamException;
import com.uxuexi.core.common.util.CollectionUtil;
import com.uxuexi.core.common.util.DateTimeUtil;
import com.uxuexi.core.common.util.MapUtil;
import com.uxuexi.core.db.dao.IIdGen;
import com.uxuexi.core.db.dao.impl.OperateDbDao;
import com.uxuexi.core.db.dao.impl.support.IFetchUser;

import com.uxuexi.core.db.mock.entity.MockEntity4OperateDbDao;

/**
 * OperateDbDao单元测试类
 *
 * @author   庄君祥
 * @Date	 2013-12-10 	 
 */
public class TestOperateDbDao {
	private OperateDbDao operateDbDao;
	private IIdGen idGen;
	private IFetchUser fetchUser;
	private NutDao nutDao;
	//private String realName = "庄君祥";
	private long userId = 9300L;
	private Ioc ioc;

	@BeforeClass
	public void init() {
		ioc = new NutIoc(new JsonLoader("db/config/dao.js"));
		nutDao = ioc.get(NutDao.class, "nutDao");
		mockIIdGen();
		mockIFetchUser();
		operateDbDao = new OperateDbDao(nutDao, idGen, fetchUser);
		nutDao.create(MockEntity4OperateDbDao.class, true);
	}

	private void mockIFetchUser() {
		fetchUser = EasyMock.createMock(IFetchUser.class);
		//EasyMock.expectLastCall().andReturn(realName).anyTimes();
		fetchUser.getCurrentUserId();
		EasyMock.expectLastCall().andReturn(userId).anyTimes();
		EasyMock.replay(fetchUser);
	}

	/**
	 * mock IIdGen的接口
	*/
	private void mockIIdGen() {
		idGen = EasyMock.createMock(IIdGen.class);
		long currentTimeMillis = System.currentTimeMillis();
		for (int i = 0; i < 100; i++) {
			idGen.getId();
			EasyMock.expectLastCall().andReturn(currentTimeMillis++);
		}
		EasyMock.replay(idGen);
	}

	@Test(expectedExceptions = ParamException.class)
	public void testInsertNull() {
		operateDbDao.insert(null);
	}

	@Test
	public void testInsertSignle() {
		MockEntity4OperateDbDao entity = new MockEntity4OperateDbDao("测试用的");
		operateDbDao.insert(entity);
		assertEquals(entity.getCreaterId(), userId);
	}

	@Test
	public void testInsertWithUserId() {
		MockEntity4OperateDbDao entity = new MockEntity4OperateDbDao("测试用的");
		long outSideUserId = 92333L;
		entity.setCreaterId(outSideUserId);
		Timestamp now = DateTimeUtil.nowDateTime();
		entity.setCreateTime(now);
		operateDbDao.insert(entity);
		assertEquals(outSideUserId, entity.getCreaterId());
		assertEquals(now, entity.getCreateTime());
	}

	@Test
	public void testInsertArray() {
		MockEntity4OperateDbDao entity1 = new MockEntity4OperateDbDao("测试用的");
		MockEntity4OperateDbDao entity2 = new MockEntity4OperateDbDao("测试用的");
		MockEntity4OperateDbDao[] entities = new MockEntity4OperateDbDao[] { entity1, entity2 };
		operateDbDao.insert(entities);
		assertEquals(entity1.getCreaterId(), userId);
		assertEquals(entity2.getCreaterId(), userId);
	}

	@Test
	public void testInsertCollection() {
		MockEntity4OperateDbDao entity1 = new MockEntity4OperateDbDao("测试用的");
		MockEntity4OperateDbDao entity2 = new MockEntity4OperateDbDao("测试用的");
		Collection<MockEntity4OperateDbDao> list = CollectionUtil.list(entity1, entity2);
		operateDbDao.insert(list);
		assertEquals(entity1.getCreaterId(), userId);
		assertEquals(entity2.getCreaterId(), userId);
	}

	@Test(expectedExceptions = ParamException.class)
	public void testInsertMap() {
		operateDbDao.insert(MapUtil.map());
	}

	@Test(expectedExceptions = ParamException.class)
	public void testUpdateNull() {
		operateDbDao.update(null);
	}

	@Test
	public void testUpdateSignle() {
		MockEntity4OperateDbDao entity = new MockEntity4OperateDbDao("测试用的");
		operateDbDao.insert(entity);
		int zero = 0;
		assertEquals(entity.getUpdaterId(), zero);
		operateDbDao.update(entity);
		assertEquals(entity.getUpdaterId(), userId);

		long updateUserId = 22277L;
		entity.setUpdaterId(updateUserId);
		Timestamp now = DateTimeUtil.nowDateTime();
		entity.setUpdateTime(now);
		operateDbDao.update(entity);
		assertEquals(updateUserId, entity.getUpdaterId());
		assertEquals(now, entity.getUpdateTime());
	}

	@Test
	public void testUpdateArray() {
		MockEntity4OperateDbDao entity1 = new MockEntity4OperateDbDao("测试用的");
		MockEntity4OperateDbDao entity2 = new MockEntity4OperateDbDao("测试用的");
		MockEntity4OperateDbDao[] entities = new MockEntity4OperateDbDao[] { entity1, entity2 };
		operateDbDao.insert(entities);
		int zero = 0;
		assertEquals(entity1.getUpdaterId(), zero);
		assertEquals(entity2.getUpdaterId(), zero);
		operateDbDao.update(entities);
		assertEquals(entity1.getUpdaterId(), userId);
		assertEquals(entity2.getUpdaterId(), userId);
	}

	@Test
	public void testUpdateCollection() {
		MockEntity4OperateDbDao entity1 = new MockEntity4OperateDbDao("测试用的");
		MockEntity4OperateDbDao entity2 = new MockEntity4OperateDbDao("测试用的");
		Collection<MockEntity4OperateDbDao> entities = CollectionUtil.list(entity1, entity2);
		operateDbDao.insert(entities);
		int zero = 0;
		assertEquals(entity1.getUpdaterId(), zero);
		assertEquals(entity2.getUpdaterId(), zero);
		operateDbDao.update(entities);
		assertEquals(entity1.getUpdaterId(), userId);
		assertEquals(entity2.getUpdaterId(), userId);
	}

	@Test(expectedExceptions = ParamException.class)
	public void testUpdateMap() {
		operateDbDao.update(MapUtil.map());
	}
}
