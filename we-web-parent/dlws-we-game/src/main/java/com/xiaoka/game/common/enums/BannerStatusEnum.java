package com.xiaoka.game.common.enums;

import com.uxuexi.core.common.enums.IEnum;

/**
 *
 */
public enum BannerStatusEnum implements IEnum {

	CLOSE(0, "关闭"),OPEN(1, "启用");
	private int key;
	private String value;

	private BannerStatusEnum(final int key, final String value) {
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
