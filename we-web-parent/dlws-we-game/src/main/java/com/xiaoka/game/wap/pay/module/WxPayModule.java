package com.xiaoka.game.wap.pay.module;

import java.util.Map;

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

import com.uxuexi.core.db.dao.IDbDao;
import com.xiaoka.game.common.actionfilter.PayNotifyFilter;
import com.xiaoka.game.common.result.MobileResult;
import com.xiaoka.game.wap.pay.form.WxPayForm;
import com.xiaoka.game.wap.pay.form.WxPayProcessForm;
import com.xiaoka.game.wap.pay.service.WxPayService;

@Filters
@IocBean
@At("/weixin/pay")
public class WxPayModule {
	
	private static Logger logger = LoggerFactory.getLogger(WxPayModule.class) ;
	
	@Inject
	private WxPayService wxPayService;
	
	/**
	 * 注入容器中的dbDao对象，用于数据库查询、持久操作
	 */
	@Inject
	protected IDbDao dbDao;
	
	
	/**
	 * 转收银台
	 */
	@Ok(">>:${obj}")
	@At
	public Object pay(@Param("..") final WxPayForm form,HttpSession session) {
		   
		return wxPayService.cashCounter(form,session);
	}
	
	/**
	 * 校咖支付成功同步处理地址(点击[完成])
	 */
	//直接从请求的参数中获取参数,转课程列表
	@At
	@Ok(">>:${obj}") 
	public Object process(@Param("..") final WxPayProcessForm form,final HttpSession session){
		return "/wap/center/toCenter.html";
	}
}
