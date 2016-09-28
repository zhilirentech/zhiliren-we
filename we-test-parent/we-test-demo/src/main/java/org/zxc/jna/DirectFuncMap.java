/**
 * DirectFuncMap.java
 * org.zxc.jna
 * Copyright (c) 2015, 北京浪潮世纪科技有限公司版权所有.
*/

package org.zxc.jna;

import com.sun.jna.Native;
import com.sun.jna.Platform;

/**
 * TODO(作用)
 * <p>
 * TODO(类补充说明 )
 *
 * @author   朱晓川
 * @Date	 2015-6-10 	 
 */
public class DirectFuncMap {

	//要调用的native函数声明
	public static native double cos(double x);

	public static native double sin(double x);

	public static native double pow(double x, double y);

	public static void main(String[] args) {

		// 映射（加载）函数库
		Native.register(Platform.isWindows() ? "msvcrt" : "m");

		// 调用数学函数进行数学运算
		System.out.println("cos(0)=" + cos(0));
		System.out.println("sin(0)=" + sin(0));
		System.out.println("2^16=" + pow(2, 16));
	}
}
