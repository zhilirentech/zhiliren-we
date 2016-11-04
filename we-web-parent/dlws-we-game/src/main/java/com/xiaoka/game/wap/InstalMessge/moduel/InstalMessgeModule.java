package com.xiaoka.game.wap.InstalMessge.moduel;
import javax.servlet.http.HttpSession;

import org.nutz.dao.pager.Pager;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.By;
import org.nutz.mvc.annotation.Filters;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.Param;

import com.xiaoka.game.common.actionfilter.CacheUserFilter;
import com.xiaoka.game.common.actionfilter.WxAuthFilter;
import com.xiaoka.game.wap.InstalMessge.form.InstalMessgeSqlForm;
import com.xiaoka.game.wap.InstalMessge.form.SiteMessageReadAddForm;
import com.xiaoka.game.wap.InstalMessge.service.InstalMessgeService;

@Filters({ @By(type = CacheUserFilter.class),@By(type = WxAuthFilter.class)})
@IocBean
@At("/wap")
public class InstalMessgeModule {
	@Inject
	private InstalMessgeService instalMessgeService; 
	
	
	
	/**
	 * 站内信息
	 * @param userId
	 * @return
	 */
	@At
	@Ok("jsp")
	public Object mynews(@Param("..")InstalMessgeSqlForm sqlForm,final HttpSession session,@Param("..") final Pager pager) {
		pager.setPageSize(12);
		return instalMessgeService.mynews(sqlForm,session,pager);
	}
	
	/**
	 * 我的消息
	 * @param userId
	 * @return
	 */
	@At
	public Object mynewsAjax(@Param("..")InstalMessgeSqlForm sqlForm,final HttpSession session,@Param("..") final Pager pager) {
		pager.setPageSize(12);
		return instalMessgeService.mynews(sqlForm,session,pager);
	}
	
	/**
	 * 站内信息详细查询
	 * @param userId
	 * @return
	 */
	@At
	@Ok("jsp")
	public Object mynewsdetail(@Param("..")final SiteMessageReadAddForm addForm,final HttpSession session) {
		return instalMessgeService.mynewsdetail(addForm,session);
	}
}
