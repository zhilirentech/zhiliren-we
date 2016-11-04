package com.xiaoka.game.wap.pay.form;

import java.io.Serializable;

import lombok.Data;

@Data
public class WxPayForm implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	/*订单金额*/
	private Double amount;
	
	
}
