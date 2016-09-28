/**
 * MockEnum.java
 * com.uxuexi.common.entity
 * Copyright (c) 2014, 北京聚智未来科技有限公司版权所有.
*/

package com.uxuexi.core.common.entity;

import com.uxuexi.core.common.enums.IEnum;

/**
 * 虚拟的Enum类
 * @author   庄君祥
 * @Date	 2013-2-20 	 
 */
public enum MockEnum implements IEnum {
	TEST("test", "测试"), MOCK("mock", "模拟");
	private String key;
	private String value;

	private MockEnum(final String key, final String value) {
		this.key = key;
		this.value = value;
	}

	@Override
	public String key() {
		return key;
	}

	@Override
	public String value() {
		return value;
	}

}
