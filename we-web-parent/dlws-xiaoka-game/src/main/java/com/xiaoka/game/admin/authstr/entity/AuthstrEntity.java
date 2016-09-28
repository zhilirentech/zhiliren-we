package com.xiaoka.game.admin.authstr.entity;





import java.sql.Timestamp;

import lombok.Data;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Comment;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Table;

import com.xiaoka.game.admin.authority.rolemanage.entity.RoleEntity;

/**
 * 未提现实体
 * @author Administrator
 *
 */
@Table("up_withdraw_application")
@Data
@Comment
public class AuthstrEntity {
	
		@Column
		@Id(auto = true)
		@Comment("主键")
		private long id;
		
		@Column
		@Comment("用户openId")
		private String openId;
		
		@Column
		@Comment("提现金额")
		private double money;
		
		@Column()
		@Comment("审核状态 0：未审核，1：通过，2：不通过")
		private Integer checkStatus;
		
		@Column()
		@Comment("审核信息")
		private String checkInfo;

	}


