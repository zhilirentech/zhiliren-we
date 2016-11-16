package com.linyun.airline.admin.authority.functionmanage.module;

import java.util.HashMap;
import java.util.Map;

import org.nutz.dao.Cnd;
import org.nutz.dao.SqlManager;
import org.nutz.dao.pager.Pager;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.GET;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.POST;
import org.nutz.mvc.annotation.Param;

import com.linyun.airline.admin.authority.functionmanage.entity.FunctionEntity;
import com.linyun.airline.admin.authority.functionmanage.form.FunctionAddForm;
import com.linyun.airline.admin.authority.functionmanage.form.FunctionModForm;
import com.linyun.airline.admin.authority.functionmanage.form.FunctionSqlForm;
import com.uxuexi.core.common.util.DateTimeUtil;
import com.uxuexi.core.db.dao.IDbDao;
import com.uxuexi.core.web.chain.support.JsonResult;
import com.uxuexi.core.web.util.FormUtil;

/**
 * 
 * 功能管理	控制类
 *
 */
@IocBean
@At("/admin/authority/function")
public class FunctionModule {
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
	public Object add() {
		return dbDao.query(FunctionEntity.class, null, null);
	}

	/**
	 * 添加
	 * 
	 * @param addForm 添加表单对象
	 */
	@At
	@POST
	public Object add(@Param("..") final FunctionAddForm addForm) {
		addForm.setCreateTime(DateTimeUtil.nowDateTime());
		FormUtil.add(dbDao, addForm, FunctionEntity.class);
		return JsonResult.success("添加成功");
	}

	/**
	 * 跳转到'修改操作'的录入数据页面,实际就是[按照主键查询单个实体]
	 */
	@At
	@GET
	@Ok("jsp")
	public Object update(@Param("id") final long id) {
		Map<String, Object> obj = new HashMap<String, Object>();
		//上级功能选择的时候要排除自己
		obj.put("list", dbDao.query(FunctionEntity.class, Cnd.where("id", "!=", id), null));
		obj.put("function", dbDao.fetch(FunctionEntity.class, id));
		return obj;
	}

	/**
	 * 执行'修改操作'
	 */
	@At
	@POST
	public Object update(@Param("..") final FunctionModForm modForm) {
		modForm.setUpdateTime(DateTimeUtil.nowDateTime());
		FormUtil.modify(dbDao, modForm, FunctionEntity.class);
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
	public Object list(@Param("..") final FunctionSqlForm sqlForm, @Param("..") final Pager pager) {
		Map<String, Object> obj = FormUtil.query(dbDao, sqlManager, sqlForm, pager);
		obj.put("functions", dbDao.query(FunctionEntity.class, Cnd.where("level", "<=", 2), null));
		return obj;
	}

	/**
	 * 删除记录
	 */
	@At
	public Object delete(@Param("id") final long id) {
		FormUtil.delete(dbDao, FunctionEntity.class, id);
		return JsonResult.success("删除成功");
	}

	/**
	 * 批量删除记录
	 */
	@At
	public Object batchDelete(@Param("ids") final long[] ids) {
		FormUtil.delete(dbDao, FunctionEntity.class, ids);
		return JsonResult.success("删除成功");
	}

}
