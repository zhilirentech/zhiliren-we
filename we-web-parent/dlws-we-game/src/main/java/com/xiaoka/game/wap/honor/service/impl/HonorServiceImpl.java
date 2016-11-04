package com.xiaoka.game.wap.honor.service.impl;

import java.util.List;
import java.util.Map;

import org.nutz.dao.Cnd;
import org.nutz.dao.Sqls;
import org.nutz.dao.pager.Pager;
import org.nutz.dao.sql.Sql;
import org.nutz.ioc.loader.annotation.IocBean;

import com.uxuexi.core.db.util.DbSqlUtil;
import com.uxuexi.core.web.util.FormUtil;
import com.xiaoka.game.common.service.BaseService;
import com.xiaoka.game.wap.honor.dto.RenDto;
import com.xiaoka.game.wap.honor.entity.HonorEntity;
import com.xiaoka.game.wap.honor.form.HonorSqlForm;
import com.xiaoka.game.wap.honor.service.HonorService;

@IocBean(name= "honorService")
public class HonorServiceImpl extends BaseService implements HonorService{
	
	@Override
	public Object list(String openId,final HonorSqlForm sqlForm,final Pager pager) {
		
		/*所有勋章*/
		Map<String, Object> obj = FormUtil.query(dbDao, HonorEntity.class, Cnd.where("isDel", "=", "1"), null);
		
		/*微信用户个人信息*/
		Sql renSql = Sqls.create(sqlManager.get("honor_percen"));
		renSql.params().set("openId", openId) ;
		List<RenDto> dtoLst = DbSqlUtil.query(dbDao, RenDto.class, renSql, null) ;
		obj.put("dtoLst", dtoLst);
		
		/*最大签到天数*/
		Sql signDaySql = Sqls.create(sqlManager.get("honor_sign_max"));
		signDaySql.params().set("openId", openId) ;
		List<RenDto> signDayList = DbSqlUtil.query(dbDao, RenDto.class, signDaySql, null) ;
		obj.put("signDayList", signDayList);
		
		
		return obj;
	}
	
}
