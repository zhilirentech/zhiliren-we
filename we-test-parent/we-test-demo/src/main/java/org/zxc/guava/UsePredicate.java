/**
 * UseFunction.java
 * org.zxc.guava
 * Copyright (c) 2016, 北京聚智未来科技有限公司版权所有.
*/

package org.zxc.guava;

import java.util.List;

import org.zxc.guava.bean.Person;
import org.zxc.guava.reduce.Func;
import org.zxc.guava.reduce.Reduce;

import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.common.collect.FluentIterable;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

/**
 * 用Predicate作为某些操作的条件判断
 * <p>
 * Iterables.filter
 * Iterables.find
 * 
 * @author   zxc
 * @Date	 2016年5月12日 	 
 */
public class UsePredicate {

	private static Predicate<Person> ageBiggerThan(final int age) {
		return new Predicate<Person>() {
			@Override
			public boolean apply(Person person) {
				return person.getAge() >= age;
			}
		};
	}

	private static Predicate<Person> nameContains(final String str) {
		return new Predicate<Person>() {
			@Override
			public boolean apply(Person person) {
				return person.getName().contains(str);
			}
		};
	}

	public static void main(String[] args) {
		List<Person> people = Lists.newArrayList(new Person("bowen", 27), new Person("bob", 20),
				new Person("Katy", 18), new Person("Logon", 24));
		/*1*/
		List<Person> oldPeople = Lists.newArrayList(Iterables.filter(people, new Predicate<Person>() {

			@Override
			public boolean apply(Person person) {
				return person.getAge() >= 20;
			}

		}));

		/*2*/
		List<Person> namedPeople = Lists.newArrayList(Iterables.filter(people, new Predicate<Person>() {

			@Override
			public boolean apply(Person person) {
				return person.getName().contains("b");
			}

		}));

		/*与，或操作*/
		List<Person> andLst = Lists.newArrayList(Iterables.filter(people,
				Predicates.and(ageBiggerThan(20), nameContains("b"))));

		List<Person> orLst = Lists.newArrayList(Iterables.filter(people,
				Predicates.or(ageBiggerThan(20), nameContains("b"))));

		System.out.println("oldPeople  :" + oldPeople.toString());
		System.out.println("namedPeople:" + namedPeople.toString());
		System.out.println("andLst     :" + andLst.toString());
		System.out.println("orLst      :" + orLst.toString());

		/**获取最大年龄*/
		Integer ages = Reduce.reduce(people, new Func<Person, Integer>() {

			@Override
			public Integer apply(Person person, Integer origin) {
				return person.getAge() > origin ? person.getAge() : origin;
			}

		}, 0);
		System.out.println(ages);

		Integer sum = Reduce.reduce(people, new Func<Person, Integer>() {

			@Override
			public Integer apply(Person person, Integer origin) {
				return origin + person.getAge();
			}
		}, 0);

		System.out.println(sum);

		/**
		 * 多重过滤，转换
		 * 得到年龄大于20的人的名字
		 */
		List<String> lst = FluentIterable.from(people).filter(ageBiggerThan(20))
				.transform(new Function<Person, String>() {
					@Override
					public String apply(Person person) {
						return person.getName();
					}
				}).toList();
		System.out.println(lst.toString());

	}

}
