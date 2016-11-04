/**
 * FunctionEntity.java
 * com.xiaoka.web.authority.entity
 * Copyright (c) 2014, 北京世纪新干线科技有限公司版权所有.
*/
package com.xiaoka.game.admin.signin.entity;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

import lombok.Data;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Comment;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Table;

/**
 * 功能实体
 * @author  董锡福
 * @Date	 2016-08-11 
 */
@Data
@Table("up_sign_in")
@Comment
public class SignInEntity  implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L; 

	@Column
	@Id(auto = true)
	@Comment("主键")
	private long id;
	
	@Column
	@Comment("微信用户id")
	private String openId ;
	
	@Column
	@Comment("签到投入金额")
	private double inAmount ;
	
	@Column
	@Comment("分成金额")
	private double outAmount;
	
	@Column
	@Comment("签到日期")
	private Date signDate ;
	
	@Column
	@Comment("签到时间")
	private Timestamp signTime ;
	
	@Column
	@Comment(" 签到状态（0：未签到，1：已签到）")
	private int status;
	
	@Column()
	@Comment("类型（0：全国，1：个人）")
	private int type;
	
	@Column()
	@Comment("游戏表主键")
	private int gameId;
	
	@Column()
	@Comment("分成状态")
	private Integer dividedStatus;
	
	@Column()
	@Comment("创建时间")
	private Timestamp createTime;
	
}
