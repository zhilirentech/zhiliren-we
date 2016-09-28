package com.xiaoka.game.admin.pkrecord.service;

import java.util.Map;

import org.nutz.dao.pager.Pager;

import com.xiaoka.game.admin.authority.rolemanage.form.RoleModForm;
import com.xiaoka.game.admin.pkrecord.form.PkRecordSqlForm;
import com.xiaoka.game.admin.pkrecord.form.SignInSqlForm;

public interface PkRecordService {
	
	/**
	 * 
	 * 项目名称：dlws-xiaoka-game
	 * 描述：分页查询pk发起列表
	 * 创建人： ln
	 * 创建时间： 2016年8月11日
	 * 标记：admin
	 * @param sqlForm
	 * @param pager
	 * @return
	 * @version
	 */
	public Object queryPkRecordList(PkRecordSqlForm sqlForm, Pager pager);
	
	/**
	 * 
	 * 项目名称：dlws-xiaoka-game
	 * 描述：分页查询某一个game参与人员列表
	 * 创建人： ln
	 * 创建时间： 2016年8月11日
	 * 标记：
	 * @param sqlForm
	 * @param pager
	 * @return
	 * @version
	 */
	public Object signInByGameIdList(SignInSqlForm sqlForm, Pager pager);
}
