/**
 * TestWeCnd.java
 * com.uxuexi.core.db.support
 * Copyright (c) 2014, 北京聚智未来科技有限公司版权所有.
*/

package com.uxuexi.core.db.support;

import org.nutz.dao.Cnd;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.uxuexi.core.common.util.ArrayUtil;
import com.uxuexi.core.common.util.CollectionUtil;
import com.uxuexi.core.db.support.WeCnd;

/**
 * TODO(这里用一句话描述这个类的作用)
 * <p>
 * TODO(这里描述这个类补充说明 – 可选)
 *
 * @author   庄君祥
 * @Date	 2014-7-8 	 
 */
public class TestWeCnd {

	@Test
	public void testWithNull() throws Exception {
		Cnd cnd = WeCnd.limit().and("a", "=", null);
		Assert.assertEquals(cnd.toString(), "");
	}

	@Test
	public void testWithCollectionEmpty() throws Exception {
		Cnd cnd = WeCnd.limit().and("a", "=", CollectionUtil.list());
		Assert.assertEquals(cnd.toString(), "");
	}

	@Test
	public void testWithArrayEmpty() throws Exception {
		Cnd cnd = WeCnd.limit().and("a", "=", ArrayUtil.array());
		Assert.assertEquals(cnd.toString(), "");
	}

	@Test
	public void testNumberWithLongIll() {
		long illLong = -1L;
		Cnd cnd = WeCnd.limit().and("a", "=", illLong);
		Assert.assertEquals(cnd.toString(), "");
	}

	@Test
	public void testNumberWithIntIll() {
		int illInt = -1;
		//数字的空怎么
		Cnd cnd = WeCnd.limit().and("a", "=", illInt);
		Assert.assertEquals(cnd.toString(), "");
	}

	@Test
	public void testNumberWithLong() {
		long longValue = System.currentTimeMillis();
		String name = "a";
		String op = "=";
		Cnd cnd = WeCnd.where(name, op, longValue);
		Assert.assertEquals(cnd.toString().trim(), "WHERE " + name + op + longValue);
	}

	@Test
	public void testNumberWithInt() {
		int intValue = 1;
		String name = "a";
		String op = "=";
		Cnd cnd = WeCnd.where(name, op, intValue);
		Assert.assertEquals(cnd.toString().trim(), "WHERE " + name + op + intValue);
	}

	@Test
	public void testWhereWithIllLog() {
		int illLong = -11;
		String name = "a";
		String op = "=";
		Cnd cnd = WeCnd.where(name, op, illLong);
		Assert.assertEquals(cnd.toString(), "");
	}

	@Test
	public void testWhereWithIntLog() {
		int illInt = -11;
		String name = "a";
		String op = "=";
		Cnd cnd = WeCnd.where(name, op, illInt);
		Assert.assertEquals(cnd.toString(), "");
	}

	@Test
	public void testWhere() {
		int intValue = 11;
		String name = "a";
		String op = "=";
		Cnd cnd = WeCnd.where(name, op, intValue);
		Assert.assertEquals(cnd.toString().trim(), "WHERE " + name + op + intValue);
	}

}
