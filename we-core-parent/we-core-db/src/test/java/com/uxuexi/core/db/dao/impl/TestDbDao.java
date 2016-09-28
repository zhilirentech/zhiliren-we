/**
 * TestDbDao.java
 * com.uxuexi.core.db.dao.impl
 * Copyright (c) 2014, 北京聚智未来科技有限公司版权所有.
*/

package com.uxuexi.core.db.dao.impl;

import static com.uxuexi.core.common.util.CollectionUtil.*;
import static org.testng.Assert.*;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.easymock.EasyMock;
import org.nutz.dao.Chain;
import org.nutz.dao.Cnd;
import org.nutz.dao.DaoException;
import org.nutz.dao.Sqls;
import org.nutz.dao.entity.Record;
import org.nutz.dao.impl.NutDao;
import org.nutz.dao.pager.Pager;
import org.nutz.dao.sql.Sql;
import org.nutz.ioc.Ioc;
import org.nutz.ioc.impl.NutIoc;
import org.nutz.ioc.loader.json.JsonLoader;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.uxuexi.core.common.exception.impl.ParamException;
import com.uxuexi.core.common.util.MapUtil;
import com.uxuexi.core.db.dao.IIdGen;
import com.uxuexi.core.db.dao.impl.DbDao;
import com.uxuexi.core.db.util.RecordUtil;

import com.uxuexi.core.db.mock.entity.MockEntity4DbQuery;
import com.uxuexi.core.db.mock.entity.MockEntityWithAutoId;
import com.uxuexi.core.db.mock.entity.MockEntityWithoutAutoId;
import com.uxuexi.core.db.mock.entity.MockEntityWithoutId;

/**
 * DbDao测试类
 *
 * @author   庄君祥
 * @Date	 2013-12-9 	 
 */
public class TestDbDao {
	private DbDao dbDao;
	private IIdGen idGen;
	private NutDao nutDao;
	private int totalQuerySize = 100;
	private Ioc ioc;

	@BeforeClass
	public void init() {
		ioc = new NutIoc(new JsonLoader("db/config/dao.js"));
		nutDao = ioc.get(NutDao.class, "nutDao");
		mockIIdGen();
		dbDao = new DbDao(nutDao, idGen);
		nutDao.create(MockEntityWithoutId.class, true);
		nutDao.create(MockEntityWithoutAutoId.class, true);
		nutDao.create(MockEntityWithAutoId.class, true);
		nutDao.create(MockEntity4DbQuery.class, true);
		initQueryData();
	}

	/**
	 * 初始查询数据
	*/
	private void initQueryData() {
		List<MockEntity4DbQuery> entites = list();
		for (int i = 0; i < totalQuerySize; i++) {
			entites.add(new MockEntity4DbQuery("测试" + i));
		}
		dbDao.insert(entites);

	}

	/**
	 * mock IIdGen的接口
	*/
	private void mockIIdGen() {
		idGen = EasyMock.createMock(IIdGen.class);
		long currentTimeMillis = System.currentTimeMillis();
		for (int i = 0; i < 1000; i++) {
			idGen.getId();
			EasyMock.expectLastCall().andReturn(currentTimeMillis++);
		}
		EasyMock.replay(idGen);
	}

	@Test(expectedExceptions = ParamException.class)
	public void testInsertNull() {
		dbDao.insert(null);
	}

	@Test(expectedExceptions = ParamException.class)
	public void testInsertArrayNull() {
		dbDao.insert(new MockEntityWithAutoId[0]);
	}

	@Test(expectedExceptions = ParamException.class)
	public void testInsertCollectionNull() {
		dbDao.insert(list());
	}

	@Test(expectedExceptions = ParamException.class)
	public void testInsertMapNull() {
		dbDao.insert(MapUtil.map());
	}

	/**
	 * 单实体没有@Id并且没有赋值id
	 * <p>
	 * 预期结果，可以插入记录，并且id=0
	*/
	@Test
	public void testInsertSingleWithoutIdNotHasId() {
		MockEntityWithoutId entity = new MockEntityWithoutId();
		entity.setName("测试名字");
		MockEntityWithoutId insertEntity = dbDao.insert(entity);
		int zero = 0;
		assertEquals(insertEntity.getId(), zero);
		assertEquals(entity.getName(), insertEntity.getName());
	}

