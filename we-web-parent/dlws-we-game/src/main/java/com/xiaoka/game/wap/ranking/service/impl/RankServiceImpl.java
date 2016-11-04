package com.xiaoka.game.wap.ranking.service.impl;

import java.util.List;
import java.util.Map;

import org.nutz.dao.Cnd;
import org.nutz.dao.Sqls;
import org.nutz.dao.pager.Pager;
import org.nutz.dao.sql.Sql;
import org.nutz.ioc.loader.annotation.IocBean;

import com.uxuexi.core.db.util.DbSqlUtil;
import com.uxuexi.core.web.util.FormUtil;
import com.xiaoka.game.admin.banner.entity.BannerEntity;
import com.xiaoka.game.common.service.BaseService;
import com.xiaoka.game.wap.ranking.dto.RankDto;
import com.xiaoka.game.wap.ranking.form.RankSqlForm;
import com.xiaoka.game.wap.ranking.form.ScolRankSqlForm;
import com.xiaoka.game.wap.ranking.service.RankService;

@IocBean(name= "rankService")
public class RankServiceImpl extends BaseService implements RankService{
	
	@Override
	public Object list(final RankSqlForm sqlForm,final Pager pager) {
		Map<String, Object> obj = FormUtil.query(dbDao, sqlManager, sqlForm, pager);
		Sql renSql = Sqls.create(sqlManager.get("ranking_RenCount"));
		List<RankDto> dtoLst = DbSqlUtil.query(dbDao, RankDto.class, renSql, null) ;
		
		/*排行页图片*/
		Cnd cnd = Cnd.limit();
		cnd.and("type","=","0");
		List<BannerEntity> banerList = dbDao.query(BannerEntity.class,cnd, pager);
		
		obj.put("banerList", banerList);//图片
		obj.put("dtoLst", dtoLst);//全国一共多少人参加up
		return obj;
	}
	
	@Override
	public Object schoList(final ScolRankSqlForm sqlForm,final Pager pager) {
		Map<String, Object> obj = FormUtil.query(dbDao, sqlManager, sqlForm, pager);
		return obj;
	}
	
	
}
