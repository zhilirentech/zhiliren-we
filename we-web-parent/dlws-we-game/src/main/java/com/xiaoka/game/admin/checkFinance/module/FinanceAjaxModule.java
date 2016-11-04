package com.xiaoka.game.admin.checkFinance.module;

import org.nutz.dao.pager.Pager;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.Param;

import com.uxuexi.core.db.dao.IDbDao;
import com.xiaoka.game.admin.checkFinance.form.FinanceSqlForm;
import com.xiaoka.game.admin.checkFinance.po.FinancePo;
import com.xiaoka.game.admin.checkFinance.service.FinanceService;

@IocBean
@At("/admin/finance/ajax")
public class FinanceAjaxModule {
	
	/**
	 * 注入容器中的dbDao对象，用于数据库查询、持久操作
	 */
	@Inject
	private IDbDao dbDao;
	
	@Inject
	private FinanceService financeService;
	
	/**
	 * 
	 * 项目名称：dlws-xiaoka-game
	 * 描述：用户提现审核，审核失败
	 * 创建人： ln
	 * 创建时间： 2016年8月11日
	 * 标记：
	 * @param queryForm 查询表单
	 * @param pager 	分页对象
	 * @return
	 * @version
	 */
	@At
	@Ok("json")
	public Object checkFail(@Param("id") final Long id, @Param("..") final Pager pager) {
	
		return financeService.checkFail(id);
	/*return FormUtil.query(dbDao, sqlManager, sqlForm, pager);*/
	}
	/**
	 * 
	 * 项目名称：dlws-xiaoka-game
	 * 描述：根据ID获取提现内容详情，进行审核,审核成功
	 * 创建人： ln
	 * 创建时间： 2016年8月11日
	 * 标记：
	 * @param sqlForm
	 * @param pager
	 * @return
	 * @version
	 */
	@At
	@Ok("json")
	public Object checkSuccess(@Param("id") final Long id,@Param("openId") final String openId ,@Param("checkMoney") final double checkMoney ,
			@Param("..") final Pager pager) {
		return financeService.checkSuccess(id,openId,checkMoney,pager);
	}
	
	
}
