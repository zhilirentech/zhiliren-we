/**
 * InitUtil.java
 * com.uxuexi.web.common.util
 * Copyright (c) 2014, 北京聚智未来科技有限公司版权所有.
*/

package com.uxuexi.core.web.util;

import java.util.Map;

import javax.servlet.ServletContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.uxuexi.core.common.util.MapUtil;
import com.uxuexi.core.web.config.ConfigKey;
import com.uxuexi.core.web.config.KvConfig;

import freemarker.template.Configuration;
import freemarker.template.TemplateModelException;

/**
 * 初始化工具类
 * @author   庄君祥
 * @Date	 2013-11-16 	 
 */
public final class InitUtil {

	/**
	* Logger for this class
	*/
	private static final Logger logger = LoggerFactory.getLogger(InitUtil.class);

	public static final String GLOBAL_CONFIG_KEY = "we";

	private InitUtil() {

	}

	/**
	 * 初始化web全局的配置
	 *
	 * @param config 配置
	 * @param sc 全局上下文
	 */
	public static void initWebGlobalConfig(final KvConfig config, final ServletContext sc) {
		if (config == null || sc == null) {
			return;
		}

		sc.setAttribute(GLOBAL_CONFIG_KEY, getValues(config));

		sc.setAttribute(ConfigKey.STATIC.key(), config.getValue(ConfigKey.STATIC));
	}

	private static Map<String, Map<String, String>> getValues(final KvConfig config) {
		return MapUtil.map("vr", config.getValues());
	}

	/**
	 * 初始化ftl全局的配置
	 *
	 * @param config 配置
	 * @param ftlConf ftl的配置
	 */
	public static void initFtlGlobalConfig(final KvConfig config, final Configuration ftlConf) {
		if (config == null || ftlConf == null) {
			return;
		}
		try {
			ftlConf.setSharedVariable(GLOBAL_CONFIG_KEY, getValues(config));
			ftlConf.setSharedVariable(ConfigKey.STATIC.key(), config.getValue(ConfigKey.STATIC));
		} catch (TemplateModelException e) {
			logger.error("设置ftl公用属性时出错！", e); //$NON-NLS-1$
		}
	}
}
