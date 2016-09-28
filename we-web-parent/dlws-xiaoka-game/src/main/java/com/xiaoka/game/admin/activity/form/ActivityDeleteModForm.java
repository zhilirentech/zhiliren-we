package com.xiaoka.game.admin.activity.form;

import java.sql.Timestamp;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.uxuexi.core.web.form.ModForm;

@Data
@EqualsAndHashCode(callSuper = true)
public class ActivityDeleteModForm extends ModForm{
	
	/*是否删除*/
	private int isDel ;
	
}
