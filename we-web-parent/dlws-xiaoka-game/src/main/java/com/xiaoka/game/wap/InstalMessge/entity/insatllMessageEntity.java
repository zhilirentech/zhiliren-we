package com.xiaoka.game.wap.InstalMessge.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Comment;
import org.nutz.dao.entity.annotation.Table;

import lombok.Data;

@Data
@Table("up_message")
@Comment
public class insatllMessageEntity implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column
	@Comment("id")
	private long id;
	
	@Column
	@Comment("信息标题")
	private String title;
	
	@Column
	@Comment("信息内容")
	private String content;
	
	
	@Column
	@Comment("0未发送;1已发送")
	private String status;
	
	@Column
	@Comment("发布时间")
	private Timestamp createTime;
	
	@Column
	@Comment("是否删除")
	private Timestamp isDelete;
}
