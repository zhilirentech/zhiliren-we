package com.xiaoka.game.wap.pay.service.impl;

import java.util.Date;

import javax.servlet.http.HttpSession;

import org.nutz.dao.Chain;
import org.nutz.ioc.aop.Aop;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Splitter;
import com.uxuexi.core.common.util.Util;
import com.xiaoka.game.common.service.BaseService;
import com.xiaoka.game.wap.center.entity.AccountEntity;
import com.xiaoka.game.wap.center.entity.CenterCustomerEntity;
import com.xiaoka.game.wap.center.service.CenterService;
import com.xiaoka.game.wap.home.module.HomeModule;
import com.xiaoka.game.wap.pay.dto.NotifyResultDto;
import com.xiaoka.game.wap.pay.form.WxPayNotifyForm;
import com.xiaoka.game.wap.pay.service.WxNotifyService;
import com.xiaoka.game.wap.pay.service.WxPayService;

@IocBean(name= "wxNotifyService")
public class WxNotifyServiceImpl extends BaseService implements WxNotifyService{
	private static Logger log = LoggerFactory.getLogger(WxNotifyServiceImpl.class);
	@Inject
	private WxPayService wxPayService ;

	@Inject
	private CenterService centerService;

	
	@Aop("txDb")
	@Override
	public Object notify(WxPayNotifyForm form,HttpSession session) throws Exception {
		NotifyResultDto result = new NotifyResultDto() ;
		
		//订单号
		String orderNo = form.getOrderId(); 
		if (Util.isEmpty(orderNo)) {
			result.setFlag(false); 
			logger.error("订单号不能为空");
			return result ;
		}
		String openId = form.getOpenId();
		log.info("++++++++++++++++++++++进入支付成功回调，且订单号不为空!orderNo="+orderNo+"+++++++openId="+openId);
		String[] split = orderNo.split("_");
		orderNo=split[0];
	    String amount = split[1];
	    log.info("++++++++++++++++++++++orderId="+orderNo+"+amount = "+amount);
	    double a = Double.valueOf(amount);
		//支付成功后的业务处理 TODO
	    log.info("++++++++++++++++++++++++++++获取账户余额+");
		CenterCustomerEntity cenCust = centerService.getCenterCustomer(openId);
		log.info("++++++++++++++++++++++++++++cenCust="+cenCust);
		double balance =0.0;
		if(null!=cenCust){
			//获取余额
			 balance = cenCust.getBalance()+a;
		}
		//保存待支付信息
		log.info("++++++++++++++++++++++balance="+balance+"+amount="+a);
		//插入支付信息
		dbDao.insert(AccountEntity.class,Chain.make("id",orderNo).add("openId", openId)
				.add("money",amount).add("balance",balance).add("platType",0).add("type",1).add("createTime", new Date()));
		

		
		
		return result ;
	}
}
