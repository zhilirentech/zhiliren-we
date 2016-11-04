package com.xiaoka.game.admin.checkFinance.service;

import org.nutz.dao.pager.Pager;

import com.xiaoka.game.admin.checkFinance.form.FinanceSqlForm;
import com.xiaoka.game.admin.checkFinance.po.FinancePo;

public interface FinanceService {
	
	/**
	 * 
	 * 项目名称：dlws-xiaoka-game
	 * 描述：财务审核列表
	 * 创建人： ln
	 * 创建时间： 2016年8月11日
	 * 标记：admin
	 * @param sqlForm
	 * @param pager
	 * @return
	 * @version
	 */
	public Object queryCheckFinanceList(FinanceSqlForm sqlForm, Pager pager);
	/**
	 * 
	 * 项目名称：dlws-xiaoka-game
	 * 描述：根据ID获取提现内容详情，进行审核
	 * 创建人： ln
	 * 创建时间： 2016年8月11日
	 * 标记：
	 * @param sqlForm
	 * @param pager
	 * @return
	 * @version
	 */
	public FinancePo getWidthAppById(Long uId, Pager pager);
	
	/**
	 * 
	 * 项目名称：dlws-xiaoka-game
	 * 描述：用户提现审核，审核失败
	 * 创建人： ln
	 * 创建时间： 2016年8月11日
	 * 标记：ajax
	 * @param queryForm 查询表单
	 * @param pager 	分页对象
	 * @return
	 * @version
	 */
	public Object checkFail(Long id);
	/**
	 * 
	 * 项目名称：dlws-xiaoka-game
	 * 描述：用户提现申请成功（打款）
	 * 创建人： ln
	 * 创建时间： 2016年8月12日
	 * 标记：用户提现申请成功（打款）
	 * @param uId
	 * @param pager
	 * @return
	 * @version
	 */
	public Object checkSuccess(Long id,String openId,Double checkMoney,Pager pager);
	
	
}
