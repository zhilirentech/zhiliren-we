package com.xiaoka.game.common.enums;

import com.uxuexi.core.common.enums.IEnum;

/**
 * 用户状态
 * 0-未激活，1-激活，2-冻结
 *
 */
public enum IsDelEnum implements IEnum {

	NO(0, "未删除"),IS(1, "已删除");
	private int key;
	private String value;

	private IsDelEnum(final int key, final String value) {
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
