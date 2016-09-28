/**
 * HtmlUtil.java
 * com.uxuexi.core.common.util
 * Copyright (c) 2014, 北京聚智未来科技有限公司版权所有.
*/

package com.uxuexi.core.common.util;

import static com.uxuexi.core.common.util.Util.*;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringEscapeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * html相关工具类
 *
 * @author   庄君祥
 * @Date	 2014-5-26 	 
 */
public final class HtmlUtil {

	/**
	* Logger for this class
	*/
	private static final Logger logger = LoggerFactory.getLogger(HtmlUtil.class);

	/**
	 * 将字符串转换成 html
	 * @param message 待转换的 数据
	 * @return 转换后的html
	 */
	public static String toHtml(final String message) {
		if (isEmpty(message)) {
			return "";
		}
		return StringEscapeUtils.escapeHtml(message);
	}

	/**
	 * 将html 转换程 字符串
	 * @param message 待转换的 数据
	 * @return 转换后的字符串
	 */
	public static String deHtml(final String message) {
		if (isEmpty(message)) {
			return "";
		}
		return StringEscapeUtils.unescapeHtml(message);
	}

	/**
	 * 对网址编码
	 * @param str 网址
	*/
	public static String urlEncode(final String str) {
		if (isEmpty(str)) {
			return "";
		}
		String encode = "";
		try {
			encode = URLEncoder.encode(str, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			logger.error("网址编码出错", e);
		}
		return encode;
	}

	/**
	 * 对网址解码
	 * @param encode 编码后的字符串
	*/
	public static String urlDecode(final String encode) {
		if (isEmpty(encode)) {
			return "";
		}
		String decode = "";
		try {
			decode = URLDecoder.decode(encode, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			logger.error("网址解码出错", e);
		}
		return decode;
	}

	/**
	 * 将字符串转换成 xml
	 * @param message 待转换的 数据
	 * @return 转换后的xml
	 */
	public static String toXml(final String message) {
		if (isEmpty(message)) {
			return "";
		}
		return StringEscapeUtils.escapeXml(message);
	}

	/**
	 * 将xml 转换程 字符串
	 * @param message 待转换的 数据
	 * @return 转换后的字符串
	 */
	public static String deXml(final String message) {
		if (isEmpty(message)) {
			return "";
		}
		return StringEscapeUtils.unescapeXml(message);
	}

	/**  
	 * html转为纯文本
	 * <p>
	 * 过滤里面的html标签
	 * 
	 * @param str :  
	 *            source string  
	 * @param width :  
	 *            string's byte width  
	 * @param ellipsis :  
	 *            a string added to abbreviate string bottom  
	*   
	 */
	public static String html2Text(final String inputString) {
		String htmlStr = inputString; // 含html标签的字符串   
		String textStr = "";
		java.util.regex.Pattern p_script;
		java.util.regex.Matcher m_script;
		java.util.regex.Pattern p_style;
		java.util.regex.Matcher m_style;
		java.util.regex.Pattern p_html;
		java.util.regex.Matcher m_html;

		java.util.regex.Pattern p_html1;
		java.util.regex.Matcher m_html1;

		try {
			String regEx_script = "<[\\s]*?script[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?script[\\s]*?>"; // 定义script的正则表达式{或<script[^>]*?>[\\s\\S]*?<\\/script>   
			// }   
			String regEx_style = "<[\\s]*?style[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?style[\\s]*?>"; // 定义style的正则表达式{或<style[^>]*?>[\\s\\S]*?<\\/style>   
			// }   
			String regEx_html = "<[^>]+>"; // 定义HTML标签的正则表达式   
			String regEx_html1 = "<[^>]+";
			p_script = Pattern.compile(regEx_script, Pattern.CASE_INSENSITIVE);
			m_script = p_script.matcher(htmlStr);
			htmlStr = m_script.replaceAll(""); // 过滤script标签   

			p_style = Pattern.compile(regEx_style, Pattern.CASE_INSENSITIVE);
			m_style = p_style.matcher(htmlStr);
			htmlStr = m_style.replaceAll(""); // 过滤style标签   

			p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);
			m_html = p_html.matcher(htmlStr);
			htmlStr = m_html.replaceAll(""); // 过滤html标签   

			p_html1 = Pattern.compile(regEx_html1, Pattern.CASE_INSENSITIVE);
			m_html1 = p_html1.matcher(htmlStr);
			htmlStr = m_html1.replaceAll(""); // 过滤html标签   

			textStr = htmlStr;

		} catch (Exception e) {
			System.err.println("Html2Text: " + e.getMessage());
		}
		return textStr;// 返回文本字符串   
	}

	/**
	 * 除了常见的html过滤，本方法添加了过滤字符串"&nbsp;"
	 * @param inputString
	 * @return 
	 */
	public static String html2TextRemoveSpace(final String inputString) {
		String htmlStr = inputString;
		htmlStr = html2Text(htmlStr);
		java.util.regex.Matcher m_space;
		java.util.regex.Pattern p_html1;
		p_html1 = Pattern.compile("\\&nbsp;", Pattern.CASE_INSENSITIVE);
		m_space = p_html1.matcher(htmlStr);
		htmlStr = m_space.replaceAll("");
		return htmlStr;
	}

	/**
	 * 将String转html
	 * @param inputString
	 * @return 
	 */
	public static String text2Html(final String inputString) {
		String htmlStr = inputString.replaceAll("&nbsp;", "");
		htmlStr = htmlStr.replaceAll("&middot;", "·");
		htmlStr = htmlStr.replaceAll("&lt;", "<");
		htmlStr = htmlStr.replaceAll("&gt;", ">");
		htmlStr = htmlStr.replaceAll("&quot;", "\"");
		htmlStr = htmlStr.replaceAll("&egrave;", "è");
		return htmlStr;
	}
}
