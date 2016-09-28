package com.xiaoka.game.wap.InstalMessge.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Comment;
import org.nutz.dao.entity.annotation.Table;

import lombok.Data;

@Data
@Table("site_message_read")
@Comment
public class SiteMessageReadEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Column
	@Comment("id")
	private long id;

	@Column
	@Comment("用户id")
	private String openId;

	@Column
	@Comment("信息id")
	private int messageId;
	
	@Column
	@Comment("读取时间")
	private Timestamp readTime;
	
}
