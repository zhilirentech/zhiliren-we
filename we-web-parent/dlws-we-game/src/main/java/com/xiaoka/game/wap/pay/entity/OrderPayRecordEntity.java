/**
 * MerchantEntity.java
 * cn.ccig.web.merchant.entity
 * Copyright (c) 2015, 北京快先生信息技术有限责任公司版权所有.
*/

package com.xiaoka.game.wap.pay.entity;

import lombok.Data;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Comment;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Table;

@Data
@Table("order_pay_record")
@Comment("订单支付记录")
public class OrderPayRecordEntity {

	@Column
	@Id(auto = true)
	@Comment("主键")
	private long id;

	@Column
	@Comment("订单id")
	private Integer orderId;

	@Column
	@Comment("订单号")
	private String orderNo;

	@Column
	@Comment("支付类型")
	private Integer payType;

	@Column
	@Comment("支付结果")
	private Integer payStatus;

	@Column
	@Comment("支付完成时间")
	private String payTime;

	@Column
	@Comment("平台交易号")
	private String tradeNo;

}
