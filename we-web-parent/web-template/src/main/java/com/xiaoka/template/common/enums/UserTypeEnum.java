package com.xiaoka.template.common.enums;

import com.uxuexi.core.common.enums.IEnum;

public enum UserTypeEnum implements IEnum {

	FRONT(0, "前台用户"),BACK(1, "后台用户");
	private int key;
	private String value;

	private UserTypeEnum(final int key, final String value) {
		this.value = value;
		this.key = key;
	}

	@Override
	public String key() {
		return String.valueOf(key);
	}

	@Override
	public String value() {
		return value;
	}

	public int intKey() {
		return key;
	}

}
