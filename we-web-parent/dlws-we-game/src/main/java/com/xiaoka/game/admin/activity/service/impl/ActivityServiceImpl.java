package com.xiaoka.game.admin.activity.service.impl;

import org.nutz.dao.Cnd;
import org.nutz.ioc.loader.annotation.IocBean;

import com.xiaoka.game.admin.activity.service.ActivityService;
import com.xiaoka.game.admin.authority.functionmanage.entity.FunctionEntity;
import com.xiaoka.game.common.service.BaseService;

@IocBean(name="activityService")
public class ActivityServiceImpl extends BaseService implements ActivityService {

	@Override
	public FunctionEntity findFuctionByRequestPath(String requestPath) { 
		return dbDao.fetch(FunctionEntity.class, Cnd.where("url", "LIKE", "%" + requestPath + "%"));
	}
	

}
