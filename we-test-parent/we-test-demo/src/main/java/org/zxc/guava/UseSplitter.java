/**
 * UseJoiner.java
 * org.zxc.guava
 * Copyright (c) 2016, 北京聚智未来科技有限公司版权所有.
*/

package org.zxc.guava;

import java.util.Iterator;
import java.util.List;

import com.google.common.base.Splitter;

/**
 * TODO(这里用一句话描述这个类的作用)
 * <p>
 * TODO(这里描述这个类补充说明 – 可选)
 *
 * @author   朱晓川
 * @Date	 2016年5月12日 	 
 */
public class UseSplitter {

	public static void main(String[] args) {
		Iterator<String> iter = Splitter.on("|").split("a|b|c").iterator();
		while (iter.hasNext()) {
			System.out.println(iter.next());
		}

		List<String> list = Splitter.on("|").splitToList("a|b|c");
		for (String s : list) {
			System.out.println(s);
		}

	}

}
