/**
 * FunctionEntity.java
 * com.xiaoka.web.authority.entity
 * Copyright (c) 2014, 北京世纪新干线科技有限公司版权所有.
*/
package com.xiaoka.template.admin.authority.functionmanage.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import lombok.Data;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Comment;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Table;

/**
 * 功能实体
 * @author   朱晓川
 * @Date	 2014-10-21	 
 */
@Data
@Table("s_function")
@Comment
public class FunctionEntity  implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L; 

	@Column
	@Id(auto = true)
	@Comment("主键")
	private long id;
	
	@Column
	@Comment("父功能id")
	private long parentId ;
	
	@Column
	@Comment("功能名称")
	private String name ;
	
	@Column
	@Comment("访问地址")
	private String url;
	
	@Column
	@Comment("功能等级")
	private Integer level ;
	
	@Column
	@Comment("备注")
	private String remark;
	
	@Column()
	@Comment("创建时间")
	private Timestamp createTime;
	
	@Column()
	@Comment("修改时间")
	private Timestamp updateTime;
	
	@Column()
	@Comment("序号")
	private Integer sort ;
	
	/**在树形节点中是否选中*/
	private String checked = "false" ;
}
