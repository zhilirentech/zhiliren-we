package com.xiaoka.game.wap.honor.service;

import org.nutz.dao.pager.Pager;

import com.xiaoka.game.wap.honor.form.HonorSqlForm;

public interface HonorService {

	/**查询全国排名列表*/
	public Object list(String openId,final HonorSqlForm sqlForm,final Pager pager);
	
}
