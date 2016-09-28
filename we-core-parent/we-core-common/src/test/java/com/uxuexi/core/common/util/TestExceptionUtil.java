/**
 * TestExceptionUtil.java
 * com.uxuexi.common.util
 * Copyright (c) 2014, 北京聚智未来科技有限公司版权所有.
*/

package com.uxuexi.core.common.util;

import static com.uxuexi.core.common.util.ExceptionUtil.*;

import org.testng.annotations.Test;

import com.uxuexi.core.common.exception.impl.ParamException;

/**


 * @Date	 2013-1-30 	 
 * @version  5.1.0
 */
public class TestExceptionUtil {

	@Test(expectedExceptions = ParamException.class)
	public void testCheckEmptyWithEmptyStr() {
		checkEmpty("", "");
	}
}
