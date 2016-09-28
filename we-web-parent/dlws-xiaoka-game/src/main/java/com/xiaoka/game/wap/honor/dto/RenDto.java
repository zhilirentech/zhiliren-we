package com.xiaoka.game.wap.honor.dto;

import java.io.Serializable;

import lombok.Data;

@Data
public class RenDto implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private int id;
	
	/*微信名*/
	private String wxname;
	
	/*微信头像*/
	private String headImgUrl;
	
	/*学校名*/
	private String schoolName;
	
	/*最大签到天数*/
	private int signDays;
	
	
}
