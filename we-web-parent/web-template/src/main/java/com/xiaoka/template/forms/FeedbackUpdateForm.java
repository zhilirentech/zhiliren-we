package com.xiaoka.template.forms;

import lombok.Data;
import lombok.EqualsAndHashCode;
import com.uxuexi.core.web.form.ModForm;
import java.util.Date;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = true)
public class FeedbackUpdateForm extends ModForm implements Serializable{
	private static final long serialVersionUID = 1L;
		
	/**联系方式*/
	private String contact;
		
	/**内容*/
	private String content;
		
	/**备注*/
	private String remark;
		
	/**反馈时间*/
	private Date time;
		
}