/**
 * Select.java
 * com.uxuexi.form.support
 * Copyright (c) 2012, 北京聚智未来科技有限公司版权所有.
 */

package com.uxuexi.core.web.form.support;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 设置select语句
 * <p>
 * select 语句和from 表
 * @author 庄君祥
 * @Date 2012-5-5
 */
@Target({ ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface Select {
	/**
	 * select的列
	 */
	String select();

	/**
	 * from的表，必填
	 */
	String from();
}
