package com.xiaoka.game.admin.pkrecord.form;

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
public class SignInSqlForm implements ISqlForm {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 游戏ID
	 */
	private Long gameId; 
	
	/**
	 * 创建日期结束时间
	 */
	private Timestamp createTimeEnd ; 

	@Override
	public Sql createPagerSql(IDbDao dbDao, SqlManager sqlManager) {
		Sql sql = Sqls.create(sqlManager.get("sign_in_record_list"));
		sql.setCondition(cnd());
		return sql ;
	}

	@Override
	public Sql createCountSql(IDbDao dbDao, SqlManager sqlManager) {
		Sql sql = Sqls.create(sqlManager.get("sign_in_list_count"));
		sql.setCondition(cnd());
		return sql;
	}
	
	private Cnd cnd() { 
		Cnd cnd = Cnd.limit();
		if (!Util.isEmpty(gameId))
			cnd.and("si.gameId", "=",gameId);
//		cnd.asc("c.sort") ;
//		cnd.desc("c.id") ;
		return cnd;
	}

}
