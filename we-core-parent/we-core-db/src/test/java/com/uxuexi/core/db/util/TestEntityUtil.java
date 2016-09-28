/**
 * TestAnnotationUtil.java
 * com.uxuexi.core.db.util
 * Copyright (c) 2014, 北京聚智未来科技有限公司版权所有.
*/

package com.uxuexi.core.db.util;

import static org.testng.Assert.*;

import org.testng.annotations.Test;

import com.uxuexi.core.db.util.EntityUtil;

import com.uxuexi.core.db.mock.entity.MockEntity;

/**
 * AnnotationUtil单元测试类
 *
 * @author   庄君祥
 * @Date	 2013-12-10 	 
 */
public class TestEntityUtil {

	@Test
	public void testGetTableName() {
		assertEquals("mock_entity", EntityUtil.getTableName(MockEntity.class));
	}

	@Test
	public void testEName2TName() {
		assertEquals("mock_entity", EntityUtil.eName2TName(MockEntity.class.getSimpleName()));
	}

}
