/**
 * TestBaseService.java
 * com.uxuexi.core.db.service
 * Copyright (c) 2015, 北京聚智未来科技有限公司版权所有.
*/

package com.uxuexi.core.db.service;

import org.nutz.dao.impl.NutDao;
import org.nutz.ioc.Ioc;
import org.nutz.ioc.impl.NutIoc;
import org.nutz.ioc.loader.annotation.AnnotationIocLoader;
import org.nutz.ioc.loader.combo.ComboIocLoader;
import org.nutz.ioc.loader.json.JsonLoader;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.uxuexi.core.common.exception.impl.ParamException;
import com.uxuexi.core.db.dao.IDbDao;
import com.uxuexi.core.db.mock.entity.MockEntity4Base;
import com.uxuexi.core.db.mock.service.BaseMockService;

/**
 * 测试实体的基础业务
 *
 * @author   庄君祥
 * @Date	 2015年10月14日 	 
 */
public class TestEntityBaseService {
	private IDbDao dbDao;
	private NutDao nutDao;
	private Ioc ioc;
	private BaseMockService baseMockService;

	@BeforeClass
	public void init() {
		ioc = new NutIoc(new ComboIocLoader(new JsonLoader("db/config/dao.js"), new AnnotationIocLoader("com.uxuexi")));
		nutDao = ioc.get(NutDao.class, "nutDao");
		dbDao = ioc.get(IDbDao.class, "dbDao");
		nutDao.create(MockEntity4Base.class, true);
		baseMockService = ioc.get(BaseMockService.class);
	}

	private MockEntity4Base create() {
		MockEntity4Base entity = new MockEntity4Base();
		entity.setName("哈哈");
		entity.setType(1);
		return dbDao.insert(entity);
	}

	@Test
	public void testGet() throws Exception {
		MockEntity4Base entity = create();
		MockEntity4Base mockEntity = baseMockService.get(entity.getId());
		Assert.assertNotNull(mockEntity);
		Assert.assertEquals(entity, mockEntity);
	}

	@Test
	public void testGetWithNull() throws Exception {
		long notExistsId = 110;
		MockEntity4Base mockEntity = baseMockService.get(notExistsId);
		Assert.assertNull(mockEntity);
	}

	@Test(expectedExceptions = ParamException.class)
	public void testGetWithEx() throws Exception {
		long notExistsId = 120;
		baseMockService.getWithEx(notExistsId);
	}

}
