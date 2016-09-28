/**
 * 
 */
package org.zxc.guava.bean;

import lombok.Data;

/**
 * TODO(这里用一句话描述这个类的作用)
 * <p>
 * TODO(这里描述这个类补充说明 – 可选)
 * @author   朱晓川
 * @Date	 2016年5月12日
 */
@Data
public class Person {

	private String name;

	private int age;

	public Person(String name, int age) {
		this.name = name;
		this.age = age;
	}

	public Person() {
	}

	@Override
	public String toString() {
		return name;
	}

}
