/**
 * Tel.java
 * com.uxuexi.core.web.chain.support
 * Copyright (c) 2014, 北京聚智未来科技有限公司版权所有.
*/

package com.uxuexi.core.web.validator.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.uxuexi.core.web.validator.TelValidator;

/**
 * TODO(这里用一句话描述这个类的作用)
 * <p>
 * TODO(这里描述这个类补充说明 – 可选)
 *
 * @author   庄君祥
 * @Date	 2014-4-28 	 
 */
//该注解可以应用在：方法，域，注解类型上
@Target({ ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE })
//注解在运行期通过反射读取
@Retention(RetentionPolicy.RUNTIME)
//注解使用的约束类
@Constraint(validatedBy = TelValidator.class)
//在生成JavaDoc时，该注解会被添加到java文档中
@Documented
public @interface Tel {
	//定义错误消息，可以直接使用错误提示内容，也可以使用模板
	String message() default "{com.pps.myannotation.CheckCase.message}";

	//定义所在的组
	Class<?>[] groups() default {};

	//定义级别条件的严重级别
	Class<? extends Payload>[] payload() default {};

}
