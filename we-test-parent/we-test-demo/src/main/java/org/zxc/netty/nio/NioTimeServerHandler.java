package org.zxc.netty.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.uxuexi.core.common.util.Util;

public class NioTimeServerHandler implements Runnable {
	
	private static Logger logger = LoggerFactory.getLogger(NioTimeServerHandler.class) ;
	
	/**通道管理器*/
	private Selector selector ;
	
	/**服务端通道*/
	private ServerSocketChannel servChannel ;
	
	private volatile boolean stop ;
	
	public NioTimeServerHandler(int port){
		try{
			//1，启动通道管理器
			selector = Selector.open() ;
			
			//2，套接字通道绑定
			servChannel = ServerSocketChannel.open() ;
			servChannel.configureBlocking(false) ;
			InetSocketAddress addr = new InetSocketAddress(port) ;
			servChannel.socket().bind(addr, 1024) ;
			
			//3，注册服务端socket通道，准备接受新的连接
			servChannel.register(selector, SelectionKey.OP_ACCEPT) ; 
			
			logger.info("nio server is start in port " + port) ;
		}catch(IOException e){
			e.printStackTrace() ;
			System.exit(0) ;
		}
	}
	
	public void stop(){
		stop = true ;
	}
	
	@Override
	public void run() {
		while(!stop){//线程轮询selector
			try{
				/**
				 * 复用器休眠时间为1秒，无论是否有读写事件发生，1秒唤醒一次
				 */
				selector.select(1000) ;
				
				//获取已就绪的事件
				Set<SelectionKey> keys = selector.selectedKeys() ;
				Iterator<SelectionKey> iter = keys.iterator() ;
				
				SelectionKey key = null ;
				while(iter.hasNext()){
					key = iter.next() ;
					
					//删除已选的key,以防重复处理
					iter.remove() ;
					try{
						handleInput(key) ;
					}catch(Exception e){
						e.printStackTrace() ;
						if(null != key){
							key.cancel() ;
							if(null != key.channel())
								key.channel().close() ;
						}
					}
				}
				
			}catch(Throwable t){
				t.printStackTrace() ;
			}
		}
		
		//关闭多路复用器，释放所有资源
		if(null != selector){
			try {
				selector.close() ;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	private void handleInput(SelectionKey key) throws IOException{ 
		/**
		 * 判断事件的类型，进行处理
		 * 如果是连接事件，则是一个新的客户端连接，
		 * 打开一个SocketChannel通道并且注册一下READ事件，
		 * 准备读取数据
		 */
		if(key.isValid()){
			//处理新接入的请求
			if(key.isAcceptable()){
				//Accept the new connection(建立TCP物理链路)
				ServerSocketChannel ssc = (ServerSocketChannel) key.channel() ;
				SocketChannel sc = ssc.accept() ;
				sc.configureBlocking(false) ;//non block
				
				//Add the new connection to the selector & register read operation
				sc.register(selector, SelectionKey.OP_READ) ;
			}
			
			/**如果当前channel有数据可读(读事件)*/
			if(key.isReadable()){
				//通过字节缓冲区读取数据,一次读取一K
				SocketChannel sc = (SocketChannel) key.channel() ;
				ByteBuffer readBuffer = ByteBuffer.allocate(1024) ; 
				
				int readBytes = sc.read(readBuffer) ;
				if(readBytes > 0){
					readBuffer.flip() ;
					
					//将数据读取到字节数组中&转为字符串
					byte[] bytes = new byte[readBuffer.remaining()] ;
					readBuffer.get(bytes) ;
					String body = new String(bytes,"UTF-8") ;
					logger.info("the time server receive order : " + body);
					
					//写
					String result = "QUERY TIME ORDER".equalsIgnoreCase(body) ? new Date(
							System.currentTimeMillis()).toString() : "BAD ORDER"; 
					doWrite(sc, result) ;
				}else if(readBytes < 0){
					//对端链路关闭
					key.cancel() ;
					sc.close() ;
				}else{
					//读到0字节，属于正常情况，do nothing。
				}
				
			}// end of isReadable
		}
	}
	
	private void doWrite(SocketChannel channel,String response) throws IOException{
		if(!Util.isEmpty(response)){
			byte[] data = response.getBytes() ;
			//准备缓冲区
			ByteBuffer writeBuffer = ByteBuffer.allocate(data.length) ;
			//放入数据
			writeBuffer.put(data) ;
			writeBuffer.flip() ;
			
			//向通道写数据
			channel.write(writeBuffer) ; 
		}
	}
	

}
