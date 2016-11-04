package com.xiaoka.game.admin.pkrecord.service.impl;

import org.nutz.dao.pager.Pager;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;

import com.uxuexi.core.db.dao.IDbDao;
import com.uxuexi.core.web.util.FormUtil;
import com.xiaoka.game.admin.pkrecord.form.PkRecordSqlForm;
import com.xiaoka.game.admin.pkrecord.form.SignInSqlForm;
import com.xiaoka.game.admin.pkrecord.service.PkRecordService;
import com.xiaoka.game.common.service.BaseService;

@IocBean(name="pkRecordService")
public class PkRecordServiceImpl extends  BaseService implements PkRecordService{
	
	@Inject
	private IDbDao dbDao;

	@Override
	public Object queryPkRecordList(PkRecordSqlForm sqlForm, Pager pager) {
		return FormUtil.query(dbDao, sqlManager, sqlForm, pager);
	}

	@Override
	public Object signInByGameIdList(SignInSqlForm sqlForm, Pager pager) {
		
		return FormUtil.query(dbDao, sqlManager, sqlForm, pager);
	}
	

	
}
