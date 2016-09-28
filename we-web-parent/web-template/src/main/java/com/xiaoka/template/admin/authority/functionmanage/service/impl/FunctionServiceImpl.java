package com.xiaoka.template.admin.authority.functionmanage.service.impl;

import org.nutz.dao.Cnd;
import org.nutz.ioc.loader.annotation.IocBean;

import com.uxuexi.core.web.base.service.BaseService;
import com.xiaoka.template.admin.authority.functionmanage.entity.FunctionEntity;
import com.xiaoka.template.admin.authority.functionmanage.service.FunctionService;

@IocBean(name = "functionService")
public class FunctionServiceImpl extends BaseService<FunctionEntity> implements FunctionService {

	@Override
	public FunctionEntity findFuctionByRequestPath(String requestPath) {
		return dbDao.fetch(FunctionEntity.class, Cnd.where("url", "LIKE", "%" + requestPath + "%"));
	}

}
