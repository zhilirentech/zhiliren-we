/**
 * IRedisDao.java
 * com.uxuexi.dao.redis
 * Copyright (c) 2014, 北京聚智未来科技有限公司版权所有.
*/

package com.uxuexi.core.redis;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * redisDao通用接口
 * 
 * @author   庄君祥
 * @Date	 2013-2-5 	 
 */
public interface IRedisDao {

	/**
	 * 将字符串值 value 关联到 key
	 * <p>
	 * 如果 key 已经持有其他值， SET 就覆写旧值
	 * @param key 键
	 * @param value 值
	 * @return 总是返回 "OK" ，因为 SET 不可能失败
	*/
	public String set(final String key, final String value);

	/**
	 * 根据Map批量增加
	 *
	 * @param prefix 前缀
	 * @param values 数据
	*/
	public void set(final String prefix, final Map<String, String> values);

	/**
	 * 根据Map批量增加
	 *
	 * @param values 数据
	*/
	public void set(final Map<String, String> values);

	/**
	 * 返回 key 所关联的字符串值。<br/>
	 * 如果 key 不存在那么返回特殊值 nil 。<br/>
	 * 假如 key 储存的值不是字符串类型，返回一个错误，因为 GET 只能用于处理字符串值。
	 * @param key 键值
	 * @return 当 key 不存在时，返回 nil ，否则，返回 key 的值
	*/
	public String get(final String key);

	/**
	 * 批量获取对象
	 *
	 * @param key key列表
	 * @return 获取数据列表
	 */
	public List<String> mget(final String... key);

	/**
	 * 检查给定 key 是否存在
	 *
	 * @param key 键值
	 * @return 若 key 存在，返回 true ，否则返回 false 
	*/
	public boolean exists(final String key);

	/**
	 * 为给定 key 设置生存时间，当 key 过期时(生存时间为 0 )，它会被自动删除。
	 *
	 * @param key 键值
	 * @param seconds 过期时间，单位为秒
	 * @return 设置成功返回 1 。当 key 不存在或者不能为 key 设置生存时间时，返回 0
	*/
	public long expire(final String key, final int seconds);

	/**
	 * 为给定 key 设置生存时间，当 key 过期时(生存时间为 0 )，它会被自动删除。
	 * <p>
	 * 单位为秒
	 * @param key	键值
	 * @param unixTime 过期时间，单位为时间戳
	 * @return 设置成功返回 1 。当 key 不存在或者不能为 key 设置生存时间时，返回 0
	*/
	public long expireAt(final String key, final long unixTime);

	/**
	 * 以秒为单位，返回给定 key 的剩余生存时间(TTL, time to live)
	 *
	 * @param key 键值
	 * @return 当 key 不存在或没有设置生存时间时，返回 -1 。否则，返回 key 的剩余生存时间(以秒为单位)。
	*/
	public long ttl(final String key);

	/**
	 * 将 key 所储存的值减去减量 decrement 。<br/>
	 * 如果 key 不存在，那么 key 的值会先被初始化为 0 ，然后再执行 DECRBY 操作。<br/>
	 * 如果值包含错误的类型，或字符串类型的值不能表示为数字，那么返回一个错误。
	 *
	 * @param key 键值
	 * @param decrement 减量
	 * @return 减去 decrement 之后， key 的值
	*/
	public long decrBy(final String key, final long decrement);

	/**
	 * 将 key 所储存的值减去1 。<br/>
	 * 如果 key 不存在，那么 key 的值会先被初始化为 0 ，然后再执行 DECR 操作。<br/>
	 * 如果值包含错误的类型，或字符串类型的值不能表示为数字，那么返回一个错误。
	 *
	 * @param key 键值
	 * @return 减去 1 之后， key 的值
	*/
	public Long decr(final String key);

	/**
	 * 将 key 所储存的值加上增量 increment 。<br/>
	 * 如果 key 不存在，那么 key 的值会先被初始化为 0 ，然后再执行 INCRBY 命令。<br/>
	 * 如果值包含错误的类型，或字符串类型的值不能表示为数字，那么返回一个错误。
	 *
	 * @param key 键值
	 * @param increment 增量
	 * @return 加上 increment 之后， key 的值
	*/
	public long incrBy(final String key, final long increment);

