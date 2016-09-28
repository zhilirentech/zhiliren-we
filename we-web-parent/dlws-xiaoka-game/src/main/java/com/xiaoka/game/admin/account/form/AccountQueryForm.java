package com.xiaoka.game.admin.account.form;

import java.sql.Timestamp;

import lombok.Data;

import org.nutz.dao.Cnd;
import org.nutz.dao.SqlManager;
import org.nutz.dao.Sqls;
import org.nutz.dao.sql.Sql;
import org.nutz.dao.util.cri.SqlExpressionGroup;

import com.uxuexi.core.common.util.Util;
import com.uxuexi.core.db.dao.IDbDao;
import com.uxuexi.core.web.form.ISqlForm;

@Data
public class AccountQueryForm implements ISqlForm{
	/**
	 * 用户姓名/openId
	 */
	private String name;
	/**
	 * 时间
	 */
	private String createTime;
	/**
	 * 操作类型
	 */
	private Integer type;
	
	
	@Override
	public Sql createPagerSql(IDbDao dbDao, SqlManager sqlManager) {
		Sql sql = Sqls.create(sqlManager.get("account_list"));
		sql.setCondition(cnd());
		return sql ;
	}

	@Override
	public Sql createCountSql(IDbDao dbDao, SqlManager sqlManager) {
		Sql sql = Sqls.create(sqlManager.get("account_list_count"));
		sql.setCondition(cnd());
		return sql;
	}
	
	private Cnd cnd() { 
		Cnd cnd = Cnd.limit();
		
		if (!Util.isEmpty(createTime)){
			cnd.and("DATE_FORMAT(ua.createTime,'%Y-%m-%d')", "=",createTime);
		}
		if (!Util.isEmpty(type)){
			cnd.and("ua.type", "=",type);
		}
		if (!Util.isEmpty(name)){
			SqlExpressionGroup exp = new SqlExpressionGroup() ;
			exp.and("uci.customerName", "LIKE", "%"+name+"%");
			exp.or("ua.openId", "LIKE", "%"+name+"%");
			cnd.and(exp);
		}
		return cnd;
	}
	
}
