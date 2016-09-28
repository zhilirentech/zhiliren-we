package com.xiaoka.game.wap.game.service.impl;

import java.math.RoundingMode;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.nutz.dao.Chain;
import org.nutz.dao.Cnd;
import org.nutz.dao.Sqls;
import org.nutz.dao.entity.Record;
import org.nutz.dao.pager.Pager;
import org.nutz.dao.sql.Sql;
import org.nutz.ioc.aop.Aop;
import org.nutz.ioc.loader.annotation.IocBean;

import com.uxuexi.core.common.util.DateTimeUtil;
import com.uxuexi.core.common.util.MapUtil;
import com.uxuexi.core.common.util.Util;
import com.uxuexi.core.db.util.DbSqlUtil;
import com.uxuexi.core.web.util.FormUtil;
import com.xiaoka.game.admin.account.entity.AccountEntity;
import com.xiaoka.game.admin.bulletin.entity.BulletinEntity;
import com.xiaoka.game.admin.message.entity.MessageEntity;
import com.xiaoka.game.admin.pkrecord.entity.GameEntity;
import com.xiaoka.game.admin.pkrecord.entity.SignInEntity;
import com.xiaoka.game.common.enums.AccountTypeEnum;
import com.xiaoka.game.common.enums.BulletinTypeEnum;
import com.xiaoka.game.common.enums.GameStatusEnum;
import com.xiaoka.game.common.enums.IsDelEnum;
import com.xiaoka.game.common.enums.PlatTypeEnum;
import com.xiaoka.game.common.enums.SignInStatusEnum;
import com.xiaoka.game.common.enums.SignInTypeEnum;
import com.xiaoka.game.common.result.MobileResult;
import com.xiaoka.game.common.service.BaseService;
import com.xiaoka.game.wap.game.form.GameAddForm;
import com.xiaoka.game.wap.game.service.GameService;

@IocBean(name = "gameService")
public class GameServiceImpl extends BaseService implements GameService {

	@Override
	public Map<String, Object> game(HttpSession session) {
		Map<String, Object> res = MapUtil.map();
		// 当天日期
		String currDate = DateTimeUtil.todayDate().toString();
		String openId = (String) session.getAttribute("openId");
		// 查看当天是否参于pk游戏（一个人一天只能参加一次）
		List<SignInEntity> signList = dbDao.query(
				SignInEntity.class,
				Cnd.where("openId", "=", openId)
						.and("type", "=", SignInTypeEnum.PERSONAL.intKey())
						.and("DATE(signDate)", "=", currDate), null);
		// 如果未参与或者已签到或者签到超时
		List<SignInEntity> signListOfTomrrow = null;
		if (Util.isEmpty(signList)
				|| signList.get(0).getStatus() != SignInStatusEnum.NO.intKey()) {
			// 判断明天是否参与
			signListOfTomrrow = dbDao.query(
					SignInEntity.class,
					Cnd.where("openId", "=", openId)
							.and("type", "=", SignInTypeEnum.PERSONAL.intKey())
							.and("DATE(signDate)", "=",
									DateTimeUtil.tomorrowDate().toString()),
					null);

		} else if (signList.get(0).getStatus() == SignInStatusEnum.NO.intKey()) {
			// 已参与，未签到，去签到页
			res.put("url", "/wap/game/show.html?id="
					+ signList.get(0).getGameId());
			return res;
		}

		if (Util.isEmpty(signListOfTomrrow)) {
			// 明天未参与，去发起pk页
			res.put("url", "/wap/game/launch.html");
			return res;
		}
		// 明天已参与，去签到页
		res.put("url", "/wap/game/show.html?id="
				+ signListOfTomrrow.get(0).getGameId());
		return res;
	}

