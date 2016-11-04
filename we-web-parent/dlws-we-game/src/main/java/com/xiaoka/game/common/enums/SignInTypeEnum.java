package com.xiaoka.game.common.enums;

import com.uxuexi.core.common.enums.IEnum;

/**
 *
 */
public enum SignInTypeEnum implements IEnum {

	NATIONWIDE(0, "全国"),PERSONAL(1, "个人");
	private int key;
	private String value;

	private SignInTypeEnum(final int key, final String value) {
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
