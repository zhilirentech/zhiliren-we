package com.xiaoka.game.common.enums;

import com.uxuexi.core.common.enums.IEnum;

/**
 * 用户状态
 * 0-未激活，1-激活，2-冻结
 *
 */
public enum PlatTypeEnum implements IEnum {

	XIAOKA(0, "校咖");
	private int key;
	private String value;

	private PlatTypeEnum(final int key, final String value) {
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
