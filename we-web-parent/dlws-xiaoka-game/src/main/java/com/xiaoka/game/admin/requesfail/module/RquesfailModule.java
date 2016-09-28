package com.xiaoka.game.admin.requesfail.module;

import org.nutz.dao.SqlManager;
import org.nutz.dao.pager.Pager;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.Param;

import com.uxuexi.core.db.dao.IDbDao;
import com.uxuexi.core.web.util.FormUtil;
import com.xiaoka.game.admin.requesfail.entity.RquesfailEntity;
import com.xiaoka.game.admin.requesfail.form.RquesfailQueryForm;

/**
 * 
 * 功能管理	控制类
 *
 */
@IocBean
@At("/admin/rquesfail")
public class RquesfailModule {
	/**
	 * 注入容器中的dbDao对象，用于数据库查询、持久操作
	 */
	@Inject
	private IDbDao dbDao;
	
	/**
	 * 注入容器中管理sql的对象，用于从sql文件中根据key取得sql
	 */
	@Inject
	private SqlManager sqlManager;


	/**
	 * 分页查询功能
	 * <P>
	 * 
	 * @param queryForm  查询表单
	 * @param pager      分页对象
	 */
	@At
	@Ok("jsp")
	public Object list(@Param("..") final RquesfailQueryForm queryForm, @Param("..") final Pager pager) {
		return FormUtil.query(dbDao, RquesfailEntity.class, queryForm.cnd(), pager);
	}

}
