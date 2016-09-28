package org.zxc.netty.stick;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.zxc.netty.unstick.NettyTimeClientStickHandler;
import org.zxc.netty.unstick.NettyTimeServer;

public class HandleStickClientHandler extends ChannelHandlerAdapter {
	
private static Logger logger = LoggerFactory.getLogger(NettyTimeClientStickHandler.class) ;
	
	/**添加行分隔符*/
	private static final String message = "QUERY TIME ORDER" + NettyTimeServer.SEPARATOR ; 
	
	private int counter ;
	
	private byte[] req ;
	
	public HandleStickClientHandler(){ 
		req = message.getBytes() ;
	}
	
	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		//使用缓冲区发送字节数据
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
		//收到的响应就是字符串
		String body = (String) msg ; 
		logger.info("Now is :" + body + " ; the counter is : " + ++counter) ;
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)throws Exception {
		 cause.printStackTrace();
	     ctx.close();
	}
	
}
