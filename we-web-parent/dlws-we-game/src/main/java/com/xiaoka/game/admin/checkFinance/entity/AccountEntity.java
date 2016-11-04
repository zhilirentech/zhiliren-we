package com.xiaoka.game.admin.checkFinance.entity;

import java.sql.Timestamp;

import lombok.Data;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Comment;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Table;

/**
 * 用户账户实体
 * @author ln
 *
 */
@Table("up_account")
@Data
@Comment
public class AccountEntity {

	@Column
	@Id(auto = true)
	@Comment("主键")
	private long id;
	
	
	@Column
	@Comment("用户openId")
	private String openId;
	
	@Column
	@Comment("(出/入，金额)")
	private double money;
	
	@Column
	@Comment("结余金额")
	private double balance;
	
	
	@Column
	@Comment("平台")
	private int platType;
	
	@Column
	@Comment("类型（1：充值:2：提现:3：投入:4：签到返现:5：分成）")
	private int type;
	
	@Column
	@Comment("创建时间")
	private Timestamp createTime;

	@Column
	@Comment("备注")
	private String remark;
	
	
	
}
