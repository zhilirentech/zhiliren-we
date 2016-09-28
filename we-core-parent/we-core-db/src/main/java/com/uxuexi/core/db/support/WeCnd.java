/**
 * WeCnd.java
 * com.uxuexi.core.db.support
 * Copyright (c) 2014, 北京聚智未来科技有限公司版权所有.
*/

package com.uxuexi.core.db.support;

import org.nutz.dao.Cnd;
import org.nutz.dao.util.cri.Exps;
import org.nutz.dao.util.cri.SqlExpression;
import org.nutz.lang.Mirror;

import com.uxuexi.core.common.util.ConvertUtil;
import com.uxuexi.core.common.util.Util;

/**
 * 基于Cnd扩展的自定义Sql拼接类
 * <p>
 * 所有And或者Or方法在使用的时候，会自动检查value或者SqlExpression是否为空。如果为空则不拼接
 * <p>
 * value为空的标准：
 * <ul>
 * 		<li>如果是非数字类型，参见Util.isEmpty</li>
 * 		<li>如果是数字类型的，只要是负数即为空</li>
 * </ul>
 *
 * @author   庄君祥
 * @Date	 2014-7-8 	 
 */
public class WeCnd extends Cnd {

	protected WeCnd(final SqlExpression exp) {
		super(exp);
	}

	public static Cnd limit() {
		return new WeCnd(null);
	}

	public static Cnd where(final String name, final String op, final Object value) {
		return new WeCnd(exp(name, op, value));
	}

	public static Cnd where(final SqlExpression e) {
		return new WeCnd(e);
	}

	public static SqlExpression exp(final String name, final String op, final Object value) {
		if (isEmpty(value)) {
			return null;
		}
		return Exps.create(name, op, value);
	}

	@Override
	public Cnd and(final String name, final String op, final Object value) {
		if (isEmpty(value)) {
			return this;
		}
		return super.and(name, op, value);
	}

	@Override
	public Cnd and(final SqlExpression exp) {
		if (isEmpty(exp)) {
			return this;
		}
		return super.and(exp);

	}

	@Override
	public Cnd or(final SqlExpression exp) {
		if (isEmpty(exp)) {
			return this;
		}
		return super.or(exp);

	}

	@Override
	public Cnd or(final String name, final String op, final Object value) {
		if (isEmpty(value)) {
			return this;
		}
		return super.or(name, op, value);

	}

	@Override
	public Cnd andNot(final SqlExpression exp) {
		if (isEmpty(exp)) {
			return this;
		}
		return super.andNot(exp);

	}

	@Override
	public Cnd andNot(final String name, final String op, final Object value) {
		if (isEmpty(value)) {
			return this;
		}
		return super.andNot(name, op, value);

	}

	@Override
	public Cnd orNot(final SqlExpression exp) {
		if (isEmpty(exp)) {
			return this;
		}
		return super.orNot(exp);

	}

	@Override
	public Cnd orNot(final String name, final String op, final Object value) {
		if (isEmpty(value)) {
			return this;
		}
		return super.orNot(name, op, value);

	}

	/**
	 * 是否为空
	 *
	 * @param value
	 * @return 如果为空返回true，否则返回false
	*/
	private static boolean isEmpty(final Object value) {
		if (Util.isEmpty(value)) {
			return true;
		}
		Mirror<Object> me = Mirror.me(value);
		if (me.isDouble()) {
			double val = ConvertUtil.obj2double(value);
			if (val < 0) {
				return true;
			}
			return false;
		}
		if (me.isNumber()) {
			long val = ConvertUtil.obj2long(value);
			if (val < 0) {
				return true;
			}
		}
		return false;
	}

}
