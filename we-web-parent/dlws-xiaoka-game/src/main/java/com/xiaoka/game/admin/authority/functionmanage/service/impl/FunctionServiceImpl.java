package com.xiaoka.game.admin.authority.functionmanage.service.impl;

import org.nutz.dao.Cnd;
import org.nutz.ioc.loader.annotation.IocBean;

import com.xiaoka.game.admin.authority.functionmanage.entity.FunctionEntity;
import com.xiaoka.game.admin.authority.functionmanage.service.FunctionService;
import com.xiaoka.game.common.service.BaseService;

@IocBean(name="functionService")
public class FunctionServiceImpl extends BaseService implements FunctionService {

	@Override
	public FunctionEntity findFuctionByRequestPath(String requestPath) { 
		return dbDao.fetch(FunctionEntity.class, Cnd.where("url", "LIKE", "%" + requestPath + "%"));
	}
	

}
