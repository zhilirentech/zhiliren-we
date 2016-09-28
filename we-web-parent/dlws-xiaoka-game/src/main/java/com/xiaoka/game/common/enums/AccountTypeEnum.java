package com.xiaoka.game.common.enums;

import com.uxuexi.core.common.enums.IEnum;

/**
 * 用户状态
 * 0-未激活，1-激活，2-冻结
 *
 */
public enum AccountTypeEnum implements IEnum {

	RECHARGE(1, "充值"),WITHDRAW(2, "提现"),PUTIN(3, "投入"),BACK(4, "返现"),DIVIDED(5, "分成"),REFUND(6,"退款");
	private int key;
	private String value;

	private AccountTypeEnum(final int key, final String value) {
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
