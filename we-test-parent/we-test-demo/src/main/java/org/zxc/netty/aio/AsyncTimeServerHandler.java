package org.zxc.netty.aio;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.util.Date;
import java.util.concurrent.CountDownLatch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.uxuexi.core.common.util.Util;

public class AsyncTimeServerHandler implements Runnable {

	private static Logger logger = LoggerFactory.getLogger(AsyncTimeServerHandler.class);

	private AsynchronousServerSocketChannel servChannel;

	/** 防止异步操作没有完成线程就退出 */
	private CountDownLatch latch;

	/**
	 * 构造方法中启动server
	 * 
	 * @param port
	 */
	public AsyncTimeServerHandler(int port) {
		try {
			servChannel = AsynchronousServerSocketChannel.open();
			servChannel.bind(new InetSocketAddress(port));
		} catch (IOException e) {
			e.printStackTrace();
		}

		logger.info("nio server is start in port " + port);
	}

	@Override
	public void run() {
		latch = new CountDownLatch(1);
		doAccept();
		try {
			latch.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void doAccept() {
		servChannel.accept(this, new AcceptCompletionHandler());

	}

	private final class AcceptCompletionHandler implements
			CompletionHandler<AsynchronousSocketChannel, AsyncTimeServerHandler> {

		@Override
		public void completed(AsynchronousSocketChannel channel, AsyncTimeServerHandler attachment) {
			attachment.servChannel.accept(attachment, this);
			ByteBuffer buffer = ByteBuffer.allocate(1024);
			channel.read(buffer, buffer, new ReadCompletionHandler(channel));

		}

		@Override
		public void failed(Throwable exc, AsyncTimeServerHandler attachment) {
			exc.printStackTrace();
			attachment.latch.countDown();
		}

	}

	private final class ReadCompletionHandler implements CompletionHandler<Integer, ByteBuffer> {

		private AsynchronousSocketChannel socketChannel;

		public ReadCompletionHandler(AsynchronousSocketChannel channel) {
			if (null == this.socketChannel) {
				this.socketChannel = channel;
			}
		}

		@Override
		public void completed(Integer result, ByteBuffer buffer) {
			buffer.flip();
			byte[] data = new byte[buffer.remaining()];
			buffer.get(data);

			String body = null;
			try {
				body = new String(data, "UTF-8");
				logger.info("the time server receive order : " + body);
				//写
				String message = "QUERY TIME ORDER".equalsIgnoreCase(body) ? new Date(System.currentTimeMillis())
						.toString() : "BAD ORDER";
				doWrite(message);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}

		}

		private void doWrite(String message) {
			if (!Util.isEmpty(message)) {
				byte[] data = message.getBytes();
				ByteBuffer writeBuffer = ByteBuffer.allocate(data.length);
				writeBuffer.put(data);
				writeBuffer.flip();

				socketChannel.write(writeBuffer, writeBuffer, new CompletionHandler<Integer, ByteBuffer>() {

					@Override
					public void completed(Integer result, ByteBuffer buffer) {
						if (buffer.hasRemaining())
							socketChannel.write(buffer, buffer, this);
					}

					@Override
					public void failed(Throwable exc, ByteBuffer buffer) {
						try {
							socketChannel.close();
						} catch (IOException e) {
							//do nothing
						}
					}

				});
			}

		}

		@Override
		public void failed(Throwable exc, ByteBuffer attachment) {
			try {
				socketChannel.close();
			} catch (IOException e) {
				//do nothing 
			}
		}

	}

}
