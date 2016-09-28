/**
 * RedisDao.java
 * com.uxuexi.dao.redis
 * Copyright (c) 2014, 北京聚智未来科技有限公司版权所有.
*/

package com.uxuexi.core.redis;

import static com.uxuexi.core.common.util.ExceptionUtil.*;
import static com.uxuexi.core.common.util.Util.*;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.uxuexi.core.common.util.CollectionUtil;
import com.uxuexi.core.redis.support.IRedisDataSource;
import com.uxuexi.core.redis.support.RedisTransController;
import com.uxuexi.core.redis.util.PipelineUtil;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;

/**
 * DAO的Redis实现
 * 
 * @author   庄君祥
 * @author   庄君祥

 * @Date	 2014-4-3  	 
 */
public class RedisDao implements IRedisDao {
	private static final Logger logger = LoggerFactory.getLogger(RedisDao.class);

	private IRedisDataSource ds;

	public RedisDao(final IRedisDataSource ds) {
		super();
		this.ds = ds;
	}

	private Jedis getCurrentJedis() {
		return (Jedis) RedisTransController.getCurrentJedis(ds);
	}

	/**
	 * 验证键值是否合法，如果不合法则抛出异常
	 * 
	 * @param key 键值
	 * @exception DataException
	*/
	private void checkKey(final Object key) {
		if (isEmpty(key)) {
			throw pEx("键值不能为空");
		}
	}

	/**
	 * 验证数据是否合法，如果不合法则抛出异常
	 *
	 * @param value 数据
	 * @exception DataException
	*/
	private void checkValue(final Object value) {
		if (value == null) {
			throw pEx("数据不能为空");
		}
	}

	@Override
	public String set(final String key, final String value) {
		checkKey(key);
		checkValue(value);
		return getCurrentJedis().set(key, value);
	}

	@Override
	public String get(final String key) {
		checkKey(key);
		return getCurrentJedis().get(key);
	}

	@Override
	public List<String> mget(final String... key) {
		checkKey(key);
		return getCurrentJedis().mget(key);
	}

	@Override
	public boolean exists(final String key) {
		checkKey(key);
		return getCurrentJedis().exists(key);
	}

	/**
	 * 验证有效时间是否合法
	 * 
	 * @param seconds 有效时间，单们为秒（second）
	 * @exception DataException
	*/
	private void checkSeconds(final int seconds) {
		if (seconds <= 0) {
			throw pEx("有效时间不合法，有效时间只能为正整数，单位为秒");
		}
	}

	/**
	 * 验证有效时间是否合法
	 * 
	 * @param unixTime 有效时间，单位为时间戮(unix timestamp)
	 * @exception DataException
	*/
	private void checkUnixTime(final long unixTime) {
		if (unixTime <= 0) {
			throw pEx("有效时间不合法，有效时间只能为正整数，单位为时间戮");
		}
	}

	@Override
	public long expire(final String key, final int seconds) {
		checkKey(key);
		checkSeconds(seconds);
		return getCurrentJedis().expire(key, seconds);
	}

	@Override
	public long expireAt(final String key, final long unixTime) {
		checkKey(key);
		checkUnixTime(unixTime);
		return getCurrentJedis().expireAt(key, unixTime);
	}

	@Override
	public long ttl(final String key) {
		try {
			checkKey(key);
			return getCurrentJedis().ttl(key);
		} catch (Exception e) {
			logger.error("获取数据出错", e);
			return 0;
		}
	}

	@Override
	public long decrBy(final String key, final long decrement) {
		checkKey(key);
		checkDecrement(decrement);
		return getCurrentJedis().decrBy(key, decrement);
	}

	/**
	 * 验证减量是否合法，不合法则抛出异常
	 *
	 * @param decrement 减量
	 * @exception DataException
	*/
	private void checkDecrement(final long decrement) {
		if (decrement <= 0) {
			throw pEx("减量不合法，应该为正整数");
		}
	}

	@Override
	public Long decr(final String key) {
		checkKey(key);
		return getCurrentJedis().decr(key);
	}

	@Override
	public long incrBy(final String key, final long increment) {
		checkKey(key);
		checkIncrement(increment);
		return getCurrentJedis().incrBy(key, increment);
	}

