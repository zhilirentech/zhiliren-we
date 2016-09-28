package com.xiaoka.game.wap.InstalMessge.form;

import java.sql.Timestamp;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.uxuexi.core.web.form.AddForm;

@Data
@EqualsAndHashCode(callSuper = true)
public class SiteMessageReadAddForm extends AddForm{
	
	private static final long serialVersionUID = 1L;
	
	/*用户id*/
	private String openId ;
	
	/*信息id*/
	private int messageId ;
	
	/*读取时间*/
	private Timestamp readTime ;

}
