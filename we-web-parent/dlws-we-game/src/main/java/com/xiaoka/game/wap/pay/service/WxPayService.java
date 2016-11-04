package com.xiaoka.game.wap.pay.service;

import java.util.Map;

import javax.servlet.http.HttpSession;

import com.xiaoka.game.wap.pay.form.WxPayForm;
import com.xiaoka.game.wap.pay.form.WxPayProcessForm;


/**
 * 微信支付service
 */
public interface WxPayService {
	
	/**
	 * 校咖支付成功同步处理地址(点击[完成])
	 */
	public Map<String,Object> process(final WxPayProcessForm form,final HttpSession session);
	
	/**
	 * 转收银台
	 */
	public String cashCounter(WxPayForm form,HttpSession session);


}
