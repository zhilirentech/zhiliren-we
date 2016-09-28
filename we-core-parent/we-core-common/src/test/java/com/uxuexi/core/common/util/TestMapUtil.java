package com.uxuexi.core.common.util;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.uxuexi.core.common.util.MapUtil;

/**

 * @Date	 2013-2-1
 * @version  5.0.2
 */
public class TestMapUtil {

	@Test
	public void testMapKV() {
		Assert.assertEquals("value1", MapUtil.map("key1", "value1").get("key1"));
	}

	@Test
	public void testMapObjectArray() {
		Assert.assertEquals("4444", MapUtil.map("key2", "2222", "key4", "4444").get("key4"));
	}

	@Test
	public void testLinkedMapKV() {
		Assert.assertEquals("1111", MapUtil.linkedMap("keyssss", "1111").get("keyssss"));
	}

	@Test
	public void testLinkedMapObjectArray() {
		Assert.assertEquals("22222", MapUtil.linkedMap("key1", "11111", "key2", "22222").get("key2"));
	}
}
