package com.xiaoka.game.admin.account.module;

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
import com.xiaoka.game.admin.account.form.AccountQueryForm;
import com.xiaoka.game.admin.account.service.AccountService;
import com.xiaoka.game.admin.authority.usermanage.entity.UserEntity;
import com.xiaoka.game.admin.robot.form.RobotAddForm;
import com.xiaoka.game.admin.robot.form.RobotModForm;
import com.xiaoka.game.admin.robot.service.RobotService;
import com.xiaoka.game.admin.signin.form.SignInQueryForm;
import com.xiaoka.game.common.enums.AccountTypeEnum;
import com.xiaoka.game.common.enums.RobotStatusEnum;
import com.xiaoka.game.common.enums.UserTypeEnum;

/**
 * 
 *
 */
@IocBean
@At("/admin/account")
public class AccountModule {
	@Inject
	private AccountService accountService;
	
	
	
	
	
	/**
	 * 充值记录
	 * @param queryForm  查询表单
	 * @param pager      分页对象
	 */
	@At
	@Ok("jsp")
	@GET
	public Object list(@Param("..") final AccountQueryForm queryForm, @Param("..") final Pager pager) {
		Map<String, Object> map = accountService.list(queryForm,pager);
		map.put("accountTypeEnum", EnumUtil.enum2(AccountTypeEnum.class));
		return map;
	}
	
}
