/**
 * QuartzUtil.java
 * cn.vko.web.util
 * Copyright (c) 2013, 北京微课创景教育科技有限公司版权所有.
*/

package com.uxuexi.core.web.util;

import java.util.List;

import org.quartz.JobExecutionContext;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.uxuexi.core.common.util.ExceptionUtil;

/**
 * 定时任务工具类
 *
 * @author   庄君祥
 * @Date	 2013-5-27 	 
 * @version  5.0.0
 */
public class QuartzUtil {
	private static final Logger logger = LoggerFactory.getLogger(QuartzUtil.class);

	private static SchedulerFactory sf = new StdSchedulerFactory();
	private static Scheduler sched;

	public static void init() {
		logger.info("init() - 开始启动定时任务");
		try {
			sched = sf.getScheduler();
			sched.start();
		} catch (SchedulerException e) {
			throw ExceptionUtil.pEx("定时任务启动失败！", e);
		}
		logger.info("init() - 启动定时任务结束，任务开始运行");
	}

	public static List<JobExecutionContext> getCurrentlyExecutingJobs() {
		try {
			return sched.getCurrentlyExecutingJobs();
		} catch (SchedulerException e) {
			throw ExceptionUtil.pEx("定时任执行情况获取失败！", e);
		}
	}
}
