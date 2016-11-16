package com.linyun.airline.admin.authority.functionmanage.service.impl;

import org.nutz.dao.Cnd;
import org.nutz.ioc.loader.annotation.IocBean;

import com.linyun.airline.admin.authority.functionmanage.entity.FunctionEntity;
import com.linyun.airline.admin.authority.functionmanage.service.FunctionService;
import com.uxuexi.core.web.base.service.BaseService;

@IocBean(name = "functionService")
public class FunctionServiceImpl extends BaseService<FunctionEntity> implements FunctionService {

	@Override
	public FunctionEntity findFuctionByRequestPath(String requestPath) {
		return dbDao.fetch(FunctionEntity.class, Cnd.where("url", "LIKE", "%" + requestPath + "%"));
	}

}
