package com.xiaoka.game.admin.log.service;

import com.xiaoka.game.admin.authority.functionmanage.entity.FunctionEntity;
import com.xiaoka.game.admin.authority.usermanage.entity.UserEntity;
import com.xiaoka.game.admin.log.form.SLogAddForm;

public interface SLogService {
	
	/**添加系统日志*/
	public boolean addSyslog(SLogAddForm addForm) ;
	
	/**
	 * 添加系统日志
	 */
	public boolean addSyslog(FunctionEntity function, UserEntity user,String ip) ;
	
}
