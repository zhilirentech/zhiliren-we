package com.xiaoka.game.common.quartz;

import org.nutz.ioc.Ioc;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.uxuexi.core.web.util.IocUtil;
import com.xiaoka.game.wap.game.service.GameService;
import com.xiaoka.game.wap.home.service.HomeService;

public class GameJob implements Job{
	
	private static GameService gameService;
	
	static{
		Ioc ioc = IocUtil.get();
		gameService = ioc.get(GameService.class,"gameService") ;
	}
	
	@Override
	public void execute(JobExecutionContext context)
			throws JobExecutionException {
		gameService.updateGameStatus();
	}

}
