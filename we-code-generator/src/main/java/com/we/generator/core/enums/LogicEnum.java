package com.we.generator.core.enums;

import com.uxuexi.core.common.enums.IEnum;

/**
 *
 */
public enum LogicEnum implements IEnum {

	ADMIN(1, "admin"), WEB(2, "web"), WAP(3, "wap"), APP(4, "app");
	private int key;
	private String value;

	private LogicEnum(final int key, final String value) {
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
