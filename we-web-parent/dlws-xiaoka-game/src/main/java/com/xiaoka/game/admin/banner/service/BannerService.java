package com.xiaoka.game.admin.banner.service;

import java.util.List;
import java.util.Map;

import org.nutz.dao.pager.Pager;

import com.xiaoka.game.admin.banner.entity.BannerEntity;
import com.xiaoka.game.admin.banner.form.BannerAddForm;
import com.xiaoka.game.admin.banner.form.BannerModForm;
import com.xiaoka.game.admin.banner.form.BannerQueryForm;

public interface BannerService {

	/**
	 * 添加
	 */
	public Map<String, String> add(BannerAddForm addForm);

	/**
	 * 修改
	 */
	public Map<String, String> update(BannerModForm addForm);

	/**
	 *  查询所有
	 */
	public List<BannerEntity> listAll();
	
	/**
	 * 按照id查询
	 */
	public Object find(final long id) ;
	
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
	public  Map<String, Object> query(final BannerQueryForm queryForm,final Pager pager) ;
	

}
