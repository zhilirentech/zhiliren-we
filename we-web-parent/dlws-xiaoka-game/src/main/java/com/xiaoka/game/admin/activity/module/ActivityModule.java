package com.xiaoka.game.admin.activity.module;

import java.util.HashMap;
import java.util.Map;

import org.nutz.dao.SqlManager;
import org.nutz.dao.pager.Pager;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.GET;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.POST;
import org.nutz.mvc.annotation.Param;

import com.uxuexi.core.common.util.DateTimeUtil;
import com.uxuexi.core.db.dao.IDbDao;
import com.uxuexi.core.web.chain.support.JsonResult;
import com.uxuexi.core.web.util.FormUtil;
import com.xiaoka.game.admin.activity.entity.ActivityEntity;
import com.xiaoka.game.admin.activity.form.ActivityAddForm;
import com.xiaoka.game.admin.activity.form.ActivityDeleteModForm;
import com.xiaoka.game.admin.activity.form.ActivityModForm;
import com.xiaoka.game.admin.activity.form.ActivityQueryForm;

/**
 * 
 * 功能管理	控制类
 *
 */
@IocBean
@At("/admin/activity")
public class ActivityModule {
	/**
	 * 注入容器中的dbDao对象，用于数据库查询、持久操作
	 */
	@Inject
	private IDbDao dbDao;
	
	/**
	 * 注入容器中管理sql的对象，用于从sql文件中根据key取得sql
	 */
	@Inject
	private SqlManager sqlManager;

	/**
	 * 跳转到'添加操作'的录入数据页面
	 */
	@At
	@GET
	@Ok("jsp")
	public void add() {
	}

	/**
	 * 添加
	 * 
	 * @param addForm 添加表单对象
	 */
	@At
	@POST
	public Object add(@Param("..") final ActivityAddForm addForm) { 
		addForm.setCreateTime(DateTimeUtil.nowDateTime());
		FormUtil.add(dbDao, addForm, ActivityEntity.class);
		return JsonResult.success("添加成功");
	}

	/**
	 * 跳转到'修改操作'的录入数据页面,实际就是[按照主键查询单个实体]
	 */
	@At
	@GET
	@Ok("jsp")
	public Object update(@Param("id") final long id) {
		Map<String,Object> obj = new HashMap<String, Object>() ;
		obj.put("Activity", dbDao.fetch(ActivityEntity.class, id)) ;
		return obj;
	}

	/**
	 * 执行'修改操作'
	 */
	@At
	@POST
	public Object update(@Param("..") final ActivityModForm modForm) { 
		FormUtil.modify(dbDao, modForm, ActivityEntity.class);
		return JsonResult.success("修改成功");
	}
	
	

	/**
	 * 分页查询功能
	 * <P>
	 * 
	 * @param queryForm  查询表单
	 * @param pager      分页对象
	 */
	@At
	@Ok("jsp")
	public Object list(@Param("..") final ActivityQueryForm queryForm, @Param("..") final Pager pager) {
		Map<String,Object> obj = FormUtil.query(dbDao, ActivityEntity.class, queryForm.cnd(), pager);
		obj.put("title", queryForm.getTitle());
		obj.put("beginTime", queryForm.getBeginTime());
		obj.put("endTime", queryForm.getEndTime());
		return obj;
	}

	
	/**
	 * 删除记录
	 */
	/*@At
	public Object delete(@Param("id") final long id) {
		FormUtil.delete(dbDao, ActivityEntity.class, id);
		return JsonResult.success("删除成功");
	}*/
	
	/**
	 * 执行删除操作
	 */
	@At
	@POST
	public Object delete(@Param("..") final ActivityDeleteModForm modForm) { 
		FormUtil.modify(dbDao, modForm, ActivityEntity.class);
		return JsonResult.success("删除成功");
	}
	
	/**
	 * 跳转到'修改操作'的录入数据页面,实际就是[按照主键查询单个实体]
	 */
	@At
	@GET
	@Ok("jsp")
	public Object activDetail(@Param("id") final long id) {
		Map<String,Object> obj = new HashMap<String, Object>() ;
		obj.put("Activity", dbDao.fetch(ActivityEntity.class, id)) ;
		return obj;
	}
	
}
