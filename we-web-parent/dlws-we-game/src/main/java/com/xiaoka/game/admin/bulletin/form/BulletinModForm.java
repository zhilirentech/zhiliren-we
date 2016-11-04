package com.xiaoka.game.admin.bulletin.form;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.uxuexi.core.common.util.StringUtil;
import com.uxuexi.core.web.form.ModForm;


@EqualsAndHashCode(callSuper = true)
public class BulletinModForm extends ModForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L; 

	/**
	 * 类型
	 */
	private int type;

	/**
	 * 内容
	 */
	private String content;

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		//将/r/n替换为<br/>
		//content = content.replaceAll("/\n|\r\n/g", "<br />") ;
		this.content = content;
	}
	
	

}
