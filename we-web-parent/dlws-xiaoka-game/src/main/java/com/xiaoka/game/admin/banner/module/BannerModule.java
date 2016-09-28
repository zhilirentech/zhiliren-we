package com.xiaoka.game.admin.banner.module;

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
import com.xiaoka.game.admin.banner.form.BannerAddForm;
import com.xiaoka.game.admin.banner.form.BannerModForm;
import com.xiaoka.game.admin.banner.form.BannerQueryForm;
import com.xiaoka.game.admin.banner.service.BannerService;
import com.xiaoka.game.common.enums.BannerStatusEnum;
import com.xiaoka.game.common.enums.BannerTypeEnum;

@IocBean
@At("/admin/banner")
public class BannerModule {
	
	@Inject
	private BannerService bannerService ;

	/**
	 * 跳转到'添加操作'的录入数据页面
	 */
	@At
	@GET
	@Ok("jsp")
	public Object add() {
		Map<String, Object> obj = new HashMap<String,Object>();
		obj.put("bannerTypeEnum", EnumUtil.enum2(BannerTypeEnum.class)) ;
		obj.put("bannerStatusEnum", EnumUtil.enum2(BannerStatusEnum.class)) ;
		return obj;
		
	}

	/**
	 * 添加
	 * 
	 * @param addForm 添加表单对象
	 */
	@At
	@POST
	public Object add(@Param("..") final BannerAddForm addForm) {
		return bannerService.add(addForm);
	}

	/**
	 * 跳转到'修改操作'的录入数据页面,实际就是[按照主键查询单个实体]
	 */
	@At
	@GET
	@Ok("jsp")
	public Object update(@Param("id") final long id) {
		return bannerService.find(id); 
	}

	/**
	 * 执行'修改操作'
	 */
	@At
	@POST
	public Object update(@Param("..") final BannerModForm modForm) {
		return bannerService.update(modForm);
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
	public Object list(@Param("..") final BannerQueryForm queryForm, @Param("..") final Pager pager) {
		Map<String, Object> map = bannerService.query(queryForm, pager); 
		map.put("bannerTypeEnum", EnumUtil.enum2(BannerTypeEnum.class)) ;
		return map;
	}

	/**
	 * 删除记录
	 */
	@At
	public Object delete(@Param("id") final long id) {
		bannerService.delete(id); 
		return JsonResult.success("删除成功");
	}
	/**
	 * 批量删除记录
	 */
	@At
	public Object batchDelete(@Param("ids") final long[] ids) {
		bannerService.batchDelete(ids); 
		return JsonResult.success("删除成功");
	}
}
