package com.linyun.airline.entities;

import org.nutz.dao.entity.annotation.*;
import lombok.Data;
import java.util.Date;

import java.io.Serializable;


@Data
@Table("app_version")
public class AppVersionEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id(auto = true)
	private Long id;
	
	@Column
    @Comment("APP类型")
	private Integer appType;
	
	@Column
    @Comment("版本号")
	private String version;
	
	@Column
    @Comment("版本名称")
	private String versionName;
	
	@Column
    @Comment("更新内容")
	private String content;
	
	@Column
    @Comment("是否强制升级")
	private Integer isForced;
	
	@Column
    @Comment("更新时间")
	private Date updateTime;
	
	@Column
    @Comment("下载地址")
	private String downloadUrl;
	
	@Column
    @Comment("每天提示次数")
	private Integer count;
	

}