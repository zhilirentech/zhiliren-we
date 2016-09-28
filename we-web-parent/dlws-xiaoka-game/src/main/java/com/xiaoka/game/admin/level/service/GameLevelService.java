package com.xiaoka.game.admin.level.service;

import org.nutz.dao.pager.Pager;

import com.xiaoka.game.admin.level.form.GameLevelSqlForm;

public interface GameLevelService {

	/**
	 * 
	 * 项目名称：dlws-xiaoka-game
	 * 描述：游戏分页查询
	 * 创建人： ln
	 * 创建时间： 2016年8月11日
	 * 标记：
	 * @param queryForm 查询表单
	 * @param pager 	分页对象
	 * @return
	 * @version
	 */
	Object queryGameLevelList(GameLevelSqlForm sqlForm, Pager pager);

}
