/**
 * Condition.java
 * com.uxuexi.form.support
 * Copyright (c) 2012, 北京聚智未来科技有限公司版权所有.
 */

package com.uxuexi.core.web.form.support;

import static com.uxuexi.core.web.form.support.Condition.ConnectType.*;
import static com.uxuexi.core.web.form.support.Condition.MatchType.*;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 生成查询条件的注解
 * 
 * @author 庄君祥
 * @Date 2012-5-5
 */
@Target({ ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface Condition {
	/**
	 * Condtion的属性对比
	 * @author   庄君祥
	 * @Date	 2012-5-5
	 */
	public static enum MatchType {
		EQ("="), LIKE("like"), LT("<"), GT(">"), LE("<="), GE(">="), IN("in"), NE("!="), BETWEEN(" between"), NOTIN(
				"not in");
		private String value;

		private MatchType(final String value) {
			this.value = value;
		}

		@Override
		public String toString() {
			return value;
		}
	}

	/**
	 * 多个属性比较类型连接
	 * <p>
	 * AND ,OR
	 * @author   庄君祥
	 * @Date	 2012-5-5
	 */
	public static enum ConnectType {
		AND, OR;
	}

	/**
	 * 比较类型
	 */
	MatchType match() default EQ;

	/**
	 * 实体别名
	 * 
	 * @return
	 */
	String entityAlias() default "";

	/**
	 * 实体的属性名，不填默认为属性名称
	 */
	String[] fields() default {};

	/**
	 * 如果设置为多个属性，设置各属性之间的连接符，
	 */
	ConnectType connect() default AND;
}
