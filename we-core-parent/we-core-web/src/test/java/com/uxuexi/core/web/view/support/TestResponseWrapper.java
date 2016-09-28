/**
 * TestResponseWrapper.java
 * com.uxuexi.core.web.view.support
 * Copyright (c) 2014, 北京聚智未来科技有限公司版权所有.
*/

package com.uxuexi.core.web.view.support;

import javax.servlet.http.HttpServletResponse;

import org.easymock.EasyMock;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.uxuexi.core.web.view.support.ResponseWrapper;

/**
 * responseWrapper 单元测试
 * @author   庄君祥
 * @Date	 2013-12-25 	 
 */
public class TestResponseWrapper {
	private ResponseWrapper rw;

	@BeforeClass
	public void init() {
		HttpServletResponse rs = EasyMock.createNiceMock(HttpServletResponse.class);
		rs.getCharacterEncoding();
		EasyMock.expectLastCall().andReturn("UTF-8");

		EasyMock.replay(rs);
		rw = new ResponseWrapper(rs);
	}

	@Test
	public void testGetOutputStream() throws Throwable {
		rw.getWriter().write("abc");
		Assert.assertEquals(rw.getContent(), "abc");
	}
}