	/**
	 * 将 key 所储存的值加上增量1 。<br/>
	 * 如果 key 不存在，那么 key 的值会先被初始化为 0 ，然后再执行 INCR 命令。<br/>
	 * 如果值包含错误的类型，或字符串类型的值不能表示为数字，那么返回一个错误。
	 *
	 * @param key 键值
	 * @return 加上 1 之后， key 的值
	*/
	public long incr(final String key);

	/**
	 * 将哈希表 key 中的域 field 的值设为 value 。<br/>
	 * 如果 key 不存在，一个新的哈希表被创建并进行 HSET 操作。<br/>
	 * 如果域 field 已经存在于哈希表中，旧值将被覆盖。
	 *
	 * @param key 键值
	 * @param field 域
	 * @param value 数值
	 * @return 如果 field 是哈希表中的一个新建域，并且值设置成功，返回 1 。<br/>
	 * 如果哈希表中域 field 已经存在且旧值已被新值覆盖，返回 0 。
	*/
	public long hset(final String key, final String field, final String value);

	/**
	 * 返回哈希表 key 中给定域 field 的值
	 *
	 * @param key 键值
	 * @param field 域
	 * @return 给定域的值。<br/>
	 * 当给定域不存在或是给定 key 不存在时，返回 nil 。
	*/
	public String hget(final String key, final String field);

	/**
	 * 批量获取哈希表 key 中给定域 field 的值
	 *
	 * @param key 键值
	 * @param fields 域
	 * @return 对应值的列表
	 */
	public List<String> hmget(final String key, final String... fields);

	/**
	 * 同时将多个 field-value (域-值)对设置到哈希表 key 中。<br/>
	 * 此命令会覆盖哈希表中已存在的域。<br/>
	 * 如果 key 不存在，一个空哈希表被创建并执行 HMSET 操作。
	 * @param key 键值
	 * @param hash 要设置的哈希表
	 * @return 如果命令执行成功，返回 OK 。<br/>
	 * 当 key 不是哈希表(hash)类型时，返回一个错误
	*/
	public String hmset(final String key, final Map<String, String> hash);

	/**
	 *为哈希表 key 中的域 field 的值加上增量 increment 。<br/>
	 *增量也可以为负数，相当于对给定域进行减法操作。<br/>
	 *如果 key 不存在，一个新的哈希表被创建并执行 HINCRBY 命令。<br/>
	 *如果域 field 不存在，那么在执行命令前，域的值被初始化为 0 。<br/>
	 *对一个储存字符串值的域 field 执行 HINCRBY 命令将造成一个错误。
	 *
	 * @param key 键值
	 * @param field 域
	 * @param value 增减量
	 * @return 哈希表 key 中域 field 的值
	*/
	public long hincrBy(final String key, final String field, final long value);

	/**
	 * 查看哈希表 key 中，给定域 field 是否存在
	 *
	 * @param key 键值
	 * @param field 域
	 * @return 如果哈希表含有给定域，返回 1 。<br/>如果哈希表不含有给定域，或 key 不存在，返回 0 
	*/
	public boolean hexists(final String key, final String field);

	/**
	 * 删除给定的一个或多个 key 。<br/>
	 * 不存在的 key 会被忽略。
	 *
	 * @param key 键值
	 * @return 被删除 key 的数量
	*/
	public long del(final String... key);

	/**
	 * 删除哈希表 key 中的一个或多个指定域，不存在的域将被忽略。
	 *
	 * @param key 键值
	 * @param fields 域数组
	 * @return 被成功移除的域的数量，不包括被忽略的域
	*/
	public long hdel(final String key, final String... fields);

	/**
	 *返回哈希表 key 中，所有的域和值。<br/>
	 *在返回值里，紧跟每个域名(field name)之后是域的值(value)。<br/>
	 *
	 * @param key 键值
	 * @return 以列表形式返回哈希表的域和域的值。若 key 不存在，返回空列表。
	*/
	public Map<String, String> hgetAll(final String key);

