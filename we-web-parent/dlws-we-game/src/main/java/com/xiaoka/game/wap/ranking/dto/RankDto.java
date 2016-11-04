package com.xiaoka.game.wap.ranking.dto;

import java.io.Serializable;

import lombok.Data;

@Data
public class RankDto implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private int id;
	
	/*参加up全国总人数*/
	private int  count;
	
	
}
