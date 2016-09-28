package com.we.generator.load;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.atteo.evo.inflector.English;
import org.nutz.lang.Strings;

import com.uxuexi.core.common.util.StringUtil;
import com.we.generator.util.Utils;

/**
 * 实体信息描述
 */
public class EntityDescriptor {

	/**entity类型名*/
	private static final String ENTITY_TYPE_NAME = "Entity";
	private static final String FORM_TYPE_NAME = "Form";

	private final String basePackageName;
	private final String baseUri;
	private final List<ColumnDescriptor> columns = new ArrayList<ColumnDescriptor>();

	public final String tableName;//表名称 

	private String pkType;//主键类型
	private String comment;//注释
	private String entityPackageName;//entity包名

	public EntityDescriptor(String tableName, String basePackageName, String baseUri, String entityPackageName) {
		this.tableName = tableName;

		this.basePackageName = basePackageName;
		this.entityPackageName = entityPackageName;
		if (!baseUri.endsWith("/")) {
			baseUri = baseUri + "/";
		}
		this.baseUri = baseUri;
	}

	public String getAddFormName() {
		return StringUtil.trimRight(getEntityClassName(), "Entity") + "AddForm";
	}

	public String getUpdateFormName() {
		return StringUtil.trimRight(getEntityClassName(), "Entity") + "UpdateForm";
	}

	public String getBaseUri() {
		return baseUri;
	}

	public String getPlural() {
		return English.plural(tableName);
	}

	public String getPkType() {
		return pkType;
	}

	public void setPkType(String pkType) {
		this.pkType = pkType;
	}

	public String getBasePackageName() {
		return basePackageName;
	}

	public List<ColumnDescriptor> getColumns() {
		return columns;
	}

	public void addColumn(ColumnDescriptor column) {
		columns.add(column);
	}

	public String getClassName() {
		return getEntityClassName();
	}

	public String getUriPrefix() {
		if (getTableName().contains("_")) {
			return baseUri + getTableName().replace("_", "/");
		}
		return baseUri + getTableName();
	}

	public String getViewBasePath() {
		return baseUri.replaceFirst("/", "") + getTableName();
	}

	public String getTableName() {
		return tableName;
	}

	public String getEntityPackageName() {
		return entityPackageName;
	}

	public void setEntityPackageName(String entityPackageName) {
		this.entityPackageName = entityPackageName;
	}

	public String getEntityClassName() {
		return Utils.UPPER_CAMEL(tableName) + ENTITY_TYPE_NAME;
	}

	public String getFormClassName() {
		return Utils.UPPER_CAMEL(tableName) + FORM_TYPE_NAME;
	}

	public String getEntityFullClassName() {
		return basePackageName + "." + getEntityPackageName() + "." + getEntityClassName();
	}

	public String getEntityInstanceName() {
		return Utils.LOWER_CAMEL(tableName);
	}

	public String getEntityInstancesName() {
		return getEntityInstanceName() + "s";
	}

	public void addPrimaryKeyColumn(String columnName) {
		for (ColumnDescriptor column : columns) {
			if (column.columnName.equals(columnName)) {
				column.primary = true;
				break;
			}
		}
	}

	public ColumnDescriptor getPrimaryColumn() {
		for (ColumnDescriptor column : columns) {
			if (column.primary) {
				return column;
			}
		}
		return null;
	}

	public String getPrimaryType() {
		ColumnDescriptor columnDescriptor = getPrimaryColumn();
		if (columnDescriptor == null) {
			return null;
		}

		return columnDescriptor.getSimpleJavaTypeName();
	}

	public String getTableAnnotation() {
		return "@Table";
	}

	public List<String> getImports() {
		Set<String> klasses = new LinkedHashSet<String>();

		for (ColumnDescriptor column : columns) {
			String klass = column.getJavaType();
			if (klass.startsWith("java.lang") || klass.indexOf('.') == -1) {
				continue;
			}
			klasses.add(column.getJavaType());
		}

		List<String> imports = new ArrayList<String>();
		imports.addAll(klasses);
		imports.add(null);

		imports.add(Serializable.class.getName());
		imports.add(null);

		klasses.clear();
		if (klasses.size() > 0) {
			imports.addAll(klasses);
			imports.add(null);
		}

		return imports;
	}

	public List<ColumnDescriptor> getEnumColumns() {
		List<ColumnDescriptor> result = new ArrayList<ColumnDescriptor>();
		for (ColumnDescriptor column : columns) {
			if (column.isEnum()) {
				result.add(column);
			}
		}
		return result;
	}

	//todo
	public String getQueryColumns(String op) {
		List<String> result = new ArrayList<String>();
		for (ColumnDescriptor column : columns) {
			if (op.equals(column.getQueryOperator())) {
				result.add("\"" + column.columnName + "\"");
			}
		}

		if (result.isEmpty()) {
			return null;
		}
		return "";
	}

	public List<ColumnDescriptor> getSearchableColumns() {
		List<ColumnDescriptor> result = new ArrayList<ColumnDescriptor>();

		for (ColumnDescriptor column : columns) {
			if (!Strings.isBlank(column.getQueryOperator())) {
				result.add(column);
			}
		}

		return result;
	}

	public List<ColumnDescriptor> getIndexColumns() {
		List<ColumnDescriptor> result = new ArrayList<ColumnDescriptor>();

		for (ColumnDescriptor column : columns) {
			if (!"id".equals(column.columnName)) {
				result.add(column);
			}
		}
		return result;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

}