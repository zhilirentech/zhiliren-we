package com.xiaoka.game.admin.signin.enums;

import com.uxuexi.core.common.enums.IEnum;



public enum SignStatus implements IEnum {

	Unsign(0, "未签到"),sign(1, "已签到");
	
	private int key;
	private String value;

	private SignStatus(final int key, final String value) {
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
