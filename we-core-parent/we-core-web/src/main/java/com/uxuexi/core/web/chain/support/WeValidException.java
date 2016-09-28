/**
 * ValidException.java
 * com.uxuexi.web.chain.support
 * Copyright (c) 2014, 北京聚智未来科技有限公司版权所有.
*/

package com.uxuexi.core.web.chain.support;

import java.util.Set;

import javax.validation.ConstraintViolation;

import com.uxuexi.core.common.exception.IParamException;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 配合hibernate-validtor错误的处理异常
 * 
 * @author   庄君祥
 * @Date	 2013-3-7 	 
 */
@SuppressWarnings("serial")
@Data
@EqualsAndHashCode(callSuper = true)
public class WeValidException extends RuntimeException implements IParamException {
	private Set<ConstraintViolation<Object>> errors;

	public WeValidException() {
		super();
	}

	public WeValidException(final String message) {
		super(message);
	}

	public WeValidException(final String message, final Set<ConstraintViolation<Object>> errors) {
		super(message);
		this.errors = errors;
	}
}
