/**
 * TestImageUtil.java
 * com.uxuexi.core.common.util
 * Copyright (c) 2014, 北京聚智未来科技有限公司版权所有.
*/

package com.uxuexi.core.common.util;

import java.io.File;

import org.testng.annotations.Test;

import com.uxuexi.core.common.util.ImageUtil;

/**
 * TODO(这里用一句话描述这个类的作用)
 * <p>
 * TODO(这里描述这个类补充说明 – 可选)
 *
 * @author   庄君祥
 * @Date	 Jul 9, 2014 	 
 */
public class TestImageUtil {

	@Test(enabled = false)
	public void testCut() {
		File file = new File("D:/1.jpg");
		ImageUtil.cut(file, file.getAbsolutePath(), 5, 5, 10, 10, "jpg");
	}

	@Test(enabled = false)
	public void testZoom() {
		File file = new File("D:/1.jpg");
		ImageUtil.zoom(file, "E:/1.jpg", 20, 30, "jpg");
	}

}
