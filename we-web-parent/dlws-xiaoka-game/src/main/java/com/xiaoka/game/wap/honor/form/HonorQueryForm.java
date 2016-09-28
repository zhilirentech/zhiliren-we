package com.xiaoka.game.wap.honor.form;

import lombok.Data;
import lombok.EqualsAndHashCode;

import org.nutz.dao.Cnd;

import com.uxuexi.core.web.form.QueryForm;

@Data
@EqualsAndHashCode(callSuper = true)
public class HonorQueryForm extends QueryForm{
	
	public  Cnd cnd() {
		Cnd cnd = Cnd.limit();
		cnd.and("isDel", "=", "1");
		return cnd;
	}
	
}