	@Override
	public Map<String, Object> launch(HttpSession session) {
		Map<String, Object> res = MapUtil.map();

		String openId = (String) session.getAttribute("openId");
		// 查询账户余额
		List<AccountEntity> accountList = dbDao.query(AccountEntity.class, Cnd
				.where("openId", "=", openId).desc("createTime"), new Pager()
				.setPageNumber(1).setPageSize(1));
		double balance = accountList == null ? 0 : accountList.size() == 0 ? 0
				: accountList.get(0).getBalance();
		res.put("balance", balance);

		// 查找PK规则
		List<BulletinEntity> bulletinList = dbDao.query(BulletinEntity.class,
				Cnd.where("type", "=", BulletinTypeEnum.PK.intKey()),
				new Pager().setPageNumber(1).setPageSize(1));
		res.put("bulletin", bulletinList == null ? null
				: bulletinList.size() == 0 ? null : bulletinList.get(0)
						.getContent());
		return res;

	}

	@Aop("txDb")
	@Override
	public Map<String, Object> add(HttpServletRequest req, GameAddForm addForm) {

		String openId = (String) (req.getSession().getAttribute("openId"));
		// 查询账户余额
		List<AccountEntity> accountList = dbDao.query(AccountEntity.class, Cnd
				.where("openId", "=", openId).desc("createTime"), new Pager()
				.setPageNumber(1).setPageSize(1));
		double balance = accountList == null ? 0 : accountList.size() == 0 ? 0
				: accountList.get(0).getBalance();

		double total = addForm.getMoney();
		// 校验账户余额是否大于投入金额
		if (total > balance) {
			return MobileResult.success("NOMONEY", null);
		}

		// 正在进行或者成功的pk游戏一天只能有一个
		List<GameEntity> gameList = dbDao.query(
				GameEntity.class,
				Cnd.where("founder", "=", openId)
						.and("status",
								"IN",
								GameStatusEnum.SUCCESS.intKey() + ","
										+ GameStatusEnum.UNDERWAY.intKey())
						.and("DATE(date)", "=", addForm.getDate().toString()), null);
		if (!Util.isEmpty(gameList)) {
			return MobileResult.success("REPEAT", null);
		}
		addForm.setFounder(openId);
		// 保存游戏信息
		GameEntity game = FormUtil.add(dbDao, addForm, GameEntity.class);
		// 保存签到信息
		SignInEntity sign = new SignInEntity();
		sign.setGameId(0);
		sign.setInAmount(total);
		sign.setOutAmount(0);
		sign.setStatus(SignInStatusEnum.NO.intKey());
		sign.setType(SignInTypeEnum.PERSONAL.intKey());
		sign.setGameId(game.getId());
		sign.setOpenId(openId);
		sign.setSignDate(addForm.getDate());
		sign.setCreateTime(DateTimeUtil.nowDateTime());
		dbDao.insert(sign);

		// 更新账户
		AccountEntity acc = new AccountEntity();
		acc.setBalance(balance - total);
		acc.setCreateTime(DateTimeUtil.nowDateTime());
		acc.setMoney(total);
		acc.setOpenId(openId);
		acc.setType(AccountTypeEnum.PUTIN.intKey());
		acc.setPlatType(PlatTypeEnum.XIAOKA.intKey());
		acc.setRemark("投注金额");
		dbDao.insert(acc);

		//保存消息
		String path = req.getContextPath();
		String basePath = req.getScheme()+"://"+req.getServerName()+":"+req.getServerPort()+path+"/";
		MessageEntity msg = new MessageEntity() ;
		msg.setContent("<a href='"+basePath+"wap/game/show?id="+game.getId()+"'>查看详情</a>");
		msg.setCreateTime(DateTimeUtil.nowDateTime());
		msg.setIsDelete(IsDelEnum.NO.intKey());
		msg.setStatus(1);//1为已发送
		msg.setTitle("您于"+game.getDate().toString()+"发起了PK游戏");
		msg.setOpenId(openId);
		dbDao.insert(msg);
		
		
		return MobileResult.success("OK", game.getId());
	}

