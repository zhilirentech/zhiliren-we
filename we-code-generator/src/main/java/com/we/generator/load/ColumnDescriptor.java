package com.we.generator.load;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.nutz.lang.Strings;

import com.we.generator.util.Utils;

/**
 * 属性（数据库列）基本信息描述。
 * <p>
 * 定义了数据库列类型和Java基本数据类型之间的映射
 * 
 */
public class ColumnDescriptor {
	//类型映射信息
	private static Map<String, Class<?>> typeMapping = new HashMap<String, Class<?>>();

	static {
		typeMapping.put("varchar", String.class);
		typeMapping.put("enum", String.class);
		typeMapping.put("bigint", Long.class);
		typeMapping.put("long", Long.class);
		typeMapping.put("integer", Integer.class);
		typeMapping.put("float", Float.class);
		typeMapping.put("double", Double.class);
		typeMapping.put("int", Integer.class);

		/**
		 * TODO 日期类型暂时使用Date完成,等有时间统一改成long类型处理，数据库保存时间戳 
		 */
		typeMapping.put("timestamp", Date.class);
		typeMapping.put("datetime", Date.class);
		typeMapping.put("boolean", boolean.class);

		/**
		 * 长度是一位的tinyint可以用boolean，但是指定了其他长度则需要用Integer
		 */
		typeMapping.put("tinyint", Integer.class);
		typeMapping.put("bool", boolean.class);
		typeMapping.put("decimal", BigDecimal.class);
	}

	private static Pattern COLUMN_TYPE_PATTERN = Pattern.compile("^(\\w+)(?:\\((\\d+)\\))?");
	private static Pattern ENUM_PATTERN = Pattern.compile("enum\\((.+)\\)");

	protected String columnName;
	protected boolean primary;
	protected String dataType;

	protected String columnType;
	protected int size;

	protected boolean nullable;
	private Object defaultValue;
	private String comment;
	private String fieldName;

	private List<String> enumValues = new ArrayList<String>();

	private String queryOperator;

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
		if (comment == null) {
			return;
		}
		extractSearchable(comment);
	}

	public String getQueryOperator() {
		return queryOperator;
	}

	private void extractSearchable(String comment) {
		// searchable: eq
		Pattern queryPattern = Pattern.compile("searchable:\\s*(\\w+)");
		Matcher m = queryPattern.matcher(comment);
		if (m.find()) {
			queryOperator = m.group(1);
		}
	}

	public String getColumnName() {
		return columnName;
	}

	public String getFieldName() {
		if (Strings.isBlank(fieldName)) {
			fieldName = Utils.LOWER_CAMEL(columnName);
		}
		return fieldName;
	}

	public List<String> getEnumValues() {
		return enumValues;
	}

	public void setColumnType(String columnType) {
		Matcher m = ENUM_PATTERN.matcher(columnType);
		if (m.find()) {
			this.columnType = "enum";

			String s = m.group(1);
			for (String v : s.split(",")) {
				v = v.trim().replaceAll("'", "");
				enumValues.add(v);
			}
			return;
		}

		m = COLUMN_TYPE_PATTERN.matcher(columnType);
		if (m.find()) {
			if (m.group(2) != null) {
				this.size = Integer.parseInt(m.group(2));
			}
			this.columnType = m.group(1);
		} else {
			throw new IllegalArgumentException();
		}
	}

	public String getJavaType() {
		if ("tinyint".equalsIgnoreCase(dataType) && size == 1) {
			return boolean.class.getName();
		}
		if ("enum".equalsIgnoreCase(dataType)) {
			return getUpperJavaFieldName();
		}
		Class<?> type = typeMapping.get(dataType);
		if (type != null) {
			return type.getName();
		}
		return String.class.getName();
	}

	public String getSimpleJavaTypeName() {
		return getJavaType().replaceFirst("^.*\\.", "");
	}

	public boolean isEnum() {
		return "enum".equalsIgnoreCase(dataType);
	}

	public boolean isBoolean() {
		return boolean.class.getName().equals(getJavaType());
	}

	public boolean isTimestamp() {
		return "timestamp".equalsIgnoreCase(dataType);
	}

	public String getUpperJavaFieldName() {
		return Utils.upperFirst(fieldName);
	}

	//getter
	public String getGetterMethodName() {
		if (isBoolean()) {
			return "is" + getUpperJavaFieldName();
		}
		return "get" + getUpperJavaFieldName();
	}

	//setter
	public String getSetterMethodName() {
		return "set" + getUpperJavaFieldName();
	}

	/**列注解*/
	public String getColumnAnnotation() {
		String ann = "@Column";
		if (primary) {
			return "@Id(auto = true)";
			//			return "@Name\r\n	@Prev(els = {@EL(\"uuid()\")})";
		}
		return ann + "\r\n    @Comment(\"" + this.comment + "\")";
	}

	public void setDefaultValue(Object v) {
		this.defaultValue = v;
	}

	public Object getDefaultValue() {
		return defaultValue;
	}

	public String getDefaultValueCode() {
		if (isEnum()) {
			return getSimpleJavaTypeName() + "." + defaultValue;
		}
		if (isBoolean()) {
			if ("1".equals(defaultValue.toString())) {
				return "true";
			} else {
				return "false";
			}
		}
		if (isTimestamp()) {
			if (("0000-00-00 00:00:00".equals(defaultValue) || "CURRENT_TIMESTAMP".equals(defaultValue))) {
				return "DateTimeUtil.nowDate()";
			}
		}
		if (defaultValue != null && Long.class.getName().equals(getJavaType())) {
			return defaultValue + "L";
		}
		if (defaultValue != null && BigDecimal.class.getName().equals(getJavaType())) {
			return "new BigDecimal(\"" + defaultValue.toString() + "\")";
		}

		//数据库varchar默认值为空字符串
		if (defaultValue != null && String.class.getName().equals(getJavaType())) {
			return "\"" + getDefaultValue().toString() + "\"";
		}

		return getDefaultValue().toString();
	}
}