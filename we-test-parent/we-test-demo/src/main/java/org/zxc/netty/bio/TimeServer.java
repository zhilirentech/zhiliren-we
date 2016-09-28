package org.zxc.netty.bio;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.uxuexi.core.common.util.Util;

/**
 * 传统的阻塞式IO会为每一个客户端创建一个新的线程进行链路处理，处理完成后将数据返回客户端，
 * 并销毁线程.
 * 可以使用java自带的JVM 监控工具 Java VisualVM打印线程堆栈信息。 jvisualvm.exe 在JDK 的 bin 目录下，
 * 从jdk1.6 update 7开始才有，双击即可启动。
 * @author 朱晓川
 *
 */
public class TimeServer {

	private static Logger logger = LoggerFactory.getLogger(TimeServer.class);

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
			while (true) {
				client = server.accept();
				new Thread(new TimeServerHandler(client)).start();
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
