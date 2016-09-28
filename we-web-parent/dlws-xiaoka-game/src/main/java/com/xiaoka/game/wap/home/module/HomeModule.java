package com.xiaoka.game.wap.home.module;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.nutz.dao.SqlManager;
import org.nutz.dao.pager.Pager;
import org.nutz.http.Http;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.json.Json;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.By;
import org.nutz.mvc.annotation.Filters;
import org.nutz.mvc.annotation.GET;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.POST;
import org.nutz.mvc.annotation.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.uxuexi.core.common.util.MapUtil;
import com.uxuexi.core.common.util.Util;
import com.uxuexi.core.db.dao.IDbDao;
import com.uxuexi.core.web.config.KvConfig;
import com.xiaoka.game.common.actionfilter.CacheUserFilter;
import com.xiaoka.game.common.actionfilter.WxAuthFilter;
import com.xiaoka.game.common.annotation.NoFilter;
import com.xiaoka.game.wap.home.entity.CityInfoEntity;
import com.xiaoka.game.wap.home.entity.CustomerEntity;
import com.xiaoka.game.wap.home.entity.UpCustomerEntity;
import com.xiaoka.game.wap.home.form.AttendForm;
import com.xiaoka.game.wap.home.service.HomeService;

@IocBean
@At("/wap/home")
@Filters({ @By(type = CacheUserFilter.class),@By(type = WxAuthFilter.class)})
public class HomeModule {
	private static Logger log = LoggerFactory.getLogger(HomeModule.class);
	/**
	 * 注入容器中的dbDao对象，用于数据库查询、持久操作
	 */
	@Inject
	private IDbDao dbDao;
	@Inject
	private SqlManager sqlManager;
	@Inject
	private HomeService homeService; 
	@Inject
	private KvConfig wxPayConfig ; 
	
	
	@At
	@Ok("->:${obj}")
	@NoFilter
	public Object entrance(HttpSession session) {
		session.removeAttribute("targetUrl");
		return "/wap/home/toIndex.html";
	}
	
	
	
	
	