	/**
	 * 将一个或多个值 value 插入到列表 key 的表尾(最右边)。<br/>
	 * 如果有多个 value 值，那么各个 value 值按从左到右的顺序依次插入到表尾：比如对一个空列表 mylist 执行 RPUSH mylist a b c ，得出的结果列表为 a b c ，等同于执行命令 RPUSH mylist a 、 RPUSH mylist b 、 RPUSH mylist c 。<br/>
	 * 如果 key 不存在，一个空列表会被创建并执行 RPUSH 操作。<br/>
	 * 当 key 存在但不是列表类型时，返回一个错误。
	 *
	 * @param key 键值
	 * @param vals 数据
	 * @return 执行 RPUSH 命令后，列表的长度
	*/
	public long rpush(final String key, final String... vals);

	/**
	 * 将一个或多个值 value 插入到列表 key 的表头<br/>
	 * 如果有多个 value 值，那么各个 value 值按从左到右的顺序依次插入到表头： 比如说，对空列表 mylist 执行命令 LPUSH mylist a b c ，列表的值将是 c b a ，这等同于原子性地执行 LPUSH mylist a 、 LPUSH mylist b 和 LPUSH mylist c 三个命令。<br/>
	 * 如果 key 不存在，一个空列表会被创建并执行 LPUSH 操作。<br/>
	 * 当 key 存在但不是列表类型时，返回一个错误。
	 *
	 * @param key 键值
	 * @param vals 数据
	 * @return 执行 LPUSH 命令后，列表的长度
	*/
	public long lpush(final String key, final String... vals);

	/**
	 * 返回列表 key 的长度。<br/>
	 * 如果 key 不存在，则 key 被解释为一个空列表，返回 0 .<br/>
	 * 如果 key 不是列表类型，返回一个错误。<br/>
	 * 
	 * @param key 键值
	 * @return 列表 key 的长度
	*/
	public Long llen(final String key);

	/**
	 * 返回列表 key 中指定区间内的元素，区间以偏移量 start 和 stop 指定。<br/>
	 * 下标(index)参数 start 和 stop 都以 0 为底，也就是说，以 0 表示列表的第一个元素，以 1 表示列表的第二个元素，以此类推。<br/>
	 * 你也可以使用负数下标，以 -1 表示列表的最后一个元素， -2 表示列表的倒数第二个元素，以此类推。
	 * <p>
	 * <b>超出范围的下标:</b><br/>
	 * 超出范围的下标值不会引起错误。<br/>
	 * 如果 start 下标比列表的最大下标 end ( LLEN list 减去 1 )还要大，或者 start > stop ， LRANGE 返回一个空列表。<br/>
	 * 如果 stop 下标比 end 下标还要大，Redis将 stop 的值设置为 end 。
	 *
	 * @param key 键值
	 * @param start 开始的下标
	 * @param end 结束的下标
	 * @return 一个列表，包含指定区间内的元素
	*/
	public List<String> lrange(final String key, final long start, final long end);

	/**
	 * 返回列表 key 中，下标为 index 的元素。<br/>
	 * 下标(index)参数 start 和 stop 都以 0 为底，也就是说，以 0 表示列表的第一个元素，以 1 表示列表的第二个元素，以此类推。<br/>
	 * 你也可以使用负数下标，以 -1 表示列表的最后一个元素， -2 表示列表的倒数第二个元素，以此类推。<br/>
	 * 如果 key 不是列表类型，返回一个错误。
	 *
	 * @param key 键值
	 * @param index 下标
	 * @return 列表中下标为 index 的元素。<br/>
	 * 如果 index 参数的值不在列表的区间范围内(out of range)，返回 nil 。
	*/
	public String lindex(final String key, final long index);

	/**
	 * 将列表 key 下标为 index 的元素的值设置为 value 。<br/>
	 * 当 index 参数超出范围，或对一个空列表( key 不存在)进行 LSET 时，返回一个错误。
	 *
	 * @param key 键值
	 * @param index 下标
	 * @param value 数据
	 * @return 操作成功返回 "ok" ，否则返回错误信息
	*/
	public String lset(final String key, final long index, final String value);

	/**
	 * 移除并返回列表 key 的头元素。
	 *
	 * @param key 键值
	 * @return 列表的头元素。当 key 不存在时，返回 nil 
	*/
	public String lpop(final String key);

