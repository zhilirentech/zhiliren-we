package com.xiaoka.game.admin.checkFinance.form;

import java.sql.Timestamp;

import lombok.Data;

import org.nutz.dao.Cnd;
import org.nutz.dao.SqlManager;
import org.nutz.dao.Sqls;
import org.nutz.dao.sql.Sql;

import com.uxuexi.core.common.util.Util;
import com.uxuexi.core.db.dao.IDbDao;
import com.uxuexi.core.web.form.ISqlForm;
import com.xiaoka.game.common.util.DateUtil;

@Data
public class FinanceByIdSqlForm implements ISqlForm {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 审核申请ID
	 */
	private Long withId; 
	

	@Override
	public Sql createPagerSql(IDbDao dbDao, SqlManager sqlManager) {
		Sql sql = Sqls.create(sqlManager.get("withdraw_application_by_id"));
		sql.setCondition(cnd());
		return sql ;
	}
	@Override
	public Sql createCountSql(IDbDao dbDao, SqlManager sqlManager) {
		return null;
	}
	private Cnd cnd() { 
		Cnd cnd = Cnd.limit();
		if (!Util.isEmpty(withId))
			cnd.and("uwa.id", "=",withId);
//		cnd.asc("c.sort") ;
//		cnd.desc("c.id") ;
		return cnd;
	}
	

}
