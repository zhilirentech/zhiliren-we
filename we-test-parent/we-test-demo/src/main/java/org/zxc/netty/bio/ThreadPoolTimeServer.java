package org.zxc.netty.bio;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.uxuexi.core.common.util.Util;

/**
 * 使用线程池进行优化
 * @author 朱晓川
 *
 */
public class ThreadPoolTimeServer {

	private static Logger logger = LoggerFactory.getLogger(ThreadPoolTimeServer.class);

	public static void main(String[] args) throws IOException {
		int port = 8080;
		if (!Util.isEmpty(args)) {
			port = Integer.valueOf(args[0]);
		}

		ServerSocket server = null;
		try {
			server = new ServerSocket(port);
			logger.info("The time server is start on port : " + port);

			Socket client = null;
			TimeServerHandlerExecutePool singleExecutor = new TimeServerHandlerExecutePool(50, 10000);

			while (true) {
				client = server.accept();
				singleExecutor.execute(new TimeServerHandler(client));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (null != server) {
				logger.info("close time server ");
				server.close();
				server = null;
			}
		}
	}

}
