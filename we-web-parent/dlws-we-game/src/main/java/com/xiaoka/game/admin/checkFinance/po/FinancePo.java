package com.xiaoka.game.admin.checkFinance.po;

import lombok.Data;
@Data
public class FinancePo {

	private long id;
	
	private double money;
	
	private int checkStatus;
	
	private String checkInfo;
	
	private String customerName;
	
	private String openId;
	
}
