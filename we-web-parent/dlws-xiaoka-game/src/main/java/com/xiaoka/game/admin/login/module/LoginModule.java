package com.xiaoka.game.admin.login.module;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Filters;
import org.nutz.mvc.annotation.GET;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.POST;
import org.nutz.mvc.annotation.Param;

import com.xiaoka.game.admin.login.form.LoginForm;
import com.xiaoka.game.admin.login.service.LoginService;

@At("/admin")
@IocBean
public class LoginModule {
	
	@Inject
	private LoginService loginService;

	/**
	 * 登陆
	 * @param username 用户名
	 * @param password 密码
	 * @param session session会话对象
	 * @return 
	*/
	@At
	@POST
	@Filters
	@Ok("jsp:${obj == null ? 'main' : 'admin.login'}")//登录成功返回主页,失败返回登录页
	public Object login(@Param("..") final LoginForm form,
			final HttpSession session,final HttpServletRequest req) {
		
		if(!loginService.login(form, session,req)){
			return form;
		}
		return null ;
	}

	@At
	@GET
	@Filters
	@Ok("jsp")
	public Object login() {
		return null;
	}
	
	@At
	@Filters
	@Ok("jsp:admin.login")
	public void logout(final HttpSession session) {
		loginService.logout(session) ;
	}
	
}