package com.xiaoka.game.admin.checkFinance.po;

import java.sql.Timestamp;

import lombok.Data;

/**
 *用户当前账户余额实体
 * @author ln
 *
 */
@Data
public class UserBalance {
	
	private long id;
	
	private String openId;
	
	private double money;
	
	private double balance;
	
	private int platType ;
	
	private int type;
	
	private Timestamp createTime;
	
	private String remak;
}
