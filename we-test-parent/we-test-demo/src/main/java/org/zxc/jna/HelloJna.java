/**
 * HelloJna.java
 * org.zxc.jna
 * Copyright (c) 2015, 北京浪潮世纪科技有限公司版权所有.
*/

package org.zxc.jna;

import com.sun.jna.Library;
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
public class HelloJna {

	// 定义接口CLibrary，继承自com.sun.jna.Library
	public interface CLibrary extends Library {
		// 定义并初始化接口的静态变量
		CLibrary Instance = (CLibrary) Native.loadLibrary((Platform.isWindows() ? "msvcrt" : "c"), CLibrary.class);

		// 声明要调用动态链接库中的printf函数
		void printf(String format, Object... args);
	}

	public static void main(String[] args) {
		// 调用printf打印信息
		CLibrary.Instance.printf("Hello, JNA!\n");
	}
}
