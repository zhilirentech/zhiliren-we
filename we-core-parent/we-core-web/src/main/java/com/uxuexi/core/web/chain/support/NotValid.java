/**
 * NotValid.java
 * com.uxuexi.web.chain.support
 * Copyright (c) 2014, 北京聚智未来科技有限公司版权所有.
*/

package com.uxuexi.core.web.chain.support;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 添加该注解则不需要对对象信息作校验
 * <p>
 * 使用于Module方法的参数上<br/>
 * 如：<br/>
 * public Object add(<b>@NotValid</b> Object obj);
 * <p>
 * 加上该注解则不需要校验对象信息
 * @author   庄君祥
 * @Date	 2013-3-6 	 
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.PARAMETER })
@Documented
public @interface NotValid {

}
