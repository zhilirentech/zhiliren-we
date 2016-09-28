/**
 * UsePrimitives.java
 * org.zxc.guava
 * Copyright (c) 2016, 北京聚智未来科技有限公司版权所有.
*/

package org.zxc.guava;

import com.google.common.primitives.Ints;

/**
 * Guava操作基本数据类型
 * <p>
 * TODO(这里描述这个类补充说明 – 可选)
 *
 * @author   朱晓川
 * @Date	 2016年5月12日 	 
 */
public class UsePrimitives {

	public static void main(String[] args) {
		/*判断集合(数组)中是否包含*/
		int[] array = { 1, 2, 3, 4, 5 };
		int[] array2 = { 6, 7, 8 };
		int a = 4;
		boolean contains = Ints.contains(array, a);
		System.out.println(contains);

		int idx = Ints.indexOf(array, a);
		int max = Ints.max(array);
		int min = Ints.min(array);
		int[] concat = Ints.concat(array, array2);

		System.out.println("idx:" + idx);
		System.out.println("max:" + max);
		System.out.println("min:" + min);

		System.out.println(Ints.asList(concat).toString());
	}

}
