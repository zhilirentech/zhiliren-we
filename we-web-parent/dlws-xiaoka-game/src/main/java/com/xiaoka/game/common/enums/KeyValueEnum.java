package com.xiaoka.game.common.enums;

import com.uxuexi.core.common.enums.IEnum;
import com.xiaoka.game.common.constants.CommonConstants;

/**
 *
 */
public enum KeyValueEnum implements IEnum {

	SIGNINTIME(1,"签到时间");
	private int key;
	private String value;

	private KeyValueEnum(final int key, final String value) {
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
