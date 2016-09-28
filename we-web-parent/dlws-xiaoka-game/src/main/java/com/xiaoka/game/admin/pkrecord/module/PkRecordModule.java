package com.xiaoka.game.admin.pkrecord.module;

import org.nutz.dao.pager.Pager;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.GET;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.POST;
import org.nutz.mvc.annotation.Param;

import com.uxuexi.core.common.util.DateTimeUtil;
import com.uxuexi.core.db.dao.IDbDao;
import com.uxuexi.core.web.chain.support.JsonResult;
import com.uxuexi.core.web.util.FormUtil;
import com.xiaoka.game.admin.authority.rolemanage.entity.RoleEntity;
import com.xiaoka.game.admin.authority.rolemanage.form.RoleAddForm;
import com.xiaoka.game.admin.authority.rolemanage.form.RoleModForm;
import com.xiaoka.game.admin.authority.rolemanage.form.RoleQueryForm;
import com.xiaoka.game.admin.pkrecord.form.PkRecordSqlForm;
import com.xiaoka.game.admin.pkrecord.form.SignInSqlForm;
import com.xiaoka.game.admin.pkrecord.service.PkRecordService;

@IocBean
@At("/admin/pkRecord")
public class PkRecordModule {
	
	/**
	 * 注入容器中的dbDao对象，用于数据库查询、持久操作
	 */
	@Inject
	private IDbDao dbDao;
	
	@Inject
	private PkRecordService pkRecordService;
	
	/**
	 * 
	 * 项目名称：dlws-xiaoka-game
	 * 描述：分页查询,用户发起的活动
	 * 创建人： ln
	 * 创建时间： 2016年8月11日
	 * 标记：
	 * @param queryForm 查询表单
	 * @param pager 	分页对象
	 * @return
	 * @version
	 */
	@At
	@Ok("jsp")
	public Object list(@Param("..") final PkRecordSqlForm sqlForm, @Param("..") final Pager pager) {
	
		return pkRecordService.queryPkRecordList(sqlForm,pager);
	/*return FormUtil.query(dbDao, sqlManager, sqlForm, pager);*/
	}
	/**
	 * 
	 * 项目名称：dlws-xiaoka-game
	 * 描述：分页查询某一个game参与人员列表
	 * 创建人： ln
	 * 创建时间： 2016年8月11日
	 * 标记：
	 * @param sqlForm
	 * @param pager
	 * @return
	 * @version
	 */
	@At
	@Ok("jsp")
	public Object signInByGameIdList(@Param("..") final SignInSqlForm sqlForm, @Param("..") final Pager pager) {
		return pkRecordService.signInByGameIdList(sqlForm,pager);
	/*return FormUtil.query(dbDao, sqlManager, sqlForm, pager);*/
	}
	


	
}
