package com.xiaoka.game.wap.honor.moduel;
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
import com.xiaoka.game.wap.honor.form.HonorSqlForm;
import com.xiaoka.game.wap.honor.service.HonorService;

@Filters({ @By(type = CacheUserFilter.class),@By(type = WxAuthFilter.class)})
@IocBean
@At("/wap")
public class HonorModule {
	@Inject
	private HonorService honorService; 
	
	
	@At
	@Ok("jsp")
	public Object honor(HttpSession session, @Param("..") final HonorSqlForm sqlForm, @Param("..") final Pager pager) {
		String openId = (String)session.getAttribute("openId");
		return honorService.list(openId,sqlForm, pager);
	}
	
	
}