	/**
	 * 
	 * 项目名称：dlws-xiaoka-game
	 * 描述：跳转到UP首页
	 * 创建人： ln
	 * 创建时间： 2016年8月15日
	 * 标记：wap
	 * @param request
	 * @param sqlForm
	 * @param pager
	 * @return
	 * @version
	 */
	@At
	@Ok(">>:${obj}")
	public String toIndex(HttpServletRequest request, @Param("..") final Pager pager) {
		try {
			
			String openId = (String) request.getSession().getAttribute("openId");
			
				CustomerEntity customer = homeService.getCustomerById(openId);
				
				if(null!=customer && null!=customer.getSchoolId()){//判断用户是否选择了学校
				
					UpCustomerEntity upCustomer =  homeService.getUpCustomerByOpenId(openId);
					
					if(null!=upCustomer && null!=upCustomer.getCustomerName()){//判断是否维护了个人信息(姓名)
						Object targetUrl = request.getSession().getAttribute("targetUrl") ;
						if(Util.isEmpty(targetUrl)){
							request.getSession().removeAttribute("targetUrl");
							return "/wap/home/index.html";
						}
						return (String)targetUrl;
					}
					return "/wap/home/toSaveCustomerInfo.html";
				}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "/wap/home/toSelectSchool.html";
	}
	
	
	
	/**
	 * 
	 * 项目名称：dlws-xiaoka-game
	 * 描述：去选择学校
	 * 创建人： ln
	 * 创建时间： 2016年8月15日
	 * 标记：wap
	 * @param request
	 * @param sqlForm
	 * @param pager
	 * @return
	 * @version
	 */
	@At
	@Ok("jsp:wap.school")
	public Object toSelectSchool(HttpServletRequest request, @Param("..") final Pager pager) {
		Map<String,Object> resMap = new HashMap<String, Object>();
		String openId = String.valueOf(request.getSession().getAttribute("openId"));

		List<CityInfoEntity> citys = null;
		//获取城市，获取学校
		citys = homeService.selectSchool(openId);
		resMap.put("citys",citys);
		resMap.put("openId", openId);
		return resMap;
	}

	/**
	 * 保存学校
	 * @param request
	 * @param model
	 * @param schoolId
	 * @return String
	 */
	@At
	@Ok(">>:${obj}")
	public String saveSchool(HttpSession session,@Param("schoolId") final String schoolId, @Param("..") final Pager pager) {
		try {
			String openId = String.valueOf(session.getAttribute("openId"));
			log.info("up+++saveSchool+++++++++保存学校++++++++++++++++++++++++++++++openId="+openId);
			if(null!=schoolId&&schoolId.trim().length()>0){
				session.setAttribute("schoolId", schoolId);
				homeService.saveSchool(openId,schoolId);
			}
			return "/wap/home/toIndex.html";
		} catch (Exception e) {
			e.printStackTrace();
			return "/wap/home/toIndex.html";
		}
	}
	/**
	 * 
	 * 项目名称：dlws-xiaoka-game
	 * 描述：up去维护个人信息
	 * 创建人： ln
	 * 创建时间： 2016年8月15日
	 * 标记：
	 * @param request
	 * @param pager
	 * @return
	 * @version
	 */
	@At
	@Ok("jsp:wap.login")
	public Object toSaveCustomerInfo( @Param("customerName") final String customerName,@Param("..") final Pager pager) {
		return "";
	}
	/**
	 * 
	 * 项目名称：dlws-xiaoka-game
	 * 描述：up保存用户信息
	 * 创建人： ln
	 * 创建时间： 2016年8月15日
	 * 标记：wap
	 * @param customerName
	 * @param pager
	 * @return
	 * @version
	 */
	@At
	@Ok(">>:${obj}")
	public Object saveCustomerInfo(HttpSession session,@Param("customerName") final String customerName,@Param("..") final Pager pager) {
		
		String openId = String.valueOf(session.getAttribute("openId"));
		
		homeService.saveCustomerInfo(customerName,openId);
		//默认给用户账户充值1元
		homeService.saveAccount(openId);
		return "/wap/home/toIndex.html";
		
	}
	/**
	 * 跳转到首页
	 * @param session
	 * @return
	 */
	@At
	@Ok("jsp")
	public Object index(@Param("..") final HttpSession session) {
		return homeService.index(session);
	}
	
	/**
	 * 签到
	 * @param session
	 * @return
	 */
	@At
	public Object signIn(@Param("..") final HttpSession session) {
		return homeService.signIn(session);
	}
	
	/**
	 * 签到页面
	 * @param session
	 * @return
	 */
	@At
	@GET
	@Ok("jsp")
	public Object attend(@Param("..") final HttpSession session) {
		return homeService.attend(session);
	}
	
	/**
	 * 签到页面
	 * @param session
	 * @return
	 */
	@At
	@POST
	@Ok("json")
	@NoFilter
	public Object attendSignIn(@Param("..") final HttpSession session,@Param("..") final AttendForm form) {
		return homeService.attendSignIn(session,form);
	}
	
	/**
	 * 获取js签名
	 */
	@At
	@Filters
	public Object getJSConfig(@Param("clientUrl") final String clientUrl) {
		String post = null ;
		try{
			String url = wxPayConfig.getValue("weixin_js_config_url");
			//String ecodUrl = URLEncoder.encode(clientUrl,"UTF-8");
			Map<String,Object> send = MapUtil.map() ;
			send.put("clientUrl", clientUrl) ;
			post = Http.post(url,send,10000) ;
		}catch(Exception e){
			e.printStackTrace();
			log.info("<<<<<<<<<<<ajax方式获取js签名失败<<<<<<<<<<<<");
			return post;
		}
		return post;
	}
	/**
	 * 微信分享入口
	 * @param session
	 * @param shareUrl 被分享页面的url
	 * @return
	 * @throws IllegalArgumentException
	 */
	@Ok(">>:${obj}")
	@Filters
	@At
	public Object wxShare(final HttpServletRequest req,@Param("shareUrl") final String shareUrl){ 
		log.info("分享连接shareUrl==="+shareUrl);
		return shareUrl;
	}
	
	
	/**
	 * 执行定时任务
	 * @param req
	 * @param shareUrl
	 * @return
	 */
	@Ok(">>:${obj}")
	@Filters
	@At
	public Object task(){ 
		homeService.divided();
		return "/wap/home/index.html";
	}
	
}
