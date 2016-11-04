package com.xiaoka.game.admin.customer.form;

import lombok.Data;
import lombok.EqualsAndHashCode;

import org.nutz.dao.Cnd;

import com.uxuexi.core.common.util.Util;
import com.uxuexi.core.web.form.QueryForm;

@Data
@EqualsAndHashCode(callSuper = true)
public class CustomerQueryForm extends QueryForm{
	private String customerName;
	public  Cnd cnd() {
	Cnd cnd = Cnd.limit();
	if(!Util.isEmpty(customerName)){
	    cnd.and("customerName", "like", "%"+customerName+"%");
		}
	return cnd;
	}
}