	/**
	 * 移除并返回列表 key 的尾元素
	 *
	 * @param key 键值
	 * @return 列表的尾元素。当 key 不存在时，返回 nil 。
	*/
	public String rpop(final String key);

	/**
	 * 将一个或多个 member 元素加入到集合 key 当中，已经存在于集合的 member 元素将被忽略。<br/>
	 * 假如 key 不存在，则创建一个只包含 member 元素作成员的集合。<br/>
	 * 当 key 不是集合类型时，返回一个错误。
	 *
	 * @param key 键值
	 * @param members 数据
	 * @return 被添加到集合中的新元素的数量，不包括被忽略的元素
	*/
	public long sadd(final String key, final String... members);

	/**
	 * 返回集合 key 中的所有成员。<br/>
	 * 不存在的 key 被视为空集合。
	 *
	 * @param key 键值
	 * @return 集合中的所有成员。
	*/
	public Set<String> smembers(final String key);

	/**
	 * 移除集合 key 中的一个或多个 member 元素，不存在的 member 元素会被忽略。<br/>
	 * 当 key 不是集合类型，返回一个错误。
	 *
	 * @param key 键值
	 * @param members 成员数值
	 * @return 被成功移除的元素的数量，不包括被忽略的元素
	*/
	public long srem(final String key, final String... members);

	/**
	 * 移除并返回集合中的一个随机元素。<br/>
	 * 如果只想获取一个随机元素，但不想该元素从集合中被移除的话，可以使用 {@link #srandmember} 命令。
	 *
	 * @param key 键值
	 * @return 被移除的随机元素。当 key 不存在或 key 是空集时，返回 nil 。
	*/
	public String spop(final String key);

	/**
	 * 返回集合 key 的基数(集合中元素的数量)。
	 *
	 * @param key 键值
	 * @return 集合的基数。当 key 不存在时，返回 0 
	*/
	public long scard(final String key);

	/**
	 * 判断 member 元素是否集合 key 的成员
	 *
	 * @param key 键值
	 * @param member 成员 
	 * @return 如果 member 元素是集合的成员，返回 true 。如果 member 元素不是集合的成员，或 key 不存在，返回 false 。
	*/
	public boolean sismember(final String key, final String member);

	/**
	 * 回集合中的一个随机元素。
	 *
	 * @param key  键值
	 * @return 随机元素
	*/
	public String srandmember(final String key);

	/**
	 * member 元素及其 score 值加入到有序集 key 当中。<br/>
	 * 如果某个 member 已经是有序集的成员，那么更新这个 member 的 score 值，并通过重新插入这个 member 元素，来保证该 member 在正确的位置上。<br/>
	 * score 值可以是整数值或双精度浮点数。<br/>
	 * 如果 key 不存在，则创建一个空的有序集并执行 ZADD 操作。<br/>
	 * 当 key 存在但不是有序集类型时，返回一个错误。
	 *
	 * @param key 键值
	 * @param score 排序值
	 * @param member 成员 
	 * @return 被成功添加的新成员的数量，不包括那些被更新的、已经存在的成员
	*/
	public long zadd(final String key, final double score, final String member);

	/**
	 * 批量添加元素到有序集中
	 *
	 * @param key 键值
	 * @param scoreMembers 权重及值的map
	 * @return 添加的对象个数
	 */
	public long zadd(final String key, final Map<Double, String> scoreMembers);

	/**
	 * 返回有序集 key 中，指定区间内的成员。<br/>
	 * 其中成员的位置按 score 值递增(从小到大)来排序。<br/>
	 * 具有相同 score 值的成员按字典序(lexicographical order )来排列。<br/>
	 * 如果你需要成员按 score 值递减(从大到小)来排列，请使用 ZREVRANGE 命令。<br/>
	 * 下标参数 start 和 stop 都以 0 为底，也就是说，以 0 表示有序集第一个成员，以 1 表示有序集第二个成员，以此类推。
	 * 你也可以使用负数下标，以 -1 表示最后一个成员， -2 表示倒数第二个成员，以此类推。
	 *
	 * @param key 键值
	 * @param start 起始下标
	 * @param end 结束下标
	 * @return 指定区间内，带有 score 值(可选)的有序集成员的列表。
	*/
	public Set<String> zrange(final String key, final long start, final long end);

