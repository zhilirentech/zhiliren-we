package com.uxuexi.core.db.transaction;

import java.sql.Connection;

import javax.sql.DataSource;

import org.nutz.dao.ConnCallback;
import org.nutz.dao.impl.DaoRunner;
import org.springframework.jdbc.datasource.DataSourceUtils;

/**
 * 使用spring DataSourceTransactionManager 管理nutDao的事务
 * 在初始化nutDao的时候，调用setRunner(DaoRunner runner)
 * 注入此类的一个实例即可：
 * 
 * <bean id="springDaoRunner" class="com.we.dao.transcation.SpringDaoRunner"></bean>
 *	<bean id="nutDao" class="org.nutz.dao.impl.NutDao">
 *		<property name="runner" ref="springDaoRunner"/>
 *		<constructor-arg>
 *			<ref bean="dataSource"/>
 *		</constructor-arg>
 *	</bean>
 * @author 朱晓川
 */
public class SpringDaoRunner implements DaoRunner {

	@Override
	public void run(DataSource ds, ConnCallback callback) { 
		/**
		 * 如果直接使用原本的NutDao，那么会自带处理事务的逻辑，可参看
		 * NutDaoRunner的实现
		 * 
		 * 如果spring的Ioc容器直接引用nutdao，可能导致管理事务失败
		 * (nutz的逻辑+spring的逻辑，冲突了)
		 * 
		 * 因此为NutDao提供一个DaoRunner实现，去除事务处理的部分,
		 * 调用SetRunner注入即可
		 */
		Connection conn = DataSourceUtils.getConnection(ds);   
        try {  
            callback.invoke(conn);  
        }
        catch (Exception e) {  
            if (e instanceof RuntimeException)  
                throw (RuntimeException) e;  
            else  
                throw new RuntimeException(e);  
        } finally {  
            DataSourceUtils.releaseConnection(conn, ds);
        }
	}

}
