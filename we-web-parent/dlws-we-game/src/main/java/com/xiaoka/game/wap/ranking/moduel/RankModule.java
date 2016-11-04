package com.xiaoka.game.wap.ranking.moduel;
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
import com.xiaoka.game.common.annotation.NoFilter;
import com.xiaoka.game.wap.ranking.form.RankSqlForm;
import com.xiaoka.game.wap.ranking.form.ScolRankSqlForm;
import com.xiaoka.game.wap.ranking.service.RankService;

@Filters({ @By(type = CacheUserFilter.class),@By(type = WxAuthFilter.class)})
@IocBean
@At("/wap")
public class RankModule {
	@Inject
	private RankService rankService; 
	
	
	@At
	@Ok("jsp")
	public Object ranking(@Param("..") final RankSqlForm sqlForm,@Param("..") final Pager pager) {
		pager.setPageSize(4);
		return rankService.list(sqlForm, pager);
	}
	
	@At
	@NoFilter
	public Object rankingAjax(@Param("..") final RankSqlForm sqlForm,@Param("..") final Pager pager) {
		pager.setPageSize(4);
		return rankService.list(sqlForm, pager);
	}
	
	@At
	@NoFilter
	public Object SchoolAjax(HttpSession session, @Param("..") final ScolRankSqlForm sqlForm,@Param("..") final Pager pager) {
		pager.setPageSize(4);
		String openId = (String)session.getAttribute("openId");
		sqlForm.setOpenId(openId);
		return rankService.schoList(sqlForm, pager);
	}
	
	
	
}
