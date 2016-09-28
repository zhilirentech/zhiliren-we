package com.uxuexi.core.common.exception.impl;

import com.uxuexi.core.common.exception.ITimeoutException;

/**
 * 用户登录超时的异常，直接显示给用户
 * <p>
 * 不需要记录日志或者提示相关人员
 * 
 * @author   庄君祥
 * @Date	 2014-4-3 
 */
public class TimeoutException extends RuntimeException implements ITimeoutException {
	private static final long serialVersionUID = -1217490019899825218L;

	public TimeoutException() {
		super();
	}

	public TimeoutException(final String message) {
		super(message);
	}

	public TimeoutException(final String message, final Throwable cause) {
		super(message, cause);
	}

	public TimeoutException(final Throwable cause) {
		super(cause);
	}
}
