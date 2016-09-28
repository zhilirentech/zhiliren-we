package org.zxc.netty.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NioTimeClientHandler implements Runnable {
	
	private static Logger logger = LoggerFactory.getLogger(NioTimeClientHandler.class) ;
	
	private static final String message = "QUERY TIME ORDER" ;
	
	private String host ;
	
	private int port ;
	
	/**客户端通道*/
	private SocketChannel clientChannel ; 
	
	/**通道管理器*/
	private Selector selector ;
	
	private volatile boolean stop ;
	
	public NioTimeClientHandler(String host,int port){
		this.host = (host == null ? "127.0.0.1" : host);
		this.port = port ;
		
		try {
			//创建复用器，通道，并设置非阻塞模式
			selector = Selector.open() ;
			clientChannel = SocketChannel.open() ;
			clientChannel.configureBlocking(false) ;
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1) ;
		}
	}
	
	@Override
	public void run() {
		//连接
		try {
			doConnect() ;
		} catch (IOException e1) {
			e1.printStackTrace();
			System.exit(1) ;
		}
		
		//处理逻辑(轮询，不断的遍历复用器的事件)
		while(!stop){
			try {
				//启动复用器
				selector.select(1000) ;
				/*
				 * 这里调用selector.keys()会导致iter.remove()报不支持的操作异常
				 * 因为selector.keys()返回的集合是不可修改的
				 */
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
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		//释放资源
		if(null != selector){
			try {
				selector.close() ;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	private void handleInput(SelectionKey key) throws IOException{
		if(key.isValid()){
			//客户端要判断是否连接成功
			SocketChannel sc = (SocketChannel) key.channel() ; 
			if(key.isConnectable()){
				if(sc.finishConnect()){
					/*
					 * 如果已经完成连接，则向selector注册读事件，因为我们之后要从服务端读取数据
					 */
					sc.register(selector, SelectionKey.OP_READ) ;
					
					//发送消息
					doWrite(sc, message)  ;
				}else{
					//连接失败，进程退出
					System.exit(1) ;
				}
			}
			
			if(key.isReadable()){
				ByteBuffer readBuffer = ByteBuffer.allocate(1024) ;
				/*
				 * 读,因为是异步的，所以可能读到半包
				 */
				int readBytes = sc.read(readBuffer) ;
				if(readBytes > 0){
					readBuffer.flip() ;
					byte[] bytes = new byte[readBuffer.remaining()] ;
					readBuffer.get(bytes) ;
					String body = new String(bytes,"UTF-8") ;
					logger.info("Now is :" + body) ;
					this.stop = true ;
				}else if(readBytes < 0){
					//关闭对端链路
					key.cancel() ;
					sc.close() ;
				}else{
					//正常情况，do nothing
				}
			}
		}
		
	}
	
	/**
	 * 连接服务端:
	 * 如果直接连接成功，则注册到多路复用器上，发送消息请求，读应答
	 * @throws IOException 
	 */
	private void doConnect() throws IOException{
		if(clientChannel.connect(new InetSocketAddress(host, port))) {
			//当前通道向复用器注册读事件，等待读取数据
			clientChannel.register(selector, SelectionKey.OP_READ) ; 
			
			doWrite(clientChannel, message) ;
		}else{
			//注册连接事件，等待连接
			clientChannel.register(selector, SelectionKey.OP_CONNECT) ;
		}
	}
	
	private void doWrite(SocketChannel channel,String message) throws IOException{
		byte[] data = message.getBytes() ;
		ByteBuffer writeBuffer = ByteBuffer.allocate(data.length) ; 
		writeBuffer.put(data) ;
		writeBuffer.flip() ;
		channel.write(writeBuffer) ;
		
		//异步发送完毕
		if(!writeBuffer.hasRemaining()){
			logger.info("message send succeed ") ;
		}
	}

}
