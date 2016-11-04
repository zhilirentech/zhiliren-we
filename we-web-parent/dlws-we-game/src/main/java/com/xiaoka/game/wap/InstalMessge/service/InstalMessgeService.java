package com.xiaoka.game.wap.InstalMessge.service;

import javax.servlet.http.HttpSession;

import org.nutz.dao.pager.Pager;

import com.xiaoka.game.wap.InstalMessge.form.InstalMessgeSqlForm;
import com.xiaoka.game.wap.InstalMessge.form.SiteMessageReadAddForm;

public interface InstalMessgeService {
	/**
	 * 查找用户的消息
	 * @param userId 用户Id
	 * @return Map<String, String>
	 */
	public Object mynews(InstalMessgeSqlForm sqlForm,final HttpSession session,final Pager pager);
	/**
	 * 查找我的消息详细
	 * @param sqlForm 
	 * @return Object
	 */
	public Object mynewsdetail(SiteMessageReadAddForm addForm,HttpSession session);
	
	
}