	/**
	 * 移除有序集 key 中的一个或多个成员，不存在的成员将被忽略。
	 *
	 * @param key 键值
	 * @param members 成员
	 * @return 被成功移除的成员的数量，不包括被忽略的成员
	*/
	public long zrem(final String key, final String... members);

	/**
	 * 为有序集 key 的成员 member 的 score 值加上增量 increment 。<br/>
	 * 可以通过传递一个负数值 increment ，让 score 减去相应的值，比如 ZINCRBY key -5 member ，就是让 member 的 score 值减去 5 。<br/>
	 * 当 key 不存在，或 member 不是 key 的成员时， ZINCRBY key increment member 等同于 ZADD key increment member 。<br/>
	 * 当 key 不是有序集类型时，返回一个错误。<br/>
	 * score 值可以是整数值或双精度浮点数。
	 *
	 * @param key 键值
	 * @param score 优先集
	 * @param member 成员
	 * @return member 成员的新 score 值
	*/
	public double zincrby(final String key, final double score, final String member);

	/**
	 * 返回有序集 key 中成员 member 的排名。其中有序集成员按 score 值递增(从小到大)顺序排列。<br/>
	 * 排名以 0 为底，也就是说， score 值最小的成员排名为 0 。<br/>
	 * 使用 ZREVRANK 命令可以获得成员按 score 值递减(从大到小)排列的排名。
	 *
	 * @param key 键值
	 * @param member 成员 
	 * @return 如果 member 是有序集 key 的成员，返回 member 的排名。<br/>
	 * 如果 member 不是有序集 key 的成员，返回 nil 。
	*/
	public long zrank(final String key, final String member);

	/**
	 * 返回有序集 key 中成员 member 的排名。其中有序集成员按 score 值递减(从大到小)排序。<br/>
	 * 排名以 0 为底，也就是说， score 值最大的成员排名为 0 。<br/>
	 * 使用 ZRANK 命令可以获得成员按 score 值递增(从小到大)排列的排名。
	 *
	 * @param key 键值
	 * @param member 成员
	 * @return 如果 member 是有序集 key 的成员，返回 member 的排名。<br/>
	 * 如果 member 不是有序集 key 的成员，返回 nil 。
	*/
	public Long zrevrank(final String key, final String member);

	/**
	 * 返回有序集 key 中成员 member 的排名。其中有序集成员按 score 值递减(从大到小)排序。<br/>
	 * 排名以 0 为底，也就是说， score 值最大的成员排名为 0 。<br/>
	 * 使用 ZRANK 命令可以获得成员按 score 值递增(从小到大)排列的排名。
	 *
	 * @param key  键值
	 * @param start 开始下标
	 * @param end 结束下标
	 * @return 如果 member 是有序集 key 的成员，返回 member 的排名。<br/>
	 * 如果 member 不是有序集 key 的成员，返回 nil 。
	*/
	public Set<String> zrevrange(final String key, final long start, final long end);

	/**
	 * 返回有序集 key 的基数(集合无数的个数)
	 *
	 * @param key 键值
	 * @return 当 key 存在且是有序集类型时，返回有序集的基数。<br/>
	 * 当 key 不存在时，返回 0 。
	*/
	public long zcard(final String key);

	/**
	 * 返回有序集 key 中，成员 member 的 score 值。<br/>
	 * 如果 member 元素不是有序集 key 的成员，或 key 不存在，返回 nil 。
	 *
	 * @param key 键值
	 * @param member 成员 
	 * @return member 成员的 score 值，可能为null
	*/
	public Double zscore(final String key, final String member);

	/**
	 * 返回或保存给定列表、集合、有序集合 key 中经过排序的元素。<br/>
	 * 排序默认以数字作为对象，值被解释为双精度浮点数，然后进行比较。
	 *
	 * @param key 键值
	 * @return 返回该列表值的递增(从小到大)排序结果。
	*/
	public List<String> sort(final String key);

