package com.xiaoka.game.wap.center.entity;

import lombok.Data;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Comment;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Table;

@Data
@Table("up_withdraw_application")
@Comment
public class WithdrawEntity {
	@Column
	@Id(auto = true)
	private long  id;
	@Column
	private String openId;
	@Column
	private double money;
	@Column
	private int checkStatus;
	@Column
	private String checkInfo;
}
