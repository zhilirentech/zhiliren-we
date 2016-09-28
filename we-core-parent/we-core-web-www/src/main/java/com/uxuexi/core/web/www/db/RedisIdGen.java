/**
 * RedisIdGen.java
 * com.uxuexi.core.web.www
 * Copyright (c) 2013, 北京微课创景教育科技有限公司版权所有.
*/

package com.uxuexi.core.web.www.db;

import static com.uxuexi.core.common.util.ExceptionUtil.*;
import static com.uxuexi.core.redis.support.RedisTransController.*;
import lombok.Data;
import redis.clients.jedis.exceptions.JedisException;

import com.uxuexi.core.db.dao.IIdGen;
import com.uxuexi.core.redis.IRedisDao;
import com.uxuexi.core.redis.util.RedisCountUtil;

/**
 * 使用redis唯一主键生成策略
 * 
 * @author   庄君祥
 * @Date	 2013-1-26 	 
 */
@Data
public class RedisIdGen implements IIdGen {
	private static final String IDKEY = "unique_import";
	private IRedisDao dao;

	@Override
	public long getId() {
		try {
			beginNew();
			return RedisCountUtil.incr(dao, IDKEY);
		} catch (JedisException e) {
			closeWithEx();
			e.printStackTrace();
			throw pEx("读取全局唯一主键失败！:" + e.getMessage());
		} finally {
			close();
		}
	}
}
