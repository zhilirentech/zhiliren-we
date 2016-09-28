/**
 * RedisRelUtil.java
 * com.uxuexi.core.redis.util
 * Copyright (c) 2014, 北京聚智未来科技有限公司版权所有.
 */
package com.uxuexi.core.redis.util;

import static com.uxuexi.core.common.util.ArrayUtil.*;
import static com.uxuexi.core.common.util.CollectionUtil.*;
import static com.uxuexi.core.common.util.Util.*;
import static com.uxuexi.core.redis.support.RedisKeyPrefix.*;
import static com.uxuexi.core.redis.util.RedisUtil.*;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.uxuexi.core.redis.IRedisDao;

/**
 * Redis关系工具类
 * 
 * @author   庄君祥
 * @author   朱晓川
 * @Date	 2013-07-18
 * @version  0.0.0	 
 */
public final class RedisRelUtil {
	private RedisRelUtil() {
	}

	/**
	 * 建立无序的单向关系
	 * 
	 * @param redis dao对象
	 * @param Ttype 甲
	 * @param key  标识
	 * @param toType 乙
	 * @param member 成员
	 * @return >0 成功添加的新成员的数量、0 失败
	 */
	public static long buildRel(final IRedisDao redis, final String Ttype, final Object key, final String toType,
			final Object member) {
		if (isEmpty(member) || isEmpty(key)) {
			return 0;
		}
		checkDao(redis);
		if (key instanceof Collection<?>) {
			List<?> keys = collection2list((Collection<?>) key);
			int index = 0;
			for (Object k : keys) {
				index += redis.sadd(key(RELATION, Ttype, k, toType), arrayObjectString(member));
			}
			return index;
		}
		if (key.getClass().isArray()) {
			Object[] keys = (Object[]) key;
			int index = 0;
			for (Object k : keys) {
				index += redis.sadd(key(RELATION, Ttype, k, toType), arrayObjectString(member));
			}
			return index;
		}
		return redis.sadd(key(RELATION, Ttype, key, toType), arrayObjectString(member));
	}

	/**
	 * 建立有序的单向关系
	 * 
	 * @param dao dao对象
	 * @param Ttype 甲
	 * @param key  标识
	 * @param toType 乙
	 * @param score 权重，分值等
	 * @param member 成员
	 * @return >0 成功添加的新成员的数量、0 失败
	 */
	public static long buildRel(final IRedisDao dao, final String Ttype, final Object key, final String toType,
			final double score, final Object member) {
		if (isEmpty(member) || isEmpty(key)) {
			return 0;
		}
		checkDao(dao);
		return dao.zadd(key(RELATION, Ttype, key, toType), score, member.toString());
	}

	/**
	 * 建立无序的双向关系
	 * 
	 * @param dao dao对象
	 * @param type1
	 * @param key1
	 * @param type2
	 * @param key2
	 * @return >0 成功添加的新成员的数量、0 失败
	 */
	public static long buildRel2(final IRedisDao dao, final String type1, final Object key1, final String type2,
			final Object key2) {
		buildRel(dao, type1, key1, type2, key2);
		buildRel(dao, type2, key2, type1, key1);
		return 1;
	}

	/**
	 * 建立有序的双向关系
	 * 
	 * @param dao dao对象
	 * @param type1
	 * @param score1 权重，分值等
	 * @param key1
	 * @param type2
	 * @param score2 权重，分值
	 * @param key2
	 * @return >0 成功添加的新成员的数量、0 失败
	 */
	public static long buildRel2(final IRedisDao dao, final String type1, final double score1, final Object key1,
			final String type2, final double score2, final Object key2) {
		buildRel(dao, type1, key1, type2, score2, key2);
		buildRel(dao, type2, key2, type1, score1, key1);
		return 1;
	}

	/**
	 * 移除 无序单向关系
	 * @param dao dao对象
	 * @param type 
	 * @param key
	 * @param toType
	 * @param members 成员
	 * @return >0 成功添加的新成员的数量、0 失败
	 */
	public static long delRel(final IRedisDao dao, final String type, final Object key, final String toType,
			final Object members) {
		if (isEmpty(members)) {
			return 0;
		}
		checkDao(dao);
		return dao.srem(key(RELATION, type, key, toType), arrayObjectString(members));
	}

	/**
	 * 移除 有序单向关系
	 * @param dao dao对象
	 * @param type 
	 * @param key
	 * @param toType
	 * @param members 成员
	 * @return >0 成功添加的新成员的数量、0 失败
	 */
	public static long delZrel(final IRedisDao dao, final String type, final Object key, final String toType,
			final Object members) {
		if (isEmpty(members)) {
			return 0;
		}
		checkDao(dao);
		return dao.zrem(key(RELATION, type, key, toType), arrayObjectString(members));
	}