	/**
	 * 单实体有@Id并且auto=true，没有赋值id
	 * <p>
	 * 预期结果，可以插入记录，并且id自增
	*/
	@Test
	public void testInsertSingleWithAutoIdNotHasId() {
		MockEntityWithAutoId entity = new MockEntityWithAutoId();
		entity.setName("测试名字");
		MockEntityWithAutoId insertEntity = dbDao.insert(entity);
		assertEquals(entity.getName(), insertEntity.getName());
		assertTrue(insertEntity.getId() > 0);
	}

	/**
	 * 单实体有@Id并且auto=true，赋值id
	 * <p>
	 * 预期结果，可以插入记录，并且id自增,id不等于赋值的id
	*/
	@Test
	public void testInsertSingleWithAutoIdHasId() {
		MockEntityWithAutoId entity = new MockEntityWithAutoId();
		long id = idGen.getId();
		entity.setId(id);
		entity.setName("测试名字");
		MockEntityWithAutoId insertEntity = dbDao.insert(entity);
		assertEquals(entity.getName(), insertEntity.getName());
		assertNotEquals(insertEntity.getId(), id);
	}

	/**
	 * 单实体有@Id并且auto=true，没有赋值id
	 * <p>
	 * 预期结果，可以插入记录，并且id等于id生成策略的id
	*/
	@Test
	public void testInsertSingleWithNotAutoIdNotHasId() {
		MockEntityWithoutAutoId entity = new MockEntityWithoutAutoId();
		entity.setName("测试名字");
		MockEntityWithoutAutoId insertEntity = dbDao.insert(entity);
		assertEquals(entity.getName(), insertEntity.getName());
		assertTrue(insertEntity.getId() > 0);
	}

	/**
	 * 单实体不是auto时，赋值id
	 * <p>
	 * 预期结果，可以插入记录，并且id等于赋值的id
	*/
	@Test
	public void testInsertWithOutAutoHasId() {
		MockEntityWithoutId entityWithoutId = new MockEntityWithoutId();
		long id = idGen.getId();
		entityWithoutId.setId(id);
		entityWithoutId.setName("测试名字");
		MockEntityWithoutId insertEntityWithoutId = dbDao.insert(entityWithoutId);
		assertEquals(insertEntityWithoutId.getId(), id);
		assertEquals(entityWithoutId.getName(), insertEntityWithoutId.getName());

		MockEntityWithoutAutoId entity = new MockEntityWithoutAutoId();
		long id2 = idGen.getId();
		entity.setId(id2);
		entity.setName("测试名字");
		MockEntityWithoutAutoId insertEntity = dbDao.insert(entity);
		assertEquals(entity.getName(), insertEntity.getName());
		assertEquals(insertEntity.getId(), id2);
	}

	/**
	 * 插入数组
	 * <p>
	 * 预期结果，插入成功
	*/
	@Test
	public void testInsertArray() {
		MockEntityWithAutoId[] autoEntities = { new MockEntityWithAutoId("测试1"), new MockEntityWithAutoId("测试2") };
		MockEntityWithAutoId[] result = dbDao.insert(autoEntities);
		assertEquals(result.length, autoEntities.length);
	}

	/**
	 * 插入不同类型的数组
	 * <p>
	 * 预期结果，报错
	*/
	@Test(expectedExceptions = RuntimeException.class)
	public void testInsertArrayNotSame() {
		Object[] autoEntities = { new MockEntityWithAutoId("测试1"), new MockEntityWithoutAutoId("测试2") };
		dbDao.insert(autoEntities);
	}

	/**
	 * 插入集合
	 * <p>
	 * 预期结果，插入成功
	*/
	@Test
	public void testInsertList() {
		Collection<MockEntityWithAutoId> autoEntities = list(new MockEntityWithAutoId("测试1"), new MockEntityWithAutoId(
				"测试2"));
		Collection<MockEntityWithAutoId> result = dbDao.insert(autoEntities);
		assertEquals(result.size(), autoEntities.size());
	}

	/**
	 * 插入数组，含有null元素
	 * <p>
	 * 预期结果，插入失败，报空指针
	*/
	@Test(expectedExceptions = NullPointerException.class)
	public void testInsertArrayHasNull() {
		MockEntityWithAutoId[] autoEntities = { new MockEntityWithAutoId("测试1"), new MockEntityWithAutoId("测试2"), null };
		dbDao.insert(autoEntities);
	}

