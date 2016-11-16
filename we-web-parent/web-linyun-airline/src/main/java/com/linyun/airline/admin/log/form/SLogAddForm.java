package com.linyun.airline.admin.log.form;

import java.sql.Timestamp;

import lombok.Data;
import lombok.EqualsAndHashCode;
import com.uxuexi.core.common.util.DateTimeUtil;
import com.uxuexi.core.web.form.AddForm;

@Data
@EqualsAndHashCode(callSuper = true)
public class SLogAddForm extends AddForm {
	
	/**
	 * 操作员id
	 */
	private long operatorId ;
	
	/**
	 * 功能id
	 */
	private long functionId ;
	
	/**
	 * 访问路径
	 */
	private String path ;
	
	/**
	 * 操作员名称
	 */
	private String operatorName ;
	
	/**
	 * 功能名称
	 */
	private String functionName ;
	
	/**
	 * IP地址
	 */
	private String ip ;
	
	/**
	 * 操作时间
	 */
	private Timestamp operatorTime = DateTimeUtil.nowDateTime();
	
}
