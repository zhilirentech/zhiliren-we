package com.xiaoka.game.wap.center.module;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.nutz.dao.pager.Pager;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.json.Json;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Filters;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.uxuexi.core.db.dao.IDbDao;
import com.xiaoka.game.admin.banner.entity.BannerEntity;
import com.xiaoka.game.wap.center.entity.AccountEntity;
import com.xiaoka.game.wap.center.form.AccountSqlForm;
import com.xiaoka.game.wap.center.service.CenterService;
import com.xiaoka.game.wap.home.module.HomeModule;

@Filters
@IocBean
@At("/wap/ajax/center")
public class CenterAjaxModule {

	private static Logger log = LoggerFactory.getLogger(HomeModule.class);
	/**
	 * 注入容器中的dbDao对象，用于数据库查询、持久操作
	 */
	@Inject
	private IDbDao dbDao;
	@Inject
	private CenterService centerService; 

	/**
	 * 
	 * 项目名称：dlws-xiaoka-game
	 * 描述：异步获取账户信息列表（充值提现记录）
	 * 创建人： ln
	 * 创建时间： 2016年8月16日
	 * 标记：wap
	 * @param session
	 * @param schoolName
	 * @param pager
	 * @return
	 * @version
	 */
	@At
	@Ok("json")
	public Object queryAccount(HttpSession session,@Param("..") final AccountSqlForm sqlForm, @Param("..") final Pager pager) {
		
		List<AccountEntity> accounts = null;
		String openId  = String.valueOf(session.getAttribute("openId"));
		sqlForm.setOpenId(openId);
		try {
			//分页查询用户提现充值记录
			return centerService.queryAccountList(sqlForm,pager);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}
	/**
	 * 
	 * 项目名称：dlws-xiaoka-game
	 * 描述：保存用户提现申请
	 * 创建人： ln
	 * 创建时间： 2016年8月17日
	 * 标记：wap
	 * @param session
	 * @param money
	 * @param pager
	 * @return
	 * @version
	 */
	@At
	@Ok("json")
	public Object addWithdraw(HttpSession session,@Param("money") final double money, @Param("..") final Pager pager) {
		String openId  = String.valueOf(session.getAttribute("openId"));
		
		boolean flag = centerService.addWithdraw(money,openId,pager);
		
		return flag;
		
	}
	/**
	 * 获取bannerList
	 * 项目名称：dlws-xiaoka-game
	 * 描述：
	 * 创建人： ln
	 * 创建时间： 2016年8月17日
	 * 标记：
	 * @param session
	 * @param money
	 * @param pager
	 * @return
	 * @version
	 */
	@At
	@Ok("json")
	public Object queryBannerList(HttpSession session,@Param("money") final double money, @Param("..") final Pager pager) {
		int type = 2;
		Map<String,Object> map = new HashMap<String,Object>();
		//轮播图
		List<BannerEntity> bannerList =  centerService.queryBanner(type);
		map.put("list", bannerList);
		return map; 
	}
	/**活动轮播图**/
	@At
	@Ok("json")
	public Object ActiveBannerList(HttpSession session,@Param("money") final double money, @Param("..") final Pager pager) {
		int type = 1;
		Map<String,Object> map = new HashMap<String,Object>();
		//轮播图
		List<BannerEntity> bannerList =  centerService.queryBanner(type);
		map.put("list", bannerList);
		return map; 
	}
	
	
}




























