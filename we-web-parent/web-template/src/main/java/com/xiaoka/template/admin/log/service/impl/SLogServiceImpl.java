package com.xiaoka.template.admin.log.service.impl;

import org.nutz.ioc.loader.annotation.IocBean;

import com.uxuexi.core.web.base.service.BaseService;
import com.uxuexi.core.web.util.FormUtil;
import com.xiaoka.template.admin.authority.functionmanage.entity.FunctionEntity;
import com.xiaoka.template.admin.authority.usermanage.entity.UserEntity;
import com.xiaoka.template.admin.log.entity.SLogEntity;
import com.xiaoka.template.admin.log.form.SLogAddForm;
import com.xiaoka.template.admin.log.service.SLogService;

@IocBean(name = "sLogService")
public class SLogServiceImpl extends BaseService<SLogEntity> implements SLogService {

	@Override
	public boolean addSyslog(SLogAddForm addForm) {
		FormUtil.add(dbDao, addForm, SLogEntity.class);
		return true;
	}

	@Override
	public boolean addSyslog(FunctionEntity function, UserEntity user, String ip) {
		SLogAddForm addForm = new SLogAddForm();
		addForm.setFunctionId(function.getId());
		addForm.setFunctionName(function.getName());
		addForm.setPath(function.getUrl());

		addForm.setIp(ip);

		addForm.setOperatorId(user.getId());
		addForm.setOperatorName(user.getUsername());
		return addSyslog(addForm);
	}

}
