package com.xiaoka.game.admin.level.form;

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
public class GameLevelSqlForm implements ISqlForm {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * levelID
	 */
	private Long id; 
	
	/**
	 * 等级名称
	 */
	private String levelName; 
	
	private int isDel;

	
	@Override
	public Sql createPagerSql(IDbDao dbDao, SqlManager sqlManager) {
		Sql sql = Sqls.create(sqlManager.get("gameLevel_query_list"));
		sql.setCondition(cnd());
		return sql ;
	}

	@Override
	public Sql createCountSql(IDbDao dbDao, SqlManager sqlManager) {
		Sql sql = Sqls.create(sqlManager.get("gameLevel_query_list_count"));
		sql.setCondition(cnd());
		return sql;
	}
	
	private Cnd cnd() { 
		Cnd cnd = Cnd.limit();
		if (!Util.isEmpty(isDel))
			cnd.and("isDel", "=",isDel);
		if(!Util.isEmpty(levelName))
			cnd.and("levelName","like","%"+levelName+"%");

//		cnd.asc("c.sort") ;
//		cnd.desc("c.id") ;
		return cnd;
	}

}
