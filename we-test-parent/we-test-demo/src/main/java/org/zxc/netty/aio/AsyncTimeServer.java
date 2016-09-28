package org.zxc.netty.aio;

import java.io.IOException;

import com.uxuexi.core.common.util.Util;

public class AsyncTimeServer {

	public static void main(String[] args) throws IOException {
		int port = 8080;
		if (!Util.isEmpty(args)) {
			port = Integer.valueOf(args[0]);
		}

		new Thread(new AsyncTimeServerHandler(port), "AsyncTimeServer-001").start();

	}

}
