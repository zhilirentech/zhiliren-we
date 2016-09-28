package org.zxc.rpc.java;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.ServerSocket;
import java.net.Socket;

import org.zxc.rpc.service.HelloService;
import org.zxc.rpc.service.impl.HelloServiceImp;

public class TcpProvider { 
	
	public static void main(String[] args) throws IOException, ClassNotFoundException, IllegalArgumentException, IllegalAccessException, InvocationTargetException, SecurityException, NoSuchMethodException { 
		ServerSocket server =  new ServerSocket(8082) ;
		while(true){
			Socket socket = server.accept() ;
			ObjectInputStream input = new ObjectInputStream(socket.getInputStream()) ;
			
			//按照和客户端约定的顺序进行读取
			String interfaceName = input.readUTF() ;
			String methodName = input.readUTF() ;
			Class<?>[] parameterTypes = (Class<?>[]) input.readObject() ;
			Object[] params =  (Object[]) input.readObject() ; 
			
			//执行方法调用
			Class<?> serviceClazz = Class.forName(interfaceName) ;
			Method method = serviceClazz.getMethod(methodName, parameterTypes) ;
			
			//实现类的实例(这里一般会将服务实现类的实例放入一个静态map缓存起来，通过名称key进行获取)
			HelloService service = new HelloServiceImp() ;
			
			//通过实例和参数调用方法
			Object result = method.invoke(service, params) ;
			
			//将结果从socket写回客户端
			ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream()) ; 
			output.writeObject(result) ;
			
			input.close() ;
			output.close() ;
		}
	}
}
