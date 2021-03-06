package com.xiaoka.game;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.servlet.ServletContext;

import org.nutz.dao.entity.annotation.Table;
import org.nutz.dao.impl.NutDao;
import org.nutz.ioc.Ioc;
import org.nutz.lang.Tasks;
import org.nutz.mvc.NutConfig;
import org.nutz.mvc.Setup;
import org.nutz.resource.Scans;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.uxuexi.core.common.enums.IEnum;
import com.uxuexi.core.common.util.MapUtil;
import com.uxuexi.core.web.config.KvConfig;
import com.uxuexi.core.web.util.FtlUtil;
import com.uxuexi.core.web.util.InitUtil;
import com.uxuexi.core.web.util.IocUtil;
import com.uxuexi.core.web.util.QuartzUtil;
import com.xiaoka.game.wap.game.service.impl.GameServiceImpl;
import com.xiaoka.game.wap.home.service.impl.HomeServiceImpl;

import freemarker.template.Configuration;

/**
 * 启动时设置
 * 
 * @author   朱晓川
 */
public class WeSetup implements Setup {

	static enum GameConfigKey implements IEnum {
		STATIC_PATH, FILE_SERVER, VERSION, HOST, LOGIN_PAGE, avatar_l, manage_list, h5_index_carousel, h5_index_course, h5_course_list, h5_course_detail, h5_user_avatar;

		@Override
		public String key() {
			return this.name().toLowerCase();
		}

		@Override
		public String value() {
			return this.name().toLowerCase();
		}
	}

	private static final String GLOBAL_CONFIG_KEY = "we";

	private static final Logger logger = LoggerFactory.getLogger(WeSetup.class);

	/**
	 * freemarker模板文件加载的相对路径
	 */
	private static final String FTL_RALATIVE_PATH = "/";

	/**
	 * freemarker属性配置文件的路径
	 */
	private static final String FTL_CONFIGURATION_PATH = "webconfig/ftl.properties";

	@Override
	public void init(final NutConfig config) {
		Ioc ioc = config.getIoc();
		//初始全局变量：比如通用的域名
		KvConfig kvConfig = ioc.get(KvConfig.class, "kvConfig");
		initWebGlobalConfig(kvConfig, config.getServletContext());

		//初始freemarket配置
		Configuration ftlConf = ioc.get(Configuration.class);
		FtlUtil.initConfiguration(ftlConf, config.getServletContext(), FTL_RALATIVE_PATH, FTL_CONFIGURATION_PATH);
		InitUtil.initFtlGlobalConfig(kvConfig, ftlConf);

		//初始化缓存数据
		initCacheData(ioc);

		//启动的时候注册观察者
		registerObserver(ioc);
		createTableIfNotExists(config);
		IocUtil.setIoc(ioc); 

		//启动定时任务
		//startTasks(ioc) ;
		QuartzUtil.init();
	}

