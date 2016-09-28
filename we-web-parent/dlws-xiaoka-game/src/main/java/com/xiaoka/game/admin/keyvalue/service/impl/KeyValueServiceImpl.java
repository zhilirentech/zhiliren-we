package com.xiaoka.game.admin.keyvalue.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.nutz.dao.Cnd;
import org.nutz.dao.pager.Pager;
import org.nutz.ioc.loader.annotation.IocBean;

import com.uxuexi.core.common.util.BeanUtil;
import com.uxuexi.core.common.util.Util;
import com.uxuexi.core.web.chain.support.JsonResult;
import com.uxuexi.core.web.util.FormUtil;
import com.xiaoka.game.admin.keyvalue.entity.KeyValueEntity;
import com.xiaoka.game.admin.keyvalue.form.KeyValueAddForm;
import com.xiaoka.game.admin.keyvalue.form.KeyValueModForm;
import com.xiaoka.game.admin.keyvalue.form.KeyValueQueryForm;
import com.xiaoka.game.admin.keyvalue.service.KeyValueService;
import com.xiaoka.game.common.service.BaseService;

@IocBean(name="keyValueService")
public class KeyValueServiceImpl extends BaseService implements KeyValueService	 {

	@Override
	public Map<String, String> add(KeyValueAddForm addForm) { 
		FormUtil.add(dbDao, addForm, KeyValueEntity.class);
		return JsonResult.success("添加成功");
	}

	@Override
	public Map<String, String> update(KeyValueModForm modForm) {
		KeyValueEntity entity = dbDao.fetch(KeyValueEntity.class, Cnd.where("id", "=", modForm.getId()));
		if (Util.isEmpty(entity)) {
			return JsonResult.error("数据不存在");
		}

		BeanUtil.copyProperties(modForm, entity);
		dbDao.update(entity);
		return JsonResult.success("修改成功");

	}


	@Override
	public Map<String, Object> find(long id) {
		Map<String, Object> obj = new HashMap<String,Object>();
		KeyValueEntity entity = dbDao.fetch(KeyValueEntity.class, id);
		String[] strs = entity.getNameVal().split("-");
		obj.put("keyValue", entity);
		obj.put("strs", strs);
		return obj; 
	}

	@Override
	public int delete(long id) {
		return FormUtil.delete(dbDao, KeyValueEntity.class, id); 
	}
	
	@Override
	public int batchDelete(long[] ids){
		return FormUtil.delete(dbDao, KeyValueEntity.class, ids);
	}
	@Override
	public Map<String, Object> query(KeyValueQueryForm queryForm, Pager pager) {
		return FormUtil.query(dbDao, KeyValueEntity.class, queryForm, pager); 
	}

	
}
