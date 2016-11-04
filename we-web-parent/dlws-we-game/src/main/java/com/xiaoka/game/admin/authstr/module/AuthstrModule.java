package com.xiaoka.game.admin.authstr.module;

import org.nutz.dao.Cnd;
import org.nutz.dao.pager.Pager;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.Param;

import com.uxuexi.core.db.dao.IDbDao;
import com.uxuexi.core.web.util.FormUtil;
import com.xiaoka.game.admin.authstr.entity.AuthstrEntity;
import com.xiaoka.game.admin.authstr.form.AuthstrQueryForm;
import com.xiaoka.game.admin.authstr.service.AuthstrService;

@IocBean
@At("/admin/authstr")
public class AuthstrModule {

	/**
	 * 注入容器中的dbDao对象，用于数据库查询、持久操作
	 */
	@Inject
	private IDbDao dbDao;
	@Inject
	private AuthstrService authstrService;

	/**
	 * 分页查询
	 * <P>
	 * 
	 * @param queryForm  查询表单
	 * @param pager      分页对象
	 */
	@At
	@Ok("jsp")
	public Object list(@Param("..") final AuthstrQueryForm queryForm, @Param("..") final Pager pager) {
		return FormUtil.query(dbDao, AuthstrEntity.class, Cnd.where("checkStatus", "=", 0), pager);
	}
	
}
