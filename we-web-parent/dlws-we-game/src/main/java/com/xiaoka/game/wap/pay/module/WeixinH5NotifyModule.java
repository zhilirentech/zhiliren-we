package com.xiaoka.game.wap.pay.module;

import javax.servlet.http.HttpSession;

import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.By;
import org.nutz.mvc.annotation.Filters;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.uxuexi.core.web.chain.support.JsonResult;
import com.xiaoka.game.common.actionfilter.PayNotifyFilter;
import com.xiaoka.game.wap.pay.form.WxPayNotifyForm;
import com.xiaoka.game.wap.pay.service.WxNotifyService;

@IocBean
@At("/api/pay/weixin")
@Filters
public class WeixinH5NotifyModule{
	
	protected Logger logger  = LoggerFactory.getLogger(WeixinH5NotifyModule.class) ;

	@Inject
	private WxNotifyService wxNotifyService;
	
	
	/**
	 * 校咖支付服务支付成功通知
	 */
	@At
	@Ok("json")
	@Filters
	public Object notify(@Param("..") final WxPayNotifyForm form,HttpSession session) {
		try {
			return wxNotifyService.notify(form,session);
		} catch (Exception e) {
			logger.info("异步通知接口调用异常"); 
			e.printStackTrace();
			return JsonResult.error("异步通知接口调用异常") ;
		}
		
	}

}