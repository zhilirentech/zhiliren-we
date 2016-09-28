/**
 * TelValidator.java
 * com.uxuexi.core.web.chain.support
 * Copyright (c) 2014, 北京聚智未来科技有限公司版权所有.
*/

package com.uxuexi.core.web.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.uxuexi.core.web.validator.annotations.Tel;

/**
 * TODO(这里用一句话描述这个类的作用)
 * <p>
 * TODO(这里描述这个类补充说明 – 可选)
 *
 * @author   庄君祥
 * @Date	 2014-4-28 	 
 */
public class TelValidator implements ConstraintValidator<Tel, String> {
	private Tel tel;

	@Override
	public void initialize(final Tel constraintAnnotation) {
		this.tel = constraintAnnotation;
	}

	@Override
	public boolean isValid(final String value, final ConstraintValidatorContext context) {
		Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0-9]))\\d{8}$");
		Matcher m = p.matcher(value);
		boolean isValid = m.matches();
		if (!isValid) {

			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(tel.message()).addConstraintViolation();
		}
		return isValid;
	}

}
