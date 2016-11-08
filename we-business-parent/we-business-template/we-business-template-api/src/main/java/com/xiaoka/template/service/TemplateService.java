package com.xiaoka.template.service;

import org.nutz.dao.pager.Pager;

import com.xiaoka.template.service.form.ParamForm;

public interface TemplateService {
	
	/**查询测试列表*/
	public Object queryTestlist(final ParamForm queryForm,final Pager pager);
	
	public String sayHello(final String name);

}
