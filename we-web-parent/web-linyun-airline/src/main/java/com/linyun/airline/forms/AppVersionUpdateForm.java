package com.linyun.airline.forms;

import lombok.Data;
import lombok.EqualsAndHashCode;
import com.uxuexi.core.web.form.ModForm;
import java.util.Date;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = true)
public class AppVersionUpdateForm extends ModForm implements Serializable{
	private static final long serialVersionUID = 1L;
		
	/**APP类型*/
	private Integer appType;
		
	/**版本号*/
	private String version;
		
	/**版本名称*/
	private String versionName;
		
	/**更新内容*/
	private String content;
		
	/**是否强制升级*/
	private Integer isForced;
		
	/**更新时间*/
	private Date updateTime;
		
	/**下载地址*/
	private String downloadUrl;
		
	/**每天提示次数*/
	private Integer count;
		
}