package com.xiaoka.game.common.quartz;

import org.nutz.ioc.Ioc;
import org.nutz.ioc.loader.annotation.IocBean;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.uxuexi.core.web.util.IocUtil;
import com.xiaoka.game.wap.home.service.HomeService;

public class DevidedJob implements Job{
	
	private static HomeService homeService;
	
	static{
		Ioc ioc = IocUtil.get();
		homeService = ioc.get(HomeService.class,"homeService") ;
	}
	
	@Override
	public void execute(JobExecutionContext context)
			throws JobExecutionException {
		homeService.divided();
	}

}
