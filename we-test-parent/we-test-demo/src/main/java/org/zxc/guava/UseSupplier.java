/**
 * 
 */
package org.zxc.guava;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.zxc.guava.bean.Person;

import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;

/**
 * 使用Supplier接口，实现单例，总是new，或者延迟初始化等创建对象的方式
 * <p>
 * 我们经常会遇到一些需要延迟计算的情形，比如某些运算非常消耗资源，如果提前算出来却没有用到，会得不偿失。在计算机科学中，
 * 有个专门的术语形容它：惰性求值。惰性求值是一种求值策略，也就是把求值延迟到真正需要的时候。
 * 在Java里，我们有一个专门的设计模式几乎就是为了处理这种情形而生的：Proxy。不过，现在我们有了新的选择：Supplier。
 * 
 * @author   朱晓川
 * @Date	 2016年5月13日
 */
public class UseSupplier {

	public static void main(String[] args) {

	}

	@Test
	public void singleTonTest() {
		Person p1 = SingleTonHolder.getPerson();
		Person p2 = SingleTonHolder.getPerson();
		Assert.assertTrue(p1 == p2);
	}

	public static void another() {

		Supplier<Person> supplier = new Supplier<Person>() {

			@Override
			public Person get() {
				System.out.println("***");
				Person p = new Person();
				p.setAge(1);
				p.setName("a");
				return p;
			}

		};

		supplier = Suppliers.memoize(supplier); //这里如果不赋值的话，不会缓存person对象

		Person p = supplier.get();
		Person p1 = supplier.get();

		Person p2 = new Person();
		p2.setAge(1);
		p2.setName("a");

		System.out.println(p == p1);
		System.out.println(p == p2);

	}

}
