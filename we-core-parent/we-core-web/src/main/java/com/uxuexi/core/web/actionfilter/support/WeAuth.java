/**
 * weAuth.java
 * com.uxuexi.web.common.actionfilter.support
 * Copyright (c) 2014, 北京聚智未来科技有限公司版权所有.
*/

package com.uxuexi.core.web.actionfilter.support;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 权限注解
 * <p>
 * 用于方法，如果方法添加此注解则需要权限核验<br/>
 * 而且可以用于自动生成权限
 *
 * @author   庄君祥
 * @Date	 2013-10-17 	 
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface WeAuth {
	/**
	 * 权限名称
	 *
	 * @return 权限名称
	*/
	String name();

	/**
	 * 描述
	 *
	 * @return 描述
	*/
	String description() default "";
}