	/**
	 * 返回有序集 key 中， score 值在 min 和 max 之间(默认包括 score 值等于 min 或 max )的成员的数量。
	 *
	 * @param key 键值
	 * @param min 最小值
	 * @param max 最大值
	 * @return score 值在 min 和 max 之间的成员的数量
	*/
	public long zcount(final String key, final double min, final double max);

	/**
	 *返回有序集 key 中，所有 score 值介于 min 和 max 之间(包括等于 min 或 max )的成员。<br/>
	 *有序集成员按 score 值递增(从小到大)次序排列。
	 *
	 * @param key 键值
	 * @param min 最小值
	 * @param max 最大值
	 * @return 指定区间内，带有 score 值(可选)的有序集成员的列表。
	*/
	public Set<String> zrangeByScore(final String key, final double min, final double max);

	/**
	 *返回有序集 key 中，所有 score 值介于 min 和 max 之间(包括等于 min 或 max )的成员。<br/>
	 *有序集成员按 score 值递减(从大到小)来排列。
	 *
	 * @param key 键值
	 * @param min 最小值
	 * @param max 最大值
	 * @return 指定区间内，带有 score 值(可选)的有序集成员的列表。
	*/
	public Set<String> zrevrangeByScore(final String key, final double max, final double min);

	/**
	 *分页返回有序集 key 中，所有 score 值介于 min 和 max 之间(包括等于 min 或 max )的成员。<br/>
	 *有序集成员按 score 值递增(从小到大)次序排列。
	 *
	 * @param key 键值
	 * @param min 最小值
	 * @param max 最大值
	 * @param offset 起始位置
	 * @param count 个数
	 * @return 指定区间内，带有 score 值(可选)的有序集成员的列表。
	*/
	public Set<String> zrangeByScore(final String key, final double min, final double max, final int offset,
			final int count);

	/**
	 * 同上 ，只是按 score 值递减(从大到小)来排列。
	 *
	 * @param key 键值
	 * @param min 最小值
	 * @param max 最大值
	 * @param offset 起始位置
	 * @param count 个数
	 * @return 指定区间内，带有 score 值(可选)的有序集成员的列表。
	 */
	public Set<String> zrevrangeByScore(final String key, final double max, final double min, final int offset,
			final int count);

	/**
	 *返回有序集 key 中，所有 score 值介于 min 和 max 之间(包括等于 min 或 max )的成员。<br/>
	 *有序集成员按 score 值递增(从小到大)次序排列。
	 *
	 * @param key 键值
	 * @param min 最小的对象
	 * @param max 最大的对象
	 * @return 指定区间内，带有 score 值(可选)的有序集成员的列表。
	*/
	public Set<String> zrangeByScore(final String key, final String min, final String max);

	/**
	 *返回有序集 key 中，所有 score 值介于 min 和 max 之间(包括等于 min 或 max )的成员。<br/>
	 *有序集成员 score 值递减(从大到小)来排列。
	 *
	 * @param key 键值
	 * @param max 最大的对象
	 * @param min 最小的对象
	 * @return 指定区间内，带有 score 值(可选)的有序集成员的列表。
	*/
	public Set<String> zrevrangeByScore(final String key, final String max, final String min);

	/**
	 *分页返回有序集 key 中，所有 score 值介于 min 和 max 之间(包括等于 min 或 max )的成员。<br/>
	 *有序集成员按 score 值递增(从小到大)次序排列。
	 *
	 * @param key 键值
	 * @param min 最小值
	 * @param max 最大值
	 * @param offset 起始位置
	 * @param count 个数
	 * @return 指定区间内，带有 score 值(可选)的有序集成员的列表。
	*/
	public Set<String> zrangeByScore(final String key, final String min, final String max, final int offset,
			final int count);

	/**
	 * 同上 ，只是按 score 值递减(从大到小)来排列。
	 *
	 * @param key 键值
	 * @param min 最小值
	 * @param max 最大值
	 * @param offset 起始位置
	 * @param count 个数
	 * @return 指定区间内，带有 score 值(可选)的有序集成员的列表。
	 */
	public Set<String> zrevrangeByScore(final String key, final String max, final String min, final int offset,
			final int count);

	/**
	 * 根据排名移出对象
	 *
	 * @param key 键值
	 * @param start 起始
	 * @param end 终止
	 * @return 移除的对象个数
	 */
	public Long zremrangeByRank(final String key, final long start, final long end);

