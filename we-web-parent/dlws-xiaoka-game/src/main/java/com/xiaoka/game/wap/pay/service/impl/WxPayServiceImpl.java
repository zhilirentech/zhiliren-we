package com.xiaoka.game.wap.pay.service.impl;

import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;

import com.uxuexi.core.web.config.KvConfig;
import com.xiaoka.game.common.service.BaseService;
import com.xiaoka.game.wap.center.service.CenterService;
import com.xiaoka.game.wap.pay.dto.WxPayParamsDto;
import com.xiaoka.game.wap.pay.entity.PayEntity;
import com.xiaoka.game.wap.pay.form.WxPayForm;
import com.xiaoka.game.wap.pay.form.WxPayProcessForm;
import com.xiaoka.game.wap.pay.service.WxPayService;

@IocBean(name= "wxPayService")
public class WxPayServiceImpl extends BaseService implements WxPayService{
	
	@Inject
	private KvConfig wxPayConfig ;
	@Inject
	private CenterService centerService;

	
	@Override
	public String cashCounter(WxPayForm form, HttpSession session) {
		
		String openId = (String) session.getAttribute("openId");
		
		WxPayParamsDto par = new WxPayParamsDto(wxPayConfig);
		
		//设置商品名称，设置支付用户openId
		//TODO
		par.setCommodityName("up充值");
		par.setOpenId(openId);
		
		
		//支付金额
		//价格 TODO
		String totalPrice = String.valueOf(form.getAmount());
		par.setTotalPrice(totalPrice);
		
		//TODO  这里直接使用商品id作为课程id   课程套餐暂未处理 
		PayEntity pe = new PayEntity();
		String orderNo = String.valueOf(new Date().getTime());
		orderNo = orderNo+"_"+totalPrice;
		pe.setOrderNo(orderNo);
		par.setSendUrl(pe,session);
		String payUrl = par.getPayUrl(wxPayConfig.getValue("wxPay_order_rediskey"), wxPayConfig.getValue("notify_url"), pe.getOrderNo());
		
		logger.info("payUrl:" + payUrl);
		return payUrl;

	}
	
	@Override
	public Map<String, Object> process(WxPayProcessForm form,
			HttpSession session) {
		// TODO Auto-generated method stub
		return null;
	}

	
	
		
}
