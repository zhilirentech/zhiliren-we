/**
 * JsonpUtil.java
 * com.uxuexi.core.common.util
 * Copyright (c) 2014, 北京聚智未来科技有限公司版权所有.
*/

package com.uxuexi.core.common.util;

import static com.uxuexi.core.common.util.Util.*;

import java.io.IOException;
import java.io.Writer;
import java.text.MessageFormat;
import java.util.Locale;

import javax.servlet.http.HttpServletResponse;

import org.nutz.json.Json;
import org.nutz.mvc.Mvcs;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.uxuexi.core.common.exception.impl.ParamException;

/**
 * 有关前端js跨域访问后端时后端数据处理工具类
 * 
 * @author   庄君祥
 * @Date	 2014-4-3  	 
 */
public final class JsonpUtil {

	private static final Logger logger = LoggerFactory.getLogger(JsonpUtil.class);
	private static final String CALLBACK_FOMART = "{0}({1})";

	/**
	 * 将正常结果返回jsonP
	 *
	 * @param resp 返回流
	 * @param obj 成功对象
	 * @param callback 请求来的callback
	 */
	public static void ok2JsonP(final Object obj, final String callback) {
		HttpServletResponse resp = Mvcs.getResp();
		safeWrite(resp, format(CALLBACK_FOMART, callback, Json.toJson(obj)));
	}

	/**
	 * 使用MessageFormat类的方式对字符串进行格式化
	 * <p>
	 * 传入空串或null时，返回空串
	 * 传入空参数时，返回原字符串
	 * 
	 * @param format 格式化串
	 * @param args 参数
	 * @return 格式化后的字符串
	 * @throws DataException 如果格式存在异常，则提示DataException
	 */
	private static String format(final String format, final Object... args) throws ParamException {
		return format(Locale.getDefault(), format, args);
	}

	/**
	 * 使用MessageFormat类的方式对字符串进行格式化
	 * <p>
	 * 传入空串或null时，返回空串
	 * 传入空参数时，返回原字符串
	 * @param locale 本地化语言
	 * @param format 格式化串
	 * @param args 参数
	 * @return 格式化后的字符串
	 * @throws DataException 如果格式存在异常，则提示DataException
	 */
	private static String format(final Locale locale, final String format, final Object... args) throws ParamException {
		if (isEmpty(format)) {
			return "";
		}
		if (args == null) {
			return format;
		}
		try {
			MessageFormat formart = new MessageFormat(format, locale);
			return formart.format(args);
		} catch (Exception e) {
			throw new ParamException("字符串格式化错误", e);
		}
	}

	private static void safeWrite(final HttpServletResponse resp, final String str) {
		if (resp == null) {
			return;
		}
		try {
			resp.setContentType("application/x-javascript");
			safeWrite(resp.getWriter(), str);
		} catch (IOException e) {
			logger.info("写入流失败", e); //$NON-NLS-1$
		}
	}

	private static void safeWrite(final Writer writer, final String str) {
		if (writer == null) {
			return;
		}
		try {
			writer.write(str);
		} catch (IOException e) {
			logger.info("写入流失败", e); //$NON-NLS-1$
		}
	}

}
