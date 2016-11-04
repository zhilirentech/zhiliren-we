package com.xiaoka.game.wap.home.module;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.nutz.dao.pager.Pager;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.json.Json;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.By;
import org.nutz.mvc.annotation.Filters;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.uxuexi.core.db.dao.IDbDao;
import com.xiaoka.game.common.actionfilter.CacheUserFilter;
import com.xiaoka.game.common.actionfilter.WxAuthFilter;
import com.xiaoka.game.common.annotation.NoFilter;
import com.xiaoka.game.wap.home.entity.SchoolEntity;
import com.xiaoka.game.wap.home.service.HomeService;

@IocBean
@At("/wap/ajax/home")
@Filters({ @By(type = CacheUserFilter.class),@By(type = WxAuthFilter.class)})
public class HomeAjaxModule {
	private static Logger log = LoggerFactory.getLogger(HomeModule.class);
	/**
	 * 注入容器中的dbDao对象，用于数据库查询、持久操作
	 */
	@Inject
	private IDbDao dbDao;
	@Inject
	private HomeService homeService; 
	
	
	/**
	 * 
	 * 项目名称：dlws-xiaoka-game
	 * 描述：查询学校列表
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
	@Ok("json")
	@NoFilter
	public Object toSelectSchool(HttpServletRequest request,@Param("schoolName") final String schoolName, @Param("..") final Pager pager) {
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		List<SchoolEntity> schools = null;
		try {
			schools = homeService.findSchoolInfosBySchoolName(schoolName);
			
			resultMap.put("list", schools);
			resultMap.put("ajax_status", "ajax_status_success");
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("info", "数据错误");
			resultMap.put("ajax_status", "ajax_status_failure");
		}
		String resStr = Json.toJson(resultMap);
		return resStr;
	}
}
