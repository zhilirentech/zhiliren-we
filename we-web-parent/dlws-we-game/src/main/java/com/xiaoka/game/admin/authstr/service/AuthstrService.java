package com.xiaoka.game.admin.authstr.service;

import java.util.Map;

public interface AuthstrService {
	/**
	 * 查询提现未审核
	 * @param id
	 * @return
	 */
	
	Map<String,Object> findAuthstr(long id) ;
}
