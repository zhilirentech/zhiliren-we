package com.linyun.airline.admin.log.form;

import lombok.Data;
import lombok.EqualsAndHashCode;

import org.nutz.dao.Cnd;

import com.uxuexi.core.common.util.Util;
import com.uxuexi.core.web.form.QueryForm;
import com.uxuexi.core.web.form.support.Condition;
import com.uxuexi.core.web.form.support.Condition.MatchType;

@Data
@EqualsAndHashCode(callSuper = true)
public class SLogQueryForm extends QueryForm {
	
	/**
	 * 操作员名称
	 */
	@Condition(match = MatchType.LIKE)
	private String operatorName ;
	
	/**
	 * 功能名称
	 */
	@Condition(match = MatchType.LIKE)
	private String functionName ;
	
	@Override
	public Cnd createCnd() {
		Cnd cnd = Cnd.limit();
		if(Util.isEmpty(cnd)){
			cnd = Cnd.limit();
		}
		cnd.desc("operatorTime") ;
		return cnd ;
	}
	
}
