package com.xiaoka.game.admin.activity.enums;

import com.uxuexi.core.common.enums.IEnum;



public enum IsDeleteEnum implements IEnum {

	isDel(0, "已删除"),PLAY(1, "未删除");
	
	private int key;
	private String value;

	private IsDeleteEnum(final int key, final String value) {
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
