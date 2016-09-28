var ioc = {
	/*
	 * 数据库连接池，单元测试使用H2database的连接池
	 */
	dataSource : {
		type : 'org.h2.jdbcx.JdbcConnectionPool',
		events : {
			depose : 'dispose'
		},
		args : [ "jdbc:h2:mem:xiaoka-test;AUTO_RECONNECT=TRUE",
				"sa", "sa" ]
	},
	/*
	 * NutDao
	 */
	nutDao : {
		type : "org.nutz.dao.impl.NutDao",
		args : [ {
			refer : "dataSource"
		} ]
	},
	/*
	 * DbDao 
	 **/
	dbDao : {
		type : "com.uxuexi.core.db.dao.impl.DbDao",
		args : [ {
			refer : "nutDao"
		}, {
			refer : "idGen"
		} 
		]
	},
	/*
	 * 单元测试使用的主键生成策略
	 */
	idGen : {
		type : "com.uxuexi.core.db.dao.impl.TestNGIdGen"
	},
	sqlManager : {
		type : "org.nutz.dao.impl.FileSqlManager",
		args : [ "websql/","sql" ]
	}
}