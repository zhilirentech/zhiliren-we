package com.xiaoka.game.wap.activity.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.nutz.dao.Cnd;
import org.nutz.dao.Sqls;
import org.nutz.dao.pager.Pager;
import org.nutz.dao.sql.Sql;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;

import com.uxuexi.core.db.util.DbSqlUtil;
import com.uxuexi.core.web.util.FormUtil;
import com.xiaoka.game.admin.activity.entity.ActivityEntity;
import com.xiaoka.game.admin.activity.form.ActivityModForm;
import com.xiaoka.game.admin.banner.entity.BannerEntity;
import com.xiaoka.game.admin.banner.form.BannerQueryForm;
import com.xiaoka.game.admin.banner.service.BannerService;
import com.xiaoka.game.common.service.BaseService;
import com.xiaoka.game.wap.activity.dto.ActiviDto;
import com.xiaoka.game.wap.activity.form.ActiviSqlForm;
import com.xiaoka.game.wap.activity.service.ActiviService;

@IocBean(name= "activiService")
public class ActiviServiceImpl extends BaseService implements ActiviService{

	@Inject
	private BannerService bannerService ;
	
	@Override
	public Object list(final ActiviSqlForm sqlForm,final Pager pager) {
		/*活动页图片*/
		BannerQueryForm queryForm = new BannerQueryForm();
		queryForm.setType(1);
		Cnd cnd = Cnd.limit();
		cnd.and("type","=","1");
		List<BannerEntity> banerList = dbDao.query(BannerEntity.class,cnd, pager);
		//查询历史活动
		Map<String, Object> obj = FormUtil.query(dbDao, sqlManager, sqlForm, pager);
		obj.put("banerList", banerList);
		//查询热门活动
		Sql couseSql = Sqls.create(sqlManager.get("activityHot_list")) ;
		List<ActiviDto> hotList = DbSqlUtil.query(dbDao, ActiviDto.class, couseSql, null) ;
		obj.put("hotList", hotList);
		return obj;
	}
	
	public Map<String,Object>  acdetail(final ActivityModForm modForm){
		ActivityEntity acdeital = dbDao.fetch(ActivityEntity.class, Cnd.where("id", "=", modForm.getId()));
		Map<String, Object> obj = new HashMap<String, Object>();
		obj.put("HotHis", modForm.getHotHis());
		obj.put("acdeital", acdeital);
		return obj;
	}
}
