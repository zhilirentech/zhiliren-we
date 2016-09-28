/**
 * TestEnumUtil.java
 * com.uxuexi.common.enums
 * Copyright (c) 2014, 北京聚智未来科技有限公司版权所有.
*/

package com.uxuexi.core.common.util;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.uxuexi.core.common.util.EnumUtil;

import com.uxuexi.core.common.entity.MockEnum;

/**

 * @Date	 2014-4-3 	
 * @version  5.0.1  
 */
public class TestEnumUtil {

	@Test
	public void testEnum2() throws Exception {
		Map<String, String> re = EnumUtil.enum2(MockEnum.class);
		Assert.assertEquals(2, re.size());
		Assert.assertEquals("测试", re.get("test"));
		Assert.assertEquals("模拟", re.get("mock"));
	}
}
