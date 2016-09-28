package org.zxc.netty.stick;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.zxc.netty.unstick.NettyTimeServer;

public class HandleStickServerHandler extends ChannelHandlerAdapter  {
	
	private static Logger logger = LoggerFactory.getLogger(HandleStickServerHandler.class) ;

	private int counter = 0 ;

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg)throws Exception { 
		//收到的消息就是删除了回车和换行后的消息
		String body = (String) msg ; 
		logger.info("the time server receive order : " + body + " ; the counter is : " + ++counter); 
		
		//响应数据
		String result = "QUERY TIME ORDER".equalsIgnoreCase(body) ? new Date(
				System.currentTimeMillis()).toString() : "BAD ORDER"; 
				
		result = result + NettyTimeServer.SEPARATOR ;
		ByteBuf resp = Unpooled.copiedBuffer(result.getBytes()) ;
		
		//写入通道
		ctx.write(resp) ;
		
	}
	
    @Override
	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
    	ctx.flush() ;
	}

	@Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) { // (4)
        cause.printStackTrace();
        ctx.close();
    }
	
}
