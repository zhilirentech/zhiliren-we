package org.zxc.netty.unstick;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NettyTimeClientStickHandler extends ChannelHandlerAdapter {
	
	private static Logger logger = LoggerFactory.getLogger(NettyTimeClientStickHandler.class) ;
	
	/**添加行分隔符*/
	private static final String message = "QUERY TIME ORDER" + NettyTimeServer.SEPARATOR ; 
	
	private int counter ;
	
	private byte[] req ;
	
	public NettyTimeClientStickHandler(){ 
		req = message.getBytes() ;
	}
	
	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		ByteBuf byteBuf = null ;
		//向服务端发送100次消息
		for(int i = 0 ;i < 100 ;i++){
			byteBuf = Unpooled.buffer(req.length) ;
			byteBuf.writeBytes(req) ;
			ctx.writeAndFlush(byteBuf) ;
		}
	}
	
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg)throws Exception {
		ByteBuf buffer = (ByteBuf) msg ;
		//准备字节数组
		byte[] data = new byte[buffer.readableBytes()] ;
		//从缓冲区读取数据到字节数组中
		buffer.readBytes(data) ;
		String body = new String(data,"UTF-8") ; 
		logger.info("Now is :" + body + " ; the counter is : " + ++counter) ;
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)throws Exception {
		 cause.printStackTrace();
	     ctx.close();
	}
	
}
