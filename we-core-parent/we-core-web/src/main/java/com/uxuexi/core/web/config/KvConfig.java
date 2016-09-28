/**
 * KvConfig.java
 * com.uxuexi.web.common.config
 * Copyright (c) 2014, 北京聚智未来科技有限公司版权所有.
*/

package com.uxuexi.core.web.config;

import java.util.Map;

import com.uxuexi.core.common.enums.IEnum;

import lombok.Data;

/**
 * keyvalue配置文件，系统统一配置
 * @author   庄君祥
 * @Date	 2013-11-6 	 
 */
@Data
public class KvConfig {
	/**
	 * map结构存储的配置
	 */
	private Map<String, String> values;

	public String getValue(final String key) {
		if (values == null) {
			return null;
		}
		return values.get(key);
	}

	public String getValue(final IEnum key) {
		if (values == null) {
			return null;
		}
		if (key == null) {
			return null;
		}
		return values.get(key.key());
	}
}
