package com.xiaoka.game.wap.ranking.service;

import org.nutz.dao.pager.Pager;

import com.xiaoka.game.wap.ranking.form.RankSqlForm;
import com.xiaoka.game.wap.ranking.form.ScolRankSqlForm;

public interface RankService {

	/**查询全国排名列表*/
	public Object list(final RankSqlForm sqlForm,final Pager pager);
	
	/**查询全校排名列表*/
	public Object schoList(final ScolRankSqlForm sqlForm,final Pager pager);
	
}
