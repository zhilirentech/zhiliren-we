/**
 * AbstractCacheCms.java
 * com.uxuexi.core.cms.interfaces.impl
 * Copyright (c) 2014, 北京聚智未来科技有限公司版权所有.
*/

package com.uxuexi.core.web.cms.interfaces.impl;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.uxuexi.core.common.util.CollectionUtil;
import com.uxuexi.core.common.util.MapUtil;
import com.uxuexi.core.common.util.StringUtil;
import com.uxuexi.core.common.util.Util;
import com.uxuexi.core.db.dao.IDbDao;
import com.uxuexi.core.redis.IRedisDao;
import com.uxuexi.core.web.cms.ParseUtil;

/**
 * 使用缓存和数据库进行处理的cms类
 * @author   庄君祥
 * @Date	 2013-12-25 	 
 */
@Data
@EqualsAndHashCode(callSuper = true)
public abstract class AbstractCacheCms extends AbstractCms {
	/**
	 * 原始的字符串
	 */
	private static final String ORIGIN = "origin";
	/**
	 * 原始的字符串,()
	 */
	public static final String AFTER_CMS = "afterCms";

	protected IRedisDao redis;
	protected IDbDao dbDao;

	@Override
	protected void changeMatch(final HttpServletRequest req, final Set<String> needDeal,
			final Map<String, String> result) {
		//获取每个cms参数的列表
		List<Map<String, String>> props = getPropMap(needDeal);
		//先从redis中获取
		//TODO 获取数据间断性
		loadFromRedis(props, result);

		//剩余的从db中获取后，进行构造，并塞入缓存
		loadFormDb(props, result);
		//如果还有未处理的暂时不处理
	}

	//路旭光：方法注释
	protected List<Map<String, String>> getPropMap(final Set<String> needDeal) {
		@SuppressWarnings("unchecked")
		List<Map<String, String>> result = CollectionUtil.list();
		for (String one : needDeal) {
			Map<String, String> props = ParseUtil.getProps(one);
			props.put(ORIGIN, one);
			result.add(props);
		}
		return result;
	}

	//路旭光：方法注释
	public void loadFromRedis(final List<Map<String, String>> tagProps, final Map<String, String> result) {
		List<String> keys = CollectionUtil.list();
		for (Map<String, String> one : tagProps) {
			keys.add(getRedisKey(one));
		}
		String[] collection2array = CollectionUtil.collection2array(keys);
		List<String> re = redis.mget(collection2array);
		if (Util.isEmpty(re)) {
			return;
		}
		for (int i = re.size() - 1; i >= 0; i--) {
			String string = re.get(i);
			if (string == null) {
				continue;
			}
			Map<String, String> remove = tagProps.remove(i);
			result.put(remove.get(ORIGIN), string);
		}
	}

	//路旭光：方法注释
	protected String getRedisKey(final Map<String, String> map) {
		List<String> keys = CollectionUtil.list("cms", getType());
		if (map.containsKey(UID)) {
			keys.add(map.get(UID));
		}
		if (map.containsKey(SUB_TYPE)) {
			keys.add(map.get(SUB_TYPE));
		}
		return StringUtil.join(":", keys);
	}

	//路旭光：方法注释
	protected void loadFormDb(final List<Map<String, String>> tagProps, final Map<String, String> result) {
		if (Util.isEmpty(tagProps)) {
			return;
		}
		loadFormDb(tagProps);
		Map<String, String> needCache = MapUtil.map();
		for (Map<String, String> one : tagProps) {
			String after = "";
			if (one.containsKey(AFTER_CMS)) {
				after = one.get(AFTER_CMS);
			}
			result.put(one.get(ORIGIN), after);
			needCache.put(getRedisKey(one), after);
		}
		redis.set(needCache);
		//设置过期时间
		for (Entry<String, String> entry : needCache.entrySet()) {
			redis.expire(entry.getKey(), getExpireTime());
		}
	}

	/**
	 * 过期时间 
	 * <p>
	 * 默认的过期时间 
	 *
	 * @return 过期时间 
	*/
	protected int getExpireTime() {
		return 10;
	}

	/**
	 * 从数据库里面加载数据
	 * 处理完毕之后，直接放到tagProps的每个map的AFTER_CMS属性里面
	 *
	 * @param tagProps 待处理的数据
	 */
	protected abstract void loadFormDb(final List<Map<String, String>> tagProps);

	/**
	 * 如果 subType为空，则更新某个对象的所有类型的缓存
	 * <p>
	 * 如果id为空，则更新subType类型的所有对象的缓存
	 * <p>
	 * 如果，两个都为空，则表示更新该类getType()的所有缓存
	 */
	@Override
	public void rmCache(final String subType, final String id) {
		List<String> keys = CollectionUtil.list("cms", getType());
		if (!Util.isEmpty(id)) {
			keys.add(id);
		}
		if (!Util.isEmpty(subType)) {
			keys.add(subType);
		}
		String key = StringUtil.join(":", keys) + "*";
		redis.removeKeys(key);
	}

	/**
	 * 替换
	 * <p>
	 * 把标签替换成根据业务处理后的字符串
	 *
	 * @param tag 标签对象
	 * @param result 需要替换的字符串
	*/
	protected void replace(Map<String, String> tag, String result) {
		tag.put(AFTER_CMS, result);
	}
}
