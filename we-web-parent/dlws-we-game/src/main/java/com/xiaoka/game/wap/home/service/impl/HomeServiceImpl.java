package com.xiaoka.game.wap.home.service.impl;

import java.math.RoundingMode;
import java.sql.Date;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.nutz.dao.Chain;
import org.nutz.dao.Cnd;
import org.nutz.dao.Sqls;
import org.nutz.dao.entity.Record;
import org.nutz.dao.pager.Pager;
import org.nutz.dao.sql.Sql;
import org.nutz.ioc.aop.Aop;
import org.nutz.ioc.loader.annotation.IocBean;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.uxuexi.core.common.util.DateTimeUtil;
import com.uxuexi.core.common.util.MapUtil;
import com.uxuexi.core.common.util.Util;
import com.uxuexi.core.db.util.DbSqlUtil;
import com.xiaoka.game.admin.account.entity.AccountEntity;
import com.xiaoka.game.admin.bulletin.entity.BulletinEntity;
import com.xiaoka.game.admin.keyvalue.entity.KeyValueEntity;
import com.xiaoka.game.admin.message.entity.MessageEntity;
import com.xiaoka.game.admin.message.entity.MessageStatus;
import com.xiaoka.game.admin.pkrecord.entity.GameEntity;
import com.xiaoka.game.admin.pkrecord.entity.SignInEntity;
import com.xiaoka.game.admin.signdays.entity.SignDaysEntity;
import com.xiaoka.game.common.constants.CommonConstants;
import com.xiaoka.game.common.enums.AccountTypeEnum;
import com.xiaoka.game.common.enums.GameStatusEnum;
import com.xiaoka.game.common.enums.IsDelEnum;
import com.xiaoka.game.common.enums.KeyValueEnum;
import com.xiaoka.game.common.enums.PlatTypeEnum;
import com.xiaoka.game.common.enums.SignInStatus2Enum;
import com.xiaoka.game.common.enums.SignInStatusEnum;
import com.xiaoka.game.common.enums.SignInTypeEnum;
import com.xiaoka.game.common.result.MobileResult;
import com.xiaoka.game.common.service.BaseService;
import com.xiaoka.game.wap.home.entity.CityInfoEntity;
import com.xiaoka.game.wap.home.entity.CustomerEntity;
import com.xiaoka.game.wap.home.entity.SchoolEntity;
import com.xiaoka.game.wap.home.entity.UpCustomerEntity;
import com.xiaoka.game.wap.home.form.AttendForm;
import com.xiaoka.game.wap.home.service.HomeService;

@IocBean(name = "homeService")
public class HomeServiceImpl extends BaseService implements HomeService {

