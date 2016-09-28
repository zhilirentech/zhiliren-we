package com.xiaoka.game.admin.checkFinance.entity;

import java.sql.Timestamp;

import lombok.Data;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Comment;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Table;

/**
 * 提现审核实体
 * @author ln
 *
 */
@Table("up_withdraw_application")
@Data
@Comment
public class FinanceEntity {
	
	@Column
	@Id(auto = true)
	@Comment("主键")
	private long id;
	
	@Column
	@Comment("提现用户openId")
	private String openId;
	
	@Column
	@Comment("提现金额")
	private double money;
	
	@Column()
	@Comment("审核状态")
	private int checkStatus;
	
	@Column()
	@Comment("审核信息")
	private String checkInfo;
	
}
