package com.xiaoka.game.admin.bulletin.service;

import java.util.List;
import java.util.Map;

import org.nutz.dao.pager.Pager;

import com.xiaoka.game.admin.banner.entity.BannerEntity;
import com.xiaoka.game.admin.bulletin.form.BulletinAddForm;
import com.xiaoka.game.admin.bulletin.form.BulletinModForm;
import com.xiaoka.game.admin.bulletin.form.BulletinQueryForm;

public interface BulletinService {

	/**
	 * 添加
	 */
	public Map<String, String> add(BulletinAddForm addForm);

	/**
	 * 修改
	 */
	public Map<String, String> update(BulletinModForm addForm);

	/**
	 * 按照id查询
	 */
	public Map<String, Object> find(final long id) ;
	
	/**
	 * 按照id删除
	 */
	public int delete(final long id) ;
	
	/**
	 * 批量删除
	 */
	public int batchDelete(final long[] ids) ;
	
	
	/**
	 * 分页查询
	 */
	public  Map<String, Object> query(final BulletinQueryForm queryForm,final Pager pager) ;
	

}
