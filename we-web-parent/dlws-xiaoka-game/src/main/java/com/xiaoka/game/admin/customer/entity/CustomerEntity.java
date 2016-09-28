
package com.xiaoka.game.admin.customer.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import lombok.Data;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Comment;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Table;

/**
 * 功能实体
 * @author  董锡福
 * @Date	 2016-08-12	 
 */
@Data
@Table("up_customer_info")
@Comment
public class CustomerEntity  implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L; 

	@Column
	@Id(auto = true)
	@Comment("主键")
	private long id;
	
	@Column
	@Comment("用户openId")
	private String openId;
	
	@Column
	@Comment("客户名称")
	private String customerName;
}
