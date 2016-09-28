package com.uxuexi.core.common.util;

import java.io.File;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.uxuexi.core.common.util.FileUtil;

/**
 * 无需自动测试
 * 

 * @Date	 2013-2-1
 * @version  5.1.0
 */
public class TestFileUtil {

	@Test
	public void testCreateNewFile() {
		File file = new File("sss.txt");
		Assert.assertEquals(true, FileUtil.createNewFile(file));
	}

	@Test(dependsOnMethods = "testCreateNewFile")
	public void testIsExist() {
		Assert.assertEquals(true, FileUtil.isExist("sss.txt"));
	}

	@Test(dependsOnMethods = "testIsExist")
	public void testCopyFile() {
		String tagFileName = "ttt.txt";
		Assert.assertEquals(true, FileUtil.copyFile("sss.txt", tagFileName));
		Assert.assertTrue(FileUtil.isExist(tagFileName));
		Assert.assertEquals(true, FileUtil.deleteFile(tagFileName));
	}

	@Test(dependsOnMethods = "testCopyFile")
	public void testDeleteFile() {
		Assert.assertEquals(true, FileUtil.deleteFile("sss.txt"));
	}

	@Test
	public void testMakeDir() {
		String pathname = "new_1bak";
		FileUtil.deleteFile(pathname);
		File file = new File(pathname);
		Assert.assertEquals(true, FileUtil.makeDir(file));
		Assert.assertTrue(FileUtil.isExist(pathname));
	}

	@Test(dependsOnMethods = "testMakeDir")
	public void testGetFileName() {
		String pathName = "new_1bak";
		Assert.assertEquals("new_1bak", FileUtil.getFileName(pathName));
		Assert.assertEquals(true, FileUtil.deleteFile(pathName));
	}
}
