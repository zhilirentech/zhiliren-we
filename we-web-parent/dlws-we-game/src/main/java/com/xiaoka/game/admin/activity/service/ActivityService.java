package com.xiaoka.game.admin.activity.service;

import com.xiaoka.game.admin.authority.functionmanage.entity.FunctionEntity;

public interface ActivityService {
	
	/**
	 * 根据请求路径查询功能
	 * @param requestPath
	 * @return
	 */
	public FunctionEntity findFuctionByRequestPath(String requestPath) ;
}
