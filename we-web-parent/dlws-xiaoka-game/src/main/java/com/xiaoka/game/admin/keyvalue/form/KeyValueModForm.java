package com.xiaoka.game.admin.keyvalue.form;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.uxuexi.core.common.util.StringUtil;
import com.uxuexi.core.common.util.Util;
import com.uxuexi.core.web.form.ModForm;


@EqualsAndHashCode(callSuper = true)
public class KeyValueModForm extends ModForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L; 

	/**
	 * 类型
	 */
	private String name;

	/**
	 * 内容
	 */
	private String nameVal;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNameVal() {
		return nameVal;
	}

	public void setNameVal(String nameVal) {
		if(!Util.isEmpty(nameVal)){
			nameVal = nameVal.replaceAll(",", "-");
		}
		this.nameVal = nameVal;
	}

	
}
