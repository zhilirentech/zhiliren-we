package com.xiaoka.game.wap.InstalMessge.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.nutz.dao.Cnd;
import org.nutz.dao.Sqls;
import org.nutz.dao.entity.Record;
import org.nutz.dao.pager.Pager;
import org.nutz.dao.sql.Sql;
import org.nutz.ioc.loader.annotation.IocBean;

import com.uxuexi.core.common.util.DateTimeUtil;
import com.uxuexi.core.db.util.DbSqlUtil;
import com.uxuexi.core.web.util.FormUtil;
import com.xiaoka.game.common.service.BaseService;
import com.xiaoka.game.wap.InstalMessge.entity.SiteMessageReadEntity;
import com.xiaoka.game.wap.InstalMessge.entity.insatllMessageEntity;
import com.xiaoka.game.wap.InstalMessge.form.InstalMessgeSqlForm;
import com.xiaoka.game.wap.InstalMessge.form.SiteMessageReadAddForm;
import com.xiaoka.game.wap.InstalMessge.service.InstalMessgeService;

@IocBean(name = "instalMessgeService")
public class InstalMessgeServiceImpl extends BaseService implements InstalMessgeService{
	/**
	 * 查找用户的消息
	 * @param userId 用户Id
	 * @return Map<String, String>
	 */
	@Override
	public Object mynews(InstalMessgeSqlForm sqlForm,final HttpSession session,final Pager pager) {
		String openId = (String)session.getAttribute("openId");
		sqlForm.setOpenId(openId);
		Sql sql = Sqls.create(sqlManager.get("instal_messge_list"));
		sql.params().set("openId", openId);
		List<Record> list = dbDao.query(sql, Cnd.limit(), null);
		return list;
	}
	
	@Override
	public Object mynewsdetail(SiteMessageReadAddForm addForm,HttpSession session){ 
		String openId = (String)session.getAttribute("openId");
		int messageId = addForm.getMessageId();
		Map<String, Object> obj = new HashMap<String,Object>();
		obj.put("sitMessage", dbDao.fetch(insatllMessageEntity.class, Cnd.where("id", "=", messageId))); //内容，标题
		Sql readSql = Sqls.create(sqlManager.get("messageReadArealy")) ;
		readSql.params().set("openId", openId) ;
		readSql.params().set("messageId", messageId) ;
		List<SiteMessageReadEntity> list = DbSqlUtil.query(dbDao, SiteMessageReadEntity.class, readSql, null);
		if(list.isEmpty()){
			AddSiteMessageRead(openId,messageId);
		}
		return obj;
	}
	
	public void AddSiteMessageRead(String openId,int messageId){
		SiteMessageReadEntity en = new SiteMessageReadEntity() ;
		en.setOpenId(openId);
		en.setMessageId(messageId);
		en.setReadTime(DateTimeUtil.nowDateTime());
		dbDao.insert(en);
	}
}
