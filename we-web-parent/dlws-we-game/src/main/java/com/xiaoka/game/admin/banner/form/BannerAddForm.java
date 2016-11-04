package com.xiaoka.game.admin.banner.form;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.uxuexi.core.common.util.StringUtil;
import com.uxuexi.core.web.form.AddForm;
import com.xiaoka.game.common.enums.IsDelEnum;

@Data
@EqualsAndHashCode(callSuper = true)
public class BannerAddForm extends AddForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 标题
	 */
	private String title;
	/**
	 * 图片地址
	 */
	private String imgUrl;
	
	/**
	 * 类型
	 */
	private int type;

	/**
	 * 状态
	 */
	private Integer status;


	/**
	 * 序号
	 */
	private Integer sort;
	/**
	 * 是否删除
	 */
	private Integer isDel=IsDelEnum.NO.intKey();


}
