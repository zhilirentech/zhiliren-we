package com.linyun.airline.entities;

import org.nutz.dao.entity.annotation.*;
import lombok.Data;
import java.util.Date;

import java.io.Serializable;


@Data
@Table("trade_record")
public class TradeRecordEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id(auto = true)
	private Long id;
	
	@Column
    @Comment("单品id")
	private Integer itemId;
	
	@Column
    @Comment("是否交易当前单品")
	private Integer isCurrentItem;
	
	@Column
    @Comment("平台商品id")
	private String platformItemId;
	
	@Column
    @Comment("用户id")
	private Integer userId;
	
	@Column
    @Comment("交易平台类型")
	private Integer tradePlatform;
	
	@Column
    @Comment("平台用户")
	private String platformUser;
	
	@Column
    @Comment("平台交易号")
	private String tradeNo;
	
	@Column
    @Comment("支付结果")
	private Integer payStatus;
	
	@Column
    @Comment("支付时间")
	private Date payTime;
	

}