	/**
	 * 
	 * 项目名称：dlws-xiaoka-game 描述：根据openId获取用户信息 创建人： ln 创建时间： 2016年8月15日 标记：wap
	 * 
	 * @param openId
	 * @return
	 * @version
	 */
	@Override
	public CustomerEntity getCustomerById(String openId) {
		try {
			Sql sql = Sqls.create(sqlManager.get("home_getCustomer_byId"));
			sql.params().set("openId", openId);
			return DbSqlUtil.fetchEntity(dbDao, CustomerEntity.class, sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 
	 * 项目名称：dlws-xiaoka-game 描述：根据opneId获取up系统中维护的个人信息。 创建人： ln 创建时间： 2016年8月15日
	 * 标记：wap
	 * 
	 * @param openId
	 * @return
	 * @version
	 */
	public UpCustomerEntity getUpCustomerByOpenId(String openId) {
		try {
			Sql sql = Sqls
					.create(sqlManager.get("home_getUpCustomer_byOpneId"));
			sql.params().set("openId", openId);
			return DbSqlUtil.fetchEntity(dbDao, UpCustomerEntity.class, sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 
	 * 项目名称：dlws-xiaoka-game 描述：获取城市，获取城市下学校 创建人： ln 创建时间： 2016年8月15日 标记：wap
	 * 
	 * @param openId
	 * @return
	 * @version
	 */
	public List<CityInfoEntity> selectSchool(String openId) {

		try {
			Sql cusSql = Sqls.create(sqlManager.get("home_getCustomer_byId"));
			cusSql.params().set("openId", openId);
			CustomerEntity customer = DbSqlUtil.fetchEntity(dbDao,
					CustomerEntity.class, cusSql);

			Sql sql = Sqls.create(sqlManager.get("home_query_CityList"));

			List<CityInfoEntity> citys = DbSqlUtil.query(dbDao,
					CityInfoEntity.class, sql, null);

			List<SchoolEntity> schools = null;

			for (CityInfoEntity city : citys) {
				Sql schoolSql = Sqls.create(sqlManager
						.get("home_query_schoolByCityId"));
				schoolSql.params().set("cityId", city.getId());
				schools = DbSqlUtil.query(dbDao, SchoolEntity.class, schoolSql,
						null);

				city.setSchools(schools);

				if (null != customer && city.getId() == customer.getCityId()) {
					city.setCurrent(true);
				} else {
					city.setCurrent(false);
				}
			}
			return citys;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 
	 * 项目名称：dlws-xiaoka-game 描述：用户选择学校，进行学校的保存 创建人： ln 创建时间： 2016年8月15日 标记：wap
	 * 
	 * @param openId
	 * @param schoolId
	 * @version
	 */
	@Override
	public void saveSchool(String openId, String schoolId) {
		dbDao.update(CustomerEntity.class, Chain.make("tgSchoolId", schoolId),
				Cnd.where("openId", "=", openId));
	}

	/**
	 * 
	 * 项目名称：dlws-xiaoka-game 描述：根据学校名称筛选学校 创建人： ln 创建时间： 2016年8月15日 标记：
	 * 
	 * @param schoolName
	 * @return
	 * @version
	 */
	public List<SchoolEntity> findSchoolInfosBySchoolName(String schoolName) {
		try {
			Sql sql = Sqls.create(sqlManager
					.get("home_querySchool_BySchoolName"));
			sql.params().set("schoolName", schoolName);
			return DbSqlUtil.query(dbDao, SchoolEntity.class, sql, null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 
	 * 项目名称：dlws-xiaoka-game 描述：up保存用户姓名 创建人： ln 创建时间： 2016年8月15日 标记：
	 * 
	 * @param customerName
	 * @version
	 */
	public void saveCustomerInfo(String customerName, String openId) {
		try {
			dbDao.insert(
					UpCustomerEntity.class,
					Chain.make("customerName", customerName).add("openId",
							openId));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public Map<String, Object> index(HttpSession session) {
		Map<String, Object> res = MapUtil.map();
		String currDate = DateTimeUtil.nowDate().toString();
		String openId = (String) session.getAttribute("openId");

		// 平台签到时间
		List<KeyValueEntity> keyValueList = dbDao.query(KeyValueEntity.class,
				Cnd.where("name", "=", KeyValueEnum.SIGNINTIME.intKey()),
				getPager(1, 1));
		res.put("keyValue", keyValueList == null ? null
				: keyValueList.size() == 0 ? null : keyValueList.get(0)
						.getNameVal());

		// 获取用户信息
		res.put("customer",
				dbDao.fetch(CustomerEntity.class,
						Cnd.where("openId", "=", openId)));

		// 连续签到时间
		List<SignDaysEntity> dayList = dbDao.query(SignDaysEntity.class, Cnd
				.where("openId", "=", openId).desc("signTime"), getPager(1, 1));
		res.put("days", dayList == null ? 0 : dayList.size() == 0 ? 0 : dayList
				.get(0).getSignDays());

		// 当日奖金总额
		List<Record> total = dbDao.query(Sqls.create(sqlManager
				.get("sign_in_total_money")),
				Cnd.where("DATE(signDate)", "=", DateTimeUtil.tomorrowDate())
						.and("type", "=", SignInTypeEnum.NATIONWIDE.intKey()),
				null);
		res.put("total",
				total == null ? 0 : total.size() == 0 ? 0 : total.get(0).get(
						"total"));

		// 所有平台签到记录,只保留时间和状态
		List<SignInEntity> signList = dbDao.query(
				SignInEntity.class,
				Cnd.where("openId", "=", openId).and("type", "=",
						SignInTypeEnum.NATIONWIDE.intKey()), null);
		Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation()
				.setDateFormat("yyyy-M-d").create();
		String signListJson = gson.toJson(signList);
		res.put("signListJson", signListJson);

		// 账户余额
		List<AccountEntity> accountList = dbDao.query(AccountEntity.class, Cnd
				.where("openId", "=", openId).desc("createTime"),
				getPager(1, 1));
		res.put("balance", accountList == null ? 0
				: accountList.size() == 0 ? 0 : accountList.get(0).getBalance());

		// 判断当日是否参与平台签到
		Cnd cnd = Cnd.limit();
		cnd.and("openId", "=", openId).and("DATE(signDate)", "=", currDate)
				.and("type", "=", SignInTypeEnum.NATIONWIDE.intKey());

		List<SignInEntity> currSignList = dbDao.query(SignInEntity.class, cnd,
				null);
		if (Util.isEmpty(currSignList)) {
			res.put("signStatus", SignInStatus2Enum.NOATTEND.intKey());
		} else {
			if (currSignList.get(0).getStatus() == SignInStatusEnum.NO.intKey()) {
				res.put("signStatus", SignInStatusEnum.NO.intKey());
			} else {
				res.put("signStatus", SignInStatusEnum.YES.intKey());
			}
		}

		// 查找PK规则
		List<BulletinEntity> bulletinList = dbDao.query(BulletinEntity.class,
				Cnd.where("type", "=", 0), getPager(1, 1));
		res.put("bulletin", bulletinList == null ? null
				: bulletinList.size() == 0 ? null : bulletinList.get(0)
						.getContent());

		res.put("currDate", currDate);
		return res;
	}

	@Aop("txDb")
	@Override
	public Map<String, Object> signIn(HttpSession session) {
		Map<String, Object> resMap = MapUtil.map();
		String openId = (String) session.getAttribute("openId");
		String currDate = DateTimeUtil.todayDate().toString();
		// 判断当日是否参与签到和是否已签到
		List<SignInEntity> currSignList = dbDao.query(
				SignInEntity.class,
				Cnd.where("openId", "=", openId)
						.and("DATE(signDate)", "=", currDate)
						.and("type", "=", SignInTypeEnum.NATIONWIDE.intKey()),
				null);

		if (Util.isEmpty(currSignList)) {
			return MobileResult.success("NOATTEND", null);// 未参加
		}
		SignInEntity signIn = currSignList.get(0);
		if (signIn.getStatus() == SignInStatusEnum.YES.intKey()) {
			return MobileResult.success("SIGNINED", null);// 已签到
		}

		// 平台签到时间
		List<KeyValueEntity> keyValueList = dbDao.query(KeyValueEntity.class,
				Cnd.where("name", "=", KeyValueEnum.SIGNINTIME.intKey()),
				getPager(1, 1));
		if (Util.isEmpty(keyValueList)) {
			return MobileResult.success("NOTIME", null);
		}
		String scpoeStr = keyValueList.get(0).getNameVal();
		int res = isScope(scpoeStr);
		// 判断是否在签到时间范围内
		if (res == 0) {// 在范围内
			// 更新新签到表的签到状态和签到时间

			dbDao.update(
					SignInEntity.class,
					Chain.make("status", SignInStatusEnum.YES.intKey()).add(
							"signTime", DateTimeUtil.nowDateTime()),
					Cnd.where("openId", "=", openId)
							.and("DATE(signDate)", "=", currDate)
							.and("type", "=",
									SignInTypeEnum.NATIONWIDE.intKey()));

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
			// 签到成功后，更新连续签到表
			Cnd cnd = Cnd.where("openId", "=", openId);
			cnd.and("signTime", "=", DateTimeUtil.yesterdayDate().toString());
			List<SignDaysEntity> sd = dbDao.query(SignDaysEntity.class, cnd,
					null);
			int days = (sd == null ? 0 : sd.size() == 0 ? 0 : sd.get(0)
					.getSignDays());
			if (days == 0) {
				SignDaysEntity signDays = new SignDaysEntity();
				signDays.setOpenId(openId);
				signDays.setSignDays(days + 1);
				signDays.setSignTime(DateTimeUtil.todayDate());
				dbDao.insert(signDays);
			} else if (days > 0) {
				dbDao.update(
						SignDaysEntity.class,
						Chain.make("signDays", days + 1 + "").add("signTime",
								currDate),
						Cnd.where("id", "=", sd.get(0).getId()));
			}
			// 如果符合条件提示去点亮图标

			DecimalFormat df = new DecimalFormat("#.00");
			df.setRoundingMode(RoundingMode.DOWN);
			resMap.put("balance", df.format(balance + inAmount));
			resMap.put("days", days + 1);
			return MobileResult.success("YES", resMap);
		}
		if (res == -1) {
			return MobileResult.success("BEFORE", null);
		}
		if (res == 1) {
			// 将签到状态置为超时
			dbDao.update(
					SignInEntity.class,
					Chain.make("status", SignInStatusEnum.OVERTIME.intKey())
							.add("signTime", DateTimeUtil.nowDateTime()),
					Cnd.where("openId", "=", openId)
							.and("DATE(signDate)", "=", currDate)
							.and("type", "=",
									SignInTypeEnum.NATIONWIDE.intKey()));
			return MobileResult.success("AFTER", null);
		}

		return MobileResult.success("NO", null);
	}

	private Pager getPager(int number, int size) {
		Pager pager = new Pager();
		pager.setPageNumber(number);
		pager.setPageSize(size);
		return pager;
	}

	private int isScope(String scope) {
		Long currMills = DateTimeUtil.millis();
		String[] strs = scope.split("-");
		String currDate = DateTimeUtil.todayDate().toString();

		String beginStr = currDate + " " + strs[0] + ":00";
		Long beginMills = DateTimeUtil.string2DateTime(beginStr, null)
				.getMillis();

		String endStr = currDate + " " + strs[1] + ":00";
		Long endMills = DateTimeUtil.string2DateTime(endStr, null).getMillis();

		if (beginMills <= currMills && currMills <= endMills) {
			return 0;
		}
		if (currMills < beginMills) {
			return -1;
		}
		return 1;
	}

	@Override
	public Map<String, Object> attend(HttpSession session) {
		Map<String, Object> res = MapUtil.map();
		String openId = (String) session.getAttribute("openId");
		// 查询账户余额
		List<AccountEntity> accountList = dbDao.query(AccountEntity.class, Cnd
				.where("openId", "=", openId).desc("createTime"),
				getPager(1, 1));

		res.put("balance", accountList == null ? 0
				: accountList.size() == 0 ? 0 : accountList.get(0).getBalance());

		// 所有签到记录,只保留时间和状态
		List<SignInEntity> signList = dbDao.query(
				SignInEntity.class,
				Cnd.where("openId", "=", openId).and("type", "=",
						SignInTypeEnum.NATIONWIDE.intKey()), null);
		Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation()
				.setDateFormat("yyyy-M-d").create();
		String signListJson = gson.toJson(signList);
		res.put("signListJson", signListJson);

		return res;
	}

	@Aop("txDb")
	@Override
	public Map<String, Object> attendSignIn(HttpSession session, AttendForm form) {
		Map<String, Object> res = MapUtil.map();
		String openId = (String) session.getAttribute("openId");
		// 查询账户余额
		List<AccountEntity> accountList = dbDao.query(AccountEntity.class, Cnd
				.where("openId", "=", openId).desc("createTime"),
				getPager(1, 1));
		double balance = accountList == null ? 0 : accountList.size() == 0 ? 0
				: accountList.get(0).getBalance();
		double total = form.getTotalMoney();

		// 校验账户余额是否大于投入金额
		if (total > balance) {
			return MobileResult.success("NOMONEY", null);
		}

		String[] dayArr = form.getAttendDays().split(",");
		// 校验日期是否是今天之后的日期
		String dateStr = valDate(dayArr, DateTimeUtil.todayDate());
		if (!Util.isEmpty(dateStr)) {
			return MobileResult.success("NOTAFTER", dateStr);
		}

		// 校验日期是否重复

		// 校验平均金额是否介于1-200

		// 获取每天投入金额
		double vag = total / dayArr.length;

		for (String day : dayArr) {
			SignInEntity sign = new SignInEntity();
			sign.setGameId(0);
			sign.setInAmount(vag);
			sign.setOutAmount(0);
			sign.setStatus(SignInStatusEnum.NO.intKey());
			sign.setType(SignInTypeEnum.NATIONWIDE.intKey());
			sign.setGameId(0);
			sign.setOpenId(openId);
			sign.setSignDate(DateTimeUtil.string2Date(day, "yyyy-MM-dd"));
			sign.setCreateTime(DateTimeUtil.nowDateTime());
			dbDao.insert(sign);
		}
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
		/*
		 * //账户结余金额 res.put("balance", balance - total) ; // 所有签到记录,只保留时间和状态
		 * List<SignInEntity> signList = dbDao.query(SignInEntity.class,
		 * Cnd.where("openId", "=", openId), null); Gson gson = new
		 * GsonBuilder()
		 * .excludeFieldsWithoutExposeAnnotation().setDateFormat("yyyy-M-d"
		 * ).create() ; String signListJson = gson.toJson(signList);
		 * res.put("signListJson", signListJson);
		 */
		return MobileResult.success("OK", res);
	}

	private String valDate(String[] dayArr, Date curr) {
		for (String day : dayArr) {
			Date date = DateTimeUtil.string2Date(day, "yyyy-MM-dd");
			if (date.before(curr)) {
				return date.toString();
			}
		}
		return null;
	}

	/*
	 * 定时任务，分成
	 */
	public void divided() {
		logger.info("分成定时任务执行");
		// 全国，执行当天的分成
		nationDivided();
		// 个人,执行昨天的分成
		personalDivided();

	}

	@Aop("txDb")
	private void nationDivided() {
		/**
		 * 全国
		 */
		String currDate = DateTimeUtil.todayDate().toString();

		// 查找未签到的和签到超时的投入金额的总和
		List<Record> noTotal = dbDao.query(
				Sqls.create(sqlManager.get("sign_in_total_money")),
				Cnd.where("DATE(signDate)", "=", currDate)
						.and("status","IN",SignInStatusEnum.NO.intKey() + ","
										+ SignInStatusEnum.OVERTIME.intKey())
						.and("type", "=", SignInTypeEnum.NATIONWIDE.intKey()),
				null);
		double noSignTotal = noTotal == null ? 0D : noTotal.size() == 0 ? 0D
				: noTotal.get(0).get("total") == null ? 0D : (double) noTotal
						.get(0).get("total");
		if (noSignTotal == 0D)
			return;// 都没签到，不分成

		// 查找已签到的投入金额的总和
		List<Record> total = dbDao.query(
				Sqls.create(sqlManager.get("sign_in_total_money")),
				Cnd.where("DATE(signDate)", "=", currDate)
						.and("status", "=", SignInStatusEnum.YES.intKey())
						.and("type", "=", SignInTypeEnum.NATIONWIDE.intKey()),
				null);
		double signTotal = total == null ? 0D : total.size() == 0 ? 0D : total
				.get(0).get("total") == null ? 0D : (double) total.get(0).get(
				"total");
		if (signTotal == 0D)
			return;// 都签到，不分成

		// 查找当天已签到的签到信息集合
		List<SignInEntity> signList = dbDao.query(
				SignInEntity.class,
				Cnd.where("status", "=", SignInStatusEnum.YES.intKey())
						.and("DATE(signDate)", "=", currDate)
						.and("type", "=", SignInTypeEnum.NATIONWIDE.intKey()),
				null);

		for (SignInEntity signIn : signList) {
			String openId = signIn.getOpenId();
			// 修改已签到信息的分成金额
			double outAmount = (noSignTotal / signTotal) * signIn.getInAmount();
			// 如果分成金额小于0.01，不生成分成记录
			if (outAmount < 0.01D) {
				break;
			}

			dbDao.update(
					SignInEntity.class,
					Chain.make("outAmount", outAmount),
					Cnd.where("openId", "=", openId)
							.and("DATE(signDate)", "=", currDate)
							.and("type", "=", SignInTypeEnum.NATIONWIDE.intKey()));
			// 个人账户插入分成记录
			// 查询账户余额

			if (!signIn.getOpenId().contains(
					CommonConstants.XIAOKA_ROBOT_PREFIX)) {
				List<AccountEntity> accountList = dbDao.query(
						AccountEntity.class, Cnd.where("openId", "=", openId)
								.desc("createTime"), getPager(1, 1));
				double balance = accountList == null ? 0
						: accountList.size() == 0 ? 0 : accountList.get(0)
								.getBalance();

				AccountEntity acc = new AccountEntity();
				acc.setBalance(balance + outAmount);
				acc.setCreateTime(DateTimeUtil.nowDateTime());
				acc.setMoney(outAmount);
				acc.setOpenId(openId);
				acc.setType(AccountTypeEnum.DIVIDED.intKey());
				acc.setPlatType(PlatTypeEnum.XIAOKA.intKey());
				acc.setRemark("分成金额");
				dbDao.insert(acc);
				
				//插入分成消息
				DecimalFormat df = new DecimalFormat("#.00");
				MessageEntity msg = new MessageEntity() ;
				msg.setContent("您于"+DateTimeUtil.nowDate().toString()+"参与的全国签到中赢取了"+df.format(outAmount)+"元。");
				msg.setCreateTime(DateTimeUtil.nowDateTime());
				msg.setIsDelete(IsDelEnum.NO.intKey());
				msg.setStatus(1);//1为已发送
				msg.setOpenId(openId);
				msg.setTitle(DateTimeUtil.yesterdayDate().toString() + "全国分成");
				dbDao.insert(msg);
			}

		}
	}

	@Aop("txDb")
	private void personalDivided() {
		// 个人,执行昨天的分成
		Date tomorrow = DateTimeUtil.tomorrowDate();
		List<GameEntity> gameList = dbDao.query(
				GameEntity.class,
				Cnd.where("status", "=", GameStatusEnum.SUCCESS.intKey()).and(
						"DATE(`date`)", "=", tomorrow.toString()), null);
		for (GameEntity game : gameList) {

			// 获取每个游戏未签到的总金额
			Sql sql = Sqls.create(sqlManager.get("sign_in_total_money"));
			List<Record> noTotal = dbDao
					.query(sql,
							Cnd.where("gameId", "=", game.getId())
									.and("status",
											"IN",
											SignInStatusEnum.NO.intKey()
													+ ","
													+ SignInStatusEnum.OVERTIME
															.intKey())
									.and("type", "=",
											SignInTypeEnum.PERSONAL.intKey()),
							null);

			double noSignTotal = noTotal == null ? 0D
					: noTotal.size() == 0 ? 0D
							: noTotal.get(0).get("total") == null ? 0D
									: (double) noTotal.get(0).get("total");
			if (noSignTotal == 0D) {
				continue;// 都没签到，不分成
			}

			// 获取每个游戏签到的总金额
			List<Record> total = dbDao
					.query(sql,
							Cnd.where("gameId", "=", game.getId())
									.and("status", "=",
											SignInStatusEnum.YES.intKey())
									.and("type", "=",
											SignInTypeEnum.PERSONAL.intKey()),
							null);
			double signTotal = total == null ? 0D : total.size() == 0 ? 0D
					: total.get(0).get("total") == null ? 0D : (double) total
							.get(0).get("total");
			if (signTotal == 0D) {
				continue;// 都签到，不分成
			}

			// 计算分成金额
			double outAmount = (noSignTotal / signTotal) * game.getMoney();
			// 如果分成金额小于0.01，不生成分成记录
			if (outAmount < 0.01) {
				continue;
			}

			// 更新签到表的分成金额
			dbDao.update(
					SignInEntity.class,
					Chain.make("outAmount", outAmount),
					Cnd.where("gameId", "=", game.getId()).and("status", "=",
							SignInStatusEnum.YES.intKey()));

			/* 更新签到人的账户 */
			// 1.获取当前游戏所有签到信息
			List<SignInEntity> signList = dbDao.query(
					SignInEntity.class,
					Cnd.where("gameId", "=", game.getId()).and("status", "=",
							SignInStatusEnum.YES.intKey()), null);
			for (SignInEntity signIn : signList) {
				// 个人账户插入分成记录
				// 查询账户余额
				String openId = signIn.getOpenId();
				List<AccountEntity> accountList = dbDao.query(
						AccountEntity.class, Cnd.where("openId", "=", openId)
								.desc("createTime"), getPager(1, 1));
				double balance = accountList == null ? 0
						: accountList.size() == 0 ? 0 : accountList.get(0)
								.getBalance();

				AccountEntity acc = new AccountEntity();
				acc.setBalance(balance + outAmount);
				acc.setCreateTime(DateTimeUtil.nowDateTime());
				acc.setMoney(outAmount);
				acc.setOpenId(openId);
				acc.setType(AccountTypeEnum.DIVIDED.intKey());
				acc.setPlatType(PlatTypeEnum.XIAOKA.intKey());
				acc.setRemark("分成金额");
				dbDao.insert(acc);
				
				//插入分成消息
				DecimalFormat df = new DecimalFormat("#.00");
				MessageEntity msg = new MessageEntity() ;
				msg.setContent("您于"+DateTimeUtil.yesterdayDate().toString()+"参与的PK活动中赢取了"+df.format(outAmount)+"元。");
				msg.setCreateTime(DateTimeUtil.nowDateTime());
				msg.setIsDelete(IsDelEnum.NO.intKey());
				msg.setStatus(1);
				msg.setOpenId(openId);
				msg.setTitle(DateTimeUtil.yesterdayDate().toString() + "PK分成");
				dbDao.insert(msg);
			}
		}
	}

	/**
	 * 
	 * 项目名称：dlws-xiaoka-game 描述：用户账户充值1元 创建人： ln 创建时间： 2016年8月19日 标记：
	 * 
	 * @param openId
	 * @version
	 */
	public void saveAccount(String openId) {
		dbDao.insert(
				AccountEntity.class,
				Chain.make("money", 1).add("openId", openId).add("balance", 1)
						.add("platType", 0).add("type", 1)
						.add("createTime", new java.util.Date())
						.add("remark", "系统奖励"));

	}
}
