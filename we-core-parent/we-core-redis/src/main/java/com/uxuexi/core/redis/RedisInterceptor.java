/**
 * RedisInterceptor.java
 * com.uxuexi.dao.redis
 * Copyright (c) 2014, 北京聚智未来科技有限公司版权所有.
*/

package com.uxuexi.core.redis;

import static com.uxuexi.core.redis.support.RedisTransController.*;

import org.nutz.aop.InterceptorChain;
import org.nutz.aop.interceptor.AbstractMethodInterceptor;

import redis.clients.jedis.exceptions.JedisException;

/**
 * Redis拦截器
 * 
 * @author   庄君祥

 * @Date	 2014-4-3  	 
 */
public class RedisInterceptor extends AbstractMethodInterceptor {
	@Override
	public void filter(final InterceptorChain chain) throws Throwable {
		if (!isNoTransaction()) {
			chain.doChain();
			return;
		}
		try {
			begin();
			chain.doChain();
		} catch (JedisException e) {
			closeWithEx();
		} finally {
			close();
		}
	}
}
