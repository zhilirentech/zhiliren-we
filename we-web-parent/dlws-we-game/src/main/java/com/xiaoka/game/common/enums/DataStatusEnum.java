package com.xiaoka.game.common.enums;

import com.uxuexi.core.common.enums.IEnum;

/**
 * 数据状态
 * @author 朱晓川
 *
 */
public enum DataStatusEnum implements IEnum{
	FREEZE(0, "已冻结"),ENABLE(1, "启用中"),DELETE(2,"已删除") ;
	private int key;
	private String value;

	private DataStatusEnum(final int key, final String value) {
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
