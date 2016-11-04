package com.xiaoka.game.admin.keyvalue.module;

import java.util.HashMap;
import java.util.Map;

import org.nutz.dao.pager.Pager;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.GET;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.POST;
import org.nutz.mvc.annotation.Param;

import com.uxuexi.core.common.util.EnumUtil;
import com.uxuexi.core.web.chain.support.JsonResult;
import com.xiaoka.game.admin.keyvalue.form.KeyValueAddForm;
import com.xiaoka.game.admin.keyvalue.form.KeyValueModForm;
import com.xiaoka.game.admin.keyvalue.form.KeyValueQueryForm;
import com.xiaoka.game.admin.keyvalue.service.KeyValueService;
import com.xiaoka.game.common.enums.BannerTypeEnum;
import com.xiaoka.game.common.enums.KeyValueEnum;

@IocBean
@At("/admin/keyvalue")
public class KeyValueModule {
	
	@Inject
	private KeyValueService keyValueService ; 

	/**
	 * 跳转到'添加操作'的录入数据页面
	 */
	@At
	@GET
	@Ok("jsp")
	public Object add() {
		Map<String, Object> obj = new HashMap<String,Object>();
		obj.put("keyValueEnum", EnumUtil.enum2(KeyValueEnum.class)) ;
		return obj;
		
	}

	/**
	 * 添加
	 * 
	 * @param addForm 添加表单对象
	 */
	@At
	@POST
	public Object add(@Param("..") final KeyValueAddForm addForm) {
		return keyValueService.add(addForm);
	}

	/**
	 * 跳转到'修改操作'的录入数据页面,实际就是[按照主键查询单个实体]
	 */
	@At
	@GET
	@Ok("jsp")
	public Object update(@Param("id") final long id) {
		Map<String, Object> map = keyValueService.find(id);
		map.put("keyValueEnum", EnumUtil.enum2(KeyValueEnum.class)) ;
		return map; 
	}

	/**
	 * 执行'修改操作'
	 */
	@At
	@POST
	public Object update(@Param("..") final KeyValueModForm modForm) {
		return keyValueService.update(modForm);
	}

	/**
	 * 查询分页信息
	 * <P>
	 * 
	 * @param queryForm  查询表单
	 * @param pager      分页对象
	 */
	@At
	@Ok("jsp")
	public Object list(@Param("..") final KeyValueQueryForm queryForm, @Param("..") final Pager pager) {
		Map<String, Object> map = keyValueService.query(queryForm, pager); 
		map.put("keyValueEnum", EnumUtil.enum2(KeyValueEnum.class)) ;
		return map;
	}

	/**
	 * 删除记录
	 */
	@At
	public Object delete(@Param("id") final long id) {
		keyValueService.delete(id); 
		return JsonResult.success("删除成功");
	}
	/**
	 * 批量删除记录
	 */
	@At
	public Object batchDelete(@Param("ids") final long[] ids) {
		keyValueService.batchDelete(ids); 
		return JsonResult.success("删除成功");
	}
}
