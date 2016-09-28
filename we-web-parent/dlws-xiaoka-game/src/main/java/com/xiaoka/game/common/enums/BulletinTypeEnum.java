package com.xiaoka.game.common.enums;

import com.uxuexi.core.common.enums.IEnum;

/**
 *
 */
public enum BulletinTypeEnum implements IEnum {

	PLAY(0, "玩法介绍"),PK(1, "PK介绍"),GLOBAL(2, "全局公告");
	private int key;
	private String value;

	private BulletinTypeEnum(final int key, final String value) {
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
