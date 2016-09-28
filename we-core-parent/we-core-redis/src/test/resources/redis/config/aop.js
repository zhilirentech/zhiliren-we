var ioc = {
	$aop : {
		type : 'org.nutz.ioc.aop.config.impl.ComboAopConfigration',
		fields : {
			aopConfigrations : [
					{
						type : 'org.nutz.ioc.aop.config.impl.JsonAopConfigration',
						fields : {
							itemList : [
							        [ 'com.uxuexi.core.redis.RedisDao', '.+', 'ioc:redisAop' ],
							        [ 'com.uxuexi.core.redis.ShardRedisDao', '.+', 'ioc:redisAop' ] ]
						}
					},
					{
						type : 'org.nutz.ioc.aop.config.impl.AnnotationAopConfigration'
					} ]
		}
	},
	redisAop : {
		type : 'com.uxuexi.core.redis.RedisInterceptor'
	}
}