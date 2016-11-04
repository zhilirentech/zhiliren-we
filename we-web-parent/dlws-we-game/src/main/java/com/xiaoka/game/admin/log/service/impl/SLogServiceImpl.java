package com.xiaoka.game.admin.log.service.impl;

import org.nutz.ioc.loader.annotation.IocBean;

import com.xiaoka.game.admin.authority.functionmanage.entity.FunctionEntity;
import com.xiaoka.game.admin.authority.usermanage.entity.UserEntity;
import com.xiaoka.game.admin.log.entity.SLogEntity;
import com.xiaoka.game.admin.log.form.SLogAddForm;
import com.xiaoka.game.admin.log.service.SLogService;
import com.xiaoka.game.common.service.BaseService;
import com.uxuexi.core.web.util.FormUtil;

@IocBean(name="sLogService")
public class SLogServiceImpl extends BaseService implements SLogService {

	@Override
	public boolean addSyslog(SLogAddForm addForm) {
		FormUtil.add(dbDao, addForm, SLogEntity.class);
		return true ;
	}

	@Override
	public boolean addSyslog(FunctionEntity function, UserEntity user,String ip) {
		SLogAddForm addForm = new SLogAddForm() ;
		addForm.setFunctionId(function.getId()) ;
		addForm.setFunctionName(function.getName()) ;
		addForm.setPath(function.getUrl()) ;
		
		addForm.setIp(ip) ;
		
		addForm.setOperatorId(user.getId()) ;
		addForm.setOperatorName(user.getUsername()) ;
		return addSyslog(addForm); 
	}

}
