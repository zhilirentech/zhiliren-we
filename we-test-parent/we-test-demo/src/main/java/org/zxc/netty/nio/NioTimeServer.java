package org.zxc.netty.nio;

import java.io.IOException;

import com.uxuexi.core.common.util.Util;

public class NioTimeServer {
	
	public static void main(String[] args) throws IOException {
		int port = 8080 ;
		if(!Util.isEmpty(args)){
			port = Integer.valueOf(args[0]) ; 
		}
		
		NioTimeServerHandler task = new NioTimeServerHandler(port) ;
		
		//start Reactor Thread
		new Thread(task, "NIO-MultiplexerTimeServer-001").start() ;
	}
	
}
