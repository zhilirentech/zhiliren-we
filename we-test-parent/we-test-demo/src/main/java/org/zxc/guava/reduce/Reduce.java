/**
 * 
 */
package org.zxc.guava.reduce;

import java.util.Iterator;

/**
 * TODO(这里用一句话描述这个类的作用)
 * <p>
 * TODO(这里描述这个类补充说明 – 可选)
 * @author   朱晓川
 * @Date	 2016年5月12日
 */
public class Reduce {

	private Reduce() {
	}

	public static <F, T> T reduce(final Iterable<F> iterable, final Func<F, T> func, T origin) {
		for (Iterator<F> iterator = iterable.iterator(); iterator.hasNext();) {
			origin = func.apply(iterator.next(), origin);
		}
		return origin;
	}

}
