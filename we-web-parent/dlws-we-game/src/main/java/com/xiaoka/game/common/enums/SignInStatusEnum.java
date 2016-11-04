package com.xiaoka.game.common.enums;

import com.uxuexi.core.common.enums.IEnum;

/**
 *
 */
public enum SignInStatusEnum implements IEnum {

	NO(0, "未签到"),YES(1, "已签到"),OVERTIME(3,"签到超时");
	private int key;
	private String value;

	private SignInStatusEnum(final int key, final String value) {
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