	/**
	 * 验证减量是否合法，不合法则抛出异常
	 *
	 * @param increment 减量
	 * @exception DataException
	*/
	private void checkIncrement(final long increment) {
		if (increment <= 0) {
			throw pEx("增量不合法，应该为正整数");
		}
	}

	@Override
	public long incr(final String key) {
		checkKey(key);
		return getCurrentJedis().incr(key);
	}

	@Override
	public long hset(final String key, final String field, final String value) {
		checkKey(key);
		checkField(field);
		checkValue(value);
		return getCurrentJedis().hset(key, field, value);
	}

	/**
	 * 验证域的是否合法
	 *
	 * @param field 域
	 * @exception DataException
	*/
	private void checkField(final String field) {
		if (isEmpty(field)) {
			throw pEx("域不能为空");
		}
	}

	@Override
	public String hget(final String key, final String field) {
		checkKey(key);
		checkField(field);
		return getCurrentJedis().hget(key, field);
	}

	@Override
	public List<String> hmget(final String key, final String... fields) {
		checkKey(key);
		return getCurrentJedis().hmget(key, fields);
	}

	@Override
	public String hmset(final String key, final Map<String, String> hash) {
		checkKey(key);
		checkHash(hash);
		return getCurrentJedis().hmset(key, hash);
	}

	/**
	 *验证哈希表是否合法，如果为空则抛出异常
	 *
	 * @param hash 哈希表
	 * @exception DataException
	*/
	private void checkHash(final Map<String, String> hash) {
		if (isEmpty(hash)) {
			throw pEx("哈希表不能为空");
		}
	}

	@Override
	public long hincrBy(final String key, final String field, final long value) {
		checkKey(key);
		checkField(field);
		return getCurrentJedis().hincrBy(key, field, value);
	}

	@Override
	public boolean hexists(final String key, final String field) {
		checkKey(key);
		checkField(field);
		return getCurrentJedis().hexists(key, field);
	}

	@Override
	public long del(final String... key) {
		if (isEmpty(key)) {
			return 0;
		}
		return getCurrentJedis().del(key);
	}

	@Override
	public long hdel(final String key, final String... fields) {
		checkKey(key);
		if (isEmpty(fields)) {
			return 0;
		}
		return getCurrentJedis().hdel(key, fields);
	}

	@Override
	public Map<String, String> hgetAll(final String key) {
		checkKey(key);
		return getCurrentJedis().hgetAll(key);
	}

	@Override
	public long rpush(final String key, final String... vals) {
		checkKey(key);
		checkValue(vals);
		return getCurrentJedis().rpush(key, vals);
	}

	@Override
	public long lpush(final String key, final String... vals) {
		checkKey(key);
		checkValue(vals);
		return getCurrentJedis().lpush(key, vals);
	}

	@Override
	public Long llen(final String key) {
		checkKey(key);
		return getCurrentJedis().llen(key);
	}

	@Override
	public List<String> lrange(final String key, final long start, final long end) {
		checkKey(key);
		return getCurrentJedis().lrange(key, start, end);
	}

	@Override
	public void ltrim(final String key, final long start, final long end) {
		checkKey(key);
		getCurrentJedis().ltrim(key, start, end);
	}

	@Override
	public String lindex(final String key, final long index) {
		checkKey(key);
		return getCurrentJedis().lindex(key, index);
	}

	@Override
	public String lset(final String key, final long index, final String value) {
		checkKey(key);
		return getCurrentJedis().lset(key, index, value);
	}

	@Override
	public String lpop(final String key) {
		checkKey(key);
		return getCurrentJedis().lpop(key);
	}

	@Override
	public String rpop(final String key) {
		checkKey(key);
		return getCurrentJedis().rpop(key);
	}

	@Override
	public String rpoplpush(final String key, final String dstkey) {
		checkKey(key);
		return getCurrentJedis().rpoplpush(key, dstkey);
	}

	@Override
	public long sadd(final String key, final String... members) {
		checkKey(key);
		checkValue(members);
		return getCurrentJedis().sadd(key, members);
	}

