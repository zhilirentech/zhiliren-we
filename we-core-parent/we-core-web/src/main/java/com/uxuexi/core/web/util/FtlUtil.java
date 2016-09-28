/**
 * FreeMarkerUtil.java
 * com.uxuexi.core.utils
 * Copyright (c) 2011, 北京聚智未来科技有限公司版权所有.
*/

package com.uxuexi.core.web.util;

import static com.uxuexi.core.common.util.ExceptionUtil.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Map;
import java.util.Properties;

import javax.servlet.ServletContext;

import lombok.Cleanup;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.uxuexi.core.common.util.StringUtil;
import com.uxuexi.core.common.util.Util;

import freemarker.cache.ClassTemplateLoader;
import freemarker.cache.MultiTemplateLoader;
import freemarker.cache.TemplateLoader;
import freemarker.cache.WebappTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

/**
 * 操作freemarker工具类
 * 
 * @author   庄君祥
 * @Date	 2013-11-16
 * @version  5.2.1
 */
public class FtlUtil {
	private static final Logger logger = LoggerFactory.getLogger(FtlUtil.class);

	/**
	 * 初始化配置信息
	 * 
	 * @param context
	 * @param relativePath 模板相对路径
	 * @param propPath 配置文件路径
	 */
	public static void initConfiguration(final Configuration cfg, final ServletContext context,
			final String relativePath, final String propPath, final Class<?>... scans) {
		checkNull(cfg, "初始化freemarker配置时，配置对象为空！");
		if (context == null) {
			throw pEx("初始化freemarker配置时，ServletContext为空！");
		}
		if (relativePath == null) {
			throw pEx("初始化freemarker配置时，文件目录为空！");
		}

		try {
			Properties p = new Properties();
			p.load(new FileInputStream(Thread.currentThread().getContextClassLoader().getResource("").getPath()
					+ propPath));
			cfg.setSettings(p);

			int length = 1 + (Util.isEmpty(scans) ? 0 : scans.length);
			TemplateLoader[] ts = new TemplateLoader[length];
			for (int i = 0; i < scans.length; i++) {
				ts[i] = new ClassTemplateLoader(scans[i], "/");
			}
			ts[ts.length - 1] = new WebappTemplateLoader(context, relativePath);

			cfg.setTemplateLoader(new MultiTemplateLoader(ts));
		} catch (Exception e) {
			throw pEx("初始化配置时，文件目录" + relativePath + "访问异常！", e);
		}
	}

	/**
	 * 使用模板拼装属性，写入out
	 * <p>
	 * 为了更方便的支持webapp项目，所以如果找不到的话，自动再加上"WEB-INF"，再找一遍。
	 *
	 * @param templateName 模版名称
	 * @param context 对象map
	 * @param out 输出的流
	 * @throws IOException 文件读取错误
	 * @throws TemplateException 模版解析失败
	 */
	public static void parse(final Configuration cfg, final String templateName, final Map<String, Object> context,
			final Writer out) throws IOException, TemplateException {
		/* 获取或创建模板*/
		Template temp = null;
		try {
			temp = cfg.getTemplate(StringUtil.path2Web(templateName));
			temp.process(context, out);

		} catch (Exception e) {
			StringBuilder webSb = new StringBuilder(File.separator + "WEB-INF" + File.separator);
			webSb.append(templateName);
			//统一转义成"/"
			temp = cfg.getTemplate(StringUtil.path2Web(webSb.toString()));
			temp.process(context, out);
		} finally {
			out.flush();
		}
	}

	/**
	 * 使用模板拼装属性，写入字符串
	 * <P>
	 * <b>
	 * 	对templateName的使用说明 ：
	 *  <ul>
	 *  	<li>可以使用如下格式：1)"a/b.ftl"   2)"a"+File.separator+"b.ftl"，<font color='red'>绝对不能使用"\"</font></li>
	 *  	<li><font color='red'>如果在webapp项目，前面的"/WEB-INF/"可以省略</font>。比如在：/WEB-INF/a/b.ftl下，可以写成"a/b.ftl"或"/WEB-INF/a/b.ftl"</li>
	 *  </ul>
	 * </b>
	 * @param configuration ftl配置文件
	 * @param templateName 模版相对路径
	 * @param context 对象map
	 * @return 返回的字符串
	 */
	public static String build(final Configuration configuration, final String templateName,
			final Map<String, Object> context) {
		try {
			@Cleanup
			StringWriter stringWriter = new StringWriter();
			FtlUtil.parse(configuration, templateName, context, stringWriter);
			return stringWriter.toString();
		} catch (Exception e) {
			logger.error("模版写入流失败，请核查模版与数据是否匹配！", e);
			return "";
		}
	}

	/**
	 * 使用模板拼装属性，写入字符串
	 *
	 * @param templateName 模版相对路径
	 * @param context 对象map
	 * @return 返回的字符串
	 * @exception DataException 模版写入流失败
	 */
	public static String buildWithEx(final Configuration cfg, final String templateName,
			final Map<String, Object> context) {
		try {
			@Cleanup
			StringWriter stringWriter = new StringWriter();
			FtlUtil.parse(cfg, templateName, context, stringWriter);
			return stringWriter.toString();
		} catch (Exception e) {
			throw pEx("模版写入流失败，请核查模版与数据是否匹配！", e);
		}
	}
}
