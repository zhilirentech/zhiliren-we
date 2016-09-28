package com.xiaoka.game.admin.level.form;

import com.uxuexi.core.web.form.AddForm;

import lombok.Data;

@Data
public class GameLevelForm extends AddForm {

	private String levelName;
	
	private int levelNo;
	
	private String grayIcon;
	
	private String brightIcon;
	
	private int isDel;	

}
