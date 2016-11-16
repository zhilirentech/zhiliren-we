package com.linyun.airline.forms;

import com.linyun.airline.entities.AppVersionEntity;
import com.uxuexi.core.web.form.SQLParamForm;
import com.uxuexi.core.db.util.EntityUtil;

import org.nutz.dao.Cnd;
import org.nutz.dao.SqlManager;
import org.nutz.dao.Sqls;
import org.nutz.dao.sql.Sql;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;
import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = true)
public class AppVersionForm extends SQLParamForm implements Serializable{
	private static final long serialVersionUID = 1L;
	/**主键*/
	private Long id;
	
	/**APP类型*/
	private Integer appType;
	
	/**版本号*/
	private String version;
	
	/**版本名称*/
	private String versionName;
	
	/**更新内容*/
	private String content;
	
	/**是否强制升级*/
	private Integer isForced;
	
	/**更新时间*/
	private Date updateTime;
	
	/**下载地址*/
	private String downloadUrl;
	
	/**每天提示次数*/
	private Integer count;
	
	@Override
	public Sql sql(SqlManager sqlManager) {
		/**
		 * 默认使用了当前form关联entity的单表查询sql,如果是多表复杂sql，
		 * 请使用sqlManager获取自定义的sql，并设置查询条件
		 */
		String sqlString = EntityUtil.entityCndSql(AppVersionEntity.class);
		Sql sql = Sqls.create(sqlString);
		sql.setCondition(cnd());
		return sql;
	}

	private Cnd cnd() {
		Cnd cnd = Cnd.NEW();
		//TODO 添加自定义查询条件（可选）

		return cnd;
	}
}