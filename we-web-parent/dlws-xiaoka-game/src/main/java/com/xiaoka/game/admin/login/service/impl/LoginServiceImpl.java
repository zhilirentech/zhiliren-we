package com.xiaoka.game.admin.login.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;

import com.uxuexi.core.common.util.Util;
import com.xiaoka.game.admin.authority.functionmanage.entity.FunctionEntity;
import com.xiaoka.game.admin.authority.usermanage.entity.UserEntity;
import com.xiaoka.game.admin.authority.usermanage.service.UserService;
import com.xiaoka.game.admin.log.service.SLogService;
import com.xiaoka.game.admin.login.form.LoginForm;
import com.xiaoka.game.admin.login.service.LoginService;
import com.xiaoka.game.common.access.AccessConfig;
import com.xiaoka.game.common.access.sign.MD5;
import com.xiaoka.game.common.constants.CommonConstants;
import com.xiaoka.game.common.service.BaseService;
import com.xiaoka.game.common.util.IpUtil;

@IocBean(name="loginService")
public class LoginServiceImpl extends BaseService implements LoginService {
	
	@Inject
	private UserService userService ;
	
	@Inject
	private SLogService sLogService ;

	@Override
	public boolean login(final LoginForm form, final HttpSession session,final HttpServletRequest req) {
		
		String recode = (String)session.getAttribute(CommonConstants.CONFIRMCODE);
		String vCode = form.getValidateCode() ;
	    if(Util.isEmpty(vCode) ||!recode.equalsIgnoreCase(vCode)){ 
			form.setErrMsg("验证码不正确") ;
			return false ;
		}
		
	    String passwd = MD5.sign(form.getPassword(), AccessConfig.password_secret, AccessConfig.INPUT_CHARSET);
		UserEntity user = userService.findUser(form.getLoginName(),passwd); 
		if (user == null) {
			form.setErrMsg("用户名或密码错误") ;
			return false ;
		} else {
			if(CommonConstants.DATA_STATUS_VALID != user.getStatus()){
				form.setErrMsg("账号未激活") ;
				return false ;
			}
			
			addLoginlog(user,req); 
			
			List<FunctionEntity>  allUserFunction = userService.findUserFunctions(user.getId()) ;
			
			//1级菜单
			List<FunctionEntity> menus = new ArrayList<FunctionEntity>() ;
			//根据菜单取功能的map
			Map<Long,List<FunctionEntity>> functionMap = new HashMap<Long,List<FunctionEntity>>() ;
			for(FunctionEntity f : allUserFunction){
				if(1 == f.getLevel()){
					menus.add(f) ;
				}
				
				if(2 == f.getLevel()){
					List<FunctionEntity> subList = functionMap.get(f.getParentId()) ;
					if(null == subList){
						subList = new ArrayList<FunctionEntity>() ;
						subList.add(f) ;
						functionMap.put(f.getParentId(),subList) ;
					}else{
						subList.add(f) ;
					}
				}
			}
			
			//排序functionMap
			Set<Long> keySet = functionMap.keySet() ;
			for(Long key : keySet){
				List<FunctionEntity> list = functionMap.get(key) ;
				if(!Util.isEmpty(list)){
					Collections.sort(list, new Comparator<FunctionEntity>() {
						@Override
						public int compare(FunctionEntity o1, FunctionEntity o2) {
							if(null == o1 || null == o1.getSort()){
								return 0 ;
							}
							return o1.getSort().compareTo(o2.getSort()) ; 
						}
					}) ;
				}
			}
			
			//将用户权限保存到session中
			session.setAttribute(FUNCTION_MAP_KEY, functionMap);  //功能
			session.setAttribute(MENU_KEY, menus);                //菜单
			session.setAttribute(AUTHS_KEY, allUserFunction);     //所有功能
			session.setAttribute(LOGINUSER, user);
			session.setAttribute(IS_LOGIN_KEY, true);
		}
		return true ;
	}

	@Override
	public void logout(HttpSession session) {
		session.removeAttribute(AUTHS_KEY);
		session.removeAttribute(IS_LOGIN_KEY);
		session.invalidate();
	}

	@Override
	public Boolean isLogin(HttpSession session) {
		return !Util.isEmpty(session.getAttribute(IS_LOGIN_KEY));
	}
	
	/**添加登录日志*/
	private void addLoginlog(UserEntity user,HttpServletRequest req) { 
		FunctionEntity function = new FunctionEntity() ;
		function.setId(-1) ;
		function.setName("登录") ;
		function.setUrl("/login.html") ;
		
		String ip = IpUtil.getIpAddr(req) ;
		
		sLogService.addSyslog(function, user,ip);
	}

}
