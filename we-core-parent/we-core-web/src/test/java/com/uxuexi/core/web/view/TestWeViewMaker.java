/**
 * TestweViewMaker.java
 * com.uxuexi.core.web.view
 * Copyright (c) 2014, 北京聚智未来科技有限公司版权所有.
*/

package com.uxuexi.core.web.view;

import java.util.regex.Pattern;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * weViewMaker测试类
 * 
 * @author   庄君祥
 * @Date	 Dec 26, 2013 	 
 */
public class TestWeViewMaker {
	@Test
	public void testMake() throws Exception {
		Pattern jspPattern = Pattern.compile("\\s*jsp\\s*");
		String jsp = "jsp";
		Assert.assertTrue(jspPattern.matcher(jsp).find());
		String jspBlankBefore = "  jsp";
		Assert.assertTrue(jspPattern.matcher(jspBlankBefore).find());
		String jspBlankBoth = "  jsp    ";
		Assert.assertTrue(jspPattern.matcher(jspBlankBoth).find());
		String jspBlankAfter = "jsp    ";
		Assert.assertTrue(jspPattern.matcher(jspBlankAfter).find());
		String jspTableAfter = "jsp		";
		Assert.assertTrue(jspPattern.matcher(jspTableAfter).find());

		String jspWithParam = "jsp-p";
		Assert.assertTrue(jspPattern.matcher(jspWithParam).find());
	}
}
