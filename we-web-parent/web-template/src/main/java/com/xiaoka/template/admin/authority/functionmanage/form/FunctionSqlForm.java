package com.xiaoka.template.admin.authority.functionmanage.form;

import lombok.Data;

import org.nutz.dao.Cnd;
import org.nutz.dao.SqlManager;
import org.nutz.dao.Sqls;
import org.nutz.dao.sql.Sql;

import com.uxuexi.core.common.util.Util;
import com.uxuexi.core.db.dao.IDbDao;
import com.uxuexi.core.web.form.ISqlForm;

@Data
public class FunctionSqlForm implements ISqlForm {
	
	/**
	 * 检索条件
	 */
	private String name;
	
	/**上级功能*/
	private Integer parentId;

	@Override
	public Sql createPagerSql(IDbDao dbDao, SqlManager sqlManager) {
		Sql sql = Sqls.create(sqlManager.get("function_manage_list"));
		sql.setCondition(cnd());
		return sql ;
	}

	@Override
	public Sql createCountSql(IDbDao dbDao, SqlManager sqlManager) {
		Sql sql = Sqls.create(sqlManager.get("function_manage_list_count"));
		sql.setCondition(cnd());
		return sql;
	}
	
	private Cnd cnd() {
		Cnd cnd = Cnd.limit();
		if (!Util.isEmpty(name))
			cnd.and("s.name", "LIKE", "%"+name+"%");
		
		if (!Util.isEmpty(parentId) && parentId > 0){
			cnd.and("s.parentId", "=", parentId);
		}
		cnd.desc("s.id") ;
		return cnd;
	}

}
