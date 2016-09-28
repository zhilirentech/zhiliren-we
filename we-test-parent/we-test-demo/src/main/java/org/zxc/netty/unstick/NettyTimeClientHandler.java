package org.zxc.netty.unstick;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NettyTimeClientHandler extends ChannelHandlerAdapter {
	
	private static Logger logger = LoggerFactory.getLogger(NettyTimeClientHandler.class) ;
	
	private static final String message = "QUERY TIME ORDER" ;
	
	private final ByteBuf byteBuf ;
	
	public NettyTimeClientHandler(){
		byte[] bytes = message.getBytes() ;
		//先分配缓冲区大小
		byteBuf = Unpooled.buffer(bytes.length) ;
		//缓冲区写入数据
		byteBuf.writeBytes(bytes) ;
	}

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg)throws Exception {
		ByteBuf buffer = (ByteBuf) msg ;
		//准备字节数组
		byte[] data = new byte[buffer.readableBytes()] ;
		//从缓冲区读取数据到字节数组中
		buffer.readBytes(data) ;
		String body = new String(data,"UTF-8") ; 
		logger.info("Now is :" + body) ;
	}

	
	
	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		ctx.writeAndFlush(byteBuf) ;
	}



	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)throws Exception {
		 cause.printStackTrace();
	     ctx.close();
	}
	
}
