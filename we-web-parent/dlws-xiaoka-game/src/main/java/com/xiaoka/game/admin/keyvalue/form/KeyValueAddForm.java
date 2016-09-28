package com.xiaoka.game.admin.keyvalue.form;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.uxuexi.core.common.util.StringUtil;
import com.uxuexi.core.common.util.Util;
import com.uxuexi.core.web.form.AddForm;
import com.xiaoka.game.common.enums.IsDelEnum;

@EqualsAndHashCode(callSuper = true)
public class KeyValueAddForm extends AddForm {

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
	/**
	 * 是否删除
	 */
	private Integer isDel=IsDelEnum.NO.intKey();
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
	public Integer getIsDel() {
		return isDel;
	}
	public void setIsDel(Integer isDel) {
		this.isDel = isDel;
	}
	

}
