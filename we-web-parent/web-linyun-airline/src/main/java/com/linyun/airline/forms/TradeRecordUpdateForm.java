package com.linyun.airline.forms;

import lombok.Data;
import lombok.EqualsAndHashCode;
import com.uxuexi.core.web.form.ModForm;
import java.util.Date;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = true)
public class TradeRecordUpdateForm extends ModForm implements Serializable{
	private static final long serialVersionUID = 1L;
		
	/**单品id*/
	private Integer itemId;
		
	/**是否交易当前单品*/
	private Integer isCurrentItem;
		
	/**平台商品id*/
	private String platformItemId;
		
	/**用户id*/
	private Integer userId;
		
	/**交易平台类型*/
	private Integer tradePlatform;
		
	/**平台用户*/
	private String platformUser;
		
	/**平台交易号*/
	private String tradeNo;
		
	/**支付结果*/
	private Integer payStatus;
		
	/**支付时间*/
	private Date payTime;
		
}