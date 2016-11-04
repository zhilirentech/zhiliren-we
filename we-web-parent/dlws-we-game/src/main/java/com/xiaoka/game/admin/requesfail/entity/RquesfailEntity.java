
package com.xiaoka.game.admin.requesfail.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import lombok.Data;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Comment;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Table;

/**
 * 功能实体
 * @author  董锡福
 * @Date	 2016-08-12	 
 */
@Data
@Table("up_game")
@Comment
public class RquesfailEntity  implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L; 

	@Column
	@Id(auto = true)
	@Comment("主键")
	private long id;
	
	@Column
	@Comment("游戏名称（预留字段）")
	private String name ;
	
	@Column
	@Comment("活动日期")
	private Timestamp date ;
	
	@Column
	@Comment("活动开始时间")
	private Timestamp beginTime;
	
	@Column
	@Comment("活动结束时间")
	private Timestamp endTime ;
	
	@Column
	@Comment("活动金额")
	private double money;
	
	@Column()
	@Comment("活动状态（0:未开始，1：正在进行，2：已结束）")
	private int status;
	
	@Column()
	@Comment("创建人openId")
	private String founder;
	
	@Column()
	@Comment("创建时间")
	private Timestamp createTime ;
}
