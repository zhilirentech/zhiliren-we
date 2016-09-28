package com.xiaoka.template.service.impl;


import java.util.Map;

import org.nutz.dao.Cnd;
import org.nutz.dao.pager.Pager;
import org.springframework.stereotype.Component;

import com.uxuexi.core.web.util.FormUtil;
import com.xiaoka.template.service.BaseService;
import com.xiaoka.template.service.TemplateService;
import com.xiaoka.template.service.entity.TestEntity;
import com.xiaoka.template.service.form.ParamForm;

@Component(value = "templateService")
public class TemplateServiceImpl extends BaseService implements TemplateService{

	@Override
	public Object queryTestlist(ParamForm queryForm, Pager pager) {
		Map<String,Object> obj = FormUtil.query(dbDao, TestEntity.class, Cnd.where("name", "=", queryForm.getName()), pager);
		return obj ;
	}

	@Override
	public String sayHello(String name) {
		return  " Say Hello to " + name; 
	}
	

}
