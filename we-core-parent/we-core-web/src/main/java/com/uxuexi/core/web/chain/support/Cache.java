/**
 * Cache.java
 * com.uxuexi.web.cache
 * Copyright (c) 2014, 北京聚智未来科技有限公司版权所有.
*/

package com.uxuexi.core.web.chain.support;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 用于标识页面缓存时间
 * 
 * @author   庄君祥
 * @Date	 2013-3-29 	 
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.METHOD })
@Documented
public @interface Cache {
	/**
	 * 缓存时间，单位秒
	 * 默认时间3600秒
	 * @return 缓存时间
	 */
	int value() default 3600;
}
