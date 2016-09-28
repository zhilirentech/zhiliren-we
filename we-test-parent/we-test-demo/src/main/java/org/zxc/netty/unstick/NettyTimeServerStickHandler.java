package org.zxc.netty.unstick;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
 
/**
 * TCP粘包例程，当TCP发生粘包的时候，程序就不能正常工作，
 * 原本打算接收100次指令，结果只接收到2条，数据都粘合在一起了
 * @author 朱晓川
 *
 */
public class NettyTimeServerStickHandler extends ChannelHandlerAdapter  {
	
	private static Logger logger = LoggerFactory.getLogger(NettyTimeServerStickHandler.class) ;
	
	private int counter = 0 ;

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg)throws Exception { 
		ByteBuf buffer = (ByteBuf) msg ;
		byte[] req = new byte[buffer.readableBytes()] ;
		buffer.readBytes(req) ;
		String body = new String(req,"UTF-8").substring(0, req.length - NettyTimeServer.SEPARATOR.length()) ;
		
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
