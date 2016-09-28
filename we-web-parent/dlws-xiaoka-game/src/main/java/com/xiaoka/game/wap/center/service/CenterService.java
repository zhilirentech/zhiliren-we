package com.xiaoka.game.wap.center.service;

import java.util.List;

import org.nutz.dao.pager.Pager;

import com.xiaoka.game.admin.banner.entity.BannerEntity;
import com.xiaoka.game.wap.center.entity.AccountEntity;
import com.xiaoka.game.wap.center.entity.CenterCustomerEntity;
import com.xiaoka.game.wap.center.form.AccountSqlForm;

public interface CenterService {
	
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
	CenterCustomerEntity getCenterCustomer(String openId);
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
	Object queryAccountList(AccountSqlForm sqlForm, Pager pager);
	/**
	 * 
	 * 项目名称：dlws-xiaoka-game
	 * 描述：保存用户提现信息
	 * 创建人： ln
	 * 创建时间： 2016年8月17日
	 * 标记：
	 * @param money
	 * @param openId
	 * @param pager
	 * @return
	 * @version
	 */
	boolean addWithdraw(double money, String openId, Pager pager);
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
	List<BannerEntity> queryBanner(int type);

}
