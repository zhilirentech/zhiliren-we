/**
 * UseJoiner.java
 * org.zxc.guava
 * Copyright (c) 2016, 北京聚智未来科技有限公司版权所有.
*/

package org.zxc.guava;

import java.util.Map;

import com.google.common.base.Joiner;
import com.google.common.base.Joiner.MapJoiner;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

/**
 * TODO(这里用一句话描述这个类的作用)
 * <p>
 * TODO(这里描述这个类补充说明 – 可选)
 *
 * @author   朱晓川
 * @Date	 2016年5月12日 	 
 */
public class UseJoiner {

	public static void main(String[] args) {
		String str = Joiner.on("|").skipNulls().join("a", "b", "c", "d");
		String str1 = Joiner.on("|").useForNull("NULL").join("a", "b", "");

		System.out.println(str);
		System.out.println(str1);

		Map<String, String> map = Maps.newHashMap();
		map.put("a", "b");
		map.put("c", "d");
		MapJoiner jn = Joiner.on("#").withKeyValueSeparator("=");
		System.out.println(jn.join(map));

		ImmutableList<String> of = ImmutableList.of("a", "b", "c", "d");

		String tmpValue = "a_b_c_1_2_3";
		String[] valArr = tmpValue.split("_");

		// 求字符串数组的子串，并最后拼接起来
		String tmpVal = "";
		for (int i = 1; i < valArr.length; i++) {
			tmpVal = tmpVal.equalsIgnoreCase("") ? valArr[i] : tmpVal + "_" + valArr[i];
		}
		System.out.println(tmpVal);
		System.out.println("———————");

		// 上面这么一段与下面这句等价
		System.out.println(Joiner.on("_").join(Lists.newArrayList(valArr).subList(1, valArr.length)));

	}

}
