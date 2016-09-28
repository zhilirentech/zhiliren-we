package com.xiaoka.game.common.enums;

import com.uxuexi.core.common.enums.IEnum;

/**
 *
 */
public enum BannerTypeEnum implements IEnum {

	ACTIVITY(1, "活动"),RANKING(0, "排行"),RECHARGE(2, "充值"),;
	private int key;
	private String value;

	private BannerTypeEnum(final int key, final String value) {
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
