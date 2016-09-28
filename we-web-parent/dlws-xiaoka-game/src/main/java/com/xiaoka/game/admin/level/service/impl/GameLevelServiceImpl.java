package com.xiaoka.game.admin.level.service.impl;

import org.nutz.dao.pager.Pager;
import org.nutz.ioc.loader.annotation.IocBean;

import com.uxuexi.core.web.util.FormUtil;
import com.xiaoka.game.admin.level.form.GameLevelSqlForm;
import com.xiaoka.game.admin.level.service.GameLevelService;
import com.xiaoka.game.common.service.BaseService;
@IocBean(name="gameLevelService")
public class GameLevelServiceImpl extends BaseService implements GameLevelService{

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
	public Object queryGameLevelList(GameLevelSqlForm sqlForm, Pager pager) {
		
		return FormUtil.query(dbDao, sqlManager, sqlForm, pager);
	}

}
