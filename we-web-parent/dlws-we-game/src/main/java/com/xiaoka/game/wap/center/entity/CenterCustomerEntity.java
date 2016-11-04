package com.xiaoka.game.wap.center.entity;

import java.sql.Timestamp;

import lombok.Data;

import org.nutz.dao.entity.annotation.Comment;
import org.nutz.dao.entity.annotation.Table;

import com.xiaoka.game.admin.pkrecord.entity.GameEntity;

/**
 * 个人中心信息存放
 * @author ln
 *
 */
@Data
public class CenterCustomerEntity {
	
	private String customerName;
	
	private String schoolName;
	
	private double balance;
	
	private String headImgUrl;
	
	
}
