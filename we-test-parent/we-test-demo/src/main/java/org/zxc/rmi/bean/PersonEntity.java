package org.zxc.rmi.bean;

import java.io.Serializable;

import lombok.Data;

@Data
public class PersonEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	private int id;
	private String name;
	private int age;

}
