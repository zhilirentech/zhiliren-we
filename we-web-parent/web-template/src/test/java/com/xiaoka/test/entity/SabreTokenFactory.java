/**
 * AccessTokenCache.java
 * com.xiaoka.test.entity
 * Copyright (c) 2016, 北京科技有限公司版权所有.
*/

package com.xiaoka.test.entity;

import org.nutz.log.Log;
import org.nutz.log.Logs;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.RemovalListener;
import com.google.common.cache.RemovalNotification;

/**
 * TODO(这里用一句话描述这个类的作用)
 * <p>
 * TODO(这里描述这个类补充说明 – 可选)
 *
 * @author   朱晓川
 * @Date	 2016年11月15日 	 
 */
public class SabreTokenFactory {

	static Log log = Logs.getLog(SabreTokenFactory.class);

	//LoadingCache在缓存项不存在时可以自动加载缓存 
	private static Cache<String, String> cache

	//CacheBuilder的构造函数是私有的，只能通过其静态方法newBuilder()来获得CacheBuilder的实例
	= CacheBuilder.newBuilder()

	//设置并发级别，并发级别是指可以同时写缓存的线程数
			.concurrencyLevel(2)

			//设置缓存容器的初始容量为10
			.initialCapacity(10)

			//设置缓存最大容量为100，超过100之后就会按照LRU最近虽少使用算法来移除缓存项
			.maximumSize(100)

			//设置要统计缓存的命中率
			.recordStats()

			//设置缓存的移除通知
			.removalListener(new RemovalListener<Object, Object>() {
				@Override
				public void onRemoval(RemovalNotification<Object, Object> notification) {
					log.info(notification.getKey() + " was removed, cause is " + notification.getCause());
				}
			})

			//build方法中可以指定CacheLoader，在缓存不存在时通过CacheLoader的实现自动加载缓存
			.build(new CacheLoader<String, String>() {
				@Override
				public String load(String key) throws Exception {
					log.info("load access_token " + key);

					return null;
				}
			});

}
