package com.xiaoka.game.admin.login.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.xiaoka.game.admin.login.form.LoginForm;

public interface LoginService {
	
	public static final String IS_LOGIN_KEY = "isLogin";
	public static final String AUTHS_KEY = "auths";
	public static final String MENU_KEY = "menus";
	public static final String FUNCTION_MAP_KEY = "functionMap";
	public static final String LOGINUSER = "loginuser";
	
	/**登录*/
	public boolean login(final LoginForm form, final HttpSession session,final HttpServletRequest req) ;
	
	/**
	 * 登出
	 * @param session session会话
	 */
	public void logout(final HttpSession session) ;
	
	/**是否已经登录*/
	public Boolean isLogin(final HttpSession session) ;
}
