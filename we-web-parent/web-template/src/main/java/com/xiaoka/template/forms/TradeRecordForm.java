package com.xiaoka.template.forms;

import com.uxuexi.core.web.form.SQLParamForm;
import com.uxuexi.core.db.util.EntityUtil;
import org.nutz.dao.Cnd;
import org.nutz.dao.SqlManager;
import org.nutz.dao.Sqls;
import org.nutz.dao.sql.Sql;
import lombok.Data;
import lombok.EqualsAndHashCode;
import com.xiaoka.template.entities.TradeRecordEntity;
import java.util.Date;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = true)
public class TradeRecordForm extends SQLParamForm implements Serializable{
	private static final long serialVersionUID = 1L;
	/**主键*/
	private Long id;
	
	/**单品id*/
	private Integer itemId;
	
	/**是否交易当前单品*/
	private Integer isCurrentItem;
	
	/**平台商品id*/
	private String platformItemId;
	
	/**用户id*/
	private Integer userId;
	
	/**交易平台类型*/
	private Integer tradePlatform;
	
	/**平台用户*/
	private String platformUser;
	
	/**平台交易号*/
	private String tradeNo;
	
	/**支付结果*/
	private Integer payStatus;
	
	/**支付时间*/
	private Date payTime;
	
	@Override
	public Sql sql(SqlManager sqlManager) {
		/**
		 * 默认使用了当前form关联entity的单表查询sql,如果是多表复杂sql，
		 * 请使用sqlManager获取自定义的sql，并设置查询条件
		 */
		String sqlString = EntityUtil.entityCndSql(TradeRecordEntity.class);
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