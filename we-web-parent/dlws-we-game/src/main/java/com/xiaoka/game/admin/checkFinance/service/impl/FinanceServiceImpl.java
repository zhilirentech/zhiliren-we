package com.xiaoka.game.admin.checkFinance.service.impl;

import java.util.Date;
import java.util.Map;

import org.json.JSONObject;
import org.nutz.dao.Chain;
import org.nutz.dao.Cnd;
import org.nutz.dao.Sqls;
import org.nutz.dao.pager.Pager;
import org.nutz.dao.sql.Sql;
import org.nutz.http.Http;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.uxuexi.core.common.util.MapUtil;
import com.uxuexi.core.db.dao.IDbDao;
import com.uxuexi.core.db.util.DbSqlUtil;
import com.uxuexi.core.web.chain.support.JsonResult;
import com.uxuexi.core.web.config.KvConfig;
import com.uxuexi.core.web.util.FormUtil;
import com.xiaoka.game.admin.checkFinance.entity.AccountEntity;
import com.xiaoka.game.admin.checkFinance.entity.FinanceEntity;
import com.xiaoka.game.admin.checkFinance.form.FinanceSqlForm;
import com.xiaoka.game.admin.checkFinance.po.FinancePo;
import com.xiaoka.game.admin.checkFinance.po.UserBalance;
import com.xiaoka.game.admin.checkFinance.service.FinanceService;
import com.xiaoka.game.common.access.AccessConfig;
import com.xiaoka.game.common.access.AccessCore;
import com.xiaoka.game.common.service.BaseService;

@IocBean(name="financeService")
public class FinanceServiceImpl extends  BaseService implements FinanceService{
	
	@Inject
	private IDbDao dbDao;
	
	@Inject
	private KvConfig wxPayConfig ;
	private static Logger log = LoggerFactory.getLogger(FinanceServiceImpl.class);
	
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
	@Override
	public Object queryCheckFinanceList(FinanceSqlForm sqlForm, Pager pager) {
		return FormUtil.query(dbDao, sqlManager, sqlForm, pager);
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
	@Override
	public FinancePo getWidthAppById(Long uId, Pager pager) {
		
		Sql sql = Sqls.create(sqlManager.get("withdraw_application_by_id"));
		
		sql.params().set("uId", uId);

		FinancePo finance = DbSqlUtil.fetchEntity(dbDao, FinancePo.class, sql);
		
		return finance;
	}
	
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
	@Override
	public Object checkFail(Long id) {
		
		dbDao.update(FinanceEntity.class, Chain.make("checkStatus",2), Cnd.where("id", "=",id));
		
		return JsonResult.success("更新成功") ;  
	}
	
	
	/**
	 * 
	 * 项目名称：dlws-xiaoka-game
	 * 描述：用户提现申请成功（打款）
	 * 创建人： ln
	 * 创建时间： 2016年8月12日
	 * 标记：用户提现申请成功（打款）
	 * @param id
	 * @param pager
	 * @return
	 * @version
	 */
	@Override
	public Object checkSuccess(Long id,String openId,Double checkMoney, Pager pager) {
		Map<String,Object> map = MapUtil.map();
		Map<String,String> params = MapUtil.map();
		Map<String,Object> m = MapUtil.map();
		
		Sql sql = Sqls.create(sqlManager.get("getuser_balance"));
		
		sql.params().set("openId", openId);

		UserBalance ub = DbSqlUtil.fetchEntity(dbDao, UserBalance.class, sql);
		//获取用户账户当前余额
		double balance = ub.getBalance();
		
		if(balance-checkMoney>=0){
			map.put("flag",false);
			map.put("msg", "用户账户余额不足!");
			return map;
		}
		//调接口--打款
		params.put("openid",openId);
		params.put("amount",checkMoney.toString());
		params.put("desc","up审核打款!");
		//参数签名
		String sign = AccessCore.sign(params, AccessConfig.xiaoka_wxpay_secret)  ;
		params.put("sign",sign);
		log.info("++++++签名信息="+sign);
		String url = wxPayConfig.getValue("wx_news_plat_money");//打款url
		/*url=url+"openid="+openId+"&amount="+checkMoney+"&desc="+"up审核打款";*/
		m.put("openid",openId);
		m.put("amount",checkMoney.toString());
		m.put("desc","up审核打款!");
		m.put("sign", sign);
		log.info("++++++++++++++发送post请求"+m+"url="+url);
		String resJson= Http.post(url,m,10000);
		log.info("+++++++++++打款请求返回="+resJson);
		JSONObject obj = new JSONObject(resJson);
		boolean flag = (boolean) obj.get("flag");
		if(!flag){
			log.info("++++++++++++++++打款返回信息"+obj.get("msg"));
			map.put("flag", false);
			map.put("msg", obj.get("msg"));
			return map;
		}
		//用户账户记录表中插入记录
		dbDao.insert(AccountEntity.class, Chain.make("openId", openId).add("money",checkMoney)
					.add("balance",balance-checkMoney).add("platType", 0).add("type",2).add("createTime",new Date()));
		//进行申请状态更新--跟新为成功
		int update = dbDao.update(FinanceEntity.class, Chain.make("checkStatus",1), Cnd.where("id", "=",id));
		if(update>0){
			map.put("flag",true);
			map.put("msg","审核成功");
			return map;
		}
		return null;
	}


	
}
