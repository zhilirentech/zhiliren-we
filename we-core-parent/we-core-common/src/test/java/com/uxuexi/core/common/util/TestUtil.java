/**
 * TestUtil.java
 * com.uxuexi.common.util
 * Copyright (c) 2014, 北京聚智未来科技有限公司版权所有.
*/

package com.uxuexi.core.common.util;

import static com.uxuexi.core.common.util.CollectionUtil.*;
import static com.uxuexi.core.common.util.MapUtil.*;
import static com.uxuexi.core.common.util.Util.*;
import static org.testng.Assert.*;

import org.testng.annotations.Test;

import com.uxuexi.core.common.entity.MockEntity;

/**

 * @Date	 2014-4-3 
 * @version  5.0.1	 
 */
public class TestUtil {

	@Test
	public void testIsEmpty() {
		assertTrue(isEmpty(null));
		assertTrue(isEmpty(""));
		assertTrue(isEmpty("   "));
		assertTrue(isEmpty(new String[0]));
		assertTrue(isEmpty(list()));
		assertTrue(isEmpty(map()));
		assertFalse(isEmpty(new MockEntity()));
	}

	@Test
	public void testEq() {
		assertTrue(eq(Long.valueOf("1"), Long.valueOf("1")));
		assertTrue(eq("1", Long.valueOf("1")));
		assertFalse(eq(null, "null"));
	}

	@Test
	public void testFirst() {
		assertNull(first(null));
		assertEquals(1l, first(1l));
		assertEquals(1l, first(list(1l, 2l)));
	}
}
