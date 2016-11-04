package com.xiaoka.game.admin.signin.enums;

import com.uxuexi.core.common.enums.IEnum;



public enum SiginType implements IEnum {

	World(0, "全国"),PerSon(1, "个人");
	
	private int key;
	private String value;

	private SiginType(final int key, final String value) {
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
