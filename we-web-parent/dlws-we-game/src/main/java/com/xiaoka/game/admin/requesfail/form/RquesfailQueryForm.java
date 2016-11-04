package com.xiaoka.game.admin.requesfail.form;

import org.nutz.dao.Cnd;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.uxuexi.core.web.form.QueryForm;

@Data
@EqualsAndHashCode(callSuper = true)
public class RquesfailQueryForm extends QueryForm{
	public  Cnd cnd() {
		Cnd cnd = Cnd.limit();
		cnd.and("status", "=", "3");
		return cnd;
	}
	
}
