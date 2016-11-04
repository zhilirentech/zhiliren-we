package com.xiaoka.game.common.enums;

import com.uxuexi.core.common.enums.IEnum;

/**
 *
 */
public enum SignInStatus2Enum implements IEnum {

	NOATTEND(2, "未参与签到");
	private int key;
	private String value;

	private SignInStatus2Enum(final int key, final String value) {
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
