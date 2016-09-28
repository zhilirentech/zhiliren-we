package com.xiaoka.game.wap.honor.entity;

import java.io.Serializable;

import lombok.Data;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Comment;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Table;

/**
 * 功能实体
 * @author  董锡福
 * @Date	 2014-10-21	 
 */
@Data
@Table("up_game_level")
@Comment
public class HonorEntity  implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L; 

	@Column
	@Id(auto = true)
	@Comment("主键")
	private long id;
	
	@Column
	@Comment("达到该等级所需要的数量")
	private int levelNo ;
	
	@Column
	@Comment("灰色图标url地址")
	private String grayIcon ;
	
	@Column
	@Comment("亮色图标url地址")
	private String brightIcon;
	
	@Column
	@Comment("是否删除（0：已删除，1：未删除）")
	private int isDel ;
	
}
