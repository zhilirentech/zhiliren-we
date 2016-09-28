package org.zxc.netty.nio;

import com.uxuexi.core.common.util.Util;

public class NioTimeClient {
	
	
	public static void main(String[] args) {
		int port = 8080 ;
		if(!Util.isEmpty(args)){
			port = Integer.valueOf(args[0]) ; 
		}
		new Thread(new NioTimeClientHandler("127.0.0.1", port),"NioTimeClient-001").start() ; 
	}
}
