/**
 * StringUtil.java
 * com.uxuexi.core.utils
 * Copyright (c) 2011, 北京聚智未来科技有限公司版权所有.
*/

package com.uxuexi.core.common.util;

import static com.uxuexi.core.common.util.ExceptionUtil.pEx;
import static com.uxuexi.core.common.util.Util.isEmpty;

import java.text.MessageFormat;
import java.util.Collection;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.nutz.lang.Lang;

/**
 * 字符串处理的工具类
 * 
 * @author   庄君祥
 *  @Date	 2014-4-3 
 */
public final class StringUtil {
	
	/**匹配数字的数字正则表达式*/
	public static final String REGEX_NUMBER = "\\d+";
	
	/**
	 * 抽取出字符串中的所有数字,如果没有数字字符，则返回空字符串
	 */
	public static String drawNumber(String source) {
		return drawMatches(source, REGEX_NUMBER);
	}

	/**
	 * 从字符串中取出满足正则表达式条件的字符串
	 */
	public static String drawMatches(String source, String regex) {
		Matcher matcher = getMatcher(source, regex);
		StringBuffer buff = new StringBuffer();
		while (matcher.find()) {
			buff.append((matcher.group()));
		}
		return buff.toString();
	}
	
	/**返回字符串source匹配正则表达式regex的matcher实例(regex对大小写敏感)*/
	public static Matcher getMatcher(String source, String regex) {
		Pattern pattern = Pattern.compile(regex);
		return pattern.matcher(source);
	}

	/**
	 * 使用MessageFormat类的方式对字符串进行格式化
	 * <p>
	 * 传入空串或null时，返回空串
	 * 传入空参数时，返回原字符串
	 * @param format 格式化串
	 * @param args 参数
	 * @return 格式化后的字符串
	 * @throws DataException 如果格式存在异常，则提示DataException
	 */
	public static String format(final String format, final Object... args) {
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
	public static String format(final Locale locale, final String format, final Object... args) {
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
			throw pEx("字符串格式化错误", e);
		}
	}

	/**
	 * 根据分隔符把集合拼接字符串
	 * 
	 * @param c 分隔符
	 * @param coll 集合
	 * @return 拼接后的字符串
	*/
	public static <T> String join(final Object c, final Collection<T> coll) {
		return Lang.concat(c, coll).toString();
	}

	/**
	 * 根据分隔符把集合拼接字符串
	 * 
	 * @param c 分隔符
	 * @param coll 集合
	 * @return 拼接后的字符串
	*/
	public static <T> String join(final Object c, final T[] coll) {
		return Lang.concat(c, coll).toString();
	}

	/**
	 * 根据分隔符把不定长的集合拼接字符串
	 *
	 * @param c 分隔符
	 * @param ts 不定长的集合
	 * @return 拼接后的字符串
	 */
	public static <T> String joinUncertain(final Object c, final T... ts) {
		return join(c, ts);
	}

	/**
	 * 移除匹配字符串及前面的内容
	 *
	 * @param content 源字符串
	 * @param removed 匹配字符串
	 * @return 移除后的字符串
	 */
	public static String removeLeft(final String content, final String removed) {
		if (isEmpty(content) || isEmpty(removed)) {
			return content;
		}
		int index = content.indexOf(removed);
		if (index == -1) {
			return content;
		}
		return content.substring(index + removed.length());
	}

	/**
	 * 前面为空，则返回后面值
	 *
	 * @param content 待判定的值
	 * @param as 如果为空返回的值
	 * @return 返回结果
	 */
	public static String nvl(final String content, final String as) {
		if (isEmpty(content)) {
			return as;
		}
		return content;
	}

	/**
	 * 如果为空，则返回空字符串
	 *
	 * @param content 待判定的值
	 * @return 返回结果
	 */
	public static String nvl(final String content) {
		if (isEmpty(content)) {
			return "";
		}
		return content;
	}

	/**
	 * 判断字符是否是汉字
	 *
	 * @param c 字符
	 * @return 是否汉字
	 */
	public static boolean isChinese(final char c) {
		Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
		if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS
				|| ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
				|| ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A
				|| ub == Character.UnicodeBlock.GENERAL_PUNCTUATION
				|| ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION
				|| ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS) {
			return true;
		}
		return false;
	}

	/**
	 * 是否是英文
	 *
	 * @param c 字符
	 * @return 是否是英文
	 */
	public static boolean isEnglish(final char c) {
		if (c >= 'a' && c <= 'z') {
			return true;
		}
		if (c >= 'A' && c <= 'Z') {
			return true;
		}
		return false;
	}

	/**
	 * 是否是数字
	 *
	 * @param c 字符
	 * @return 是否是英文
	 */
	public static boolean isNumber(final char c) {
		if (c >= '0' && c <= '9') {
			return true;
		}
		return false;
	}

	/**
	 * 是否是asii
	 *
	 * @param c 字符
	 * @return 是否是英文
	 */
	public static boolean isASCII(final char c) {
		if (c >= 20 && c <= 126) {
			return true;
		}
		return false;
	}

	/**
	 * 判断是否是中文
	 *
	 * @param s 待判断的字符串
	 * @return 是否是中文
	 */
	public static boolean isChinese(final String s) {
		for (int i = 0, n = s.length(); i < n; i++) {
			char c = s.charAt(i);
			if (isChinese(c)) {
				continue;
			}
			return false;
		}
		return true;
	}

	/**
	 * 替换字符串方法
	 * 普通replaceAll,如果替换的内容中存在$则会出现错误，为解决问题
	 * 特提供此方法，如果不能确定替换内容是否存在$时，使用此方法
	 *
	 * @param orgin 原始字符串
	 * @param regex 正则
	 * @param replace 要替换的字符串
	 * @return 替换后的字符串
	 */
	public static String replaceAll(final String orgin, final String regex, final String replace) {
		if (isEmpty(orgin)) {
			return "";
		}
		if (isEmpty(regex)) {
			return orgin;
		}
		String replaceStr = replace;
		if (replaceStr == null) {
			replaceStr = "";
		} else if (replaceStr.indexOf("$") > -1) {
			replaceStr = replaceStr.replaceAll("\\$", "\\\\\\$");
		}
		return orgin.replaceAll(regex, replaceStr);
	}
	
	/**
	 * 移除trim标记以及其后面的部分
	 * @param content  要操作的字符串
	 * @param flag     trim标记
	 * @return
	 */
	public static String trimRight(final String content, final String flag) {
		if (isEmpty(content) || isEmpty(flag)) {
			return content;
		}
		int index = content.indexOf(flag);
		if (index == -1) {
			return content;
		}
		return content.substring(0, index);
	}

	/**
	 * 将文件路径中的反斜线变为http协议中的斜线
	 *
	 * @param path 路径
	 * @return 转换后的路径
	 */
	public static String path2Web(final String path) {
		if (isEmpty(path)) {
			return path;
		}
		return path.replace('\\', '/');
	}
}
