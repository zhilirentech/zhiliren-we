package com.linyun.airline.admin.authority.rolemanage.module;

import org.nutz.dao.pager.Pager;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.GET;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.POST;
import org.nutz.mvc.annotation.Param;

import com.linyun.airline.admin.authority.rolemanage.entity.RoleEntity;
import com.linyun.airline.admin.authority.rolemanage.form.RoleAddForm;
import com.linyun.airline.admin.authority.rolemanage.form.RoleModForm;
import com.linyun.airline.admin.authority.rolemanage.form.RoleQueryForm;
import com.linyun.airline.admin.authority.rolemanage.service.RoleService;
import com.uxuexi.core.common.util.DateTimeUtil;
import com.uxuexi.core.db.dao.IDbDao;
import com.uxuexi.core.web.chain.support.JsonResult;
import com.uxuexi.core.web.util.FormUtil;

@IocBean
@At("/admin/authority/role")
public class RoleModule {

	/**
	 * 注入容器中的dbDao对象，用于数据库查询、持久操作
	 */
	@Inject
	private IDbDao dbDao;

	@Inject
	private RoleService roleService;

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
	public Object add(@Param("..") final RoleAddForm addForm) {
		addForm.setCreateTime(DateTimeUtil.nowDateTime());
		FormUtil.add(dbDao, addForm, RoleEntity.class);
		return JsonResult.success("添加成功");
	}

	/**
	 * 跳转到'修改操作'的录入数据页面,实际就是[按照主键查询单个实体]
	 */
	@At
	@GET
	@Ok("jsp")
	public Object update(@Param("id") final long id) {
		return roleService.findRole(id);
	}

	/**
	 * 执行'修改操作'
	 */
	@At
	@POST
	public Object update(@Param("..") final RoleModForm modForm) {
		roleService.update(modForm);
		return JsonResult.success("修改成功");
	}

	/**
	 * 分页查询
	 * <P>
	 * 
	 * @param queryForm  查询表单
	 * @param pager      分页对象
	 */
	@At
	@Ok("jsp")
	public Object list(@Param("..") final RoleQueryForm queryForm, @Param("..") final Pager pager) {
		return FormUtil.query(dbDao, RoleEntity.class, queryForm, pager);
	}

	/**
	 * 删除记录
	 */
	@At
	public Object delete(@Param("id") final long id) {
		FormUtil.delete(dbDao, RoleEntity.class, id);
		return JsonResult.success("删除成功");
	}

	/**
	 * 批量删除记录
	 */
	@At
	public Object batchDelete(@Param("ids") final long[] ids) {
		FormUtil.delete(dbDao, RoleEntity.class, ids);
		return JsonResult.success("删除成功");
	}

}
