var ioc = {
	redisDs : {
		type : 'com.uxuexi.core.redis.support.impl.RedisDataSource',
		fields : {
			config : {
				type : 'com.uxuexi.core.redis.support.RedisConfig',
				args : [ '192.168.1.15', 6378, 30000 ]
			}
		}
	},
	redis : {
		type : 'com.uxuexi.core.redis.RedisDao',
		args : [ {
			refer : "redisDs"
		} ]
	}
}