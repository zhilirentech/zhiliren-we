package com.xiaoka.game.wap.pay.service;

import javax.servlet.http.HttpSession;

import com.xiaoka.game.wap.pay.form.WxPayNotifyForm;

/**
 * 微信支付通知
 */
public interface WxNotifyService {
	
	/**
	 * 校咖支付异步通知
	 */
	public Object notify(final WxPayNotifyForm form,HttpSession session) throws Exception;
}
