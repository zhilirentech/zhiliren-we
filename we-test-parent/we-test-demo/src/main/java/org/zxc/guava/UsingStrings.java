/**
 * UsingStrings.java
 * org.zxc.guava
 * Copyright (c) 2016, 北京聚智未来科技有限公司版权所有.
*/

package org.zxc.guava;

import java.io.UnsupportedEncodingException;

import com.google.common.base.CharMatcher;
import com.google.common.base.Charsets;
import com.google.common.base.Strings;

/**
 *   字符串处理
 * <p>
 * TODO(这里描述这个类补充说明 – 可选)
 *
 * @author   朱晓川
 * @Date	 2016年5月12日 	 
 */
public class UsingStrings {

	public static void main(String[] args) {
		/**
		 * 方式1:
		 * 因为没有指定字符集，所以返回的字符集可能并不是你所想要的
		 */
		byte[] bytes = "aaa".getBytes();

		/**
		 * 方式二:
		 * 因为字符集是通过字符串指定的，你可能拼写错误而导致异常
		 */
		try {
			byte[] bytes1 = "aaa".getBytes("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		/**
		 * 使用Charsets
		 */
		byte[] bytes2 = "aaa".getBytes(Charsets.UTF_8);

		/**
		 * 字符串尾部重复添加某个字符，第二个参数指定返回的字符串的长度
		 */
		String s = Strings.padEnd("foo", 8, 'x');
		System.out.println(s);
		System.out.println(Strings.isNullOrEmpty(s));
		System.out.println(Strings.nullToEmpty(null));
		System.out.println(Strings.emptyToNull(""));

		/**长短不一的空白，换成一个空白字符*/
		String tabsAndSpaces = "aaa  bb     ccc  dd";
		String scrubbed = CharMatcher.WHITESPACE.collapseFrom(tabsAndSpaces, ' ');
		System.out.println(scrubbed);

		/**只留下数字*/
		String letterAndNumbers = "asdasd68usd98s9d8sd0s";
		String numbers = CharMatcher.JAVA_DIGIT.retainFrom(letterAndNumbers);
		System.out.println(numbers);
	}

}
