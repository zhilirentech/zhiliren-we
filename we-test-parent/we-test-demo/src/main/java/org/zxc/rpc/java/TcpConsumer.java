package org.zxc.rpc.java;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Method;
import java.net.Socket;

import org.zxc.rpc.service.HelloService;

public class TcpConsumer {
	
	public static void main(String[] args) throws Exception { 
		long start = System.currentTimeMillis() ;
		for(int i = 0;i < 10000 ; i ++){
			rpcCall() ;
		}
		long use = System.currentTimeMillis() - start ;
		System.out.println("use " + use + " milliseconds"); 
	}
	
	public static void rpcCall()throws Exception{
		//使用反射获取需要远程执行的方法
		String interfaceName = HelloService.class.getName() ;
		Method method = HelloService.class.getMethod("sayHello", String.class) ;
		
		//参数
		Object[] param = {"hello"} ;
		Socket socket = new Socket("127.0.0.1",8082) ;
		
		/*
		 * 方法名称和方法签名传输到远端,包含了
		 * 1，协议:    按照顺序，第一个写入的参数是接口名称，第二个是方法名称等约定
		 * 2，序列化：   将对象转为二进制流进行传输，使用了jdk自带的序列化
		 */
		ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream()) ;
		out.writeUTF(interfaceName) ;
		out.writeUTF(method.getName()) ;
		out.writeObject(method.getParameterTypes()) ; 
		out.writeObject(param) ;
		
		//读取远程执行的结果
		ObjectInputStream input = new ObjectInputStream(socket.getInputStream()) ;
		Object result = input.readObject() ;
		result = null ;
		
		input.close() ;
		out.close() ;
	}
}