	@Override
	public Set<String> smembers(final String key) {
		checkKey(key);
		return getCurrentJedis().smembers(key);
	}

	@Override
	public long srem(final String key, final String... members) {
		checkKey(key);
		if (isEmpty(members)) {
			return 0;
		}
		return getCurrentJedis().srem(key, members);
	}

	@Override
	public String spop(final String key) {
		checkKey(key);
		return getCurrentJedis().spop(key);
	}

	@Override
	public long scard(final String key) {
		checkKey(key);
		return getCurrentJedis().scard(key);
	}

	@Override
	public boolean sismember(final String key, final String member) {
		checkKey(key);
		checkMember(member);
		return getCurrentJedis().sismember(key, member);
	}

	/**
	 * 验证成员是否合法
	 *
	 * @param member 成员是否合法
	 * @exception DataException
	*/
	private void checkMember(final String member) {
		if (isEmpty(member)) {
			throw pEx("成员不能为空");
		}
	}

	@Override
	public String srandmember(final String key) {
		checkKey(key);
		return getCurrentJedis().srandmember(key);
	}

	@Override
	public long zadd(final String key, final double score, final String member) {
		checkKey(key);
		checkMember(member);
		return getCurrentJedis().zadd(key, score, member);
	}

	@Override
	public long zadd(final String key, final Map<Double, String> scoreMembers) {
		checkKey(key);
		checkValue(scoreMembers);
		return getCurrentJedis().zadd(key, scoreMembers);
	}

	@Override
	public Set<String> zrange(final String key, final long start, final long end) {
		checkKey(key);
		return getCurrentJedis().zrange(key, start, end);
	}

	@Override
	public long zrem(final String key, final String... members) {
		checkKey(key);
		if (isEmpty(members)) {
			return 0;
		}
		return getCurrentJedis().zrem(key, members);
	}

	@Override
	public double zincrby(final String key, final double score, final String member) {
		checkKey(key);
		checkMember(member);
		return getCurrentJedis().zincrby(key, score, member);
	}

	@Override
	public long zrank(final String key, final String member) {
		checkKey(key);
		checkMember(member);
		return getCurrentJedis().zrank(key, member);
	}

	@Override
	public Long zrevrank(final String key, final String member) {
		checkKey(key);
		checkMember(member);
		return getCurrentJedis().zrevrank(key, member);
	}

	@Override
	public Set<String> zrevrange(final String key, final long start, final long end) {
		checkKey(key);
		return getCurrentJedis().zrevrange(key, start, end);
	}

	@Override
	public long zcard(final String key) {
		checkKey(key);
		return getCurrentJedis().zcard(key);
	}

	@Override
	public Double zscore(final String key, final String member) {
		checkKey(key);
		checkMember(member);
		return getCurrentJedis().zscore(key, member);
	}

	@Override
	public List<String> sort(final String key) {
		checkKey(key);
		return getCurrentJedis().sort(key);
	}

	@Override
	public long zcount(final String key, final double min, final double max) {
		checkKey(key);
		return getCurrentJedis().zcount(key, min, max);
	}

	@Override
	public Set<String> zrangeByScore(final String key, final double min, final double max) {
		checkKey(key);
		return getCurrentJedis().zrangeByScore(key, min, max);
	}

	@Override
	public Set<String> zrevrangeByScore(final String key, final double max, final double min) {
		checkKey(key);
		return getCurrentJedis().zrevrangeByScore(key, max, min);
	}

	@Override
	public Set<String> zrangeByScore(final String key, final double min, final double max, final int offset,
			final int count) {
		checkKey(key);
		return getCurrentJedis().zrangeByScore(key, min, max, offset, count);
	}

	@Override
	public Set<String> zrevrangeByScore(final String key, final double max, final double min, final int offset,
			final int count) {
		checkKey(key);
		return getCurrentJedis().zrevrangeByScore(key, max, min, offset, count);
	}

	@Override
	public Set<String> zrangeByScore(final String key, final String min, final String max) {
		checkKey(key);
		return getCurrentJedis().zrangeByScore(key, min, max);
	}

	@Override
	public Set<String> zrevrangeByScore(final String key, final String max, final String min) {
		checkKey(key);
		return getCurrentJedis().zrevrangeByScore(key, max, min);
	}

