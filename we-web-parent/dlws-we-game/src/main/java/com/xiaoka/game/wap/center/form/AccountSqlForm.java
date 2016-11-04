package com.xiaoka.game.wap.center.form;

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
public class AccountSqlForm implements ISqlForm {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/*商品名称*/
	private String openId;

	@Override
	public Sql createPagerSql(IDbDao dbDao, SqlManager sqlManager) {
		Sql sql = Sqls.create(sqlManager.get("center_account_list"));
		sql.setCondition(cnd());
		return sql ;
	}

	@Override
	public Sql createCountSql(IDbDao dbDao, SqlManager sqlManager) {
		Sql sql = Sqls.create(sqlManager.get("center_account_list_count"));
		sql.setCondition(cnd());
		return sql;
	}
	
	private Cnd cnd() { 
		Cnd cnd = Cnd.limit();
		
		if (!Util.isEmpty(openId))
			cnd.and("openId", "=",openId);
//		cnd.asc("c.sort") ;
		cnd.desc("createTime") ;
		return cnd;
	}

}
