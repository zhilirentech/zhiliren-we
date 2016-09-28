package org.zxc.guava.concurrent;

import java.util.ArrayList;
import java.util.List;

import com.google.common.util.concurrent.Monitor;

/**
 * 条件阻塞
 *
 */
public class MonitorSample {

	private List<String> list = new ArrayList<String>();
	private static final int MAX_SIZE = 10;
	private Monitor monitor = new Monitor();

	private Monitor.Guard listBelowCapacity = new Monitor.Guard(monitor) {
		@Override
		public boolean isSatisfied() {
			return list.size() < MAX_SIZE;
		}
	};

	public void addToList(String item) throws InterruptedException {
		monitor.enterWhen(listBelowCapacity); // Guard(形如Condition)，不满足则阻塞，而且我们并没有在Guard进行任何通知操作
		try {
			list.add(item);
		} finally {
			monitor.leave();
		}
	}

}
