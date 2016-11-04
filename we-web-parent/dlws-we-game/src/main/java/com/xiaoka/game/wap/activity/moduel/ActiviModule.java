package com.xiaoka.game.wap.activity.moduel;
import org.nutz.dao.pager.Pager;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.By;
import org.nutz.mvc.annotation.Filters;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.Param;

import com.xiaoka.game.admin.activity.form.ActivityModForm;
import com.xiaoka.game.common.actionfilter.CacheUserFilter;
import com.xiaoka.game.common.actionfilter.WxAuthFilter;
import com.xiaoka.game.common.annotation.NoFilter;
import com.xiaoka.game.wap.activity.form.ActiviSqlForm;
import com.xiaoka.game.wap.activity.service.ActiviService;

@Filters({ @By(type = CacheUserFilter.class),@By(type = WxAuthFilter.class)})
@IocBean
@At("/wap")
public class ActiviModule {
	@Inject
	private ActiviService activiService; 
	
	
	
	/**
	 * 
	 * 项目名称：dlws-xiaoka-game
	 * 描述：up入口
	 * 创建人： dxf
	 * 创建时间： 2016年8月16日
	 * 标记：wap
	 * @param request
	 * @param sqlForm
	 * @param pager
	 * @return
	 * @version
	 */
	@At
	@Ok("jsp")
	public Object activeList(@Param("..") final ActiviSqlForm sqlForm,@Param("..") final Pager pager) {
		pager.setPageSize(10);
		return activiService.list(sqlForm, pager);
	}
	
	@At
	@NoFilter
	public Object activeListAjax(@Param("..") final ActiviSqlForm sqlForm,@Param("..") final Pager pager) {
		pager.setPageSize(10);
		return activiService.list(sqlForm, pager);
	}
	
	@At
	@Ok("jsp")
	public Object activeDetail(@Param("..") final ActivityModForm modForm) {
		return activiService.acdetail(modForm);
	}
}
