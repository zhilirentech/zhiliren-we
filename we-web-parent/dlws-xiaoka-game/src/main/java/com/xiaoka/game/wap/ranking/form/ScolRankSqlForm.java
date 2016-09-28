package com.xiaoka.game.wap.ranking.form;

import lombok.Data;

import org.nutz.dao.SqlManager;
import org.nutz.dao.Sqls;
import org.nutz.dao.sql.Sql;

import com.uxuexi.core.common.util.Util;
import com.uxuexi.core.db.dao.IDbDao;
import com.uxuexi.core.web.form.ISqlForm;

@Data
public class ScolRankSqlForm implements ISqlForm {
	
	private String openId;

	@Override
	public Sql createPagerSql(IDbDao dbDao, SqlManager sqlManager) {
		Sql sql = Sqls.create(sqlManager.get("scolrank_list"));
		Cnd(sql);
		return sql ;
	}

	@Override
	public Sql createCountSql(IDbDao dbDao, SqlManager sqlManager) {
		Sql sql = Sqls.create(sqlManager.get("scolrank_list_count"));
		Cnd(sql);
		return sql;
	}
	private String Cnd(Sql sql){
		if(!Util.isEmpty(openId)){
			sql.params().set("openId", openId);
		}
		return openId;
	}

}
