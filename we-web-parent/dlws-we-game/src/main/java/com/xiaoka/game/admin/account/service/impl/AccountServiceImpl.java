package com.xiaoka.game.admin.account.service.impl;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.nutz.dao.Cnd;
import org.nutz.dao.pager.Pager;
import org.nutz.ioc.loader.annotation.IocBean;

import com.uxuexi.core.common.util.DateTimeUtil;
import com.uxuexi.core.common.util.EnumUtil;
import com.uxuexi.core.common.util.MapUtil;
import com.uxuexi.core.web.chain.support.JsonResult;
import com.uxuexi.core.web.util.FormUtil;
import com.xiaoka.game.admin.account.entity.AccountEntity;
import com.xiaoka.game.admin.account.form.AccountQueryForm;
import com.xiaoka.game.admin.account.service.AccountService;
import com.xiaoka.game.admin.authority.usermanage.entity.UserEntity;
import com.xiaoka.game.admin.pkrecord.entity.SignInEntity;
import com.xiaoka.game.admin.robot.entity.RobotEntity;
import com.xiaoka.game.admin.robot.form.RobotAddForm;
import com.xiaoka.game.admin.robot.form.RobotModForm;
import com.xiaoka.game.admin.robot.service.RobotService;
import com.xiaoka.game.common.enums.RobotStatusEnum;
import com.xiaoka.game.common.enums.SignInStatusEnum;
import com.xiaoka.game.common.enums.SignInTypeEnum;
import com.xiaoka.game.common.service.BaseService;
import com.xiaoka.game.common.util.DateUtil;

@IocBean(name="accountService")
public class AccountServiceImpl extends  BaseService implements AccountService{

	@Override
	public Map<String,Object> list(AccountQueryForm queryForm, Pager pager) {
		return FormUtil.query(dbDao, sqlManager, queryForm, pager);
	}

	
	
}
