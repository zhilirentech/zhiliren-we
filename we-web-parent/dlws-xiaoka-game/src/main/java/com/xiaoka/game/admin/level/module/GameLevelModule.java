package com.xiaoka.game.admin.level.module;

import java.util.HashMap;
import java.util.Map;

import org.nutz.dao.pager.Pager;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.POST;
import org.nutz.mvc.annotation.Param;

import com.uxuexi.core.db.dao.IDbDao;
import com.uxuexi.core.web.chain.support.JsonResult;
import com.uxuexi.core.web.util.FormUtil;
import com.xiaoka.game.admin.level.entity.GameLevelEntity;
import com.xiaoka.game.admin.level.form.GameLevelDelForm;
import com.xiaoka.game.admin.level.form.GameLevelForm;
import com.xiaoka.game.admin.level.form.GameLevelModForm;
import com.xiaoka.game.admin.level.form.GameLevelSqlForm;
import com.xiaoka.game.admin.level.service.GameLevelService;

@IocBean
@At("/admin/gameLevel")
public class GameLevelModule {
	/**
	 * 注入容器中的dbDao对象，用于数据库查询、持久操作
	 */
	@Inject
	private IDbDao dbDao;
	
	@Inject
	private GameLevelService gameLevelService;
	
	
	/**
	 * 
	 * 项目名称：dlws-xiaoka-game
	 * 描述：游戏分页查询
	 * 创建人： ln
	 * 创建时间： 2016年8月11日
	 * 标记：admin
	 * @param queryForm 查询表单
	 * @param pager 	分页对象
	 * @return
	 * @version
	 */
	@At
	@Ok("jsp:admin.gameLevel.list")
	public Object list(@Param("..") final GameLevelSqlForm sqlForm, @Param("..") final Pager pager) {
		sqlForm.setIsDel(1);
		return gameLevelService.queryGameLevelList(sqlForm,pager);
	/*return FormUtil.query(dbDao, sqlManager, sqlForm, pager);*/
	}
	
	/**
	 * 
	 * 项目名称：dlws-xiaoka-game
	 * 描述：toAdd页面
	 * 创建人： ln
	 * 创建时间： 2016年8月19日
	 * 标记：admin
	 * @version
	 */
	@At
	@Ok("jsp:admin.gameLevel.add")
	public void toAdd() {
		
	}
	
	/**
	 * 
	 * 项目名称：dlws-xiaoka-game
	 * 描述：添加
	 * 创建人： ln
	 * 创建时间： 2016年8月19日
	 * 标记：
	 * @param addForm
	 * @return
	 * @version
	 */
	@At
	@POST
	public Object add(@Param("..") final GameLevelForm addForm) { 
		addForm.setIsDel(1);
		FormUtil.add(dbDao, addForm, GameLevelEntity.class);
		return JsonResult.success("添加成功");
	}
	/**
	 * 
	 * 项目名称：dlws-xiaoka-game
	 * 描述：去修改页面
	 * 创建人： ln
	 * 创建时间： 2016年8月19日
	 * 标记：wap
	 * @param id
	 * @return
	 * @version
	 */
	@At
	@Ok("jsp:admin.gameLevel.update")
	public Object update(@Param("id") final long id) {
		Map<String,Object> obj = new HashMap<String, Object>() ;
		obj.put("gameLevel", dbDao.fetch(GameLevelEntity.class, id)) ;
		return obj;
	}
	
	
	/**
	 * 
	 * 项目名称：dlws-xiaoka-game
	 * 描述：修改游戏等级
	 * 创建人： ln
	 * 创建时间： 2016年8月19日
	 * 标记：
	 * @param modForm
	 * @return
	 * @version
	 */
	@At
	@POST
	public Object update(@Param("..") final GameLevelModForm modForm) { 
		FormUtil.modify(dbDao, modForm, GameLevelEntity.class);
		return JsonResult.success("修改成功");
	}
	
	
	/**
	 * 执行删除操作
	 */
	@At
	@POST
	public Object delete(@Param("..") final GameLevelDelForm modForm) { 
		modForm.setIsDel(0);
		FormUtil.modify(dbDao, modForm, GameLevelEntity.class);
		return JsonResult.success("删除成功");
	}

	
}














