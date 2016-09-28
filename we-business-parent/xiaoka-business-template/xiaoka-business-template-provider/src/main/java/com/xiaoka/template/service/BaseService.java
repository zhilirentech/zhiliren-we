package com.xiaoka.template.service;

import org.nutz.dao.SqlManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.uxuexi.core.db.dao.IDbDao;

@Component
public class BaseService {

	/**
	 * 注入容器中的dbDao对象，用于数据库查询、持久操作
	 */
	@Autowired
	protected IDbDao dbDao;

	/**
	 * 注入容器中的sqlManager对象，用于获取sql
	 */
	@Autowired
	protected SqlManager sqlManager;

	protected Logger logger = LoggerFactory.getLogger(BaseService.class);

}
