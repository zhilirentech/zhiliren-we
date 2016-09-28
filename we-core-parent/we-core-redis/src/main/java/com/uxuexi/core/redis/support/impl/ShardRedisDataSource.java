/**
 * RedisDataSource.java
 * com.uxuexi.dao.redis.support
 * Copyright (c) 2014, 北京聚智未来科技有限公司版权所有.
*/

package com.uxuexi.core.redis.support.impl;

import static com.uxuexi.core.common.util.CollectionUtil.*;
import static com.uxuexi.core.common.util.ExceptionUtil.*;
import static com.uxuexi.core.common.util.Util.*;

import java.util.List;

import com.uxuexi.core.redis.support.IRedisDataSource;
import com.uxuexi.core.redis.support.RedisConfig;

import lombok.Data;
import redis.clients.jedis.JedisCommands;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

/**
 * redis配置的集群数据源
 * 
 * @author   庄君祥

 * @Date	 2013-1-30 	 
 */
@Data
public class ShardRedisDataSource implements IRedisDataSource {
	private ShardedJedisPool pool;
	private List<RedisConfig> config;
	private int maxConnect = 8;

	/**
	 * 初始化redis链接池
	 * 
	 * @exception DownException
	 */
	private void initPool() {
		if (pool != null) {
			return;
		}
		List<JedisShardInfo> shards = getShardInfos();
		if (isEmpty(shards)) {
			throw pEx("读取JedisShardInfo 相关配置信息失败");
		}
		JedisPoolConfig conf = new JedisPoolConfig();
		conf.setMaxActive(maxConnect);
		conf.testOnBorrow = true;
		pool = new ShardedJedisPool(conf, shards);
	}

	/**
	 * 获取集群信息列表
	 *
	 * @return 集群信息列表
	 */
	public List<JedisShardInfo> getShardInfos() {
		List<JedisShardInfo> shardInfos = list();
		for (RedisConfig redisConfig : config) {
			JedisShardInfo shardInfo = new JedisShardInfo(redisConfig.getHost(), redisConfig.getPort());
			shardInfos.add(shardInfo);
		}
		return shardInfos;
	}

	@Override
	public ShardedJedis getJedis() {
		initPool();
		return pool.getResource();
	}

	@Override
	public void returnResource(final JedisCommands jedis) {
		if (jedis == null) {
			return;
		}
		if (!(jedis instanceof ShardedJedis)) {
			throw pEx("释放redis链接时，对象类型不正确！");
		}
		pool.returnResource((ShardedJedis) jedis);
	}

	@Override
	public void returnBrokenResource(final JedisCommands jedis) {
		if (jedis == null) {
			return;
		}
		if (!(jedis instanceof ShardedJedis)) {
			throw pEx("释放redis链接时，对象类型不正确！");
		}
		pool.returnBrokenResource((ShardedJedis) jedis);
	}
}
