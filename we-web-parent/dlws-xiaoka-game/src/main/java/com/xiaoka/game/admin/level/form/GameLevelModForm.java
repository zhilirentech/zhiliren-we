package com.xiaoka.game.admin.level.form;

import lombok.Data;

import com.uxuexi.core.web.form.ModForm;

@Data
public class GameLevelModForm extends ModForm {
	
	
	private String levelName;
	
	private int levelNo;
	
	private String grayIcon;
	
	private String brightIcon;
	

}
