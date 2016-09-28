package com.xiaoka.game.common.access;

import org.nutz.ioc.Ioc;
import org.nutz.mvc.Mvcs;

import com.uxuexi.core.web.config.KvConfig;

/**
 * web访问请求配置
 * @author 朱晓川
 *
 */
public class AccessConfig {

	/**存取请求参数中的URL请求地址的key*/
	public static final String URL_KEY = "url";
	
	/**管理后台密码签名秘钥,一旦创建用户以后该值不可更改*/
	public static final String password_secret = "xiaoka360-qwerASDF#123" ;
	
	/**平台微信支付签名秘钥*/
	public static final String xiaoka_wxpay_secret = "CGgOeVe7WoyVXGbGvzHeiplAfRpQNAqW";

	/**终端签名秘钥*/
	public static final String terminal_secret = "xiaoka-1qa2wsxz#!xcv$";

	/**平台签名秘钥*/
	public static final String platform_secret = "xiaoka-platform";

	/**字符编码格式 */
	public static final String INPUT_CHARSET = "utf-8";

	/**签名方式*/
	public static final String sign_type = "MD5";

	//平台接口地址

	/**平台主机地址*/
	public static final String PLATFORM_HOST;

	static {
		Ioc ioc = Mvcs.getIoc();
		KvConfig cfg = ioc.get(KvConfig.class, "kvConfig");
		PLATFORM_HOST = cfg.getValue("platform_host");
	}

}
