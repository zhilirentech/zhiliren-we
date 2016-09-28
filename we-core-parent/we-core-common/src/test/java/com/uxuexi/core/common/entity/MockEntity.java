package com.uxuexi.core.common.entity;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import lombok.Data;
import lombok.EqualsAndHashCode;

import org.nutz.dao.entity.annotation.Column;

@Data
@EqualsAndHashCode(callSuper = true)
public class MockEntity extends MockFatherEntity {
	@Column
	private String title;
	private String str;
	private int age;
	private Date birthday;
	private Timestamp registerTime;
	private String intro;
	private double score;
	private List<String> list;
	private Map<String, String> map;
	private MockEntity entity;
}
