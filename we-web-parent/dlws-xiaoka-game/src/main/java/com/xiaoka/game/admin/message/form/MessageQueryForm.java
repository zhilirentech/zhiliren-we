package com.xiaoka.game.admin.message.form;

import lombok.Data;
import lombok.EqualsAndHashCode;

import org.nutz.dao.Cnd;

import com.uxuexi.core.common.util.Util;
import com.uxuexi.core.web.form.QueryForm;

@Data
@EqualsAndHashCode(callSuper = true)
public class MessageQueryForm extends QueryForm{
	private String title;
	public  Cnd cnd() {
		Cnd cnd = Cnd.limit();
		cnd.and("isDelete", "=", "1");
		if(!Util.isEmpty(title)){
		    cnd.and("title", "like", "%"+title+"%");
			}
		return cnd;
	}
	
}