	@Override
	public Map<String, Object> show(HttpSession session, Integer id) {

		String openId = (String) session.getAttribute("openId");

		Map<String, Object> res = MapUtil.map();
		GameEntity game = dbDao.fetch(GameEntity.class, (long) id);
		if (Util.isEmpty(game))
			return null;

		if (game.getStatus() == GameStatusEnum.UNDERWAY.intKey()
				|| game.getStatus() == GameStatusEnum.SUCCESS.intKey()) {
			res.put("beginTime", game.getBeginTime());
			res.put("endTime", game.getEndTime());
			// 获取pk全部人数
			Sql sql = Sqls.create(sqlManager.get("signin_list"));
			List<Record> list = dbDao.query(sql,
					Cnd.where("si.gameId", "=", id), null);
			List<Record> signList = new ArrayList<Record>();
			boolean isAttend = false;
			int status = 0;
			for (Record record : list) {
				if (openId.equals(record.get("openId"))) {
					isAttend = true;
					status = (int) record.get("status");
				}
				if ((int) record.get("status") == SignInStatusEnum.YES.intKey()) {
					signList.add(record);
				}
			}
			/*
			 * Gson gson = new GsonBuilder()
			 * .excludeFieldsWithoutExposeAnnotation()
			 * .setDateFormat("HH:mm").create();
			 */
			res.put("signList", signList);
			res.put("list", list);
			res.put("game", game);
			res.put("isAttend", isAttend);
			res.put("status", status);
			System.out
					.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++"
							+ res.toString());
			return res;
		}
		res.put("game", game);
		System.out
				.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++"
						+ res.toString());
		return res;
	}

	@Aop("txDb")
	@Override
	public Map<String, Object> signIn(HttpSession session) {
		Map<String, Object> resMap = MapUtil.map();
		String openId = (String) session.getAttribute("openId");
		String currDate = DateTimeUtil.todayDate().toString();
		// 判断当日是否参与签到和是否已签到
		List<SignInEntity> currSignList = dbDao.query(SignInEntity.class, Cnd
				.where("openId", "=", openId).and("DATE(signDate)", "=", currDate)
				.and("type", "=", SignInTypeEnum.PERSONAL.intKey()), null);

		if (Util.isEmpty(currSignList)) {
			return MobileResult.success("NOATTEND", null);// 未参加
		}
		SignInEntity signIn = currSignList.get(0);
		if (signIn.getStatus() == SignInStatusEnum.YES.intKey()) {
			return MobileResult.success("SIGNINED", null);// 已签到
		}

		// PK签到时间
		List<GameEntity> game = dbDao.query(GameEntity.class,
				Cnd.where("id", "=", signIn.getGameId()), null);
		Timestamp beginTime = game.get(0).getBeginTime();
		Timestamp endTime = game.get(0).getEndTime();
		Timestamp currTime = DateTimeUtil.nowDateTime();
		// 判断是否在签到时间范围内
		if (currTime.before(beginTime)) {
			return MobileResult.success("BEFORE", null);
		} else if (currTime.after(endTime)) {
			// 将签到状态置为超时
			dbDao.update(
					SignInEntity.class,
					Chain.make("status", SignInStatusEnum.OVERTIME.intKey())
							.add("signTime", DateTimeUtil.nowDateTime()),
					Cnd.where("openId", "=", openId)
							.and("DATE(signDate)", "=", currDate).and("type", "=", SignInTypeEnum.PERSONAL.intKey()));
			return MobileResult.success("AFTER", null);
		} else {
			// 更新新签到表的签到状态和签到时间
			dbDao.update(
					SignInEntity.class,
					Chain.make("status", SignInStatusEnum.YES.intKey()).add(
							"signTime", DateTimeUtil.nowDateTime()),
					Cnd.where("openId", "=", openId).and("DATE(signDate)", "=",
							currDate).and("type", "=", SignInTypeEnum.PERSONAL.intKey()));

			// 个人账户退还投注金额
			List<AccountEntity> accountList = dbDao.query(AccountEntity.class,
					Cnd.where("openId", "=", openId).desc("createTime"),
					getPager(1, 1));
			// 账户结余金额
			double balance = accountList == null ? 0
					: accountList.size() == 0 ? 0 : accountList.get(0)
							.getBalance();
			double inAmount = signIn.getInAmount();
			AccountEntity acc = new AccountEntity();
			acc.setBalance(balance + inAmount);
			acc.setCreateTime(DateTimeUtil.nowDateTime());
			acc.setMoney(inAmount);
			acc.setOpenId(openId);
			acc.setType(AccountTypeEnum.BACK.intKey());
			acc.setPlatType(PlatTypeEnum.XIAOKA.intKey());
			acc.setRemark("退还投注金额");
			dbDao.insert(acc);

			DecimalFormat df = new DecimalFormat("#.00");
			df.setRoundingMode(RoundingMode.DOWN);
			resMap.put("balance", df.format(balance + inAmount));
			return MobileResult.success("YES", resMap);
		}

	}

	private Pager getPager(int number, int size) {
		Pager pager = new Pager();
		pager.setPageNumber(number);
		pager.setPageSize(size);
		return pager;
	}

	@Override
	public Map<String, Object> acceptPK(HttpServletRequest req, Integer id) {
		String openId = (String) (req.getSession().getAttribute("openId"));
		GameEntity game = dbDao.fetch(GameEntity.class, (long) id);
		Map<String, Object> map = MapUtil.map();

		if (Util.isEmpty(game)) {
			return MobileResult.success("NODATA", null);
		}

		if (game.getStatus() == GameStatusEnum.UNDERWAY.intKey()) {
			// 获取账户余额
			// 查询账户余额
			Date gameDate = game.getDate();

			Sql sql = Sqls.create(sqlManager.get("sign_is_partic"));
			sql.params().set("openId", openId);
			sql.params().set("gameDate", gameDate.toString());
			sql.params().set("type", SignInTypeEnum.PERSONAL.intKey());
			List<SignInEntity> list = DbSqlUtil.query(dbDao,
					SignInEntity.class, sql);

			if (!Util.isEmpty(list)) {
				map.put("flag", false);
				map.put("msg", "已参加过!");
				return map;
			}

			List<AccountEntity> accountList = dbDao.query(AccountEntity.class,
					Cnd.where("openId", "=", openId).desc("createTime"),
					new Pager().setPageNumber(1).setPageSize(1));
			double balance = accountList == null ? 0
					: accountList.size() == 0 ? 0 : accountList.get(0)
							.getBalance();
			double money = game.getMoney();
			Date date = game.getDate();
			if (money > balance) {
				return MobileResult.success("NOMONEY", null);
			}
			// 接受PK
			// 保存签到信息
			SignInEntity sign = new SignInEntity();
			sign.setGameId(0);
			sign.setInAmount(money);
			sign.setOutAmount(0);
			sign.setStatus(SignInStatusEnum.NO.intKey());
			sign.setType(SignInTypeEnum.PERSONAL.intKey());
			sign.setGameId(game.getId());
			sign.setOpenId(openId);
			sign.setSignDate(date);
			sign.setCreateTime(DateTimeUtil.nowDateTime());
			dbDao.insert(sign);

			// 更新账户
			AccountEntity acc = new AccountEntity();
			acc.setBalance(balance - money);
			acc.setCreateTime(DateTimeUtil.nowDateTime());
			acc.setMoney(money);
			acc.setOpenId(openId);
			acc.setType(AccountTypeEnum.PUTIN.intKey());
			acc.setPlatType(PlatTypeEnum.XIAOKA.intKey());
			acc.setRemark("投注金额");
			dbDao.insert(acc);
			
			//插入接受PK消息
			//项目的访问路径
			String path = req.getContextPath();
			String basePath = req.getScheme()+"://"+req.getServerName()+":"+req.getServerPort()+path+"/";
			
			MessageEntity msg = new MessageEntity() ;
			msg.setContent("<a href='"+basePath+"wap/game/show?id="+game.getId()+"'>查看详情</a>");
			msg.setCreateTime(DateTimeUtil.nowDateTime());
			msg.setIsDelete(IsDelEnum.NO.intKey());
			msg.setStatus(1);//1为已发送
			msg.setTitle("您于"+date.toString()+"参与了PK游戏");
			msg.setOpenId(openId);
			dbDao.insert(msg);
			
			return MobileResult.success("OK", null);
		}
		return MobileResult.success("CLOSED", null);
	}

	@Override
	public Map<String, Object> entrance(Long id) {
		Map<String, Object> res = MapUtil.map();
		GameEntity game = dbDao.fetch(GameEntity.class, id);
		if (Util.isEmpty(game)
				|| game.getStatus() == GameStatusEnum.FAIL.intKey()) {
			res.put("isCanAtt", false);
			return res;
		}
		res.put("isCanAtt", true);
		res.put("game", game);

		Sql sql = Sqls.create(sqlManager.get("sign_in_total_money"));
		List<Record> total = dbDao.query(sql, Cnd.where("gameId", "=", id),
				null);

		double totalMoney = total == null ? 0D : total.size() == 0 ? 0D
				: (double) total.get(0).get("total");

		res.put("totalMoney", totalMoney);
		return res;
	}

	@Aop("txDb")
	@Override
	public void updateGameStatus() {
		int minute = 30;
		Sql sql = Sqls.create(sqlManager.get("sign_in_inner_game_list"));
		List<Record> list = dbDao.query(
				sql,
				Cnd.where("g.status", "=", GameStatusEnum.UNDERWAY.intKey())
						.and("s.type", "=", SignInTypeEnum.PERSONAL.intKey())
						.and("TIMESTAMPDIFF(MINUTE,g.createTime,NOW())", ">",
								minute).groupBy("s.gameId"), null);
		if (Util.isEmpty(list))
			return;

		for (Record record : list) {
			long num = (long) record.get("num");
			if (num <= 1) {

				String openId = (String) record.get("founder");

				// 将游戏状态置为失败，删除发起人在签到表的数据
				dbDao.update(GameEntity.class,
						Chain.make("status", GameStatusEnum.FAIL.intKey()),
						Cnd.where("id", "=", record.get("id")));
				// 个人账户退还投注金额
				List<AccountEntity> accountList = dbDao.query(
						AccountEntity.class, Cnd.where("openId", "=", openId)
								.desc("createTime"), getPager(1, 1));
				// 账户结余金额
				double balance = accountList == null ? 0
						: accountList.size() == 0 ? 0 : accountList.get(0)
								.getBalance();
				double money = (double) record.get("money");
				// 更新账户
				AccountEntity acc = new AccountEntity();
				acc.setBalance(balance + money);
				acc.setCreateTime(DateTimeUtil.nowDateTime());
				acc.setMoney(money);
				acc.setOpenId(openId);
				acc.setType(AccountTypeEnum.REFUND.intKey());
				acc.setPlatType(PlatTypeEnum.XIAOKA.intKey());
				acc.setRemark("投注金额");
				dbDao.insert(acc);

				List<SignInEntity> sign = dbDao.query(
						SignInEntity.class,
						Cnd.where("gameId", "=", record.get("id")).and(
								"openId", "=", openId), null);
				if(!Util.isEmpty(sign)){
					dbDao.delete(sign.get(0));
				}
			} else {
				// 将游戏状态置为成功
				dbDao.update(GameEntity.class,
						Chain.make("status", GameStatusEnum.SUCCESS.intKey()),
						Cnd.where("id", "=", record.get("id")));
			}
		}
		logger.info("检测游戏失败的定时任务执行了");
	}

}
