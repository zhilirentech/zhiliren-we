package org.zxc.netty.stick;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import com.uxuexi.core.common.util.Util;
/**
 * 已处理粘包的服务端
 * @author 朱晓川
 *
 */
public class HandleStickServer {
	
	public static final String SEPARATOR = System.getProperty("line.separator") ;
	
	public void bind(int port){
		/**服务端Nio线程组*/
		//1，用于服务端接受客户端连接
		EventLoopGroup bossGroup = new NioEventLoopGroup() ;
		//2，用于SocketChannel网络读写
		EventLoopGroup workerGroup = new NioEventLoopGroup() ;
		
		//启动设置
		ServerBootstrap boot = new ServerBootstrap() ;
		boot.group(bossGroup, workerGroup)
		.channel(NioServerSocketChannel.class)
		.option(ChannelOption.SO_BACKLOG, 1024)
		
		//绑定事件处理
		.childHandler(new ChildHandler()); 
		
		try {
			//绑定端口，同步等待
			ChannelFuture  chFuture = boot.bind(port).sync() ;
			
			//等待服务端监听端口关闭,然后关闭服务
			chFuture.channel().closeFuture().sync() ;
		} catch (InterruptedException e) {
			e.printStackTrace();
		}finally{
			//优雅退出
			bossGroup.shutdownGracefully() ;
			workerGroup.shutdownGracefully() ;
		}
	}
	
	private class ChildHandler extends ChannelInitializer<SocketChannel>{

		@Override
		protected void initChannel(SocketChannel channel) throws Exception { 
			//添加解码器
			channel.pipeline().addLast(new LineBasedFrameDecoder(1024)) ;
			channel.pipeline().addLast(new StringDecoder()) ;
			channel.pipeline().addLast(new HandleStickServerHandler()) ;
		}
	}
	
	public static void main(String[] args) {
		int port = 8080 ;
		if(!Util.isEmpty(args)){
			port = Integer.valueOf(args[0]) ; 
		}
		new HandleStickServer().bind(port) ;
	}
	
}
