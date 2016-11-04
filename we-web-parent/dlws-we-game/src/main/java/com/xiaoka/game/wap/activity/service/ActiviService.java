package com.xiaoka.game.wap.activity.service;

import java.util.Map;

import org.nutz.dao.pager.Pager;

import com.xiaoka.game.admin.activity.form.ActivityModForm;
import com.xiaoka.game.wap.activity.form.ActiviSqlForm;

public interface ActiviService {

	/**查询活动列表*/
	public Object list(final ActiviSqlForm sqlForm,final Pager pager);
	
	/**查询活动详情*/
	public Map<String,Object>  acdetail(final ActivityModForm modForm);
	
	
}
