/**
 * TestArrayUtil.java
 * com.uxuexi.common.util
 * Copyright (c) 2014, 北京聚智未来科技有限公司版权所有.
*/

package com.uxuexi.core.common.util;

import org.testng.Assert;
import org.testng.annotations.Test;

/**

 * @Date	 2014-4-3 	 
 */
public class TestArrayUtil {

	@Test
	public void testArray2List() {
		String[] str = ArrayUtil.array("1", "2");
		Assert.assertEquals("1", ArrayUtil.array2List(str).get(0));
		Assert.assertEquals("2", ArrayUtil.array2List(str).get(1));
		Assert.assertEquals(str.length, ArrayUtil.array2List(str).size());
	}

	@Test
	public void testArrayWithEmpty() throws Exception {
		Object[] array = ArrayUtil.array();
		Assert.assertTrue(Util.isEmpty(array));
	}

	@Test
	public void testArray() throws Exception {
		Integer[] array = ArrayUtil.array(1, 2, 3);
		Assert.assertEquals(array.length, 3);
		Integer[] array2 = ArrayUtil.array(1, 2, 1);
		Assert.assertEquals(array2.length, 3);
	}

}
