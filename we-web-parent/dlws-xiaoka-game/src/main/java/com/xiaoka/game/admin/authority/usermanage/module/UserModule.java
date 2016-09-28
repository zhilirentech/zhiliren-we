/**
 * UserModule.java
 * com.xiaoka.web.authority.usermanage.module
 * Copyright (c) 2014, 北京世纪新干线科技有限公司版权所有.
*/

package com.xiaoka.game.admin.authority.usermanage.module;

import java.util.Map;

import org.nutz.dao.pager.Pager;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.GET;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.POST;
import org.nutz.mvc.annotation.Param;

import com.xiaoka.game.admin.authority.usermanage.entity.UserEntity;
import com.xiaoka.game.admin.authority.usermanage.form.UserAddForm;
import com.xiaoka.game.admin.authority.usermanage.form.UserModForm;
import com.xiaoka.game.admin.authority.usermanage.form.UserQueryForm;
import com.xiaoka.game.admin.authority.usermanage.service.UserService;
import com.xiaoka.game.common.enums.UserStatusEnum;
import com.xiaoka.game.common.enums.UserTypeEnum;
import com.uxuexi.core.common.util.DateTimeUtil;
import com.uxuexi.core.common.util.EnumUtil;
import com.uxuexi.core.common.util.MapUtil;
import com.uxuexi.core.db.dao.IDbDao;
import com.uxuexi.core.web.chain.support.JsonResult;
import com.uxuexi.core.web.util.FormUtil;

/**
 * 用户管理  控制类
 * 
 * @author   朱晓川
 * @Date	 2014-10-21	 
 */
@IocBean
@At("/admin/authority/user")
public class UserModule {

	/**
	 * 注入容器中的dbDao对象，用于数据库查询、持久操作
	 */
	@Inject
	private IDbDao dbDao;
	
	@Inject
	private UserService userService;

	/**
	 * 跳转到'添加操作'的录入数据页面
	 */
	@At
	@GET
	@Ok("jsp")
	public Object add() {
		Map<String,Object> obj = MapUtil.map() ;
		obj.put("userTypeEnum", EnumUtil.enum2(UserTypeEnum.class)) ;
		obj.put("userStatusEnum", EnumUtil.enum2(UserStatusEnum.class)) ;
		return  obj;
	}

	/**
	 * 添加用户
	 * 
	 * @param addForm 添加表单对象
	 */
	@At
	@POST
	public Object add(@Param("..") final UserAddForm addForm) { 
		addForm.setCreateTime(DateTimeUtil.nowDateTime()) ;
		FormUtil.add(dbDao, addForm, UserEntity.class);
		return JsonResult.success("添加成功");
	}

	/**
	 * 跳转到'修改操作'的录入数据页面,实际就是[按照主键查询单个实体]
	 */
	@At
	@GET
	@Ok("jsp")
	public Object update(@Param("id") final long userId) {
		return userService.findUser(userId); 
	}

	/**
	 * 执行'修改操作'
	 */
	@At
	@POST
	public Object update(@Param("::user.") final UserModForm modForm) {
		userService.update(modForm) ;
		return JsonResult.success("修改成功", "user.list", true);  
	}

	/**
	 * 分页查询用户
	 * <P>
	 * 
	 * @param queryForm  查询表单
	 * @param pager      分页对象
	 */
	@At
	@Ok("jsp")
	public Object list(@Param("..") final UserQueryForm queryForm, @Param("..") final Pager pager) {
		Object obj = FormUtil.query(dbDao, UserEntity.class, queryForm, pager);
		return FormUtil.query(dbDao, UserEntity.class, queryForm, pager);
	}

	/**
	 * 删除记录
	 */
	@At
	public Object delete(@Param("id") final long id) {
		FormUtil.delete(dbDao, UserEntity.class, id);
		return JsonResult.success("删除成功");
	}

	/**
	 * 批量删除记录
	 */
	@At
	public Object batchDelete(@Param("ids") final long[] ids) {
		FormUtil.delete(dbDao, UserEntity.class, ids);
		return JsonResult.success("删除成功");
	}
}
