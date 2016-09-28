package com.xiaoka.template.admin.log.entity;

import java.sql.Timestamp;

import lombok.Data;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Comment;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Table;

@Data
@Table("s_log")
@Comment("系统日志表")
public class SLogEntity {
	
	@Column
	@Id(auto = true)
	@Comment("主键")
	private long id;
	
	@Column
	@Comment("操作员id")
	private int operatorId ;
	
	@Column
	@Comment("功能id")
	private int functionId ;
	
	@Column
	@Comment("IP地址")
	private String ip ;
	
	@Column
	@Comment("访问路径")
	private String path ;
	
	@Column
	@Comment("操作员名称")
	private String operatorName ;
	
	@Column
	@Comment("功能名称")
	private String functionName ;
	
	@Column
	@Comment("操作时间")
	private Timestamp operatorTime ;

}
