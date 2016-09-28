/**
 * Cms.java
 * com.uxuexi.core.cms
 * Copyright (c) 2014, 北京聚智未来科技有限公司版权所有.
*/

package com.uxuexi.core.web.cms;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import lombok.Data;

import org.nutz.json.Json;
import org.nutz.lang.Lang;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.uxuexi.core.common.util.JsonUtil;
import com.uxuexi.core.common.util.MapUtil;
import com.uxuexi.core.common.util.StringUtil;
import com.uxuexi.core.common.util.Util;
import com.uxuexi.core.web.cms.interfaces.ICms;
import com.uxuexi.core.web.util.RequestUtil;

/**
 * cms核心引擎，负责对外提供服务
 * @author   庄君祥
 * @Date	 2013-12-20 	 
 */
@Data
public class Cms {
	/**
	* Logger for this class
	*/
	private static final Logger logger = LoggerFactory.getLogger(Cms.class);
	private static int MAX_PARSE_NUM = 3;
	private static String PATTERNSTR = "<cms:.*?/>";
	private static Pattern PATTERN = Pattern.compile(PATTERNSTR);
	private List<ICms> changes;

	/**
	 * 使用模板解析并替换内容
	 *
	 * @param str 原字符串 
	 * @param req http请求对象
	 * @param context ServletContext对象
	 * @return 待替换的字符传
	 */
	public String parse(final HttpServletRequest req, final String str) {
		return parseTime(req, str, 0);
	}

	/**
	 * 防止存在嵌套标签，进行多次处理
	 * 在不超过最大次数的情况下进行多次处理
	 * @param req 请求对象
	 * @param str 待处理的字符串
	 * @param num 处理次数
	 * @return 处理后结果
	 */
	private String parseTime(final HttpServletRequest req, final String str, final int num) {
		if (Util.isEmpty(str)) {
			return "";
		}

		if (Util.isEmpty(changes)) {
			logger.error("not set cms changes yet!"); //$NON-NLS-1$
			return str;
		}
		Set<String> cmsTags = matchCms(str);
		if (Util.isEmpty(cmsTags)) {
			return str;
		}
		Map<String, String> needChange = MapUtil.map();
		for (ICms cms : changes) {
			cms.change(req, cmsTags, needChange);
		}
		String result = str;
		for (Entry<String, String> en : needChange.entrySet()) {
			String value = filterAjaxValue(req, en.getValue());
			String key = filterAjaxKey(req, en.getKey());

			result = StringUtil.replaceAll(result, key, value);
		}
		if (!Util.isEmpty(cmsTags)) {
			logger.error("not changed cms: {0}", Json.toJson(cmsTags)); //$NON-NLS-1$
			for (String one : cmsTags) {
				result = StringUtil.replaceAll(result, one, "");
			}
		}
		if (num < MAX_PARSE_NUM) {
			return parseTime(req, result, num + 1);
		}
		return result;
	}

	private String filterAjaxKey(HttpServletRequest req, String inputKey) {
		if (!RequestUtil.isAjax(req)) {
			return inputKey;

		}
		String key = inputKey;
		if (key != null) {
			key = key.replaceAll("\\\\", "\\\\\\\\");
		}
		return key;

	}

	/**
	 * 过滤ajax请求
	 * 如果是ajax，则需要把html代码转成json
	 * 如果不是，则直接返回即可。
	 *
	 * @param req
	 * @param value
	 * @return TODO(这里描述每个参数,如果有返回值描述返回值,如果有异常描述异常)
	*/
	private String filterAjaxValue(final HttpServletRequest req, String value) {
		if (!RequestUtil.isAjax(req)) {
			return value;

		}
		String json = JsonUtil.toJson(JsonUtil.toJson(value));
		return json.substring(3, json.length() - 3);
	}

	/**
	 * 匹配cms标签
	 *
	 * @param str 待匹配的字符串
	 * @return 匹配到的字符串
	 */
	private Set<String> matchCms(final String str) {
		Matcher matcher = PATTERN.matcher(str);
		Set<String> cmsTags = Lang.set();
		while (matcher.find()) {
			cmsTags.add(matcher.group(0));
		}
		return cmsTags;
	}

	/**
	 *根据类型清理缓存数据
	 *
	 * @param type 类型
	 * @param id 唯一标示
	 */
	public void rmCache(final String type, final String id) {
		if (Util.isEmpty(changes)) {
			return;
		}
		for (ICms cms : changes) {
			cms.rmCache(type, id);
		}
	}
}