	/**
	 * 解除 无序双向关系
	 * @param dao dao对象
	 * @param type1
	 * @param key1
	 * @param type2
	 * @param key2
	 * @return 如果>0，即为成功添加的新成员的数量；否则为失败
	 */
	public static long delRel2(final IRedisDao dao, final String type1, final Object key1, final String type2,
			final Object key2) {
		delRel(dao, type1, key1, type2, key2);
		delRel(dao, type2, key2, type1, key1);
		return 1;
	}

	/**
	 * 解除 有序双向关系
	 * @param dao dao对象
	 * @param type1
	 * @param key1
	 * @param type2
	 * @param >0 成功添加的新成员的数量、0 失败
	 */
	public static long delZrel2(final IRedisDao dao, final String type1, final Object key1, final String type2,
			final Object key2) {
		delZrel(dao, type1, key1, type2, key2);
		delZrel(dao, type2, key2, type1, key1);
		return 1;
	}

	/**
	 *  检查是否存在关系
	 * @param dao dao对象
	 * @param type 键值
	 * @param key 键值
	 * @param toType 键值
	 * @param members 成员
	 * @return true 存在 、false 不存在
	 */
	public static boolean isRel(final IRedisDao dao, final String type, final Object key, final String toType,
			final Object members) {
		checkDao(dao);
		if (isEmpty(members)) {
			return false;
		}
		return dao.sismember(key(RELATION, type, key, toType), members.toString());
	}

	/**
	 * 检查是否存在关系
	 * @param dao dao对象
	 * @param type
	 * @param key
	 * @param toType
	 * @param members
	 * @return true 存在 、false 不存在
	 */
	public static boolean isZRel(final IRedisDao dao, final String type, final Object key, final String toType,
			final Object members) {
		checkDao(dao);
		return dao.zrevrank(key(RELATION, type, key, toType), members.toString()) == 0 ? false : true;
	}

	/**
	 * 查询 无序关系
	 * @param type
	 * @param key
	 * @param toType
	 * @return set集合
	 */
	public static Set<String> queryRel(final IRedisDao dao, final String type, final Object key, final String toType) {
		checkDao(dao);
		return dao.smembers(key(RELATION, type, key, toType));
	}

	/**
	 * 查询 有序关系
	 * @param type
	 * @param key
	 * @param toType
	 * @param start
	 * @param end
	 * @return set集合
	 */
	public static Set<String> queryZrel(final IRedisDao dao, final String type, final Object key, final String toType,
			final int start, final int end) {
		checkDao(dao);
		return dao.zrange(key(RELATION, type, key, toType), start, end);
	}

	/**
	 * 删除key
	 *
	 * @param dao  dao对象
	 * @param type  甲
	 * @param key  标识
	 * @param toType 乙
	 * @return >0 成功添加的新成员的数量、0 失败
	 */
	public static long deleteRedisKeys(final IRedisDao dao, final String type, final Object key, final String toType) {
		checkDao(dao);
		return dao.del(key(RELATION, type, key, toType));
	}

	/**
	 * 取 多个键值之间关系的交集
	 * 
	 * @param dao redisDao对象
	 * @param type 需要的参数
	 * @param toType 需要的参数
	 * @param params 键值数组
	 * @return 关系之间的交集
	 */
	public static Set<String> queryRelInter(final IRedisDao dao, final String type, final String toType,
			final List<String> params) {
		checkDao(dao);
		if (isEmpty(type) || isEmpty(toType) || isEmpty(params)) {
			return new HashSet<String>();
		}
		List<String> keys = list();
		for (String key : params) {
			keys.add(key(RELATION, type, key, toType));
		}
		return dao.sinter(collection2array(keys));
	}

	/**
	 * 取 多个键值之间关系的并集
	 * 
	 * @param dao redisDao对象
	 * @param type 需要的参数
	 * @param toType 需要的参数
	 * @param params 键值数组
	 * @return 关系之间的并集
	 */
	public static Set<String> queryRelUnion(final IRedisDao dao, final String type, final String toType,
			final List<String> params) {
		checkDao(dao);
		if (isEmpty(type) || isEmpty(toType) || isEmpty(params)) {
			return new HashSet<String>();
		}
		List<String> keys = list();
		for (String key : params) {
			keys.add(key(RELATION, type, key, toType));
		}
		return dao.sunion(collection2array(keys));
	}

	/**
	 * 根据格式获取对应的key集合
	 *
	 * @param dao redisDao对象
	 * @param pattern 格式
	 * @return 匹配的key集合
	*/
	public static Set<String> keys(final IRedisDao dao, final String pattern) {
		return dao.keys(pattern);
	}
}
