package com.xiaoka.game.admin.signin.service;

import java.util.Map;

import org.nutz.dao.pager.Pager;

import com.xiaoka.game.admin.authority.rolemanage.form.RoleModForm;
import com.xiaoka.game.admin.pkrecord.form.PkRecordSqlForm;
import com.xiaoka.game.admin.signin.form.SignInQueryForm;

public interface SignInService {
	/**
	 * 签到记录列表
	 * @param queryForm
	 * @param pager
	 * @return
	 */
	public Map<String,Object> signList(SignInQueryForm queryForm, Pager pager);
	
	
}
