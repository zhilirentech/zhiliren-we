/**
 * SqlInjectProcessor.java
 * com.uxuexi.core.web.chain
 * Copyright (c) 2014, 有限公司版权所有.
*/

package com.uxuexi.core.web.chain;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.nutz.json.Json;
import org.nutz.lang.Mirror;
import org.nutz.mvc.ActionContext;
import org.nutz.mvc.annotation.Param;
import org.nutz.mvc.impl.processor.AbstractProcessor;

import com.uxuexi.core.common.util.Util;
import com.uxuexi.core.web.chain.support.WeValidException;

/**
 * sql注入工具检查
 * 
 * @author   庄君祥
 * @Date	 2014-7-10 
 * 
 */
public class SqlInjectProcessor extends AbstractProcessor {

	@Override
	public void process(final ActionContext ac) throws Throwable {
		Object[] args = ac.getMethodArgs();
		if (Util.isEmpty(args)) {
			doNext(ac);
			return;
		}
		for (Object arg : args) {
			if (Util.isEmpty(arg)) {
				continue;
			}
			if (arg instanceof String) {
				if (isOk((String) arg, ac.getRequest())) {
					continue;
				} else {
					return;
				}
			}
			Annotation[] annotations = ac.getMethod().getParameterAnnotations()[0];
			if (Util.isEmpty(annotations)) {
				continue;
			}

			for (Annotation annotation : annotations) {
				if (!(annotation instanceof Param)) {
					continue;
				}
				String pm = ((Param) annotation).value();
				if (!Util.eq("..", pm)) {
					continue;
				}
				if (!check(arg, ac.getRequest())) {
					return;
				}
			}
		}
		doNext(ac);
	}

	/**
	 * 校验字符串是否是sql注入
	 *
	 * @param arg 字符串
	 * @param request 请求对象
	 */
	private boolean isOk(final String arg, final HttpServletRequest request) {
		if (Util.isEmpty(arg)) {
			return true;
		}
		if (isIll(arg.toLowerCase())) {
			throw new WeValidException(Json.toJson("不要瞎搞，我们发现你了！"));
		}
		return true;
	}

	/**
	 * 校验pojo是否是sql注入
	 *
	 * @param arg pojo对象
	 * @param request 请求对象
	 */
	private boolean check(final Object arg, final HttpServletRequest request) {
		if (Util.isEmpty(arg)) {
			return true;
		}
		if (arg instanceof Map) {
			return true;
		}
		Mirror<?> sourceMirror = Mirror.me(arg);
		Field[] fs = sourceMirror.getFields();
		for (Field f : fs) {
			if (Util.eq(f.getType(), String.class)) {
				String s = String.valueOf(Mirror.me(arg).getValue(arg, f));
				if (!isOk(s, request)) {
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * TODO(这里用一句话描述这个方法的作用)
	 * <p>
	 * TODO(这里描述这个方法详情– 可选)
	 *
	 * @param arg
	 * @return TODO(这里描述每个参数,如果有返回值描述返回值,如果有异常描述异常)
	*/
	private boolean isIll(final String arg) {
		if (arg.indexOf("'") < 0) {
			return false;
		}
		if (arg.indexOf("or") < 0 && arg.indexOf("and") < 0 && arg.indexOf(";") < 0) {
			return false;
		}
		if (arg.indexOf("select") > -1 && arg.indexOf("from") > -1) {
			return true;
		}
		if (arg.indexOf("delete") > -1 && arg.indexOf("from") > -1) {
			return true;
		}
		if (arg.indexOf("update") > -1 && arg.indexOf("set") > -1) {
			return true;
		}
		if (arg.indexOf("truncate") > -1 && arg.indexOf("table") > -1) {
			return true;
		}
		if (arg.indexOf("insert") > -1 && arg.indexOf("into") > -1) {
			return true;
		}
		if (arg.indexOf("grant") > -1 && arg.indexOf("to") > -1) {
			return true;
		}
		if (arg.indexOf("database()") > -1) {
			return true;
		}
		if (arg.indexOf("backup") > -1 && arg.indexOf("database") > -1) {
			return true;
		}
		if (arg.indexOf("drop") > -1) {
			if (arg.indexOf("table") > -1 || arg.indexOf("database") > -1) {
				return true;
			}
		}
		return false;
	}

}
