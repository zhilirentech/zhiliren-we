package com.xiaoka.game.admin.signin.module;

import java.util.Map;

import org.nutz.dao.SqlManager;
import org.nutz.dao.pager.Pager;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.Param;

import com.uxuexi.core.common.util.EnumUtil;
import com.uxuexi.core.db.dao.IDbDao;
import com.uxuexi.core.web.util.FormUtil;
import com.xiaoka.game.admin.signin.entity.SignInEntity;
import com.xiaoka.game.admin.signin.form.SignInQueryForm;
import com.xiaoka.game.admin.signin.service.SignInService;
import com.xiaoka.game.common.enums.AccountTypeEnum;
import com.xiaoka.game.common.enums.SignInStatusEnum;

/**
 * 
 * 功能管理	控制类
 *
 */
@IocBean
@At("/admin/signIn")
public class SignInModule {
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
	
	@Inject
	private SignInService signInService;

	

	/**
	 * 分页查询功能
	 * <P>
	 * 
	 * @param queryForm  查询表单
	 * @param pager      分页对象
	 */
	@At
	@Ok("jsp")
	public Object list(@Param("..") final SignInQueryForm queryForm, @Param("..") final Pager pager) {
		queryForm.setStatus(-1);
		queryForm.setType(-1);
		return FormUtil.query(dbDao, sqlManager, queryForm, pager);
	}
	/**
	 * 分页查询功能
	 * <P>
	 * 
	 * @param queryForm  查询表单
	 * @param pager      分页对象
	 */
	@At
	@Ok("jsp")
	public Object signList(@Param("..") final SignInQueryForm queryForm, @Param("..") final Pager pager) {
		Map<String, Object> map = signInService.signList(queryForm,pager);
		map.put("signInStatusEnum",EnumUtil.enum2(SignInStatusEnum.class));
		return map;
	}
	/**
	 * 查状态为 status：1    type：0 
	 * @param queryForm
	 * @param pager
	 * @return
	 */
	@At
	@Ok("jsp")
	public Object upsignlist(@Param("..") final SignInQueryForm queryForm, @Param("..") final Pager pager) {
		queryForm.setStatus(1);
		queryForm.setType(0);
		return signInService.signList(queryForm,pager);
	}
	

}
