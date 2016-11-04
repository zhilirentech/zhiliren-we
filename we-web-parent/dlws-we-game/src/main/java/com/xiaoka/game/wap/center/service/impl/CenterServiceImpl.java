package com.xiaoka.game.wap.center.service.impl;

import java.util.List;

import org.nutz.dao.Chain;
import org.nutz.dao.Sqls;
import org.nutz.dao.pager.Pager;
import org.nutz.dao.sql.Sql;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;

import com.uxuexi.core.db.dao.IDbDao;
import com.uxuexi.core.db.util.DbSqlUtil;
import com.uxuexi.core.web.util.FormUtil;
import com.xiaoka.game.admin.banner.entity.BannerEntity;
import com.xiaoka.game.common.service.BaseService;
import com.xiaoka.game.wap.center.entity.CenterCustomerEntity;
import com.xiaoka.game.wap.center.entity.WithdrawEntity;
import com.xiaoka.game.wap.center.form.AccountSqlForm;
import com.xiaoka.game.wap.center.service.CenterService;

@IocBean(name="centerService")
public class CenterServiceImpl extends  BaseService implements CenterService{

	
	@Inject
	private IDbDao dbDao;
	
	/**
	 * 
	 * 项目名称：dlws-xiaoka-game
	 * 描述：根据用户openId获取用户信息，获取用户账户余额
	 * 创建人： ln
	 * 创建时间： 2016年8月16日
	 * 标记：wap
	 * @param openId
	 * @return
	 * @version
	 */
	public CenterCustomerEntity getCenterCustomer(String openId) {
		try {
			Sql sql = Sqls.create(sqlManager.get("center_getCustomer_byId"));
			sql.params().set("openId", openId);
			return DbSqlUtil.fetchEntity(dbDao, CenterCustomerEntity.class, sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 
	 * 项目名称：dlws-xiaoka-game
	 * 描述：分页获取用户充值提现记录
	 * 创建人： ln
	 * 创建时间： 2016年8月16日
	 * 标记：wap
	 * @param openId
	 * @param pager
	 * @return
	 * @version
	 */
	public Object queryAccountList(AccountSqlForm sqlForm, Pager pager) {
		
		/*FormUtil.query(dbDao, sqlManager, sqlForm, pager);*/
		return FormUtil.query(dbDao, sqlManager, sqlForm, pager);
	}
	/**
	 * 
	 * 项目名称：dlws-xiaoka-game
	 * 描述：保存用户提现信息
	 * 创建人： ln
	 * 创建时间： 2016年8月17日
	 * 标记：waP
	 * @param money
	 * @param openId
	 * @param pager
	 * @return
	 * @version
	 */
	@Override
	public boolean addWithdraw(double money, String openId, Pager pager) {
		try {
			//获取账户余额
			CenterCustomerEntity centerCustomer = getCenterCustomer(openId);
			double balance = centerCustomer.getBalance();//账户余额
			//获取已申请但未审核的提现金额
			Sql sql = Sqls.create(sqlManager.get("center_noCheck_money"));
			sql.params().set("openId", openId);
			WithdrawEntity withdraw = DbSqlUtil.fetchEntity(dbDao, WithdrawEntity.class, sql);
			double noCheckMoney = withdraw.getMoney();
			//本次提现金额 加 已申请提现未审核金额
			double temp = money+noCheckMoney;
			if(balance>=temp){
				dbDao.insert(WithdrawEntity.class, Chain.make("openId",openId).add("money",money).add("checkStatus",0));
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	/**
	 * 
	 * 项目名称：dlws-xiaoka-game
	 * 描述：支付页面轮播图
	 * 创建人： ln
	 * 创建时间： 2016年8月17日
	 * 标记：wap
	 * @param type
	 * @return
	 * @version
	 */
	public List<BannerEntity> queryBanner(int type) {
		Sql sql = Sqls.create(sqlManager.get("center_banner_list"));
		sql.params().set("type", type);
		sql.params().set("isDel", 0);
		List<BannerEntity> bannerList= DbSqlUtil.query(dbDao, BannerEntity.class, sql);
		return bannerList;
	}

}
