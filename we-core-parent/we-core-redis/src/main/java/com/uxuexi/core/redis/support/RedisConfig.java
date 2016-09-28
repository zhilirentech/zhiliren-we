/**
 * JedisConfig.java
 * com.uxuexi.dao.redis
 * Copyright (c) 2014, 北京聚智未来科技有限公司版权所有.
*/

package com.uxuexi.core.redis.support;

import lombok.Data;

/**
 * jedis相关配置
 * 
 * @author   庄君祥

 * @Date	 2014-4-3  	 
 */
@Data
public class RedisConfig {
	/**
	 * ip
	 */
	private String host;
	/**
	 * 端口
	 */
	private int port;
	/**
	 * 超时时间
	 */
	private int timeout;

	public RedisConfig(final String host, final int port, final int timeout) {
		super();
		this.host = host;
		this.port = port;
		this.timeout = timeout;
	}
}
