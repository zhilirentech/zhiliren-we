package com.xiaoka.template.test;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.nutz.dao.SqlManager;
import org.nutz.dao.entity.annotation.Table;
import org.nutz.dao.impl.NutDao;
import org.nutz.resource.Scans;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import com.uxuexi.core.db.dao.impl.DbDao;


/**
 * 注意:
 * 1，AbstractTransactionalJUnit4SpringContextTests默认会回滚,
 * 2，如果要测试事务是否生效，就不要使用AbstractTransactionalJUnit4SpringContextTests完成
 * @author 朱晓川
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@TransactionConfiguration(transactionManager="transactionManager",defaultRollback=false)
@ContextConfiguration(locations={"classpath:applicationContext-test.xml"})
public class DBTest extends AbstractTransactionalJUnit4SpringContextTests{
	
	@Resource  
    private DbDao dbDao;  
	
	@Resource  
    private SqlManager sqlManager;
	
	//从Ioc容器获取nutDao对象，用于建表操作
	@Resource
	private NutDao nutDao ;
	
	private Long logId ;
	
	@Before
	public void createTableIfNotExists() {
		//扫描指定包中的所有class，创建需要的数据库表
		List<Class<?>> classes = Scans.me().scanPackage("com.xiaoka.template.test");
		for (Class<?> clazz : classes) {
			if (clazz.isAnnotationPresent(Table.class)) {
				nutDao.create(clazz, false);
			}
		}
		logger.info("create table if not exists done");
	}
	
	@Test
	public void testInsert() throws Exception{ 
		SLogEntity en = new SLogEntity() ;
		en.setOperatorName("test-txName"); 
		en = dbDao.insert(en) ; 
		logId = en.getId() ;
		System.out.println("=========================>logid:" + logId); 
		Assert.assertTrue(logId > 0 ); 
	}
	
	
}
