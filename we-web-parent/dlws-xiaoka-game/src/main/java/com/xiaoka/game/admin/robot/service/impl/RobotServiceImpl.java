package com.xiaoka.game.admin.robot.service.impl;

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

@IocBean(name = "robotService")
public class RobotServiceImpl extends BaseService implements RobotService {

	@Override
	public Object list(Pager pager) {
		return FormUtil.query(dbDao, RobotEntity.class, Cnd.limit(), pager);
	}

	@Override
	public Object add(RobotAddForm addForm) {
		FormUtil.add(dbDao, addForm, RobotEntity.class);
		return JsonResult.success("添加成功");
	}

	@Override
	public Object findRobot(long id) {
		Map<String, Object> map = MapUtil.map();
		map.put("entity", dbDao.fetch(RobotEntity.class, id));
		map.put("robotStatusEnum", EnumUtil.enum2(RobotStatusEnum.class));
		return map;
	}

	@Override
	public Object update(RobotModForm modForm) {
		FormUtil.modify(dbDao, modForm, RobotEntity.class);
		return true;
	}

	@Override
	public Object delete(long id) {
		FormUtil.delete(dbDao, RobotEntity.class, id);
		return JsonResult.success("删除成功");
	}

	@Override
	public Object show(String openId, Pager pager) {
		return FormUtil.query(dbDao, SignInEntity.class,
				Cnd.where("openId", "=", openId).and("type", "=", SignInTypeEnum.NATIONWIDE.intKey()), pager);
	}

	@Override
	public Object create(String openId) {
		RobotEntity robot = dbDao.fetch(RobotEntity.class, Cnd.where("openId", "=", openId));
		//开始日期必须大于当前日期
		Date currDate = DateTimeUtil.todayDate();
		Date beginDate = robot.getBeginDate();
		if (currDate.after(beginDate) || currDate.equals(beginDate)) {
			return JsonResult.error("开始日期必须大于当前日期");
		}
		int days = robot.getDays();
		Double inAmount = robot.getInAmount();
		SignInEntity sign = new SignInEntity();
		sign.setGameId(0);
		sign.setInAmount(inAmount);
		sign.setOpenId(openId);
		sign.setOutAmount(0);
		sign.setStatus(SignInStatusEnum.YES.intKey());
		sign.setType(SignInTypeEnum.NATIONWIDE.intKey());
		sign.setGameId(0);
		for (int i = 0; i < days; i++) {
			Date addDay = DateUtil.addDay(beginDate, i);
			String addDayStr = new SimpleDateFormat("yyyy-MM-dd").format(addDay);
			sign.setSignDate(java.sql.Date.valueOf(addDayStr));
			sign.setCreateTime(DateTimeUtil.nowDateTime());
			dbDao.insert(sign);
		}
		return JsonResult.success("生成成功");
	}

	@Override
	public Object delsign(long id) {
		dbDao.delete(SignInEntity.class, id) ;
		return JsonResult.success("删除成功");
	}

}
