package com.xiaoka.template.admin.log.module;

import org.nutz.dao.pager.Pager;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.Param;

import com.uxuexi.core.db.dao.IDbDao;
import com.uxuexi.core.web.util.FormUtil;
import com.xiaoka.template.admin.log.entity.SLogEntity;
import com.xiaoka.template.admin.log.form.SLogQueryForm;

/**
 * 
 * 日志管理	controller
 *
 */
@IocBean
@At("/admin/log/system")
public class SLogModule {

	/**
	 * 注入容器中的dbDao对象，用于数据库查询、持久操作
	 */
	@Inject
	private IDbDao dbDao;

	/**
	 * 查询分页信息
	 * <P>
	 * 
	 * @param queryForm  查询表单
	 * @param pager      分页对象
	 */
	@At
	@Ok("jsp")
	public Object list(@Param("..") final SLogQueryForm queryForm, @Param("..") final Pager pager) {
		return FormUtil.query(dbDao, SLogEntity.class, queryForm, pager);
	}

}
