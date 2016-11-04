
package com.xiaoka.game.wap.game.form;

import java.sql.Date;
import java.sql.Timestamp;

import com.uxuexi.core.common.util.DateTimeUtil;
import com.uxuexi.core.web.form.AddForm;
import com.xiaoka.game.common.enums.GameStatusEnum;

/**
 */
public class GameAddForm  extends AddForm{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L; 

	
	/**游戏名称（预留字段）*/
	private String name ;
	
	/**活动日期*/
	private Date date = DateTimeUtil.tomorrowDate();
	
	/**活动开始时间*/
	private Timestamp beginTime;
	
	/**活动开始时间*/
	private String beginTimeStr;
	
	/**活动结束时间*/
	private Timestamp endTime ;
	
	/**活动结束时间*/
	private String endTimeStr ;
	
	/**创建人openId*/
	private String founder ;
	
	/**活动金额*/
	private double money;
	
	/**游戏状态（0:失败，1：成功，2：正在进行）*/
	private int status = GameStatusEnum.UNDERWAY.intKey();
	
	/**创建时间*/
	private Timestamp createTime = DateTimeUtil.nowDateTime();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Timestamp getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(Timestamp beginTime) {
		this.beginTime = beginTime;
	}

	public Timestamp getEndTime() {
		return endTime;
	}

	public void setEndTime(Timestamp endTime) {
		this.endTime = endTime;
	}

	public double getMoney() {
		return money;
	}

	public void setMoney(double money) {
		this.money = money;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public String getBeginTimeStr() {
		return beginTimeStr;
	}

	public void setBeginTimeStr(String beginTimeStr) {
		String tomttowDate = date.toString() ;
		String timeStr = tomttowDate + " " + beginTimeStr + ":00" ;
		Timestamp time = DateTimeUtil.string2Time(timeStr,null);
		this.beginTime = time ;
		this.beginTimeStr = beginTimeStr;
	}

	public String getEndTimeStr() {
		return endTimeStr;
	}

	public void setEndTimeStr(String endTimeStr) {
		String tomttowDate = date.toString() ;
		String timeStr = tomttowDate + " " + endTimeStr + ":00" ;
		Timestamp time = DateTimeUtil.string2Time(timeStr,null);
		this.endTime = time ;
		this.endTimeStr = endTimeStr;
	}

	public String getFounder() {
		return founder;
	}

	public void setFounder(String founder) {
		this.founder = founder;
	}
	
	
}
