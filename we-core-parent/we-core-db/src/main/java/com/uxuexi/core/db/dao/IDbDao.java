/**
 * IDbDao.java
 * com.uxuexi.core.db.dao
 * Copyright (c) 2014, 北京聚智未来科技有限公司版权所有.
*/

package com.uxuexi.core.db.dao;

import java.util.List;

import org.nutz.dao.Chain;
import org.nutz.dao.Condition;
import org.nutz.dao.entity.Record;
import org.nutz.dao.pager.Pager;
import org.nutz.dao.sql.Sql;

/**
 * 数据库接口
 *
 * @author   庄君祥
 * @author   朱晓川
 * @Date	 2013-12-6 	 
 */
public interface IDbDao {
	
	/**
	 * 更新关系表数据。比如，更新角色对应的功能:
	 * 1，前台传递角色最后应该拥有的全部功能，组装成after
	 * 2，查询出角色现在拥有的功能 before
	 * <p>
	 * 
	 * 泛型T必须是一个多对多关系Entity类型
	 * 
	 * @param before   (按照某种条件查询出的)关系表更新前的数据集合
	 * @param after    (更新后应处于的状态)关系表欲更新的数据集合
	 */
	public <T> void updateRelations(List<T> before, List<T> after) ;
	
	/**
	 * 插入对象，这里的对象支持：单个对象，数组，集合，map
	 * <p>
	 * 需要注意事项：
	 * <ul>
	 * <li>如果实体有@id并且auto=true，则会自动根据数据库自增来生成id</li>
	 * <li>如果实体有@id并且auto=false,则会自动根据主键生成策略来生成对应的主键</li>
	 * <li>如果实体有@id,并且插入之前给主键赋值。如果auto=true，主键还是根据数据库自增生成。如果auto=false，主键等于赋值的主键</li>
	 * <li>如果是Map的话，必须有<font color="red"><b>key=".table"(固定值)</b></font> value="tableName"(表名)。其他的key和value即为要插入的属性和值</li>
	 * </ul>
	 * <font color="red"><b>注意：</b></font> 
	 * <ul>
	 * <li>如果是集合，数组或者 Map，所有的对象必须类型相同，否则可能会出错</li>
	 * <li>插入数组和集合则不能含有空，否则会报空指针异常</li>
	 * </ul>
	 * @param obj 对象
	 */
	public <T> T insert(final T obj);

	/**
	 * 插入记录
	 *
	 * @param tableName 表名
	 * @param chain 名值链
	*/
	public void insert(final String tableName, final Chain chain);

	/**
	 * 插入记录
	 *
	 * @param clazz 类型
	 * @param chain 名值链
	*/
	public void insert(final Class<?> clazz, final Chain chain);

	/**
	 * 获取对象
	 * <p>
	 * 你的对象必须在某个字段声明了注解 '@Id'，否则本操作会抛出一个运行时异常
	 * @param clazz 类型
	 * @param id 主键
	 * @return 对象
	 */
	public <T> T fetch(final Class<T> clazz, final long id);

	/**
	 * 获取对象
	 * <p>
	 * 根据 WHERE 条件获取一个对象。如果有多个对象符合条件，将只获取 ResultSet 第一个记录
	 * 
	 * @param clazz 类型
	 * @param cnd 查询条件
	 * @return 对象
	 */
	public <T> T fetch(final Class<T> clazz, final Condition cnd);

	/**
	 * 获取记录
	 *
	 * @param tableName 表名
	 * @param id 主键
	 * @return 记录
	*/
	public Record fetch(final String tableName, final long id);

	/**
	 * 获取记录
	 *
	 * @param tableName 表名
	 * @param cnd 条件
	 * @return 记录
	*/
	public Record fetch(final String tableName, final Condition cnd);

	/**
	 * 获取记录
	 *
	 * @param sql sql对象
	 * @return 记录
	*/
	public Record fetch(final Sql sql);

	/**
	 * 更新对象，这里的对象支持：单个对象，数组，集合
	 * <p>
	 * 需要注意事项：
	 * <ul>
	 * 	<li>对象必须有 '@Id' 或者 '@Name' 或者 '@PK' 声明</li>
	 * 	<li>如果同时有 '@Id' 或者 '@Name' '@Id'优先级高</li>
	 * </ul>
	 *  <font color="red"><b>注意：</b></font> 
	 * <ul>
	 * <li>如果是集合，数组，所有的对象必须类型相同，否则可能会出错</li>
	 * <li>插入数组和集合则不能含有空，否则会报空指针异常</li>
	 * <li>暂时不支持map，如果是一个map,则返回0</li>
	 * </ul>
	 * @param obj 对象
	 * @param cols 要更新的属性列表。如果属性为空，则表示更新所有属性
	 * @return 更新的行数
	 */
	public int update(final Object obj, final String... cols);

	/**
	 * 更新数据
	 *
	 * @param tableName 表名
	 * @param chain 名值链
	 * @param cnd 条件
	 * @return 更新的行数
	*/
	public int update(final String tableName, final Chain chain, final Condition cnd);

	/**
	 * 更新数据
	 *
	 * @param clazz 类型
	 * @param chain 名值链
	 * @param cnd 条件
	 * @return 更新的行数
	*/
	public int update(final Class<?> clazz, final Chain chain, final Condition cnd);

	/**
	 * 自动判断如何删除一个对象。 
	 * <p>
	 * 可以根据 '@Id','@Name','@PK'来判断删除<br/>
	 * 如果没声明任何上面三个注解，则会抛出一个运行时异常
	 * 
	 * @param 对象
	 * @return 删除的行数
	 * 
	 */
	public int delete(final Object obj);

	/**
	 * 根据类型和主键删除对象
	 * <p>
	 * clazz里必须有@id，否则会抛出一个运行时异常
	 * @param clazz 类型
	 * @param id 主键
	 */
	public int delete(final Class<?> clazz, final long id);

	/**
	 * 分页查询对象列表
	 * 
	 * @param clazz 对象类型
	 * @param cnd WHERE 条件。如果为 null，将获取全部数据，顺序为数据库原生顺序
	 * @param pager 翻页信息。如果为 null，则一次全部返回
	 * @return 对象的列表
	 */
	public <T> List<T> query(final Class<T> clazz, final Condition cnd, final Pager pager);

	/**
	 * 记录查询
	 *
	 * @param tableName 表名
	 *@param cnd WHERE 条件。如果为 null，将获取全部数据，顺序为数据库原生顺序
	 * @param pager 翻页信息。如果为 null，则一次全部返回
	 * @return 记录列表
	*/
	public List<Record> query(final String tableName, final Condition cnd, final Pager pager);

	/**
	 * 查询记录列表
	 * <p>
	 * sql里如果要支持condition，那么需要sql里有一个占位'$condition'
	 * @param cnd WHERE 条件。如果为 null，将获取全部数据，顺序为数据库原生顺序
	 * @param pager 翻页信息。如果为 null，则一次全部返回
	 * @param pager 分页对象
	 * @return 记录列表
	 */
	public List<Record> query(final Sql sql, final Condition cnd, final Pager pager);

	/**
	 * 执行sql语句
	 *
	 * @param sqls sql语句
	*/
	public void execute(final Sql... sqls);
}
