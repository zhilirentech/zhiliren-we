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
@At("/admin/finance")
public class FinanceModule {
	
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
	 * 描述：分页查询,提现审核列表
	 * 创建人： ln
	 * 创建时间： 2016年8月11日
	 * 标记：
	 * @param queryForm 查询表单
	 * @param pager 	分页对象
	 * @return
	 * @version
	 */
	@At
	@Ok("jsp")
	public Object list(@Param("..") final FinanceSqlForm sqlForm, @Param("..") final Pager pager) {
	
		return financeService.queryCheckFinanceList(sqlForm,pager);
	/*return FormUtil.query(dbDao, sqlManager, sqlForm, pager);*/
	}
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
	@At
	@Ok("jsp")
	public Object getWidthAppById(@Param("uId") final Long uId, @Param("..") final Pager pager) {
		FinancePo finance = financeService.getWidthAppById(uId,pager);
		return finance;
	}
	


	
}
