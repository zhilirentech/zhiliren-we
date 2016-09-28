package com.we.generator.load;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.nutz.dao.Dao;
import org.nutz.dao.Sqls;
import org.nutz.dao.impl.NutDao;
import org.nutz.dao.sql.Sql;
import org.nutz.dao.sql.SqlCallback;
import org.nutz.ioc.Ioc;
import org.nutz.log.Log;
import org.nutz.log.Logs;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

/**
 * 从mysql数据库加载实体信息
 */
public class MysqlEntityLoader implements EntityLoader {

	private static final Log log = Logs.get();

	@Override
	public Map<String, EntityDescriptor> load(Ioc ioc, String basePkg, String baseUri, String entityPkg) {

		/**1，通过数据源执行select database()得到数据库名*/
		DataSource ds = ioc.get(DataSource.class);
		Dao dao = new NutDao(ds);
		Sql sql = Sqls.create("select database()");
		sql.setCallback(new SqlCallback() {

			@Override
			public Object invoke(Connection conn, ResultSet rs, Sql sql) throws SQLException {
				if (rs.next()) {
					return rs.getString(1);
				}
				return null;
			}
		});
		dao.execute(sql);
		String database = sql.getString();

		/**2，通过SQL："select * from INFORMATION_SCHEMA.COLUMNS where TABLE_SCHEMA = '数据库名'"得到列描述信息*/
		Sql colSql = Sqls.create("select * from INFORMATION_SCHEMA.COLUMNS where TABLE_SCHEMA = '" + database + "'");
		colSql.setCallback(new SqlCallback() {

			@Override
			public Object invoke(Connection conn, ResultSet rs, Sql sql) throws SQLException {
				ResultSetMetaData meta = rs.getMetaData();
				int colCnt = meta.getColumnCount();

				List<Map<String, Object>> result = Lists.newArrayList();
				while (rs.next()) {
					Map<String, Object> record = new HashMap<String, Object>();
					for (int i = 1; i <= colCnt; i++) {
						String colName = meta.getColumnName(i);
						record.put(colName, rs.getObject(colName));
					}
					result.add(record);

				}
				return result;
			}
		});
		dao.execute(colSql);
		List<Map> columns = colSql.getList(Map.class);

		/*使用表名作为key,EntityDescriptor作为Value的map*/
		Map<String, EntityDescriptor> entities = Maps.newHashMap();

		for (Map<String, Object> columnInfo : columns) {
			//表名
			String tblName = (String) columnInfo.get("TABLE_NAME");

			//列名
			ColumnDescriptor colDesc = new ColumnDescriptor();
			colDesc.columnName = (String) columnInfo.get("COLUMN_NAME");
			log.debug("in db columnName: " + colDesc.columnName);

			if ("opAt".equals(colDesc.columnName) || "opBy".equals(colDesc.columnName)
					|| "delFlag".equals(colDesc.columnName)) {
				continue;
			}
			colDesc.setDefaultValue(columnInfo.get("COLUMN_DEFAULT"));
			colDesc.dataType = (String) columnInfo.get("DATA_TYPE");
			colDesc.nullable = "YES".equals(columnInfo.get("IS_NULLABLE"));
			colDesc.primary = "PRI".equals(columnInfo.get("COLUMN_KEY"));

			String columnType = (String) columnInfo.get("COLUMN_TYPE");
			colDesc.setColumnType(columnType);
			colDesc.setComment((String) columnInfo.get("COLUMN_COMMENT"));

			EntityDescriptor entityDesc = entities.get(tblName);
			if (entityDesc == null) {
				entityDesc = new EntityDescriptor(tblName, basePkg, baseUri, entityPkg);
				entities.put(tblName, entityDesc);
			}
			if (colDesc.primary) {
				entityDesc.setPkType(colDesc.getSimpleJavaTypeName());
			}
			entityDesc.addColumn(colDesc);
		}

		/**3,得到表信息*/
		Sql tblSql = Sqls.create("select * from INFORMATION_SCHEMA.TABLES where TABLE_SCHEMA = '" + database + "'");
		tblSql.setCallback(new SqlCallback() {
			@Override
			public Object invoke(Connection conn, ResultSet rs, Sql sql) throws SQLException {
				ResultSetMetaData meta = rs.getMetaData();
				int colCnt = meta.getColumnCount();

				List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
				while (rs.next()) {
					Map<String, Object> record = new HashMap<String, Object>();
					for (int i = 1; i <= colCnt; i++) {
						String columnName = meta.getColumnName(i);
						record.put(columnName, rs.getObject(columnName));
					}
					result.add(record);

				}
				return result;
			}
		});
		dao.execute(tblSql);

		List<Map> tableInfos = tblSql.getList(Map.class);
		for (Map<String, Object> tableInfo : tableInfos) {
			String tableName = (String) tableInfo.get("TABLE_NAME");
			String comment = (String) tableInfo.get("TABLE_COMMENT");

			EntityDescriptor table = entities.get(tableName);
			if (table != null) {
				table.setComment(comment);
			}
		}
		return entities;
	}
}
