/**
 * 
 */
package com.xiaoka.game.admin.pkrecord.entity;

import java.sql.Date;
import java.sql.Timestamp;

import lombok.Data;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Comment;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Table;

import com.google.gson.annotations.Expose;

/**
 * @author ln
 *
 */
@Table("up_sign_in")
@Data
@Comment
public class SignInEntity {
	@Column
	@Id(auto = true)
	@Comment("主键")
	private long id;
	
	@Column
	@Comment("签到人openId")
	private String openId; 
	
	@Column
	@Comment("签到投入金额")
	private double inAmount;
	
	@Column
	@Comment("分成金额")
	private double outAmount;
	
	@Expose//使用Gson时，使用此注解的字段将被打成josn包
	@Column()
	@Comment("签到日期")
	private Date signDate;
	
	@Column()
	@Comment("签到时间")
	private Timestamp signTime;
	
	@Expose
	@Column()
	@Comment("签到状态")
	private int status;
	
	@Column()
	@Comment("类型（0：全国,1:个人）")
	private int type;
	
	@Column
	@Comment("外键(游戏表主键)")
	private long gameId;
	
	@Column()
	@Comment("创建时间")
	private Timestamp createTime;
}
