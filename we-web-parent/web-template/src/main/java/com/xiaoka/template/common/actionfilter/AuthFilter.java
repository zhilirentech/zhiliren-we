package com.xiaoka.template.common.actionfilter;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import lombok.Data;

import org.nutz.mvc.ActionContext;
import org.nutz.mvc.ActionFilter;
import org.nutz.mvc.Mvcs;
import org.nutz.mvc.View;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Splitter;
import com.uxuexi.core.common.exception.impl.BusinessException;
import com.uxuexi.core.common.util.StringUtil;
import com.uxuexi.core.common.util.Util;
import com.xiaoka.template.admin.authority.functionmanage.entity.FunctionEntity;
import com.xiaoka.template.admin.authority.functionmanage.service.FunctionService;
import com.xiaoka.template.admin.authority.usermanage.entity.UserEntity;
import com.xiaoka.template.admin.log.service.SLogService;
import com.xiaoka.template.admin.login.service.LoginService;
import com.xiaoka.template.common.annotation.NoFilter;
import com.xiaoka.template.common.util.IpUtil;

@Data
public class AuthFilter implements ActionFilter {
	
	private Logger logger = LoggerFactory.getLogger(AuthFilter.class) ;
	
	public static final String NO_LOGIN_MSG = "对不起你还没有登陆!";
	
	/**请求路径后缀标志符*/
	public static final String REQ_TAIL_FLAG = ".";
	
	@Override
	public View match(ActionContext actionContext) {
		//首先确定该请求是否需要进行权限验证
		if (!isNeedValidate(actionContext)) {
			return null;
		}
		boolean isAllowed = true;
		
		//获取访问路径，去掉参数
		HttpServletRequest request = actionContext.getRequest();
		String requestPath=request .getServletPath();
		
		logger.info("当前访问路径:："+requestPath);
		FunctionService functionService = Mvcs.getIoc().get(FunctionService.class, "functionService") ;
		FunctionEntity function = functionService.findFuctionByRequestPath(requestPath);
		if(function==null){
			logger.info("警告：当前路径:"+requestPath+",未定义作为功能！");
			return null ;
		}
		
		HttpSession session = Mvcs.getHttpSession();
		UserEntity user= (UserEntity)session.getAttribute(LoginService.LOGINUSER); 
		isAllowed = hasPermission(session, requestPath) ;
		
		logger.info((null == user? "--游客":user.getUsername())+ "--访问后台功能:[" + function.getName() + (isAllowed == true?"]--允许" : "]--权限不足"));
		if(isAllowed){
			SLogService sLogService = Mvcs.getIoc().get(SLogService.class, "sLogService") ;
			String ip = IpUtil.getIpAddr(request) ;
			sLogService.addSyslog(function, user,ip); 
			return null ;
		}else{
			throw new BusinessException("对不起，你没有访问该功能的权限！");
		}
	}
	
	/**
	 * 该请求是否需要进行权限管理
	 * @param actionContext 
	 * @return 布尔值
	 */
	private boolean isNeedValidate(final ActionContext actionContext) {
		final NoFilter noFilter = actionContext.getMethod().getAnnotation(NoFilter.class);
		return Util.isEmpty(noFilter);
	}
	
	public boolean hasPermission(final HttpSession session, final String requestPath) {
	
		String reqPath = StringUtil.trimRight(requestPath,REQ_TAIL_FLAG) ; 
		
		@SuppressWarnings("unchecked")
		List<FunctionEntity> functions = (List<FunctionEntity>) session.getAttribute(LoginService.AUTHS_KEY);
		if(Util.isEmpty(functions)){
			return false ;
		}
		for(FunctionEntity f : functions){
			String furl = f.getUrl() ;
			furl = StringUtil.trimRight(furl, REQ_TAIL_FLAG) ;
			
			if(reqPath.equals(furl)){
				return true ;
			}
		}
		return false ;
	}
}
