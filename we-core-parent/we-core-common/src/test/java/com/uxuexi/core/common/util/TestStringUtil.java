package com.uxuexi.core.common.util;

import static com.uxuexi.core.common.util.StringUtil.*;
import static org.testng.Assert.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.testng.annotations.Test;

/**


 * @Date	 2013-2-1
 * @version  5.2.0
 */
public class TestStringUtil {
	@Test
	public void testFormatStringObjectArray() {
		String testStr = "www.we.cn?login.html&id={0}";
		assertEquals("www.we.cn?login.html&id=000", format(testStr, "000"));
	}

	@Test
	public void testFormatLocaleStringObjectArray() {
		String str1 = "www.we.cn?login.html&id={0}";
		assertEquals("www.we.cn?login.html&id=111", format(Locale.getDefault(), str1, "111"));
	}

	@Test
	public void testJoinObjectCollectionOfT() {
		List<String> list = new ArrayList<String>();
		list.add("a");
		list.add("b");
		assertEquals("a,b", join(",", list));

	}

	@Test
	public void testJoinObjectCollectionOfArray() {
		String[] str = new String[3];
		str[0] = "1";
		str[1] = "2";
		str[2] = "3";
		assertEquals("1,2,3", join(",", str));
	}

	@Test
	public void testReplaceAll() throws Exception {
		String str = "abcd中文ac";
		assertEquals("abcda$ac", replaceAll(str, "中文", "a$"));
	}
}
