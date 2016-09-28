package com.xiaoka.game.wap.home.entity;

import java.sql.Timestamp;

import lombok.Data;

import org.nutz.dao.entity.annotation.Comment;
import org.nutz.dao.entity.annotation.Table;

import com.xiaoka.game.admin.pkrecord.entity.GameEntity;

/**
 * 客户实体
 * @author ln
 *
 */
@Table("`xiaoka`.v2_wx_customer")
@Data
public class CustomerEntity {
	
	private String openId;
	
	private String wxname;
	
	private String schoolId;
	
	private String headImgUrl;
	
	private long cityId;
	
	
}
