/**
 * SqlParamForm.java
 * com.xiaoka.game.common.form
 * Copyright (c) 2016, 北京聚智未来科技有限公司版权所有.
*/

package com.xiaoka.template.common.form;

import org.nutz.dao.SqlManager;
import org.nutz.dao.sql.Sql;

/**
 * 封装复杂SQL查询参数,可直接用于接收页面参数，然后封装好查询SQL
 * <p>
 * @author   朱晓川
 * @Date	 2016年8月26日 	 
 */
public interface SQLParamForm {

	/**
	 * 返回封装好查询参数之后的完整查询sql
	 */
	public Sql sql(final SqlManager sqlManager);

}
