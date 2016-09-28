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
public class PkRecordSqlForm implements ISqlForm {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/*商品名称*/
	private String name;
	
	/*审核状态*/
	private Integer auditStatus ;
	
	/*课程类型*/
	private Integer courseType ;
	
	//按照创建日期区间进行查询
	/**
	 * 创建日期起始时间
	 */
	private Timestamp createTimeBegin ; 
	
	/**
	 * 创建日期结束时间
	 */
	private Timestamp createTimeEnd ; 

	@Override
	public Sql createPagerSql(IDbDao dbDao, SqlManager sqlManager) {
		Sql sql = Sqls.create(sqlManager.get("pk_record_list"));
		sql.setCondition(cnd());
		return sql ;
	}

	@Override
	public Sql createCountSql(IDbDao dbDao, SqlManager sqlManager) {
		Sql sql = Sqls.create(sqlManager.get("pk_record_list_count"));
		sql.setCondition(cnd());
		return sql;
	}
	
	private Cnd cnd() { 
		Cnd cnd = Cnd.limit();
		
		if (!Util.isEmpty(name))
			cnd.and("c.name", "LIKE", "%"+name+"%");
		
		if (!Util.isEmpty(courseType))
			cnd.and("c.courseType", "=",courseType); 
		
		/**结束时间如果输入日期yyyy-MM-dd，则结束时间需要取当日的最后一秒*/
		Timestamp end = null ;
		if(!Util.isEmpty(createTimeEnd)){
			end = new Timestamp(createTimeEnd.getTime() + (DateUtil.MILLIS_OF_DAY - 1)) ;
		}
		
		//起始时间
		if (!Util.isEmpty(createTimeBegin) && Util.isEmpty(end))
			cnd.and("c.createTime", ">=",createTimeBegin);
		
		if (Util.isEmpty(createTimeBegin) && !Util.isEmpty(end))
			cnd.and("c.createTime", "<=",end);
		
		if (!Util.isEmpty(createTimeBegin) && !Util.isEmpty(end)){
			cnd.and("c.createTime", ">=",createTimeBegin);
			cnd.and("c.createTime", "<=",end);
		}
		
//		cnd.asc("c.sort") ;
//		cnd.desc("c.id") ;
		return cnd;
	}

}
