package com.xiaoka.game.admin.message.form;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.uxuexi.core.web.form.ModForm;

@Data
@EqualsAndHashCode(callSuper = true)
public class MessageDeleteModForm extends ModForm {

	/* 是否删除 */
	private int isDelete;

}
