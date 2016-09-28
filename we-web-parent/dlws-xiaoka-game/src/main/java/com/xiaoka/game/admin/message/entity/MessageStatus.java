package com.xiaoka.game.admin.message.entity;

import java.io.Serializable;

import lombok.Data;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Comment;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Table;


@Data
@Table("up_message")
@Comment
public class MessageStatus implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column
	@Id(auto = true)
	@Comment("主键")
	private long id;


	@Column
	@Comment("状态")
	private Integer status;
}
