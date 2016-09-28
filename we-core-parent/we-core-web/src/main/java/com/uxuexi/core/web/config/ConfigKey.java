/**
 * ConfigKey.java
 * com.uxuexi.web.manage.common
 * Copyright (c) 2014, 北京聚智未来科技有限公司版权所有.
*/

package com.uxuexi.core.web.config;

import com.uxuexi.core.common.enums.IEnum;

/**
 * 配置的关键key
 * @author   庄君祥
 * @Date	 2013-11-8 	 
 */
public enum ConfigKey implements IEnum {
	STATIC, LOGIN_PAGE, LOGOUT_URL, LOGOUT_TO, SSO_COOKIE_KEY, COOKIE_DOMAIN, FILE_SERVER, ZUJUAN, WWW, FUDAO, WISH, ZHIYE, ZHUANYE, COLLEGE;

	@Override
	public String key() {
		return this.name().toLowerCase();
	}

	@Override
	public String value() {
		return this.name().toLowerCase();
	}
}
