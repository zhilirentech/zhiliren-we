/**
 * ExceptionUtil.java
 * com.uxuexi.util
 * Copyright (c) 2012, 北京聚智未来科技有限公司版权所有.
*/

package com.uxuexi.core.common.util;

import static com.uxuexi.core.common.util.StringUtil.*;
import static com.uxuexi.core.common.util.Util.*;

import java.io.IOException;
import java.sql.SQLException;

import com.uxuexi.core.common.exception.IBusinessException;
import com.uxuexi.core.common.exception.IParamException;
import com.uxuexi.core.common.exception.impl.BusinessException;
import com.uxuexi.core.common.exception.impl.ParamException;

/**
 * 异常处理工具类
 * 
 * @author   庄君祥
 * @Date	 2012-5-1 	 
 */
public final class ExceptionUtil {

	/**
	 * 根据不同的异常种类，返回对应中文意义
	 * <p>
	 * 对于weException直接返回message，对于其他常见异常返回常用信息
	 * @param th 异常
	 * @return 异常信息
	 */
	public static String getSimpleMessage(final Throwable th) {
		if (th instanceof IBusinessException || th instanceof IParamException) {
			return th.getMessage();
		} else if (th instanceof NullPointerException) {
			return "调用了未经初始化的对象或者是不存在的对象！";
		} else if (th instanceof IOException) {
			return "IO异常！";
		} else if (th instanceof ClassNotFoundException) {
			return "指定的类不存在！";
		} else if (th instanceof ArithmeticException) {
			return "数学运算异常！";
		} else if (th instanceof ArrayIndexOutOfBoundsException) {
			return "数组下标越界!";
		} else if (th instanceof IllegalArgumentException) {
			return "方法的参数错误！";
		} else if (th instanceof ClassCastException) {
			return "类型强制转换错误！";
		} else if (th instanceof SecurityException) {
			return "违背安全原则异常！";
		} else if (th instanceof SQLException) {
			return "操作数据库异常！";
		} else if (th instanceof NoSuchMethodError) {
			return "方法未找到异常！";
		} else if (th instanceof InternalError) {
			return "Java虚拟机发生了内部错误";
		} else {
			return "程序内部错误，操作失败！";
		}
	}

	/**
	 * 生成businessException
	 * 
	 * @param message 异常信息	
	 * @return 业务异常
	 */
	public static BusinessException bEx(final String message, final Object... args) {
		return new BusinessException(format(message, args));
	}

	/**
	 * 生成businessException
	 * 
	 * @param message 异常信息
	 * @param e 包装的其他异常
	 * @return 业务异常
	 */
	public static BusinessException bEx(final String message, final Throwable e, final Object... args) {
		return new BusinessException(format(message, args), e);
	}

	/**
	 * 生成IParamException
	 * 
	 * @param message 异常信息
	 * @return 数据异常
	 */
	public static ParamException pEx(final String message, final Object... args) {
		return new ParamException(format(message, args));
	}

	/**
	 * 生成IParamException
	 * 
	 * @param message 异常信息
	 * @param e 包装的其他异常
	 * @return 数据异常
	 */
	public static ParamException pEx(final String message, final Throwable e, final Object... args) {
		return new ParamException(format(message, args), e);
	}

	/**
	 * 判断是否为空，如果为空则抛出业务异常
	 * @param obj 判断对象
	 * @param msg 提示信息
	 * @pa
	*/
	public static void checkNull(final Object obj, final String msg, final Object... objects) {
		if (obj == null) {
			throw pEx(msg, objects);
		}
	}

	/**
	 * 判断主键是否为空
	 * 其实主要是用于参数校验的时候校验主键合法性
	 * 
	 * @see #checkId(long, String)
	 * 
	 * @param id 主键
	 * @param msg 提示信息
	 * @param objects 对象列表
	*/
	@Deprecated
	public static void checkId(final long id, final String msg, final Object... objects) {
		if (id <= 0) {
			throw pEx(msg, objects);
		}
	}

	/**
	 * 判断主键是否为空
	 * <p>
	 * <pre>
	 * 		ExceptionUtil.checkId(userId,"用户"); 
	 * 		如果有异常，异常信息：用户id:2不允许为空
	 * </pre>
	 * @param id 主键
	 * @param msg 提示信息
	*/
	public static void checkId(final long id, final String msg) {
		if (id <= 0) {
			throw pEx(msg + "id:{0}不允许为空", id);
		}
	}

	/**
	 * 判断是否为空，如果为空则抛出数据异常
	 * @param obj 判断对象
	 * @param msg 提示信息
	*/
	public static void checkNullBEx(final Object obj, final String msg, final Object... objects) {
		if (obj == null) {
			throw pEx(msg, objects);
		}
	}

	/**
	 * 判断是否为空，如果为空则抛出业务异常
	 * @param obj 判断对象
	 * @param msg 提示信息
	*/
	public static void checkEmptyBEx(final Object obj, final String msg, final Object... objects) {
		if (isEmpty(obj)) {
			throw bEx(msg, objects);
		}
	}

	/**
	 * 判断是否为空，如果为空则抛出数据异常
	 * @param obj 判断对象
	 * @param msg 提示信息
	*/
	public static void checkEmpty(final Object obj, final String msg, final Object... objects) {
		if (isEmpty(obj)) {
			throw pEx(msg, objects);
		}
	}
}
