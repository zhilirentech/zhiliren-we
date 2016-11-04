package com.xiaoka.game.admin.message.form;

import java.sql.Timestamp;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.uxuexi.core.web.form.AddForm;

@Data
@EqualsAndHashCode(callSuper = true)
public class MessageAddForm extends AddForm {

	/* 活动标题 */
	private String title;

	/* 内容 */
	private String content;

	/* 状态（0：未发送，1：已发送） */
	private int status;

	/* 创建时间 */
	private Timestamp createTime;

	/* 是否删除 */
	private int isDelete;

}
