package com.xiaoka.game.wap.pay.entity;

import org.nutz.dao.entity.annotation.Column;

import lombok.Data;

@Data
public class PayEntity {
	@Column
	private String orderNo; 
}
