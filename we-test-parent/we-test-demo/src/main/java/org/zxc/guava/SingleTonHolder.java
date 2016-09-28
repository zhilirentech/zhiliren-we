/**
 * 
 */
package org.zxc.guava;

import org.zxc.guava.bean.Person;

import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;

/**
 * TODO(这里用一句话描述这个类的作用)
 * <p>
 * TODO(这里描述这个类补充说明 – 可选)
 * @author   朱晓川
 * @Date	 2016年5月13日
 */
public class SingleTonHolder {
	/**
	 * 还可以设置过期时间
	 */
	private static Supplier<Person> supplier = Suppliers.memoize(new Supplier<Person>() {

		@Override
		public Person get() {
			Person p = new Person();
			p.setAge(1);
			p.setName("a");
			return p;
		}

	});

	private SingleTonHolder() {
	}

	public static Person getPerson() {
		return supplier.get();
	}

}
