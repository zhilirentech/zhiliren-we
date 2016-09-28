/**
 * 
 */
package org.zxc.guava.collection;

import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.TimeUnit;

import org.testng.annotations.Test;

import com.google.common.collect.MapMaker;

/**
 * TODO(这里用一句话描述这个类的作用)
 * <p>
 * TODO(这里描述这个类补充说明 – 可选)
 * @author   朱晓川
 * @Date	 2016年5月13日
 */
public class UseMapMaker {

	@Test
	public void testWeakKeys() throws Exception {
		ConcurrentMap<String, String> map = new MapMaker().weakKeys() // 指定Map保存的Key为WeakReference机制  
				.makeMap();

		String key = "key";
		map.put(key, "value"); // 加入元素  
		key = null; // key变成了WeakReference  

		System.gc();// 触发垃圾回收  
		TimeUnit.SECONDS.sleep(1L);

		// map不一定为空，因为回收行为是不确定的 
		System.out.println(map.isEmpty());
	}
}
