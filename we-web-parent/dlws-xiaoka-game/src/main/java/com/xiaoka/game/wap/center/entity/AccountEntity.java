package com.xiaoka.game.wap.center.entity;

import java.sql.Timestamp;

import org.nutz.dao.entity.annotation.Table;

import lombok.Data;

/**
 * 账户记录实体
 * @author ln
 *
 */
@Table("up_account")
@Data
public class AccountEntity {

	private long id ;
	
	private String openId;
	
	private double money;
	
	private double balance;
	
	private int platType;
	
	private int type;
	
	private Timestamp createTime;
	
	private String remark;
	
}
