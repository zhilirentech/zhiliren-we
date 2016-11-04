package com.xiaoka.game.admin.activity.form;

import java.sql.Timestamp;
import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.uxuexi.core.web.form.AddForm;

@Data
@EqualsAndHashCode(callSuper = true)
public class ActivityAddForm extends AddForm{
	
	/*活动标题*/
	private String title ;
	
	/*内容*/
	private String content ;
	
	/*活动封面（图片的url）*/
	private String cover;
	
	/*活动状态（0：未开始，1：正在进行，2：已结束）*/
	private int status ;
	
	/*创建人*/
	private String founder;
	
	/*活动开始时间*/
	private Timestamp beginTime;
	
	/*活动结束时间*/
	private Timestamp endTime;
	
	/*创建时间*/
	private Timestamp createTime ;
	
	/*是否删除*/
	private int isDel ;
	
}
