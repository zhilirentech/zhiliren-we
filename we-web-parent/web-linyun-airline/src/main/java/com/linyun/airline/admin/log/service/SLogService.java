package com.linyun.airline.admin.log.service;

import com.linyun.airline.admin.authority.functionmanage.entity.FunctionEntity;
import com.linyun.airline.admin.authority.usermanage.entity.UserEntity;
import com.linyun.airline.admin.log.form.SLogAddForm;

public interface SLogService {
	
	/**添加系统日志*/
	public boolean addSyslog(SLogAddForm addForm) ;
	
	/**
	 * 添加系统日志
	 */
	public boolean addSyslog(FunctionEntity function, UserEntity user,String ip) ;
	
}
