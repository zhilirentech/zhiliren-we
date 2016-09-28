package com.xiaoka.game.admin.banner.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.nutz.dao.Cnd;
import org.nutz.dao.pager.Pager;
import org.nutz.ioc.loader.annotation.IocBean;




import com.uxuexi.core.common.util.BeanUtil;
import com.uxuexi.core.common.util.EnumUtil;
import com.uxuexi.core.common.util.Util;
import com.uxuexi.core.web.chain.support.JsonResult;
import com.uxuexi.core.web.util.FormUtil;
import com.xiaoka.game.admin.banner.entity.BannerEntity;
import com.xiaoka.game.admin.banner.form.BannerAddForm;
import com.xiaoka.game.admin.banner.form.BannerModForm;
import com.xiaoka.game.admin.banner.form.BannerQueryForm;
import com.xiaoka.game.admin.banner.service.BannerService;
import com.xiaoka.game.common.enums.BannerStatusEnum;
import com.xiaoka.game.common.enums.BannerTypeEnum;
import com.xiaoka.game.common.service.BaseService;

@IocBean(name="bannerService")
public class BannerServiceImpl extends BaseService implements BannerService {

	@Override
	public Map<String, String> add(BannerAddForm addForm) { 
		FormUtil.add(dbDao, addForm, BannerEntity.class);
		return JsonResult.success("添加成功");
	}

	@Override
	public Map<String, String> update(BannerModForm modForm) {
		BannerEntity entity = dbDao.fetch(BannerEntity.class, Cnd.where("id", "=", modForm.getId()));
		if (Util.isEmpty(entity)) {
			return JsonResult.error("轮播图不存在");
		}

		BeanUtil.copyProperties(modForm, entity);
		dbDao.update(entity);
		return JsonResult.success("修改成功");

	}

	@Override
	public List<BannerEntity> listAll() {
		return dbDao.query(BannerEntity.class, Cnd.where("1", "=", 1).asc("sort"), null);
	}

	@Override
	public Object find(long id) {
		Map<String, Object> obj = new HashMap<String,Object>();
		obj.put("bannerTypeEnum", EnumUtil.enum2(BannerTypeEnum.class)) ;
		obj.put("bannerStatusEnum", EnumUtil.enum2(BannerStatusEnum.class)) ;
		obj.put("banner", dbDao.fetch(BannerEntity.class, id));
		return obj; 
	}

	@Override
	public int delete(long id) {
		return FormUtil.delete(dbDao, BannerEntity.class, id); 
	}
	
	@Override
	public int batchDelete(long[] ids){
		return FormUtil.delete(dbDao, BannerEntity.class, ids);
	}
	@Override
	public Map<String, Object> query(BannerQueryForm queryForm, Pager pager) {
		return FormUtil.query(dbDao, BannerEntity.class, queryForm, pager); 
	}
}