	/**
	 * 插入集合，含有null元素
	 * <p>
	 * 预期结果，插入失败，报空指针
	*/
	@Test(expectedExceptions = NullPointerException.class)
	public void testInsertListHasNull() {
		Collection<MockEntityWithAutoId> autoEntities = list(new MockEntityWithAutoId("测试1"), new MockEntityWithAutoId(
				"测试2"), null);
		dbDao.insert(autoEntities);
	}

	/**
	 * 插入map
	 * <p>
	 * 预期结果，插入成功
	*/
	@Test
	public void testInsertMap() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(".table", "mock_entity_with_auto_id");
		map.put("name", "测试2");
		Map<String, Object> result = dbDao.insert(map);
		assertEquals(result.keySet(), map.keySet());
	}

	@Test(expectedExceptions = ParamException.class)
	public void testInsertTableByClassClassNull() {
		Class<?> clazz = null;
		Chain chain = null;
		dbDao.insert(clazz, chain);
	}

	@Test(expectedExceptions = ParamException.class)
	public void testInsertTableByClassChainNull() {
		dbDao.insert(MockEntityWithAutoId.class, null);
	}

	@Test
	public void testInsertTableByClass() {
		dbDao.insert(MockEntityWithAutoId.class, Chain.make("name", "测试来着的"));
	}

	@Test(expectedExceptions = ParamException.class)
	public void testInsertTableByTableNameTabelNameNull() {
		String tableName = null;
		Chain chain = null;
		dbDao.insert(tableName, chain);
	}

	@Test(expectedExceptions = ParamException.class)
	public void testInsertTableByTableNameChainNull() {
		dbDao.insert("mock_entity_with_auto_id", null);
	}

	@Test
	public void testInsertTableByTableName() {
		dbDao.insert("mock_entity_with_auto_id", Chain.make("name", "测试来着的"));
	}

	@Test(expectedExceptions = ParamException.class)
	public void testUpdateObjNull() throws Exception {
		dbDao.update(null);
	}

	@Test(expectedExceptions = RuntimeException.class)
	public void testUpdateWithoutId() {
		MockEntityWithoutId entity = new MockEntityWithoutId("测试");
		dbDao.update(entity);
	}

	@Test(expectedExceptions = RuntimeException.class)
	public void testUpdateWithoutIdParty() {
		MockEntityWithoutId entity = new MockEntityWithoutId("测试");
		dbDao.update(entity, "name");
	}

	@Test
	public void testUpdateSingleAll() {
		MockEntityWithAutoId entity = new MockEntityWithAutoId("测试名字");
		dbDao.insert(entity);
		String updateName = "测试后的名字";
		int updateAge = 11;
		entity.setName(updateName);
		entity.setAge(updateAge);
		dbDao.update(entity);
		MockEntityWithAutoId updateEntity = dbDao.fetch(MockEntityWithAutoId.class, entity.getId());
		assertEquals(updateAge, updateEntity.getAge());
		assertEquals(updateName, updateEntity.getName());
	}

	@Test
	public void testUpdateSingleParty() {
		MockEntityWithAutoId entity = new MockEntityWithAutoId("测试名字");
		int orginalAge = 10;
		entity.setAge(orginalAge);
		dbDao.insert(entity);
		String updateName = "测试后的名字";
		int updateAge = 11;
		entity.setName(updateName);
		entity.setAge(updateAge);
		dbDao.update(entity, "name");
		MockEntityWithAutoId updateEntity = dbDao.fetch(MockEntityWithAutoId.class, entity.getId());
		assertEquals(orginalAge, updateEntity.getAge());
		assertEquals(updateName, updateEntity.getName());
	}

	/**
	 * 更新数组部分属性
	*/
	@Test
	public void testUpdateArrayParty() {
		MockEntityWithAutoId entity1 = new MockEntityWithAutoId("测试1");
		int orginalAge1 = 9;
		entity1.setAge(orginalAge1);
		MockEntityWithAutoId entity2 = new MockEntityWithAutoId("测试2");
		int orginalAge2 = 8;
		entity2.setAge(orginalAge2);
		MockEntityWithAutoId[] entities = new MockEntityWithAutoId[] { entity1, entity2 };
		dbDao.insert(entities);
		String updateName = "修改后的测试名字";
		int updateAge = 11;
		entity1.setName(updateName);
		entity1.setAge(updateAge);
		entity2.setName(updateName);
		entity2.setAge(updateAge);
		int count = dbDao.update(entities, "name");
		assertEquals(count, entities.length);
		MockEntityWithAutoId updateEntity1 = dbDao.fetch(MockEntityWithAutoId.class, entity1.getId());
		assertEquals(updateName, updateEntity1.getName());
		assertEquals(orginalAge1, updateEntity1.getAge());

		MockEntityWithAutoId updateEntity2 = dbDao.fetch(MockEntityWithAutoId.class, entity2.getId());
		assertEquals(updateName, updateEntity2.getName());
		assertEquals(orginalAge2, updateEntity2.getAge());
	}

	/**
	 * 更新数组所有属性
	*/
	@Test
	public void testUpdateArrayAll() {
		MockEntityWithAutoId entity1 = new MockEntityWithAutoId("测试1");
		int orginalAge1 = 9;
		entity1.setAge(orginalAge1);
		MockEntityWithAutoId entity2 = new MockEntityWithAutoId("测试2");
		int orginalAge2 = 8;
		entity2.setAge(orginalAge2);
		MockEntityWithAutoId[] entities = new MockEntityWithAutoId[] { entity1, entity2 };
		dbDao.insert(entities);
		String updateName = "修改后的测试名字";
		int updateAge = 11;
		entity1.setName(updateName);
		entity1.setAge(updateAge);
		entity2.setName(updateName);
		entity2.setAge(updateAge);
		int count = dbDao.update(entities);
		assertEquals(count, entities.length);
		MockEntityWithAutoId updateEntity1 = dbDao.fetch(MockEntityWithAutoId.class, entity1.getId());
		assertEquals(updateName, updateEntity1.getName());
		assertEquals(updateAge, updateEntity1.getAge());

		MockEntityWithAutoId updateEntity2 = dbDao.fetch(MockEntityWithAutoId.class, entity2.getId());
		assertEquals(updateName, updateEntity2.getName());
		assertEquals(updateAge, updateEntity2.getAge());
	}

	/**
	 * 更新集合部分属性
	*/
	@Test
	public void testUpdateListParty() {
		MockEntityWithAutoId entity1 = new MockEntityWithAutoId("测试1");
		int orginalAge1 = 9;
		entity1.setAge(orginalAge1);
		MockEntityWithAutoId entity2 = new MockEntityWithAutoId("测试2");
		int orginalAge2 = 8;
		entity2.setAge(orginalAge2);
		Collection<MockEntityWithAutoId> entities = list(entity1, entity2);
		dbDao.insert(entities);
		String updateName = "修改后的测试名字";
		int updateAge = 11;
		entity1.setName(updateName);
		entity1.setAge(updateAge);
		entity2.setName(updateName);
		entity2.setAge(updateAge);
		int count = dbDao.update(entities, "name");
		assertEquals(count, entities.size());
		MockEntityWithAutoId updateEntity1 = dbDao.fetch(MockEntityWithAutoId.class, entity1.getId());
		assertEquals(updateName, updateEntity1.getName());
		assertEquals(orginalAge1, updateEntity1.getAge());

		MockEntityWithAutoId updateEntity2 = dbDao.fetch(MockEntityWithAutoId.class, entity2.getId());
		assertEquals(updateName, updateEntity2.getName());
		assertEquals(orginalAge2, updateEntity2.getAge());
	}

	/**
	 * 更新集合所有属性
	*/
	@Test
	public void testUpdateListAll() {
		MockEntityWithAutoId entity1 = new MockEntityWithAutoId("测试1");
		int orginalAge1 = 9;
		entity1.setAge(orginalAge1);
		MockEntityWithAutoId entity2 = new MockEntityWithAutoId("测试2");
		int orginalAge2 = 8;
		entity2.setAge(orginalAge2);
		Collection<MockEntityWithAutoId> entities = list(entity1, entity2);
		dbDao.insert(entities);
		String updateName = "修改后的测试名字";
		int updateAge = 11;
		entity1.setName(updateName);
		entity1.setAge(updateAge);
		entity2.setName(updateName);
		entity2.setAge(updateAge);
		int count = dbDao.update(entities);
		assertEquals(count, entities.size());
		MockEntityWithAutoId updateEntity1 = dbDao.fetch(MockEntityWithAutoId.class, entity1.getId());
		assertEquals(updateName, updateEntity1.getName());
		assertEquals(updateAge, updateEntity1.getAge());

		MockEntityWithAutoId updateEntity2 = dbDao.fetch(MockEntityWithAutoId.class, entity2.getId());
		assertEquals(updateName, updateEntity2.getName());
		assertEquals(updateAge, updateEntity2.getAge());
	}

	@Test(expectedExceptions = ParamException.class)
	public void testUpdateByTableNameTableNameNull() {
		String tableName = null;
		dbDao.update(tableName, null, null);
	}

	@Test(expectedExceptions = ParamException.class)
	public void testUpdateByClassTableNameNull() {
		Class<?> clazz = null;
		dbDao.update(clazz, null, null);
	}

	@Test(expectedExceptions = ParamException.class)
	public void testUpdateMap() {
		dbDao.update(MapUtil.map());
	}

	@Test(expectedExceptions = ParamException.class)
	public void testFetchByClassNull() throws Exception {
		Class<?> clazz = null;
		dbDao.fetch(clazz, Cnd.limit());
	}

	@Test(expectedExceptions = ParamException.class)
	public void testFetchByTableNameNull() throws Exception {
		String tableName = null;
		dbDao.fetch(tableName, Cnd.limit());
	}

	@Test(expectedExceptions = DaoException.class)
	public void testFetchByIdNotHasId() {
		long id = -1L;
		dbDao.fetch(MockEntityWithoutId.class, id);
	}

	@Test
	public void testFetchByCndNotHasId() {
		int notExistsId = -1;
		MockEntityWithoutId entity = dbDao.fetch(MockEntityWithoutId.class, Cnd.where("id", "=", notExistsId));
		assertNull(entity);
	}

	@Test
	public void testFetchByClassById() {
		MockEntityWithoutAutoId entity = new MockEntityWithoutAutoId("测试");
		dbDao.insert(entity);
		MockEntityWithoutAutoId fetchEntity = dbDao.fetch(MockEntityWithoutAutoId.class, entity.getId());
		assertNotNull(fetchEntity);
		assertEquals(entity, fetchEntity);
	}

	@Test
	public void testFetchByClassByNotExistsId() {
		int notExistsId = -1;
		MockEntityWithoutAutoId fetchEntity = dbDao.fetch(MockEntityWithoutAutoId.class, notExistsId);
		assertNull(fetchEntity);
	}

	@Test
	public void testFetchByClassByCnd() {
		MockEntityWithoutAutoId entity = new MockEntityWithoutAutoId("测试");
		dbDao.insert(entity);
		MockEntityWithoutAutoId fetchEntity = dbDao.fetch(MockEntityWithoutAutoId.class,
				Cnd.where("id", "=", entity.getId()));
		assertNotNull(fetchEntity);
		assertEquals(entity, fetchEntity);
	}

	@Test
	public void testFetchByTableNameById() {
		MockEntityWithoutAutoId entity = new MockEntityWithoutAutoId("测试");
		dbDao.insert(entity);
		Record fetchEntity = dbDao.fetch("mock_entity_without_auto_id", entity.getId());
		assertNotNull(fetchEntity);
		assertEquals(entity.getName(), fetchEntity.getString("name"));
		assertEquals(entity.getId(), RecordUtil.getLong(fetchEntity, "id"));
	}

	@Test
	public void testFetchByTableNameByCnd() {
		MockEntityWithoutAutoId entity = new MockEntityWithoutAutoId("测试");
		dbDao.insert(entity);
		Record fetchEntity = dbDao.fetch("mock_entity_without_auto_id", Cnd.where("id", "=", entity.getId()));
		assertNotNull(fetchEntity);
		assertEquals(entity.getName(), fetchEntity.getString("name"));
		assertEquals(entity.getId(), RecordUtil.getLong(fetchEntity, "id"));
	}

	@Test
	public void testFetchByTableNameByNotExistsId() {
		int notExistsId = -1;
		Record fetchEntity = dbDao.fetch("mock_entity_without_auto_id", notExistsId);
		assertNull(fetchEntity);
	}

	@Test(expectedExceptions = ParamException.class)
	public void testFetchBySqlNull() {
		Sql sql = null;
		assertNull(sql);
		dbDao.fetch(sql);
	}

	@Test
	public void testFetchBySql() {
		MockEntityWithoutAutoId entity = new MockEntityWithoutAutoId("测试");
		dbDao.insert(entity);
		Sql sql = Sqls.create("select * from mock_entity_without_auto_id $condition");
		sql.setCondition(Cnd.where("id", "=", entity.getId()));
		Record record = dbDao.fetch(sql);
		assertNotNull(record);
		assertEquals(entity.getId(), RecordUtil.getLong(record, "id"));
	}

	@Test
	public void testFetchBySqlNotExists() {
		Sql sql = Sqls.create("select * from mock_entity_without_auto_id $condition");
		int notExistsId = -1;
		sql.setCondition(Cnd.where("id", "=", notExistsId));
		Record record = dbDao.fetch(sql);
		assertNull(record);
	}

	@Test(expectedExceptions = ParamException.class)
	public void testDeleteNull() throws Exception {
		dbDao.delete(null);
	}

	@Test(expectedExceptions = ParamException.class)
	public void testDeleteByIdNull() throws Exception {
		dbDao.delete(null, 1L);
	}

	@Test(expectedExceptions = RuntimeException.class)
	public void testDeleteWithoutId() {
		MockEntityWithoutId entity = new MockEntityWithoutId("测试");
		dbDao.insert(entity);
		dbDao.delete(entity);
	}

	@Test
	public void testDeleteNormal() {
		MockEntityWithAutoId entity = new MockEntityWithAutoId("测试");
		dbDao.insert(entity);
		assertEquals(dbDao.delete(entity), 1);
		MockEntityWithAutoId fetchEntity = dbDao.fetch(MockEntityWithAutoId.class, entity.getId());
		assertNull(fetchEntity);
	}

	@Test
	public void testDeleteByIdNormal() {
		MockEntityWithAutoId entity = new MockEntityWithAutoId("测试");
		dbDao.insert(entity);
		assertEquals(dbDao.delete(MockEntityWithAutoId.class, entity.getId()), 1);
		MockEntityWithAutoId fetchEntity = dbDao.fetch(MockEntityWithAutoId.class, entity.getId());
		assertNull(fetchEntity);
	}

	/**
		 * 测试执行语句为空
		*/
	@Test(expectedExceptions = ParamException.class)
	public void testExecuteNull() throws Exception {
		dbDao.execute();
	}

	@Test(expectedExceptions = ParamException.class)
	public void testQueryByClassClazzNull() throws Exception {
		Class<?> clazz = null;
		dbDao.query(clazz, null, null);
	}

	@Test(expectedExceptions = ParamException.class)
	public void testQueryByTableNameTableNameNull() throws Exception {
		String tableName = null;
		dbDao.query(tableName, null, null);
	}

	@Test
	public void testQueryByClassCndAndPagerNull() {
		List<?> list = dbDao.query(MockEntity4DbQuery.class, null, null);
		assertEquals(totalQuerySize, list.size());
	}

	@Test
	public void testQueryByTableNameCndAndPagerNull() {
		List<?> list = dbDao.query("mock_entity_4_query", null, null);
		assertEquals(totalQuerySize, list.size());
	}

	@Test
	public void testQueryByClass() {
		int pageSize = 21;
		List<?> list = dbDao.query(MockEntity4DbQuery.class, null, new Pager().setPageSize(pageSize));
		assertEquals(pageSize, list.size());
	}

	@Test
	public void testQueryByTableName() {
		int pageSize = 21;
		List<?> list = dbDao.query("mock_entity_4_query", null, new Pager().setPageSize(pageSize));
		assertEquals(pageSize, list.size());
	}

	@Test
	public void testQueryByClassCnd() {
		int id = 12;
		List<?> list = dbDao.query(MockEntity4DbQuery.class, Cnd.where("id", "<=", id), null);
		assertEquals(id, list.size());
	}

	@Test
	public void testQueryByTableNameCnd() {
		int id = 12;
		List<?> list = dbDao.query("mock_entity_4_query", Cnd.where("id", "<=", id), null);
		assertEquals(id, list.size());
	}

	@Test(expectedExceptions = ParamException.class)
	public void testQueryBySqlSqlNull() {
		Sql sql = null;
		assertNull(sql);
		dbDao.query(sql, null, null);
	}

	@Test
	public void testQueryBySqlCndAndPagerNull() {
		Sql sql = Sqls.create(" select * from mock_entity_4_query");
		List<Record> list = dbDao.query(sql, null, null);
		assertEquals(totalQuerySize, list.size());
	}

	@Test
	public void testQueryBySql() {
		int pageSize = 21;
		Sql sql = Sqls.create(" select * from mock_entity_4_query");
		List<Record> list = dbDao.query(sql, null, new Pager().setPageSize(pageSize));
		assertEquals(pageSize, list.size());
	}

	@Test
	public void testQueryBySqlCnd() {
		int id = 12;
		Sql sql = Sqls.create(" select * from mock_entity_4_query $condition");
		List<?> list = dbDao.query(sql, Cnd.where("id", "<=", id), null);
		assertEquals(id, list.size());
	}
}