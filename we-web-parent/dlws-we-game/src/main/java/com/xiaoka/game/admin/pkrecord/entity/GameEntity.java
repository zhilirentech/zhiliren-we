package com.xiaoka.game.admin.pkrecord.entity;

import java.sql.Date;
import java.sql.Timestamp;

import lombok.Data;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Comment;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Table;

/**
 * 游戏实体
 * @author ln
 *
 */
@Table("up_game")
@Data
@Comment
public class GameEntity {
	
	@Column
	@Id(auto = true)
	@Comment("主键")
	private long id;
	
	@Column
	@Comment("游戏名称（预留）")
	private String name;
	
	@Column
	@Comment("活动日期")
	private Date date;
	
	@Column()
	@Comment("活动开始时间")
	private Timestamp beginTime;
	
	@Column()
	@Comment("活动结束时间")
	private Timestamp endTime;
	
	@Column()
	@Comment("活动金额")
	private double money;
	
	@Column()
	@Comment("活动状态")
	private int status; 
	
	@Column
	@Comment("创建人openId")
	private String founder;
	
	@Column()
	@Comment("活动结束时间")
	private Timestamp createTime;

}
