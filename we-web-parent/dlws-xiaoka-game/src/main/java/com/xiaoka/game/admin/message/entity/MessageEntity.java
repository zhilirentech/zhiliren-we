package com.xiaoka.game.admin.message.entity;

import java.io.Serializable;
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
@Table("up_message")
@Comment
public class MessageEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column
	@Id(auto = true)
	@Comment("主键")
	private long id;

	@Column
	@Comment("title")
	private String title;

	@Column
	@Comment("content")
	private String content;
	
	@Column
	@Comment("openId")
	private String openId;

	@Column
	@Comment("状态")
	private Integer status;

	@Column
	@Comment("创建时间")
	private Timestamp createTime;

	@Column
	@Comment("是否删除")
	private Integer isDelete;

}
