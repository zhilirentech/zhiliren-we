package com.xiaoka.template.admin.log.service;

import com.xiaoka.template.admin.authority.functionmanage.entity.FunctionEntity;
import com.xiaoka.template.admin.authority.usermanage.entity.UserEntity;
import com.xiaoka.template.admin.log.form.SLogAddForm;

public interface SLogService {
	
	/**添加系统日志*/
	public boolean addSyslog(SLogAddForm addForm) ;
	
	/**
	 * 添加系统日志
	 */
	public boolean addSyslog(FunctionEntity function, UserEntity user,String ip) ;
	
}
