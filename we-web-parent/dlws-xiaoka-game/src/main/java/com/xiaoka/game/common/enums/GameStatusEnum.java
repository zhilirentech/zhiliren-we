package com.xiaoka.game.common.enums;

import com.uxuexi.core.common.enums.IEnum;

/**
 *
 */
public enum GameStatusEnum implements IEnum {

	FAIL(0, "失败"),SUCCESS(1, "成功"),UNDERWAY(2, "正在进行");
	private int key;
	private String value;

	private GameStatusEnum(final int key, final String value) {
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
