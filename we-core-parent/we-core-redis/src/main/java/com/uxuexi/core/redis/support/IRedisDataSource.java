/**
 * IRedisDataSource.java
 * com.uxuexi.dao.redis.support
 * Copyright (c) 2014, 北京聚智未来科技有限公司版权所有.
*/

package com.uxuexi.core.redis.support;

import redis.clients.jedis.JedisCommands;

/**
 * redis数据源的通用接口
 * 
 * @author   庄君祥
 * @Date	 2013-2-5 	 
 */
public interface IRedisDataSource {

	/**
	 * 创建一个redis连接
	 *
	 * @return 获得redis连接
	*/
	public JedisCommands getJedis();

	/**
	 * 归还指定的连接资源
	 *
	 * @param jedis jedis连接对象
	*/
	public void returnResource(final JedisCommands jedis);

	/**
	 * 归还指定断开的连接资源
	 *
	 * @param jedis jedis连接对象
	*/
	public void returnBrokenResource(final JedisCommands jedis);

}
