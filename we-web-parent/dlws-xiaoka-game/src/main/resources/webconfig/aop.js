var ioc = {	$aop : {		type : 'org.nutz.ioc.aop.config.impl.ComboAopConfigration',		fields : {			aopConfigrations : [				{					type : 'org.nutz.ioc.aop.config.impl.JsonAopConfigration',					fields : {						itemList : [							[ 'com.uxuexi.core.redis.RedisDao', '.+', 'ioc:txRedis' ],							[ 'com.uxuexi.core.redis.ShardRedisDao', '.+', 'ioc:txRedis' ]						]					}				},				{					type : 'org.nutz.ioc.aop.config.impl.AnnotationAopConfigration'				} ]		}	},	/*TRANSACTION_READ_COMMITTED*/	readCommittedTxDb : {		type : 'org.nutz.aop.interceptor.TransactionInterceptor',		args : [ 2 ]	},		/*TRANSACTION_SERIALIZABLE?序列化会导致问题...*/	txDb : {		type : 'org.nutz.aop.interceptor.TransactionInterceptor',		args : [ 2 ]	},	txRedis : {		type : 'com.uxuexi.core.redis.RedisInterceptor'	}}