	/**
	 * 根据score移出对象
	 *
	 * @param key 键值
	 * @param start 起始
	 * @param end 终止
	 * @return 移除的对象个数
	 */
	public Long zremrangeByScore(final String key, final double start, final double end);

	/**
	 * 根据score顺序移出对象
	 *
	 * @param key 键值
	 * @param start 起始对象
	 * @param end 终止对象
	 * @return 移除的对象个数
	 */
	public Long zremrangeByScore(final String key, final String start, final String end);

	/**
	 * 缩减列表
	 *
	 * @param key 键
	 * @param start 起始位置
	 * @param end 结束位置
	 */
	public void ltrim(final String key, final long start, final long end);

	/**
	 * 返回一个集合的全部成员，该集合是所有给定集合的交集<br/>
	 * 不存在的 key 被视为空集。<br/>
	 * 当给定集合当中有一个空集时，结果也为空集(根据集合运算定律)。
	 *
	 * @param keys 键值
	 * @return 交集成员的列表
	*/
	public Set<String> sinter(final String... keys);

	/**
	 * 返回一个集合的全部成员，该集合是所有给定集合的并集。<br/>
	 * 不存在的 key 被视为空集。<br/>
	 *
	 * @param keys 键值
	 * @return 并集成员的列表
	*/
	public Set<String> sunion(final String... keys);

	/**
	 * 根据格式返回对应的键值列表
	 *
	 * @param pattern 格式
	 * @return 键值列表
	*/
	public Set<String> keys(final String pattern);

	/**
	 * 根据格式删除对应的键值列表
	 *
	 * @param pattern 格式
	 * @return 被删除的键值列表
	*/
	public Set<String> removeKeys(final String pattern);

	/**
	 * 根据参数 count 的值，移除列表中与参数 value 相等的元素。
	 * <p>
	 * count 的值可以是以下几种：
	 * <ul>
	 * <li>count > 0 : 从表头开始向表尾搜索，移除与 value 相等的元素，数量为 count 。</li>
	 * <li>count < 0 : 从表尾开始向表头搜索，移除与 value 相等的元素，数量为 count 的绝对值。</li>
	 * <li>count = 0 : 移除表中所有与 value 相等的值。</li>
	 * </ul>
	 * @param key 键
	 * @param count 数量
	 * @param value 元素值
	 * @return 被移除元素的数量。因为不存在的 key 被视作空表(empty list)，所以当 key 不存在时， LREM 命令总是返回 0 。
	*/
	public Long lrem(final String key, final int count, final String value);

	/**
	 * 从一个队列中弹出，放入另一个队列中
	 *
	 * @param key 源队列key
	 * @param dstkey 目的队列key
	 * @return 弹出的对象或者nil
	 */
	public String rpoplpush(final String key, final String dstkey);

	/**
	 * 返回一个集合的全部成员，该集合是所有给定集合的并集。<br/>
	 * 不存在的 key 被视为空集。<br/>
	 *
	 * @param keys 键值
	 * @return 并集成员的列表
	*/
	public Set<String> zunion(final String... keys);

	/**
	 * 返回一个集合的全部成员，该集合是所有给定集合（值在min,max之间）的并集。<br/>
	 * 不存在的 key 被视为空集。<br/>
	 * 
	 * @param min 最小值
	 * @param max 最大值
	 * @param keys 键值
	 * @return 并集成员的列表
	*/
	public Set<String> zunion(final double min, final double max, final String... keys);

	/**
	 * 存储一个集合的全部成员到目的键值，该集合是所有给定集合的并集。<br/>
	 * 不存在的 key 被视为空集。<br/>
	 *
	 * @param keys 键值
	 * @return 并集成员的个数
	*/
	public long zunionstore(final String dest, final String... keys);

	/**
	 * 存储一个集合的全部成员到目的键值，该集合是所有给定集合的交集<br/>
	 * 不存在的 key 被视为空集。<br/>
	 * 当给定集合当中有一个空集时，结果也为空集(根据集合运算定律)。
	 *
	 * @param keys 键值
	 * @return 交集成员的个数
	*/
	public long zinterstore(final String dest, final String... keys);

}
