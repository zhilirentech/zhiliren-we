package com.xiaoka.game.wap.activity.entity;

import java.util.List;

import lombok.Data;

import com.xiaoka.game.wap.home.entity.SchoolEntity;

@Data
public class ActiviEntity {
	
	private long id;//id主键
	
	private boolean current;//是否是当前城市
	
	private String cityName;//城市名称
	
	private int number;//城市编号
	
	private String cityOperateManager;//城市运营经理
	
	private String phone;//电话
	
	private double longitude;//城市纬度
	
	private double latitude;//城市纬度
	
	private String remark;//备注
	
	private List<SchoolEntity> schools;//当前城市下的所有学校

	public enum WeekDayEnum { Mon, Tue, Wed, Thu, Fri, Sat, Sun }
	
}
