package com.xiaoka.game.admin.message.module;

import java.util.HashMap;
import java.util.Map;

import org.nutz.dao.SqlManager;
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
import com.xiaoka.game.admin.message.entity.MessageEntity;
import com.xiaoka.game.admin.message.form.MessageAddForm;
import com.xiaoka.game.admin.message.form.MessageDeleteModForm;
import com.xiaoka.game.admin.message.form.MessageModForm;
import com.xiaoka.game.admin.message.form.MessageQueryForm;
import com.xiaoka.game.admin.message.form.MessageStatusForm;

/**
 * 
 * 功能管理 控制类
 *
 */
@IocBean
@At("/admin/message")
public class MessageModule {
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
	 * @param queryForm
	 *            查询表单
	 * @param pager
	 *            分页对象
	 */
	@At
	@Ok("jsp")
	public Object list(@Param("..") final MessageQueryForm queryForm,@Param("..") final Pager pager) {
		return FormUtil.query(dbDao, MessageEntity.class, queryForm.cnd(),pager);
	}

	/**
	 * 跳转到'添加操作'的录入数据页面
	 */
	@At
	@GET
	@Ok("jsp")
	public void add() {
	}

	/**
	 * 添加
	 * 
	 * @param addForm
	 *            添加表单对象
	 */
	@At
	@POST
	public Object add(@Param("..") final MessageAddForm addForm) {
		addForm.setCreateTime(DateTimeUtil.nowDateTime());
		addForm.setStatus(0);
		addForm.setIsDelete(1);
		FormUtil.add(dbDao, addForm, MessageEntity.class);
		return JsonResult.success("添加成功");
	}

	/**
	 * 跳转到'修改操作'的录入数据页面,实际就是[按照主键查询单个实体]
	 */
	@At
	@GET
	@Ok("jsp")
	public Object update(@Param("id") final long id) {
		Map<String, Object> obj = new HashMap<String, Object>();
		obj.put("message", dbDao.fetch(MessageEntity.class, id));
		return obj;
	}

	/**
	 * 执行'修改操作'
	 */
	@At
	@POST
	public Object update(@Param("..") final MessageModForm modForm) {
		
		FormUtil.modify(dbDao, modForm, MessageEntity.class);
		return JsonResult.success("修改成功");
	}

	/**
	 * 执行删除操作
	 */
	@At
	@POST
	public Object delete(@Param("..") final MessageDeleteModForm delForm) {
		FormUtil.modify(dbDao, delForm, MessageEntity.class);
		return JsonResult.success("删除成功");
	}
	
	/**
	 * 执行推送消息操作
	 */
	@At
	@POST
	public Object status(@Param("..") final MessageStatusForm StatusForm) {
		FormUtil.modify(dbDao, StatusForm, MessageEntity.class);
		return JsonResult.success("修改成功");
	}

}
