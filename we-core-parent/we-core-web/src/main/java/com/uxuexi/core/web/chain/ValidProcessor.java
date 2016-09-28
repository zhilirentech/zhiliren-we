/**
 * ValidProcessor.java
 * com.uxuexi.web.chain
 * Copyright (c) 2014, 北京聚智未来科技有限公司版权所有.
*/

package com.uxuexi.core.web.chain;

import static com.uxuexi.core.common.util.Util.*;

import java.lang.annotation.Annotation;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import lombok.Data;
import lombok.EqualsAndHashCode;

import org.nutz.mvc.ActionContext;
import org.nutz.mvc.annotation.Param;
import org.nutz.mvc.impl.processor.AbstractProcessor;

import com.uxuexi.core.common.util.Util;
import com.uxuexi.core.web.chain.support.NotValid;
import com.uxuexi.core.web.chain.support.WeValidException;

/**
 * 后台数据检验
 * <p>
 * 基于hibernate-validtor
 * @author   庄君祥
 * @Date	 2013-3-6 	 
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ValidProcessor extends AbstractProcessor {

	private static Validator validator; // 它是线程安全的 
	static {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}

	@Override
	public void process(final ActionContext ac) throws Throwable {
		Object[] args = ac.getMethodArgs();
		if (isEmpty(args)) {
			doNext(ac);
			return;
		}
		int index = 0;
		for (Object arg : args) {
			if (isEmpty(arg)) {
				continue;
			}
			Annotation[] annotations = ac.getMethod().getParameterAnnotations()[index++];
			if (isEmpty(annotations)) {
				continue;
			}
			boolean isValid = true;
			for (Annotation annotation : annotations) {
				if (annotation instanceof NotValid) {
					isValid = false;
				}
			}
			if (!isValid) {
				break;
			}
			for (Annotation annotation : annotations) {
				//如果有注释验证，验证完结束判断
				if (annotation instanceof Param) {
					errorHandler(valid(arg));
				}
			}
		}
		doNext(ac);
	}

	/**
	 * 验证信息合法
	 * @param obj 需要验证的对象
	 * @return 不合法信息的Map
	 * @throws Throwable 
	*/
	private Set<ConstraintViolation<Object>> valid(final Object obj) throws Throwable {
		if (isEmpty(obj)) {
			return null;
		}
		return validator.validate(obj);

	}

	/**
	 * 异常出现后的处理逻辑
	 *
	 * @param errors 异常信息
	 */
	public void errorHandler(final Set<ConstraintViolation<Object>> errors) {
		if (Util.isEmpty(errors)) {
			return;
		}
		StringBuilder sb = new StringBuilder("校验错误:");
		for (ConstraintViolation<Object> constraintViolation : errors) {
			sb.append(constraintViolation.getInvalidValue()).append("_").append(constraintViolation.getMessage())
					.append(";");
		}
		throw new WeValidException(sb.toString());
	}
}
