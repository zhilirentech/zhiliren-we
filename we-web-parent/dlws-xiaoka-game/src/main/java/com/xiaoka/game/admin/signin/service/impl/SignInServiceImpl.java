package com.xiaoka.game.admin.signin.service.impl;

import java.util.Map;

import org.nutz.dao.pager.Pager;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;

import com.uxuexi.core.db.dao.IDbDao;
import com.uxuexi.core.web.util.FormUtil;
import com.xiaoka.game.admin.pkrecord.form.PkRecordSqlForm;
import com.xiaoka.game.admin.pkrecord.service.PkRecordService;
import com.xiaoka.game.admin.signin.form.SignInQueryForm;
import com.xiaoka.game.admin.signin.service.SignInService;
import com.xiaoka.game.common.service.BaseService;

@IocBean(name="signInService")
public class SignInServiceImpl extends  BaseService implements SignInService{

	@Override
	public Map<String,Object> signList(SignInQueryForm queryForm, Pager pager) {
		Map<String, Object> map = FormUtil.query(dbDao,sqlManager, queryForm, pager);
		return map;
	}

	
}
