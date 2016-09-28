/**
 * RedisTransaction.java
 * com.uxuexi.dao.redis.support
 * Copyright (c) 2014, 北京聚智未来科技有限公司版权所有.
*/

package com.uxuexi.core.redis.support;

import static com.uxuexi.core.common.util.MapUtil.*;
import static com.uxuexi.core.common.util.Util.*;

import java.util.Map;
import java.util.Map.Entry;

import redis.clients.jedis.JedisCommands;

/**
 * redis的事务控制对象
 * 
 * @author   庄君祥

 * @Date	 2013-1-30 	 
 */
public class RedisTransaction {
	/**
	 * 每一个连接池的连接管理
	 */
	private Map<IRedisDataSource, JedisCommands> ds = map();

	/**
	 * 从连接池中获取一个链接,如果已经获取过，则使用旧有的链接
	 *
	 * @param dataSource 链接池
	 * @return 链接
	 */
	public JedisCommands getRedis(final IRedisDataSource dataSource) {
		if (!isEmpty(ds) && ds.containsKey(dataSource)) {
			return ds.get(dataSource);
		}
		JedisCommands j = dataSource.getJedis();
		ds.put(dataSource, j);
		return j;
	}

	/**
	 * 关闭连接
	 */
	public void close() {
		if (isEmpty(ds)) {
			return;
		}
		for (Entry<IRedisDataSource, JedisCommands> en : ds.entrySet()) {
			en.getKey().returnResource(en.getValue());
		}
		ds.clear();
	}

	/**
	 * 关闭连接
	 */
	public void closeWithEx() {
		if (isEmpty(ds)) {
			return;
		}
		for (Entry<IRedisDataSource, JedisCommands> en : ds.entrySet()) {
			en.getKey().returnBrokenResource(en.getValue());
		}
		ds.clear();
	}
}
