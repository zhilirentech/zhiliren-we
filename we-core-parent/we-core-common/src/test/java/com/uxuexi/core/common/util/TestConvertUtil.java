/**
 * TestConvertUtil.java
 * com.uxuexi.common.util
 * Copyright (c) 2014, 北京聚智未来科技有限公司版权所有.
*/

package com.uxuexi.core.common.util;

import static com.uxuexi.core.common.util.ConvertUtil.*;
import static org.testng.Assert.*;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import org.testng.annotations.Test;

import com.uxuexi.core.common.util.CollectionUtil;
import com.uxuexi.core.common.util.ConvertUtil;
import com.uxuexi.core.common.util.JsonUtil;
import com.uxuexi.core.common.util.MapUtil;

import com.uxuexi.core.common.entity.MockEntity;

/**

 * @author   庄君祥
 * @Date	 2014-4-3 
 * @version  5.1.0 
 */
public class TestConvertUtil {

	@Test
	public void testObj2str() {
		assertEquals("1000", obj2str(Long.valueOf(1000)));
		assertEquals("true", obj2str(true));
		assertEquals("100", obj2str(100));
		assertEquals("1000.0", obj2str(Double.valueOf(1000.0)));
		assertEquals("", obj2str(null));
	}

	@Test
	public void testObj2long() throws Exception {
		assertEquals(1000, obj2long("1000"));
		assertEquals(-1, obj2long("-1"));
		assertEquals(0, obj2long("0"));
		assertEquals(-1, obj2long(null));
		assertEquals(-1, obj2long("abc"));
	}

	@Test
	public void testObj2Map() {
		MockEntity entity = new MockEntity();
		long time = System.currentTimeMillis();
		Timestamp now = new Timestamp(time);
		entity.setRegisterTime(now);
		int age = 1;
		entity.setAge(age);
		List<String> list = CollectionUtil.list("a", "b");
		entity.setList(list);
		Map<String, String> map = MapUtil.map("a", "a1", "b", "b1");
		entity.setMap(map);
		MockEntity subEntity = new MockEntity();
		subEntity.setRegisterTime(now);
		entity.setEntity(subEntity);
		Map<String, String> simpleResult = obj2SimpleMap(entity);
		assertEquals(age, obj2long(simpleResult.get("age")));
		assertFalse(simpleResult.containsKey("list"));
		assertFalse(simpleResult.containsKey("map"));
		assertFalse(simpleResult.containsKey("entity"));

		Map<String, String> result = obj2Map(entity);
		assertEquals(age, obj2long(simpleResult.get("age")));
		assertEquals(list, JsonUtil.fromJsonAsList(String.class, result.get("list")));
		assertEquals(map, JsonUtil.fromJsonAsMap(String.class, result.get("map")));
		//TODO 时间未测试通过
		assertEquals(new Date(now.getTime()).toString(),
				new Date(entity(result).getRegisterTime().getTime()).toString());
		MockEntity tranEntity = ConvertUtil.map2Object(result, MockEntity.class);
		assertEquals(new Date(now.getTime()).toString(), new Date(tranEntity.getRegisterTime().getTime()).toString());
	}

	private MockEntity entity(final Map<String, String> result) {
		return JsonUtil.fromJson(result.get("entity"), MockEntity.class);
	}

}