	private void initWebGlobalConfig(final KvConfig config, final ServletContext sc) {
		if (config == null || sc == null) {
			return;
		}
		sc.setAttribute(GLOBAL_CONFIG_KEY, getValues(config));

		//静态文件访问基地址(服务器IP)
		sc.setAttribute(GameConfigKey.STATIC_PATH.key(), config.getValue(GameConfigKey.STATIC_PATH));

		//系统版本
		sc.setAttribute(GameConfigKey.VERSION.key(), config.getValue(GameConfigKey.VERSION));

		//当前服务器地址
		sc.setAttribute(GameConfigKey.HOST.key(), config.getValue(GameConfigKey.HOST));

		/**图片尺寸*/
		sc.setAttribute(GameConfigKey.avatar_l.key(), config.getValue(GameConfigKey.avatar_l));
		sc.setAttribute(GameConfigKey.manage_list.key(), config.getValue(GameConfigKey.manage_list));

		sc.setAttribute(GameConfigKey.h5_index_carousel.key(), config.getValue(GameConfigKey.h5_index_carousel));
		sc.setAttribute(GameConfigKey.h5_index_course.key(), config.getValue(GameConfigKey.h5_index_course));
		sc.setAttribute(GameConfigKey.h5_course_list.key(), config.getValue(GameConfigKey.h5_course_list));
		sc.setAttribute(GameConfigKey.h5_course_detail.key(), config.getValue(GameConfigKey.h5_course_detail));
		sc.setAttribute(GameConfigKey.h5_user_avatar.key(), config.getValue(GameConfigKey.h5_user_avatar));

		logger.info(GameConfigKey.STATIC_PATH.key() + ":" + config.getValue(GameConfigKey.STATIC_PATH));
		logger.info(GameConfigKey.VERSION.key() + ":" + config.getValue(GameConfigKey.VERSION));
		logger.info(GameConfigKey.HOST.key() + ":" + config.getValue(GameConfigKey.HOST));

		logger.info("图片尺寸配置:");
		logger.info(GameConfigKey.avatar_l.key() + ":" + config.getValue(GameConfigKey.avatar_l));
		logger.info(GameConfigKey.manage_list.key() + ":" + config.getValue(GameConfigKey.manage_list));

		logger.info(GameConfigKey.h5_index_carousel.key() + ":" + config.getValue(GameConfigKey.h5_index_carousel));
		logger.info(GameConfigKey.h5_index_course.key() + ":" + config.getValue(GameConfigKey.h5_index_course));
		logger.info(GameConfigKey.h5_course_list.key() + ":" + config.getValue(GameConfigKey.h5_course_list));
		logger.info(GameConfigKey.h5_course_detail.key() + ":" + config.getValue(GameConfigKey.h5_course_detail));
		logger.info(GameConfigKey.h5_user_avatar.key() + ":" + config.getValue(GameConfigKey.h5_user_avatar));
	}

	private void registerObserver(Ioc ioc) {

	}

	private void initCacheData(Ioc ioc) {

	}

	/**
	 * 创建项目需要的全部数据库表，如果表已经存在则不再创建
	 * @param config 
	 */
	private void createTableIfNotExists(final NutConfig config) {
		//从Ioc容器获取nutDao对象，用于建表操作
		Ioc ioc = config.getIoc();
		NutDao nutDao = ioc.get(NutDao.class, "nutDao");

		//扫描指定包中的所有class，创建需要的数据库表
		List<Class<?>> classes = Scans.me().scanPackage("com.xiaoka");
		for (Class<?> clazz : classes) {
			if (clazz.isAnnotationPresent(Table.class)) {
				nutDao.create(clazz, false);
			}
		}
		logger.info("create table if not exists done");
	}

	@Override
	public void destroy(final NutConfig config) {
	}

	private Map<String, Map<String, String>> getValues(final KvConfig config) {
		return MapUtil.map("vr", config.getValues());
	}

	private void startTasks(Ioc ioc) {
		taskOfPlatDivided(ioc);
		taskOfGameStatus(ioc);
	}

	private void taskOfPlatDivided(Ioc ioc) {
		final HomeServiceImpl homeService = ioc.get(HomeServiceImpl.class, "homeService");
		//定时每天某个时间执行
		Date now = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String startTime = sdf.format(now) + "17:00:00";
		try {
			Tasks.scheduleAtFixedRate(new Runnable() {
				public void run() {
					logger.info("分成定时任务启动----------");
					homeService.divided();
				}
			}, startTime, 1, TimeUnit.DAYS);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}

	private void taskOfGameStatus(Ioc ioc) {
		final GameServiceImpl gameService = ioc.get(GameServiceImpl.class, "gameService");
		//定时每天某个时间执行
		Date now = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String startTime = sdf.format(now) + " 00:00:00";
		try {
			Tasks.scheduleAtFixedRate(new Runnable() {
				public void run() {
					logger.info("游戏过时定时任务启动----------");
					gameService.updateGameStatus();
				}
			}, startTime, 1, TimeUnit.MINUTES);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}
}
