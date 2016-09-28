/**
 * TestRedisMultiThread.java
 * com.uxuexi.core.redis
 * Copyright (c) 2014, 北京聚智未来科技有限公司版权所有.
*/

package com.uxuexi.core.redis;

import static com.uxuexi.core.redis.support.RedisTransController.*;

import org.nutz.ioc.Ioc;
import org.nutz.ioc.impl.NutIoc;
import org.nutz.ioc.loader.json.JsonLoader;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.uxuexi.core.redis.IRedisDao;
import com.uxuexi.core.redis.RedisDao;

import redis.clients.jedis.exceptions.JedisException;

/**
 * 使用多线程获取每个链接操作后关闭，看看最大时间是否超过1s
 * @author   庄君祥
 * @Date	 2013-12-13 	 
 */
public class TestRedisMultiThread {
	private Ioc ioc;

	@BeforeClass
	public void init() {
		ioc = new NutIoc(new JsonLoader("redis/config/"));
	}

	@Test(invocationCount = 100, threadPoolSize = 50, invocationTimeOut = 10)
	public void testOpenCloseEach() {
		IRedisDao redis = ioc.get(RedisDao.class, "redis");
		redis.incr("a");
		redis.decr("a");
	}

	@Test(invocationCount = 100, threadPoolSize = 50, invocationTimeOut = 10)
	public void testOneConnect() {
		try {
			begin();
			IRedisDao redis = ioc.get(RedisDao.class, "redis");
			redis.incr("a");
			redis.decr("a");
		} catch (JedisException e) {
			closeWithEx();
		} finally {
			close();
		}
	}
}
