package com.xiaoka.game.admin.authstr.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;

import com.uxuexi.core.db.dao.IDbDao;
import com.xiaoka.game.admin.authstr.entity.AuthstrEntity;
import com.xiaoka.game.admin.authstr.service.AuthstrService;
@IocBean(name="authstrService")
public class AuthstrServiceImpl implements AuthstrService {
	@Inject
	private IDbDao dbDao;
	
	@Override
	public Map<String, Object> findAuthstr(long id) {
		Map<String,Object> obj = new HashMap<String, Object>() ;
		List<AuthstrEntity> allAuthstr =  dbDao.query(AuthstrEntity.class, null, null) ; 
		obj.put("authstrList",allAuthstr) ;                           
		return obj ;
	}
}
