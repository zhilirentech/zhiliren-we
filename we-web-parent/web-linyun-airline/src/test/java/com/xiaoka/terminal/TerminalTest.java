/**
 * TerminalTest.java
 * com.xiaoka.terminal
 * Copyright (c) 2015, 北京浪潮世纪科技有限公司版权所有.
*/

package com.xiaoka.terminal;

/**
 * TODO(作用)
 * <p>
 * TODO(类补充说明 )
 *
 * @author   朱晓川
 * @Date	 2015-6-4 	 
 */
public class TerminalTest {

	public static void main(String[] args) {
		System.out.println("byte:" + Byte.MIN_VALUE + "~" + Byte.MAX_VALUE);
		System.out.println("char 二进制位:" + Character.SIZE + "范围:" + (int) Character.MIN_VALUE + "~"
				+ (int) Character.MAX_VALUE);
		System.out.println("short:" + Short.MIN_VALUE + "~" + Short.MAX_VALUE);
	}

}
