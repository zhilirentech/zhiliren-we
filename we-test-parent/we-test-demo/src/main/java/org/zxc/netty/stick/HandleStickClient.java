package org.zxc.netty.stick;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import com.uxuexi.core.common.util.Util;
/**
 * 已处理粘包的客户端
 * @author 朱晓川
 *
 */
public class HandleStickClient {
	
	public void connect(String host,int port){
		EventLoopGroup group = new NioEventLoopGroup() ;
		
		Bootstrap boot = new Bootstrap() ;
		boot.group(group)
		.channel(NioSocketChannel.class)
		.option(ChannelOption.TCP_NODELAY, true)
		.handler(new ChannelInitializer<Channel>() {

					@Override
					protected void initChannel(Channel channel) throws Exception {
						//添加解码器
						/**
						 * LineBasedFrameDecoder
						 * <p>
						 * 		是以换行符作为结束标识的解码器，可配置单行最大长度，超过该长度将报异常，并且忽略之前接收到的码流。
						 * StringDecoder
						 * <p>
						 * 		作用就是简单的将接收到的数据转为字符串
						 * 
						 * LineBasedFrameDecoder + StringDecoder 就是按行切换的文本解码器
						 */
						channel.pipeline().addLast(new LineBasedFrameDecoder(1024)) ; 
						channel.pipeline().addLast(new StringDecoder()) ;
						channel.pipeline().addLast(new HandleStickClientHandler()) ;
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
		new HandleStickClient().connect("127.0.0.1", port); 
	}
	
}
