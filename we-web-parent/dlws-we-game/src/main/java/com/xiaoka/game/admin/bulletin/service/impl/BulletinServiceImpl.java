package com.xiaoka.game.admin.bulletin.service.impl;

import java.util.HashMap;
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
import com.xiaoka.game.admin.bulletin.entity.BulletinEntity;
import com.xiaoka.game.admin.bulletin.form.BulletinAddForm;
import com.xiaoka.game.admin.bulletin.form.BulletinModForm;
import com.xiaoka.game.admin.bulletin.form.BulletinQueryForm;
import com.xiaoka.game.admin.bulletin.service.BulletinService;
import com.xiaoka.game.common.enums.BannerStatusEnum;
import com.xiaoka.game.common.enums.BannerTypeEnum;
import com.xiaoka.game.common.service.BaseService;

@IocBean(name="bulletinService")
public class BulletinServiceImpl extends BaseService implements BulletinService	 {

	@Override
	public Map<String, String> add(BulletinAddForm addForm) { 
		FormUtil.add(dbDao, addForm, BulletinEntity.class);
		return JsonResult.success("添加成功");
	}

	@Override
	public Map<String, String> update(BulletinModForm modForm) {
		BulletinEntity entity = dbDao.fetch(BulletinEntity.class, Cnd.where("id", "=", modForm.getId()));
		if (Util.isEmpty(entity)) {
			return JsonResult.error("公告不存在");
		}

		BeanUtil.copyProperties(modForm, entity);
		dbDao.update(entity);
		return JsonResult.success("修改成功");

	}


	@Override
	public Map<String, Object> find(long id) {
		Map<String, Object> obj = new HashMap<String,Object>();
		BulletinEntity bulletin = dbDao.fetch(BulletinEntity.class, id) ;
//		String content = bulletin.getContent() ;
//		content = content.replaceAll("<br/>", "\r\n") ;
//		bulletin.setContent(content);
		obj.put("bulletin", bulletin);
		return obj; 
	}

	@Override
	public int delete(long id) {
		return FormUtil.delete(dbDao, BulletinEntity.class, id); 
	}
	
	@Override
	public int batchDelete(long[] ids){
		return FormUtil.delete(dbDao, BulletinEntity.class, ids);
	}
	@Override
	public Map<String, Object> query(BulletinQueryForm queryForm, Pager pager) {
		return FormUtil.query(dbDao, BulletinEntity.class, queryForm, pager); 
	}

	
}
