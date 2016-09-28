package com.xiaoka.game.wap.home.entity;

import java.sql.Timestamp;

import lombok.Data;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Comment;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Table;

import com.xiaoka.game.admin.pkrecord.entity.GameEntity;

/**
 * up系统中维护的客户信息实体
 * @author ln
 *
 */
@Table("up_customer_info")
@Data
public class UpCustomerEntity {
	@Column
	@Id(auto = true)
	@Comment("主键")
	private Long id;
	
	@Column
	private  String openId;
	
	@Column
	private String customerName;
	
}
