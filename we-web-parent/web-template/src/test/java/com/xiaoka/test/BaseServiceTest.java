/**
 * TerminalTest.java
 * com.xiaoka.terminal
 * Copyright (c) 2015, 北京浪潮世纪科技有限公司版权所有.
*/

package com.xiaoka.test;

import java.util.List;

import org.nutz.dao.entity.annotation.Table;
import org.nutz.dao.impl.NutDao;
import org.nutz.ioc.Ioc;
import org.nutz.ioc.impl.NutIoc;
import org.nutz.ioc.loader.annotation.AnnotationIocLoader;
import org.nutz.ioc.loader.combo.ComboIocLoader;
import org.nutz.ioc.loader.json.JsonLoader;
import org.nutz.log.Log;
import org.nutz.log.Logs;
import org.nutz.resource.Scans;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.xiaoka.test.entity.TestEntity;
import com.xiaoka.test.service.MockTestTableService;

/**
 * TODO(作用)
 * <p>
 * TODO(类补充说明 )
 *
 * @author   朱晓川
 * @Date	 2015-6-4 	 
 */
public class BaseServiceTest {

	private Ioc ioc;
	private NutDao nutDao;

	private MockTestTableService mockTestTableService;

	private static final Log log = Logs.get();

	@BeforeClass
	public void createTableIfNotExists() {
		ioc = new NutIoc(
				new ComboIocLoader(new JsonLoader("config/dao.js"), new AnnotationIocLoader("com.xiaoka.test")));
		nutDao = ioc.get(NutDao.class, "nutDao");
		mockTestTableService = ioc.get(MockTestTableService.class, "mockTestTableService");

		//扫描指定包中的所有class，创建需要的数据库表
		List<Class<?>> classes = Scans.me().scanPackage("com.xiaoka.test");
		for (Class<?> clazz : classes) {
			if (clazz.isAnnotationPresent(Table.class)) {
				nutDao.create(clazz, false);
				log.info("create table by entity :" + clazz.getSimpleName());
			}
		}
		log.info("create table if not exists done");
	}

	@Test
	public void testInsert() {
		TestEntity en = new TestEntity();
		en.setName("testInsert");
		try {
			en = mockTestTableService.fastInsert(en);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("=========================>id:" + en.getId());
		Assert.assertTrue(en.getId() > 0);
	}
}
