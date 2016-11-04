package com.xiaoka.game.admin.activity.form;

import java.sql.Timestamp;

import lombok.Data;
import lombok.EqualsAndHashCode;

import org.nutz.dao.Cnd;

import com.uxuexi.core.common.util.Util;
import com.uxuexi.core.web.form.QueryForm;

@Data
@EqualsAndHashCode(callSuper = true)
public class ActivityQueryForm extends QueryForm{
	private String title;
	
	private Timestamp beginTime;
	
	private Timestamp endTime;
	
	public  Cnd cnd() {
		Cnd cnd = Cnd.limit();
		cnd.and("isDel", "=", "1");
		if(!Util.isEmpty(title)){
	    cnd.and("title", "like", "%"+title+"%");
		}
		if(!Util.isEmpty(beginTime)&&!Util.isEmpty(endTime)){
			cnd.and("beginTime",">=",beginTime);
			cnd.and("endTime","<=",endTime);
		}
		return cnd;
	}
	
}
