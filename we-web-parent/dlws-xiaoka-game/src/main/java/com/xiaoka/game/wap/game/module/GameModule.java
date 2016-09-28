package com.xiaoka.game.wap.game.module;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.nutz.dao.pager.Pager;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.By;
import org.nutz.mvc.annotation.Filters;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.xiaoka.game.common.actionfilter.CacheUserFilter;
import com.xiaoka.game.common.actionfilter.WxAuthFilter;
import com.xiaoka.game.common.annotation.NoFilter;
import com.xiaoka.game.wap.game.form.GameAddForm;
import com.xiaoka.game.wap.game.service.GameService;

@IocBean
@At("/wap/game")
@Filters({ @By(type = CacheUserFilter.class),@By(type = WxAuthFilter.class)})
public class GameModule {
	private static Logger log = LoggerFactory.getLogger(GameModule.class);
	
	@Inject
	GameService gameService ;
	@At
	/**
	 * 找人up
	 * @param session
	 * @return
	 */
	@Ok(">>:${obj.url}")
	public Object game( @Param("..") final HttpSession session) {
		return gameService.game(session) ;
	}
	/**
	 * 发起up
	 * @param session
	 * @return
	 */
	@At
	@Ok("jsp")
	public Object launch( @Param("..") final HttpSession session) {
		return gameService.launch(session) ;
	}
	/**
	 * 保存up信息
	 * @param session
	 * @param addForm
	 * @return
	 */
	@At
	@NoFilter
	public Object add(@Param("..") final HttpServletRequest request,@Param("..") final GameAddForm addForm) {
		return gameService.add(request,addForm) ;
	}
	/**
	 * 跳转到保存up成功页
	 * @param session
	 * @param addForm
	 * @return
	 */
	@At
	@Ok("jsp")
	public Object show(@Param("..") final HttpSession session,@Param("id") final Integer id) {
		return gameService.show(session,id) ;
	}
	/**
	 * pk签到
	 * @param session
	 * @param addForm
	 * @return
	 */
	@At
	@NoFilter
	public Object signIn(@Param("..") final HttpSession session) {
		return gameService.signIn(session) ;
	}
	/**
	 * 去分享入口
	 * @param session
	 * @param addForm
	 * @return
	 */
	@At
	@Ok("jsp")
	public Object entrance(@Param("id") final Long id) {
		return gameService.entrance(id);
		//session.setAttribute("targetUrl", "/wap/game/show.html?id="+id);
		//return "/wap/home/toIndex.html";
	}
	
	/**
	 * 去参于Pk
	 * @param session
	 * @param addForm
	 * @return
	 */
	@At
	@Ok(">>:${obj}")
	public Object toAttend(@Param("..") final HttpSession session,@Param("id") final Long id) {
		session.setAttribute("targetUrl", "/wap/game/show.html?id="+id);
		return "/wap/home/toIndex.html";
	}
	
	/**
	 * 接受PK
	 * @param session
	 * @param addForm
	 * @return
	 */
	@At
	@NoFilter
	public Object acceptPK(@Param("..") final HttpServletRequest request,@Param("id") final Integer id) {
		return gameService.acceptPK(request,id) ;
	}
	
	
}
