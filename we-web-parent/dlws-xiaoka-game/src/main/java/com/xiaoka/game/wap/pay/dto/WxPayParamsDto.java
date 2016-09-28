package com.xiaoka.game.wap.pay.dto;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Function;
import com.google.common.collect.Maps;
import com.uxuexi.core.common.util.HtmlUtil;
import com.uxuexi.core.common.util.MapUtil;
import com.uxuexi.core.web.config.KvConfig;
import com.xiaoka.game.common.access.AccessConfig;
import com.xiaoka.game.common.access.AccessCore;
import com.xiaoka.game.wap.pay.entity.PayEntity;

public class WxPayParamsDto {
	
	private static Logger logger = LoggerFactory.getLogger(WxPayParamsDto.class) ;
	
	//支付配置信息
	private KvConfig wxPayConfig ;
	
	/*
	 * 同步回调地址（用户点击[完成]的时候跳转的地址）
	 */
	private String sendUrl = "";
	
	/*
	 * 拼装字符串
	 * 包含放redis的key(orderNo)
	 * 包含支付成功回调Url(通知url)
	 * orderNo 竖线分隔
	 * eg:rdsKey|notifyUrl|orderNo
	 */
	private String keyUrlOrderId = "";
	/*
	 * 用户openid
	 */
	private String openId = "";
	
	/*
	 * 支付金额
	 */
	private String totalPrice = "";
	
	/*
	 * 商品名
	 */
	private String commodityName = "";
	
	
	/*
	 * 调起支付的地址
	 */
	private String wxPayGateWay ;
	
	/**
	 * 请求支付的系统的信息
	 * train
	 */
	private String clientFlag ;
	
	public WxPayParamsDto(KvConfig wxPayConfig){
		this.wxPayConfig = wxPayConfig ;
		this.clientFlag = wxPayConfig.getValue("clientFlag") ;
		this.wxPayGateWay = wxPayConfig.getValue("wx_pay_gateway") ;
		
	}
	
	public String getSendUrl() {
		return sendUrl;
	}
	
	public String getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(String totalPrice) {
		this.totalPrice = totalPrice;
	}

	public String getCommodityName() {
		return commodityName;
	}

	public void setCommodityName(String commodityName) {
		this.commodityName = commodityName;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}
	
	public void setSendUrl(PayEntity order,final HttpSession session) {
		//计算签名
		Map<String,String> sPara = MapUtil.map() ;
		sPara.put("orderNo", order.getOrderNo());
		String sign = AccessCore.sign(sPara, AccessConfig.xiaoka_wxpay_secret) ;
		
		this.sendUrl = wxPayConfig.getValue("send_url_gateway")+ "&orderNo=" + order.getOrderNo() + "&sign=" + sign ;
	}

	public String getPayUrl(String redKye,String notifyUrl,String orderNo){
		Map<String,String> sPara = MapUtil.map() ; 
		
		keyUrlOrderId = redKye + "|" + notifyUrl + "|" + orderNo ; 
		
		logger.info("sendUrl :" + sendUrl);
		logger.info("openId :" + openId);
		logger.info("keyUrlOrderId :" + keyUrlOrderId);
		
		sPara.put("clientFlag",clientFlag) ;
		sPara.put("sendUrl", sendUrl) ;
		sPara.put("keyUrlOrderId", keyUrlOrderId) ;
		sPara.put("openId", openId) ;
		sPara.put("totalPrice", totalPrice) ;
		sPara.put("commodityName",commodityName) ;
		String sign = AccessCore.sign(sPara, AccessConfig.xiaoka_wxpay_secret)  ;
		sPara.put("sign",sign);
		
		//url encode
		Map<String, String>  encodedParams = Maps.transformValues(sPara, new Function<String, String>() {
			@Override
			public String apply(String input) {
				return HtmlUtil.urlEncode(input); 
			}
		}) ;
		
		String paramUrl = AccessCore.createLinkString(encodedParams) ;
		return wxPayGateWay+paramUrl ;
	}
}
