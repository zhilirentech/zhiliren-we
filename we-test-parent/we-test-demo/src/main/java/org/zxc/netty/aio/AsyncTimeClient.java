package org.zxc.netty.aio;

import com.uxuexi.core.common.util.Util;

public class AsyncTimeClient {

	public static void main(String[] args) {
		int port = 8080;
		if (!Util.isEmpty(args)) {
			port = Integer.valueOf(args[0]);
		}

		new Thread(new AsyncTimeClientHandler("127.0.0.1", port), "AsyncTimeClient-001").start();
	}
}