	@Override
	public Set<String> zrangeByScore(final String key, final String min, final String max, final int offset,
			final int count) {
		checkKey(key);
		return getCurrentJedis().zrangeByScore(key, min, max, offset, count);
	}

	@Override
	public Set<String> zrevrangeByScore(final String key, final String max, final String min, final int offset,
			final int count) {
		checkKey(key);
		return getCurrentJedis().zrevrangeByScore(key, max, min, offset, count);
	}

	@Override
	public Long zremrangeByRank(final String key, final long start, final long end) {
		checkKey(key);
		return getCurrentJedis().zremrangeByRank(key, start, end);
	}

	@Override
	public Long zremrangeByScore(final String key, final double start, final double end) {
		checkKey(key);
		return getCurrentJedis().zremrangeByScore(key, start, end);
	}

	@Override
	public Long zremrangeByScore(final String key, final String start, final String end) {
		checkKey(key);
		return getCurrentJedis().zremrangeByScore(key, start, end);
	}

	@Override
	public Set<String> sinter(final String... keys) {
		return getCurrentJedis().sinter(keys);
	}

	@Override
	public Set<String> sunion(final String... keys) {
		return getCurrentJedis().sunion(keys);
	}

	@Override
	public Set<String> keys(final String pattern) {
		return getCurrentJedis().keys(pattern);
	}

	@Override
	public Set<String> removeKeys(final String pattern) {
		Set<String> keys = getCurrentJedis().keys(pattern);
		if (isEmpty(keys)) {
			return keys;
		}
		getCurrentJedis().del(CollectionUtil.collection2array(keys));
		return keys;
	}

	@Override
	public Long lrem(final String key, final int count, final String value) {
		checkKey(key);
		return getCurrentJedis().lrem(key, count, value);
	}

	@Override
	public Set<String> zunion(final String... keys) {
		if (isEmpty(keys)) {
			return new LinkedHashSet<String>();
		}
		Pipeline pipe = getCurrentJedis().pipelined();
		for (String key : keys) {
			pipe.zrangeWithScores(key, 0, -1);
		}
		List<Object> re = pipe.syncAndReturnAll();
		return PipelineUtil.unionSet(re);
	}

	@Override
	public Set<String> zunion(final double min, final double max, final String... keys) {
		if (isEmpty(keys)) {
			return new LinkedHashSet<String>();
		}

		Pipeline pipe = getCurrentJedis().pipelined();
		for (String key : keys) {
			pipe.zrangeByScoreWithScores(key, min, max);
		}
		List<Object> re = pipe.syncAndReturnAll();
		return PipelineUtil.unionSet(re);
	}

	@Override
	public long zunionstore(final String dest, final String... keys) {
		checkKey(dest);
		return getCurrentJedis().zunionstore(dest, keys);
	}

	@Override
	public long zinterstore(final String dest, final String... keys) {
		checkKey(dest);
		return getCurrentJedis().zinterstore(dest, keys);
	}

	@Override
	public void set(final String prefix, final Map<String, String> values) {
		if (isEmpty(prefix) || isEmpty(values)) {
			return;
		}
		Pipeline pipeline = pipeline();
		for (Entry<String, String> value : values.entrySet()) {
			if (value == null) {
				continue;
			}
			String id = value.getKey();
			if (isEmpty(id)) {
				continue;
			}
			pipeline.set(prefix + ":" + id, value.getValue());
		}
		pipeline.sync();
	}

	@Override
	public void set(final Map<String, String> values) {
		if (isEmpty(values)) {
			return;
		}
		Pipeline pipeline = pipeline();
		for (Entry<String, String> value : values.entrySet()) {
			if (value == null) {
				continue;
			}
			String id = value.getKey();
			if (isEmpty(id)) {
				continue;
			}
			pipeline.set(id, value.getValue());
		}
		pipeline.sync();
	}

	/**
	 * 获取管道
	 * <p>
	 * <strong>如果在非aop的环境下使用，请一定记得try catch finally关闭连接</strong>
	 * @return pipeline
	 */
	public Pipeline pipeline() {
		return getCurrentJedis().pipelined();
	}
}
