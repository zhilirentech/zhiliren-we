package com.xiaoka.game.wap.center.module;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.nutz.dao.SqlManager;
import org.nutz.dao.pager.Pager;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.json.Json;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.By;
import org.nutz.mvc.annotation.Filters;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.uxuexi.core.db.dao.IDbDao;
import com.xiaoka.game.admin.banner.entity.BannerEntity;
import com.xiaoka.game.admin.banner.form.BannerQueryForm;
import com.xiaoka.game.common.actionfilter.CacheUserFilter;
import com.xiaoka.game.common.actionfilter.WxAuthFilter;
import com.xiaoka.game.wap.center.entity.CenterCustomerEntity;
import com.xiaoka.game.wap.center.service.CenterService;

@Filters({ @By(type = CacheUserFilter.class),@By(type = WxAuthFilter.class)})
@IocBean
@At("/wap/center")
public class CenterModule {
	private static Logger log = LoggerFactory.getLogger(CenterModule.class);
	/**
	 * 注入容器中的dbDao对象，用于数据库查询、持久操作
	 */
	@Inject
	private IDbDao dbDao;
	@Inject
	private SqlManager sqlManager;
	@Inject
	private CenterService centerService;

	
	/**
	 * 
	 * 项目名称：dlws-xiaoka-game
	 * 描述： 去个人中心
	 * 创建人： ln
	 * 创建时间： 2016年8月16日
	 * 标记：
	 * @param session
	 * @param pager
	 * @return
	 * @version
	 */
	@At
	@Ok("jsp:wap.my")
	public Object toCenter(HttpSession session, @Param("..") final Pager pager) {
		String openId = String.valueOf(session.getAttribute("openId"));
		//根据用户openId获取用户信息，获取用户账户余额
		CenterCustomerEntity cenCust = centerService.getCenterCustomer(openId);
		if(null!=cenCust && null!=cenCust.getCustomerName()){
			return cenCust;
		}
		return null;
	}
	/**
	 * 
	 * 项目名称：dlws-xiaoka-game
	 * 描述：up去支付输入金额页面
	 * 创建人： ln
	 * 创建时间： 2016年8月17日
	 * 标记：
	 * @param session
	 * @param pager
	 * @return
	 * @version
	 */
	@At
	@Ok("jsp:wap.pay")
	public Object toPayPage(HttpSession session,@Param("..") final Pager pager) {
		
		return "";
	}
	
}




























