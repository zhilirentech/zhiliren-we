package com.xiaoka.game.admin.robot.module;

import java.util.Map;

import org.nutz.dao.pager.Pager;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.GET;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.POST;
import org.nutz.mvc.annotation.Param;

import com.uxuexi.core.common.util.EnumUtil;
import com.uxuexi.core.common.util.MapUtil;
import com.uxuexi.core.web.chain.support.JsonResult;
import com.uxuexi.core.web.util.FormUtil;
import com.xiaoka.game.admin.authority.usermanage.entity.UserEntity;
import com.xiaoka.game.admin.robot.form.RobotAddForm;
import com.xiaoka.game.admin.robot.form.RobotModForm;
import com.xiaoka.game.admin.robot.service.RobotService;
import com.xiaoka.game.admin.signin.form.SignInQueryForm;
import com.xiaoka.game.common.enums.RobotStatusEnum;
import com.xiaoka.game.common.enums.UserTypeEnum;

/**
 * 
 * 功能管理	控制类
 *
 */
@IocBean
@At("/admin/robot")
public class RobotModule {
	@Inject
	private RobotService robotService;
	
	
	
	
	
	/**
	 * 跳转到添加机器人页面
	 * @param queryForm  查询表单
	 * @param pager      分页对象
	 */
	@At
	@Ok("jsp")
	@GET
	public Object add() {
		Map<String,Object> obj = MapUtil.map() ;
		obj.put("robotStatusEnum", EnumUtil.enum2(RobotStatusEnum.class)) ;
		return obj;
	}
	/**
	 * 跳转到添加机器人页面
	 * @param queryForm  查询表单
	 * @param pager      分页对象
	 */
	@At
	@POST
	public Object add(@Param("..") final RobotAddForm addForm) {
		return robotService.add(addForm);
	}
	/**
	 * 跳转到添加机器人页面
	 * @param queryForm  查询表单
	 * @param pager      分页对象
	 */
	@At
	@Ok("jsp")
	@GET
	public Object update(@Param("id") final long id) {
		return robotService.findRobot(id); 
	}
	/**
	 * 跳转到添加机器人页面
	 * @param queryForm  查询表单
	 * @param pager      分页对象
	 */
	@At
	@Ok("jsp")
	@POST
	public Object update(@Param("..") final RobotModForm modForm) {
		robotService.update(modForm); 
		return JsonResult.success("操作成功") ;
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
	public Object list(@Param("..") final Pager pager) {
		return robotService.list(pager);
	}
	/**
	 * 删除记录
	 */
	@At
	public Object delete(@Param("id") final long id) {
		return robotService.delete(id);
	}
	/**
	 * 删除记录
	 */
	@At
	public Object delsign(@Param("id") final long id) {
		return robotService.delsign(id);
	}
	/**
	 * 显示参于记录
	 */
	@At
	@Ok("jsp")
	public Object show(@Param("openId") final String openId,@Param("..") final Pager pager) {
		return robotService.show(openId,pager);
	}
	/**
	 * 生成参于记录
	 */
	@At
	//@Ok(">>:/admin/robot/show.html?openId=${obj}")
	public Object create(@Param("openId") final String openId) {
		return robotService.create(openId);
	}

}
