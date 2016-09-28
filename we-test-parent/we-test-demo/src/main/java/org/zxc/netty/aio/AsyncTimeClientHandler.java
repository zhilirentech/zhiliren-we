package org.zxc.netty.aio;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.util.concurrent.CountDownLatch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 未处理半包
 * @author 朱晓川
 *
 */
public class AsyncTimeClientHandler implements CompletionHandler<Void,AsyncTimeClientHandler>, Runnable {
	
	private static Logger logger = LoggerFactory.getLogger(AsyncTimeClientHandler.class) ;
	
	private static final String message = "QUERY TIME ORDER" ;
	
	/**异步socket通道*/
	private AsynchronousSocketChannel client ;
	
	/**防止异步操作没有完成线程就退出*/
	private CountDownLatch latch ;
	
	private String host ;
	
	private int port ;
	
	public AsyncTimeClientHandler(String host,int port){
		this.host = (host == null ? "127.0.0.1" : host);
		this.port = port ;
		
		try{
			client = AsynchronousSocketChannel.open() ;
		}catch(Exception e){
			e.printStackTrace() ;
		}
	}

	@Override
	public void run() {
		latch = new CountDownLatch(1) ;
		//主执行逻辑
		client.connect(new InetSocketAddress(host, port), this, this) ;
		try {
			//在主执行逻辑之后尝试await
			latch.await() ;
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		try {
			client.close() ;
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void completed(Void result, AsyncTimeClientHandler attachment) {
		//准备数据，放到缓冲区
		byte[] data = message.getBytes() ;
		ByteBuffer writeBuffer = ByteBuffer.allocate(data.length) ;
		writeBuffer.put(data);
		writeBuffer.flip() ;
		
		/*
		 * 将数据从缓冲区写入通道,
		 * 因为客户端要获取服务端的响应，因此需要用一个匿名CompletionHandler来处理
		 */
		client.write(writeBuffer, writeBuffer, new CompletionHandler<Integer,ByteBuffer>(){
				//写数据操作的回调
				@Override
				public void completed(Integer result, ByteBuffer buffer) {
					if(buffer.hasRemaining()){
						//如果没写完，继续写
						client.write(buffer,buffer,this) ;
					}else{
						//写完，准备一个缓冲区用来读数据
						ByteBuffer readbuffer = ByteBuffer.allocate(1024) ;
						
						//利用缓冲区从通道读取服务端的响应，读到了readbuffer中
						client.read(readbuffer, readbuffer, new CompletionHandler<Integer,ByteBuffer>(){

							@Override
							public void completed(Integer result,ByteBuffer readbuffer) {
								//从readbuffer中取数据，创建字符串
								readbuffer.flip() ;
								byte[] data = new byte[readbuffer.remaining()] ; 
								readbuffer.get(data) ;
								String body = null ;
								try {
									body = new String(data,"UTF-8") ;
									logger.info("Now is :" + body) ;
									latch.countDown() ;
								} catch (UnsupportedEncodingException e) {
									e.printStackTrace();
								}
							}

							@Override
							public void failed(Throwable exc,ByteBuffer readbuffer) {
								exc.printStackTrace() ;
								try {
									client.close() ;
								} catch (IOException e) {
									//do nothing
								}
							}
							
						}) ;
					}
				}
	
				@Override
				public void failed(Throwable exc, ByteBuffer attachment) {
					try {
						client.close() ;
						latch.countDown() ;
					} catch (IOException e) {
						//do nothing on close
					}
				}

			}) ;
		
	}

	@Override
	public void failed(Throwable exc, AsyncTimeClientHandler attachment) {
		exc.printStackTrace() ;
		try {
			client.close() ;
			latch.countDown() ;
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
