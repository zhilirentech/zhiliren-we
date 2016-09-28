/**
 * UseJoiner.java
 * org.zxc.guava
 * Copyright (c) 2016, 北京聚智未来科技有限公司版权所有.
*/

package org.zxc.guava.collection;

import java.util.List;

import org.zxc.guava.bean.Person;

import com.google.common.collect.Lists;

/**
 * TODO(这里用一句话描述这个类的作用)
 * <p>
 * TODO(这里描述这个类补充说明 – 可选)
 *
 * @author   朱晓川
 * @Date	 2016年5月13日 	 
 */
public class UseLists {

	public static void main(String[] args) {

		Person p1 = new Person();
		p1.setName("p1");

		Person p2 = new Person();
		p2.setName("p2");

		Person p3 = new Person();
		p3.setName("p3");

		Person p4 = new Person();
		p4.setName("p4");

		List<Person> personList = Lists.newArrayList(p1, p2, p3, p4);
		List<List<Person>> subList1 = Lists.partition(personList, 1);
		System.out.println(subList1);

		List<List<Person>> subList2 = Lists.partition(personList, 2);
		System.out.println(subList2);

		List<List<Person>> subList3 = Lists.partition(personList, 3);
		System.out.println(subList3);

		List<List<Person>> subList5 = Lists.partition(personList, 5);
		System.out.println(subList5);

	}

}
