package org.zxc.rpc.hessian;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.ServerSocket;
import java.net.Socket;

import org.zxc.rpc.service.HelloService;
import org.zxc.rpc.service.impl.HelloServiceImp;

import com.caucho.hessian.io.Hessian2Input;
import com.caucho.hessian.io.Hessian2Output;

public class HessianTcpProvider { 
	
	public static void main(String[] args) throws IOException, ClassNotFoundException, IllegalArgumentException, IllegalAccessException, InvocationTargetException, SecurityException, NoSuchMethodException { 
		ServerSocket server =  new ServerSocket(8082) ;
		while(true){
			Socket socket = server.accept() ;
			Hessian2Input in = new Hessian2Input(socket.getInputStream()) ;
			
			//按照和客户端约定的顺序进行读取
			String interfaceName = in.readString() ;
			String methodName = in.readString() ;
			Class<?>[] parameterTypes = (Class<?>[]) in.readObject() ;
			Object[] params =  (Object[]) in.readObject() ; 
			in.close() ; 
			
			//执行方法调用
			Class<?> serviceClazz = Class.forName(interfaceName) ;
			Method method = serviceClazz.getMethod(methodName, parameterTypes) ;
			
			//实现类的实例(这里一般会将服务实现类的实例放入一个静态map缓存起来，通过名称key进行获取)
			HelloService service = new HelloServiceImp() ;
			
			//通过实例和参数调用方法
			Object result = method.invoke(service, params) ;
			
			//将结果从socket写回客户端
			Hessian2Output out = new Hessian2Output(socket.getOutputStream()) ;
			out.writeObject(result) ;
			out.close() ;
		}
	}
}
