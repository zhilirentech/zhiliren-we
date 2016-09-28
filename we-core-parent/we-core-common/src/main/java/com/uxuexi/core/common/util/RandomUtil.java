/**
 * RandomUtil.java
 * com.uxuexi.common.util
 * Copyright (c) 2014, 北京聚智未来科技有限公司版权所有.
*/

package com.uxuexi.core.common.util;

import static com.uxuexi.core.common.util.ExceptionUtil.*;

import java.security.SecureRandom;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.nutz.lang.random.R;

/**
 * 随机工具类
 * <p>
 * 生成随机数，随机字符串等和随机有关的工具
 * @author   庄君祥

 * @Date	 2014-4-3  	 
 */
public final class RandomUtil {

	private static final String STRING_CODE = "0123456789abcdefghijklmnopqrstuvwsyz";
	private static final String INTEGER_CODE = "0123456789";

	/**
	 * 产生随机字符串
	 * 字符串由26个字母以及数字构成
	 * @param lengths 字符串长度
	 * @return 随机的字符串
	 */
	public static String randomString(final int lengths) {
		return random(lengths, STRING_CODE);
	}

	/**
	 * TODO(这里用一句话描述这个方法的作用)
	 * <p>
	 * TODO(这里描述这个方法详情– 可选)
	 *
	 * @param lengths
	 * @param code
	 * @return TODO(这里描述每个参数,如果有返回值描述返回值,如果有异常描述异常)
	*/
	private static String random(final int lengths, final String code) {
		if (lengths < 0) {
			return "";
		}
		StringBuilder buffer = new StringBuilder(code);
		StringBuilder sb = new StringBuilder();
		Random r = new Random();
		int range = buffer.length();
		for (int i = 0; i < lengths; i++) {
			sb.append(buffer.charAt(r.nextInt(range)));
		}
		return sb.toString();
	}

	/**
	 * 产生随机字符串
	 * 字符串由26个字母以及数字构成
	 * @param lengths 字符串长度
	 * @return 随机的字符串
	 */
	public static String randIntString(final int lengths) {
		return random(lengths, INTEGER_CODE);
	}

	/**
	 * 从列表中随机获取指定个数的不重复对象
	 *
	 * @param <T> 对象类型
	 * @param source 来源
	 * @param count 个数
	 * @return 随机的对象
	 */
	@SuppressWarnings("unchecked")
	public static <T> Set<T> randomSet(final List<T> source, final int count) {
		Set<T> result = new LinkedHashSet<T>();
		if (Util.isEmpty(source)) {
			return result;
		}
		if (source.size() < count) {
			result.addAll(source);
			return result;
		}
		List<T> temp = CollectionUtil.list();
		temp.addAll(source);
		Random random = new Random();
		while (temp.size() > 0 && result.size() < count) {
			int pos = random.nextInt(temp.size());
			result.add(temp.get(pos));
			temp.remove(pos);
		}
		return result;
	}

	/**
	 * 产生随机数
	 * 范围是:0-(n-1)
	 *
	 * @param n 最大值
	 * @return 随机数
	 */
	public static int nextInt(final int n) {
		if (n <= 0) {
			throw pEx("n必须是正数");
		}
		return new SecureRandom().nextInt(n);
	}

	/**
	 * 16进制表示的紧凑格式的 UUID
	 *
	 * @return 紧凑的64位
	 */
	public static String uu16() {
		return R.UU16();
	}
}
