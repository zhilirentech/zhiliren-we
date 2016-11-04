package com.xiaoka.game.admin.keyvalue.service;

import java.util.Map;

import org.nutz.dao.pager.Pager;

import com.xiaoka.game.admin.keyvalue.form.KeyValueAddForm;
import com.xiaoka.game.admin.keyvalue.form.KeyValueModForm;
import com.xiaoka.game.admin.keyvalue.form.KeyValueQueryForm;

public interface KeyValueService {

	/**
	 * 添加
	 */
	public Map<String, String> add(KeyValueAddForm addForm);

	/**
	 * 修改
	 */
	public Map<String, String> update(KeyValueModForm addForm);

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
	public  Map<String, Object> query(final KeyValueQueryForm queryForm,final Pager pager) ;
	

}
