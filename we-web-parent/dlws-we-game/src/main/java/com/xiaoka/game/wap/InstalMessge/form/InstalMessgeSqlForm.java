package com.xiaoka.game.wap.InstalMessge.form;

import java.sql.Timestamp;

import lombok.Data;

import org.nutz.dao.Cnd;
import org.nutz.dao.SqlManager;
import org.nutz.dao.Sqls;
import org.nutz.dao.sql.Sql;

import com.uxuexi.core.common.util.Util;
import com.uxuexi.core.db.dao.IDbDao;
import com.uxuexi.core.web.form.ISqlForm;

@Data
public class InstalMessgeSqlForm implements ISqlForm {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 发布时间
	 */
	private Timestamp postTime ; 
	/**
	 * 内容
	 */
	private String content ; 
	/**
	 * 用户Id
	 */
	private String openId ; 
	

	@Override
	public Sql createPagerSql(IDbDao dbDao, SqlManager sqlManager) {
		Sql sql = Sqls.create(sqlManager.get("instal_messge_list"));
		sql.setCondition(cnd());
		return sql ;
	}

	@Override
	public Sql createCountSql(IDbDao dbDao, SqlManager sqlManager) {
		Sql sql = Sqls.create(sqlManager.get("instal_messge_list_count"));
		sql.setCondition(cnd());
		return sql;
	}
	public  Cnd cnd() {
		Cnd cnd = Cnd.limit();
		if (!Util.isEmpty(openId)){
		}
		return cnd;
	}

}
