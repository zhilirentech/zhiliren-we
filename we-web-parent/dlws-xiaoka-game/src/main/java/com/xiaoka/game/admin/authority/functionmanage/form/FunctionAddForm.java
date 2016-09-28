package com.xiaoka.game.admin.authority.functionmanage.form;

import java.sql.Timestamp;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.uxuexi.core.web.form.AddForm;

@Data
@EqualsAndHashCode(callSuper = true)
public class FunctionAddForm extends AddForm{
	
	/**父功能id*/
	private long parentId = 0 ;
	
	/**功能名称*/
	private String name ;
	
	/**访问地址*/
	private String url;
	
	/**功能等级*/
	private Integer level ;
	
	/**备注*/
	private String remark;
	
	/**创建时间*/
	private Timestamp createTime;
	
	/**修改时间*/
	private Timestamp updateTime;
	
	/**序号*/
	private Integer sort ;
	
}
