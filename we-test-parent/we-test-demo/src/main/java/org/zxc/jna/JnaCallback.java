/**
 * JnaCallback.java
 * org.zxc.jna
 * Copyright (c) 2015, 北京浪潮世纪科技有限公司版权所有.
*/

package org.zxc.jna;

import com.sun.jna.Callback;
import com.sun.jna.Library;
import com.sun.jna.Native;

/**
 * TODO(作用)
 * <p>
 * TODO(类补充说明 )
 *
 * @author   朱晓川
 * @Date	 2015-6-10 	 
 */
public class JnaCallback {

	//定义接口CLibrary，代表一个动态链接库，继承自com.sun.jna.Library
	public interface CLibrary extends Library {

		//定义接口JavaCallbackAdd，继承自com.sun.jna.Callback
		interface JavaCallbackAdd extends Callback {
			int CallbackAdd(int a, int b);
		}

		//动态库的函数声明
		void RegisterAdd(JavaCallbackAdd callback);

		void DoAddByCallback(int a, int b);
	}

	//main
	public static void main(String[] args) {

		//加载动态链接库,loadLibrary的第一个参数为动态链接库的文件名
		CLibrary lib = (CLibrary) Native.loadLibrary("libJnaCbApi", CLibrary.class);

		//实例化回调函数
		CLibrary.JavaCallbackAdd callback = new CLibrary.JavaCallbackAdd() {
			//实现CallbackAdd函数
			public int CallbackAdd(int a, int b) {
				return a + b;
			}
		};

		//调用动态库的函数
		lib.RegisterAdd(callback);
		//由动态库执行CallbackAdd回调函数
		lib.DoAddByCallback(1, 2);
	}

}
