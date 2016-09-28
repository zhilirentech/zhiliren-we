package com.xiaoka.game.admin.account.entity;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

import lombok.Data;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Comment;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Table;

/**
 * 
 * @author xuxin
 *
 */
@Data
@Table("up_account")
@Comment
public class AccountEntity  implements Serializable{
	
	
	private static final long serialVersionUID = 1L; 

	@Column
	@Id(auto = true)
	@Comment("主键")
	private long id;
	
	@Column
	@Comment("机器人openId")
	private String openId ;
	
	@Column
	@Comment("出/入金额")
	private Double money ;
	
	@Column
	@Comment("结余金额")
	private Double balance;
	
	@Column
	@Comment("平台（属于哪个app）")
	private Integer platType;
	
	@Column
	@Comment("类型")
	private Integer type;
	
	@Column
	@Comment("创建时间")
	private Timestamp createTime;
	
	@Column
	@Comment("备注")
	private String remark ;

}
