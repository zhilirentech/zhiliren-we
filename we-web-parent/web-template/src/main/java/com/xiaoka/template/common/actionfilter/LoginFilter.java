package com.xiaoka.template.common.actionfilter;

import javax.servlet.http.HttpSession;

import org.nutz.mvc.ActionContext;
import org.nutz.mvc.ActionFilter;
import org.nutz.mvc.Mvcs;
import org.nutz.mvc.View;
import org.nutz.mvc.view.ServerRedirectView;

import com.xiaoka.template.admin.login.service.LoginService;
import com.xiaoka.template.common.annotation.NoFilter;

/**
 * 登录过滤器
 * @author 朱晓川
 *
 */
public class LoginFilter implements ActionFilter {
	
	@Override
	public View match(ActionContext ac) {
		if (ac.getModule().getClass().isAnnotationPresent(NoFilter.class)) {
			return null;
		}
		
		HttpSession session = Mvcs.getHttpSession(false);
    	if (session == null)
    		return new ServerRedirectView("/login.html");
        Object obj = session.getAttribute(LoginService.LOGINUSER);
        if (null == obj)
            return new ServerRedirectView("/login.html");
        return null;
	}

}
