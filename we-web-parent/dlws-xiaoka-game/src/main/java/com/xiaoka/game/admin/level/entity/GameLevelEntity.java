package com.xiaoka.game.admin.level.entity;

import java.io.Serializable;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Comment;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Table;

import lombok.Data;

@Data
@Table("up_game_level")
@Comment("公告")
public class GameLevelEntity implements Serializable{

	private static final long serialVersionUID = 1L;

	@Column
	@Id(auto = true)
	@Comment("主键")
	private long id;
	
	@Column()
	@Comment("等级名称")
	private String levelName;
	
	@Column()
	@Comment("达到该等级所需要的数量")
	private int levelNo;
	
	@Column()
	@Comment("灰色图标url地址")
	private String grayIcon;
	
	@Column()
	@Comment("亮色图标url地址")
	private String brightIcon;
	
	@Column()
	@Comment("是否删除")
	private Integer isDel;
	
}
