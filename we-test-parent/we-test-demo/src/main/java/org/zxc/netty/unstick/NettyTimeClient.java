package org.zxc.netty.unstick;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import com.uxuexi.core.common.util.Util;
/**
 * 未处理粘包的客户端
 * @author 朱晓川
 *
 */
public class NettyTimeClient {
	
	public void connect(String host,int port){
		EventLoopGroup group = new NioEventLoopGroup() ;
		
		Bootstrap boot = new Bootstrap() ;
		boot.group(group)
		.channel(NioSocketChannel.class)
		.option(ChannelOption.TCP_NODELAY, true)
		.handler(new ChannelInitializer<Channel>() {

					@Override
					protected void initChannel(Channel ch) throws Exception {
						ch.pipeline().addLast(new NettyTimeClientStickHandler()) ;
					}
				}) ; 
		
		try {
			//连接服务器
			ChannelFuture future = boot.connect(host, port).sync() ; 
			
			//等待端口关闭然后关闭服务
			future.channel().closeFuture().sync() ;
		} catch (InterruptedException e) {
			e.printStackTrace();
		}finally{
			group.shutdownGracefully() ;
		}
		
	}
	
	public static void main(String[] args) {
		int port = 8080 ;
		if(!Util.isEmpty(args)){
			port = Integer.valueOf(args[0]) ; 
		}
		new NettyTimeClient().connect("127.0.0.1", port); 
	}
	
}
