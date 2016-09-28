/**
 * 
 */
package org.zxc.guava.reduce;

/**
 * TODO(这里用一句话描述这个类的作用)
 * <p>
 * TODO(这里描述这个类补充说明 – 可选)
 * @author   朱晓川
 * @Date	 2016年5月12日
 */
public interface Func<F, T> {

	T apply(F currentElement, T origin);

}
