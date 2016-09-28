/**
 * RedisCountUtil.java
 * com.uxuexi.dao.util
 * Copyright (c) 2014, 北京聚智未来科技有限公司版权所有.
*/

package com.uxuexi.core.redis.util;

import static com.uxuexi.core.common.util.ExceptionUtil.*;
import static com.uxuexi.core.redis.support.RedisKeyPrefix.*;
import static com.uxuexi.core.redis.util.RedisUniUtil.*;

import com.uxuexi.core.common.util.ConvertUtil;
import com.uxuexi.core.redis.IRedisDao;

/**
 * Redis计数工具类
 * 
 * @author   庄君祥
 * @author   庄君祥

 * @Date	 2013-1-23 	
 * @version  5.1.0
 */
public final class RedisCountUtil {
	/**
	 * key对应值 +1 
	 *
	 * @param dao dao对象
	 * @param key 键值
	 * @return 增加后的值
	 */
	public static long incr(final IRedisDao dao, final String key) {
		checkDao(dao);
		return dao.incr(key(COUNT, key));
	}

	private static void checkDao(final IRedisDao dao) {
		checkNull(dao, "传人的dao为空！");
	}

	/**
	 * key 对应值增加
	 * @param dao dao对象
	 * @param key 键值
	 * @param incr 增加值
	 * @return 增加后值
	 */
	public static long incrBy(final IRedisDao dao, final String key, final long incr) {
		checkDao(dao);
		return dao.incrBy(key(COUNT, key), incr);
	}

	/**
	 * key 对应值-1 
	 *
	 * @param dao dao对象
	 * @param key 键值
	 * @return 减少后的值
	 */
	public static long decr(final IRedisDao dao, final String key) {
		checkDao(dao);
		return dao.decr(key(COUNT, key));
	}

	/**
	 * key 对应值减少
	 * @param dao dao对象
	 * @param key 键值
	 * @param decr 减少值
	 * @return 减少后值
	 */
	public static long decrBy(final IRedisDao dao, final String key, final long decr) {
		checkDao(dao);
		return dao.decrBy(key(COUNT, key), decr);
	}

	/**
	 * key对应值 +1,并会在一定时间后过期
	 * 注意过期的和普通的键值前缀不同·！！！
	 * @param dao dao对象
	 * @param key 键值
	 * @param seconds 过期秒
	 * @return 增加后的值
	 */
	public static long incr(final IRedisDao dao, final String key, final int seconds) {
		checkDao(dao);
		String realKey = key(EXP_COUNT, key);
		long ttl = dao.ttl(realKey);
		if (ttl == -1) {
			long result = dao.incr(realKey);
			dao.expire(realKey, seconds);
			return result;
		}
		return dao.incr(realKey);
	}

	/**
	 * key 对应值增加,并会在一定时间后过期
	 * @param dao dao对象
	 * @param key 键值
	 * @param incr 增加值
	 * @return 增加后值
	 */
	public static long incrBy(final IRedisDao dao, final String key, final long incr, final int seconds) {
		checkDao(dao);
		String realKey = key(EXP_COUNT, key);
		long ttl = dao.ttl(realKey);
		if (ttl == -1) {
			long result = dao.incrBy(realKey, incr);
			dao.expire(realKey, seconds);
			return result;
		}
		return dao.incrBy(realKey, incr);
	}

	/**
	 * key 对应值-1 ,并会在一定时间后过期
	 *
	 * @param dao dao对象
	 * @param key 键值
	 * @return 减少后的值
	 */
	public static long decr(final IRedisDao dao, final String key, final int seconds) {
		checkDao(dao);
		String realKey = key(EXP_COUNT, key);
		long ttl = dao.ttl(realKey);
		if (ttl == -1) {
			long result = dao.decr(realKey);
			dao.expire(realKey, seconds);
			return result;
		}
		return dao.decr(realKey);
	}

	/**
	 * key 对应值减少,并会在一定时间后过期
	 * @param dao dao对象
	 * @param key 键值
	 * @param decr 减少值
	 * @return 减少后值
	 */
	public static long decrBy(final IRedisDao dao, final String key, final long decr, final int seconds) {
		checkDao(dao);
		String realKey = key(EXP_COUNT, key);
		long ttl = dao.ttl(realKey);
		if (ttl == -1) {
			long result = dao.decrBy(realKey, decr);
			dao.expire(realKey, seconds);
			return result;
		}
		return dao.decrBy(realKey, decr);
	}

	/**
	 * key对应值 +1,并会在一定时间后过期
	 *
	 * @param dao dao对象
	 * @param key 键值
	 * @param time 过期秒
	 * @return 增加后的值
	 */
	public static long incrAt(final IRedisDao dao, final String key, final long time) {
		checkDao(dao);
		String realKey = key(EXP_COUNT, key);
		long ttl = dao.ttl(realKey);
		if (ttl == -1) {
			long result = dao.incr(realKey);
			dao.expireAt(realKey, time);
			return result;
		}
		return dao.incr(realKey);
	}

	/**
	 * key 对应值增加,并会在一定时间后过期
	 * @param dao dao对象
	 * @param key 键值
	 * @param incr 增加值
	 * @return 增加后值
	 */
	public static long incrByAt(final IRedisDao dao, final String key, final long incr, final long time) {
		checkDao(dao);
		String realKey = key(EXP_COUNT, key);
		long ttl = dao.ttl(realKey);
		if (ttl == -1) {
			long result = dao.incrBy(realKey, incr);
			dao.expireAt(realKey, time);
			return result;
		}
		return dao.incrBy(realKey, incr);
	}

	/**
	 * key 对应值-1 ,并会在一定时间后过期
	 *
	 * @param dao dao对象
	 * @param key 键值
	 * @return 减少后的值
	 */
	public static long decrAt(final IRedisDao dao, final String key, final long time) {
		checkDao(dao);
		String realKey = key(EXP_COUNT, key);
		long ttl = dao.ttl(realKey);
		if (ttl == -1) {
			long result = dao.decr(realKey);
			dao.expireAt(realKey, time);
			return result;
		}
		return dao.decr(realKey);
	}

	/**
	 * key 对应值减少,并会在一定时间后过期
	 * @param dao dao对象
	 * @param key 键值
	 * @param decr 减少值
	 * @return 减少后值
	 */
	public static long decrByAt(final IRedisDao dao, final String key, final long decr, final long time) {
		checkDao(dao);
		String realKey = key(EXP_COUNT, key);
		long ttl = dao.ttl(realKey);
		if (ttl == -1) {
			long result = dao.decrBy(realKey, decr);
			dao.expireAt(realKey, time);
			return result;
		}
		return dao.decrBy(realKey, decr);
	}

	/**
	 * 限制一定时间重复访问频度
	 *
	 * @param dao redis库对象
	 * @param key 键值
	 * @param seconds 超时时间
	 * @param max 最大值
	 * @return 是否超过最大值
	 */
	public static boolean max(final IRedisDao dao, final String key, final int seconds, final long max) {
		long v = incr(dao, key, seconds);
		return v > max;
	}

	/**
	 * 统计数目
	 *
	 * @param dao
	 * @param key 键值
	 * @return 数目
	 */
	public static int count(final IRedisDao dao, final String key) {
		checkDao(dao);
		return ConvertUtil.obj2int(dao.get(key(COUNT, key)));
	}
}
