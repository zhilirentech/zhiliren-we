package com.xiaoka.game.common.actionfilter;

import java.net.URLEncoder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.nutz.mvc.ActionContext;
import org.nutz.mvc.Mvcs;
import org.nutz.mvc.View;
import org.nutz.mvc.view.ServerRedirectView;

import com.uxuexi.core.common.util.Util;
import com.uxuexi.core.web.config.KvConfig;
import com.xiaoka.game.common.constants.CommonConstants;

/**
 * 微信登录授权过滤器
 * @author 朱晓川
 *
 */
public class WxAuthFilter extends BaseActionFilter {

	@Override
	public View match(ActionContext ac) {
		
		HttpServletRequest req = ac.getRequest() ;
		HttpSession session = Mvcs.getHttpSession(true);
		
		Cookie[] cookies = req.getCookies();//这样便可以获取一个cookie数组
		if(null!=cookies&&cookies.length>0){
			for(Cookie cookie : cookies){
			    String cookName = cookie.getName();// get the cookie name
			    if(cookName.equals("oauthCookie")){
			    	String openId = cookie.getValue(); // get the cookie value
			    	session.setAttribute(CommonConstants.WEIXIN_OPEN_ID, openId);
			    }
			}
		}
		
		if(Util.isEmpty(session.getAttribute(CommonConstants.WEIXIN_OPEN_ID))){
			//去授权
			String url = req.getRequestURL().toString();
			String queryString = req.getQueryString();
			String reqUrl = url;
			if(!Util.isEmpty(queryString)){
				reqUrl = url + "?" + queryString;
			}
			reqUrl = URLEncoder.encode(reqUrl);  
			
			KvConfig wxPayConfig = Mvcs.getIoc().get(KvConfig.class,"wxPayConfig") ;
			String authUrl = wxPayConfig.getValue("weixin_auth_url");//授权的url 
			authUrl = authUrl + "?backUrl=" + reqUrl;
			ServerRedirectView view = new ServerRedirectView(authUrl);//重定向视图
			return view;
		}
		return null;
	}

}
