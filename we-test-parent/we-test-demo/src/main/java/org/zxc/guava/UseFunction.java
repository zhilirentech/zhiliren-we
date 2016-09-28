/**
 * UseFunction.java
 * org.zxc.guava
 * Copyright (c) 2016, 北京聚智未来科技有限公司版权所有.
*/

package org.zxc.guava;

import java.util.List;
import java.util.Map;
import java.util.Objects;

import com.google.common.base.Function;
import com.google.common.base.Joiner;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

/**
 * 用Function执行某些操作
 * <p>
 *
 * @author   zxc
 * @Date	 2016年5月12日 	 
 */
public class UseFunction {

	public static void main(String[] args) {
		
		String str = Joiner.on(",").join(Lists.newArrayList(1,2,3)) ; 
		System.out.println(str); 

		Map<String, Double> eurPriceMap = Maps.newHashMap();
		eurPriceMap.put("苹果", 5.2);
		eurPriceMap.put("香蕉", 1.5);
		eurPriceMap.put("西瓜", 0.8);

		/**
		 * 对map的值，应用一个规则
		 * Function<F,T>
		 * F - from的类型
		 * T - to  的类型
		 * 
		 */
		Map<String, Double> usdPriceMap = Maps.transformValues(eurPriceMap, new Function<Double, Double>() {
			double eurToUsd = 1.4888;

			/**参数类型是F，返回类型是T*/
			@Override
			public Double apply(Double from) {
				return from * eurToUsd;
			}
		});
		System.out.println(usdPriceMap.toString());

		List<String> flst = Lists.newArrayList("a", "b", "c", "d");
		List<String> tlst = Lists.transform(flst, new Function<String, String>() {
			@Override
			public String apply(String from) {
				return from + "-0";
			}
		});

		System.out.println(Objects.hashCode(flst));
		System.out.println(Objects.hashCode(tlst));
		System.out.println(flst == tlst);
		System.out.println(tlst.toString());
	}

}
