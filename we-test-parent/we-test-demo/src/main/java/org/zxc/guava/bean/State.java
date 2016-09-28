/**
 * 
 */
package org.zxc.guava.bean;

import java.util.Set;

import lombok.Data;

import com.google.common.collect.Sets;

/**
 * TODO(这里用一句话描述这个类的作用)
 * <p>
 * TODO(这里描述这个类补充说明 – 可选)
 * @author   朱晓川
 * @Date	 2016年5月13日
 */
@Data
public class State {

	private String name;

	private String code;

	private Set<City> mainCities = Sets.newHashSet();

}
