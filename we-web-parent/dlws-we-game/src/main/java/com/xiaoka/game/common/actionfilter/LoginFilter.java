package com.xiaoka.game.common.actionfilter;

import javax.servlet.http.HttpSession;

import org.nutz.mvc.ActionContext;
import org.nutz.mvc.ActionFilter;
import org.nutz.mvc.Mvcs;
import org.nutz.mvc.View;
import org.nutz.mvc.view.ServerRedirectView;

import com.xiaoka.game.admin.login.service.LoginService;
import com.xiaoka.game.common.annotation.NoFilter;

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
    		return new ServerRedirectView("/admin/login.html");
        Object obj = session.getAttribute(LoginService.LOGINUSER);
        if (null == obj)
            return new ServerRedirectView("/admin/login.html");
        return null;
	}

}
