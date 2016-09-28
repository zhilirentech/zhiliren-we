/**
 * CacheUtil.java
 * com.uxuexi.core.web.util
 * Copyright (c) 2015, 北京聚智未来科技有限公司版权所有.
*/

package com.uxuexi.core.web.util;

import java.lang.annotation.Annotation;

import javax.servlet.http.HttpServletResponse;

import com.uxuexi.core.common.util.ExceptionUtil;
import com.uxuexi.core.common.util.Util;
import com.uxuexi.core.web.chain.support.Cache;

/**
 * 缓存工具类
 * <p>
 * 设置一个http response的cache-control属性
 *
 * @author   庄君祥
 * @Date	 2015-2-2 	 
 */
public class CacheControlUtil {
	/**
	 * 缓存
	 * <p>
	 * 设置缓存时间，如果有加Cache，则需要缓存，如果没有加，则不需要缓存
	 *
	 * @param resp 请求响应
	 * @param ans 注解集合
	*/
	public static void cache(final HttpServletResponse resp, Annotation... ans) {
		ExceptionUtil.checkEmpty(resp, "请求响应不允许为空");
		if (Util.isEmpty(ans)) {
			return;
		}
		boolean isNeedCache = false;
		long time = 0;
		Annotation[] annotations = ans;
		for (Annotation annotation : annotations) {
			if (annotation instanceof Cache) {
				isNeedCache = true;
				time = ((Cache) annotation).value();
				break;
			}
		}
		if (!isNeedCache) {
			resp.setHeader("Cache-Control", "no-cache");
		} else {
			resp.setHeader("Cache-Control", "max-age=" + time);
		}
	}

	public static void noCache(HttpServletResponse resp) {
		resp.setHeader("Cache-Control", "no-cache");
	}
}
