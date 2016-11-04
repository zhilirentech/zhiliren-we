package com.xiaoka.game.admin.robot.form;

import lombok.Data;

import org.nutz.dao.Cnd;
import org.nutz.dao.SqlManager;
import org.nutz.dao.Sqls;
import org.nutz.dao.sql.Sql;

import com.uxuexi.core.common.util.Util;
import com.uxuexi.core.db.dao.IDbDao;
import com.uxuexi.core.web.form.ISqlForm;

@Data
public class RobotQueryForm implements ISqlForm{@Override
	public Sql createPagerSql(IDbDao dbDao, SqlManager sqlManager) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Sql createCountSql(IDbDao dbDao, SqlManager sqlManager) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
