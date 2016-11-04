package com.xiaoka.game.admin.message.form;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.uxuexi.core.web.form.ModForm;

@Data
@EqualsAndHashCode(callSuper = true)
public class MessageModForm extends ModForm {

	/* 活动标题 */
	private String title;

	/* 内容 */
	private String content;


}
