/**
 * SabreAccessToken.java
 * com.xiaoka.test.entity
 * Copyright (c) 2016, 北京科技有限公司版权所有.
*/

package com.xiaoka.test.entity;

import lombok.Data;

/**
 * sabre  API调用凭据
 * <p>
 *
 * @author   朱晓川
 * @Date	 2016年11月15日 	 
 */
@Data
public class SabreAccessToken {

	/**
	 * 凭据
	 */
	private String accessToken;

	/**
	 * 凭据类型
	 */
	private String tokenType;

	/**
	 * 过期时间
	 */
	private int expiresIn;

}
