package com.xiaoka.game.admin.account.service;

import java.util.Map;

import org.nutz.dao.pager.Pager;

import com.xiaoka.game.admin.account.form.AccountQueryForm;

public interface AccountService {
	/**
	 * 充值记录
	 * @param queryForm
	 * @param pager
	 * @return
	 */
	public Map<String,Object> list(AccountQueryForm queryForm, Pager pager);
	
}
