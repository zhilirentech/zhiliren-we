package com.xiaoka.game.wap.activity.dto;

import java.io.Serializable;

import lombok.Data;

@Data
public class ActiviDto implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private int id;
	
	/*活动标题*/
	private String title;
	
	/*封面图*/
	private String cover;
	
}
