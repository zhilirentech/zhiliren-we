package com.xiaoka.game.admin.message.form;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.uxuexi.core.web.form.ModForm;

@Data
@EqualsAndHashCode(callSuper = true)
public class MessageStatusForm extends ModForm {

	/*推送状态 */
	private int status;



}
