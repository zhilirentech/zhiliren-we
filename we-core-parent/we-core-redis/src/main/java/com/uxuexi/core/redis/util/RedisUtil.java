/**
 * RedisUtil.java
 * com.uxuexi.core.redis.util
 * Copyright (c) 2014, 北京聚智未来科技有限公司版权所有.
 */
package com.uxuexi.core.redis.util;

import static com.uxuexi.core.common.util.ExceptionUtil.*;
import static com.uxuexi.core.common.util.StringUtil.*;
import static com.uxuexi.core.common.util.Util.*;

import java.util.Set;

import com.uxuexi.core.redis.IRedisDao;
import com.uxuexi.core.redis.support.RedisKeyPrefix;

/**
 * Redis拓展工具类
 * 
 * @author   庄君祥
 * @author   朱晓川
 * @Date	 2014-7-17 	 
 * @version  0.0.1
 */
public final class RedisUtil {
	/**
	 * redis key的连续符
	 */
	private static final String REDIS_KEY_JOIN_SIGN = ":";

	/**
	 * 判断RedisDao是否为空
	 *
	 * @param redisDao redisDao对象
	*/
	public static void checkDao(final IRedisDao redisDao) {
		if (redisDao == null) {
			throw pEx("调用时，RedisDao为空! ");
		}
	}

	/**
	 * 获得redis的key
	 * <p>
	 * key是为统一的前缀加后面字符串组成的。<br/>
	 * 如：<br/>
	 * cnt:loginuser
	 * <p>
	 * 前缀不能为空，后缀也不能为空，否则抛出异常
	 *
	 * @param redisKeyPrefix 前缀
	 * @param objects 需要的参数
	 * @return 完整的redis对应的key
	 * @see RedisKeyPrefix
	*/
	public static String key(final RedisKeyPrefix redisKeyPrefix, final Object... objects) {
		if (null == redisKeyPrefix) {
			throw pEx("redis key前缀不能为空");
		}
		if (isEmpty(objects)) {
			throw pEx("redis key后缀不为空");
		}
		StringBuilder sb = new StringBuilder(redisKeyPrefix.key());
		return sb.append(REDIS_KEY_JOIN_SIGN).append(join(REDIS_KEY_JOIN_SIGN, objects)).toString();
	}

	/**
	 * 是否存在key
	 *
	 * @param daoIn dao对象
	 * @param type 类别
	 * @param keyValue 唯一key
	 */
	public static boolean exists(final IRedisDao daoIn, final String type, final String keyValue) {
		checkDao(daoIn);
		checkEmptyBEx(type, "type不能为空!");
		checkEmptyBEx(keyValue, "keyValue不能为空!");
		return daoIn.exists(key(RedisKeyPrefix.UNI, type, keyValue));
	}

	/**
	 * 设置key，value
	 *
	 * @param daoIn dao对象
	 * @param type 类别
	 * @param keyValue 唯一key
	 * @param value 值
	 */
	public static void set(final IRedisDao daoIn, final String type, final String keyValue, final String value) {
		checkDao(daoIn);
		checkEmptyBEx(type, "type不能为空!");
		checkEmptyBEx(keyValue, "keyValue不能为空!");
		daoIn.set(key(RedisKeyPrefix.UNI, type, keyValue), value);
	}

	/**
	 * 从redis取值
	 *
	 * @param daoIn dao对象
	 * @param type 类别
	 * @param keyValue 唯一key
	 * @return 对应值
	 */
	public static String get(final IRedisDao daoIn, final String type, final String keyValue) {
		checkDao(daoIn);
		checkEmptyBEx(type, "type不能为空!");
		checkEmptyBEx(keyValue, "keyValue不能为空!");
		return daoIn.get(key(RedisKeyPrefix.UNI, type, keyValue));
	}

	/**
	 * 从redis删除key
	 *
	 * @param daoIn dao对象
	 * @param type 类别
	 * @param keyValue 唯一key
	 * @return 对应值
	 */
	public static long del(final IRedisDao daoIn, final String type, final String keyValue) {
		checkDao(daoIn);
		checkEmptyBEx(type, "type不能为空!");
		checkEmptyBEx(keyValue, "keyValue不能为空!");
		return daoIn.del(key(RedisKeyPrefix.UNI, type, keyValue));
	}

	/**
	 * 为给定 key 设置生存时间，当 key 过期时(生存时间为 0 )，它会被自动删除。
	 * 
	 * @param daoIn dao对象
	 * @param type 类别
	 * @param keyValue 唯一key
	 * @param unixTime 过期时间，单位为时间戳
	 * @return 设置成功返回 1 。当 key 不存在或者不能为 key 设置生存时间时，返回 0
	*/
	public static long expireAt(final IRedisDao daoIn, final String type, final String keyValue, final long unixTime) {
		checkDao(daoIn);
		checkEmptyBEx(type, "type不能为空!");
		checkEmptyBEx(keyValue, "keyValue不能为空!");
		return daoIn.expireAt(key(RedisKeyPrefix.UNI, type, keyValue), unixTime / 1000);
	}

	/**
	 * 为给定 key 设置生存时间，当 key 过期时(生存时间为 0 )，它会被自动删除。
	 * 
	 * @param daoIn dao对象
	 * @param type 类别
	 * @param keyValue 唯一key
	 * @param seconds 过期时间，单位为秒
	 * @return 设置成功返回 1 。当 key 不存在或者不能为 key 设置生存时间时，返回 0
	*/
	public static long expire(final IRedisDao daoIn, final String type, final String keyValue, final int seconds) {
		checkDao(daoIn);
		checkEmptyBEx(type, "type不能为空!");
		checkEmptyBEx(keyValue, "keyValue不能为空!");
		return daoIn.expire(key(RedisKeyPrefix.UNI, type, keyValue), seconds);
	}

	/**
	 * 为给定 key 设置生存时间，当 key 过期时(生存时间为 0 )，它会被自动删除。
	 * 
	 * 如果已经设定超时时间，而且比现在还长那就不设置了
	 * @param daoIn dao对象
	 * @param type 类别
	 * @param keyValue 唯一key
	 * @param seconds 过期时间，单位为秒
	 * @return 设置成功返回 1 。当 key 不存在或者不能(不需要)为 key 设置生存时间时，返回 0
	*/
	public static long expireMin(final IRedisDao daoIn, final String type, final String keyValue, final int seconds) {
		checkDao(daoIn);
		checkEmptyBEx(type, "type不能为空!");
		checkEmptyBEx(keyValue, "keyValue不能为空!");
		String key = key(RedisKeyPrefix.UNI, type, keyValue);
		if (daoIn.ttl(key) >= seconds) {
			return 0;
		}
		return daoIn.expire(key, seconds);
	}

	/**
	 * 根据样式批量删除key
	 *
	 * @param daoIn dao对象
	 * @param pattern 样式
	 * @return key主键列表
	 */
	public static Set<String> removeKeys(final IRedisDao daoIn, final String pattern) {
		return daoIn.removeKeys(pattern);
	}
}
