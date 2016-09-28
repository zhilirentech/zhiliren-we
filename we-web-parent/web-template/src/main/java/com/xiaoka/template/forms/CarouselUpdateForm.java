package com.xiaoka.template.forms;

import lombok.Data;
import lombok.EqualsAndHashCode;
import com.uxuexi.core.web.form.ModForm;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = true)
public class CarouselUpdateForm extends ModForm implements Serializable{
	private static final long serialVersionUID = 1L;
		
	/**图片地址*/
	private String url;
		
	/**外链地址*/
	private String link;
		
	/**描述*/
	private String description;
		
	/**序号*/
	private Integer sort;
		
}