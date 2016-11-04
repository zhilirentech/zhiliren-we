package com.xiaoka.game.common.actionfilter; 

import java.util.Enumeration;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.nutz.mvc.ActionContext;
import org.nutz.mvc.Mvcs;
import org.nutz.mvc.View;
import org.nutz.mvc.view.ServerRedirectView;

import com.uxuexi.core.common.util.Util;
import com.xiaoka.game.common.constants.CommonConstants;

/**
 * 缓存用户openid
 * @author 朱晓川
 *
 */
public class CacheUserFilter extends BaseActionFilter {

	@Override
	public View match(ActionContext ac) {
		HttpServletRequest req = ac.getRequest() ;
		HttpServletResponse rep = ac.getResponse() ;
		String openId = req.getParameter(CommonConstants.WEIXIN_OPEN_ID);
		if(!Util.isEmpty(openId)){
			HttpSession session = Mvcs.getHttpSession(true);
			session.setAttribute(CommonConstants.WEIXIN_OPEN_ID, openId);
			//存入cookie
			Cookie cookie = new Cookie("oauthCookie",openId);
			cookie.setMaxAge(14*24*3600);
			cookie.setPath("/");
			rep.addCookie(cookie);
			
			//======将请求中的openId去除
			String url = req.getRequestURL().toString();
			String params = "";
			Enumeration<String> paramKeys = req.getParameterNames();
			while (paramKeys.hasMoreElements()) {
				String key = paramKeys.nextElement();
				if(!"null".equals(key)&&!CommonConstants.WEIXIN_OPEN_ID.equals(key)){
					params = params + key + "=" + (req.getParameter(key)).toString() + "&" ;
				}
			}
			if(!Util.isEmpty(params)){
				params = params.substring(0, params.length()-1);
				url = url + "?" + params;
			}
			ServerRedirectView view = new ServerRedirectView(url);//重定向视图
			return view;
		}
		return null;
	}

}
