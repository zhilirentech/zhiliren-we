package com.xiaoka.game.admin.signin.form;

import java.sql.Date;

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
public class SignInQueryForm implements ISqlForm{
	/**顾客姓名或openId*/
	private String name ;
	/**
	 * 创建日期
	 */
	private Date signDate;
	/**
	 * 签到状体
	 */
	private Integer status;
	/**
	 * 签到类型   0全国    1个人
	 */
	private int type;
	

	@Override
	public Sql createPagerSql(IDbDao dbDao, SqlManager sqlManager) {
		Sql sql = Sqls.create(sqlManager.get("signin_list"));
		sql.setCondition(cnd());
		return sql ;
	}

	@Override
	public Sql createCountSql(IDbDao dbDao, SqlManager sqlManager) {
		Sql sql = Sqls.create(sqlManager.get("signin_list_count"));
		sql.setCondition(cnd());
		return sql;
	}
	
	private Cnd cnd() { 
		Cnd cnd = Cnd.limit();
		
		if (!Util.isEmpty(signDate)){
			cnd.and("si.signDate", "=",signDate);
		}
		if (!Util.isEmpty(name)){
			SqlExpressionGroup exp = new SqlExpressionGroup() ;
			exp.and("uci.customerName", "LIKE", "%"+name+"%");
			exp.or("si.openId", "LIKE", "%"+name+"%");
			cnd.and(exp);
		}
		if (!Util.isEmpty(status)&&status>-1){
			cnd.and("si.status", "=",status);
		}
		if (!Util.isEmpty(type)&&type>-1){
			cnd.and("si.type", "=",type);
		}
		return cnd;
	}
}
