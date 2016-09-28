package org.zxc.seria;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * 无论何种类型的数据最终都要转换成二进制流在网络上进行传输，RPC如何将对象发送到远端:
 * 对象的序列化与反序列化
 * <P>
 * 常见的序列化方式:
 * 1, google protocol buffers
 * 2, java
 * 3, hessian
 * 4, json
 * 5, xml
 * 等。
 * protocol buffers性能优异，跨平台。但是不能直接使用编程语言的内置类型，需要编写proto文件
 * 就像thrift需要编写thrift文件一样；
 * java本身的序列化方式效率不理想；
 * hessian比protocol buffer性能稍低，但是对各种编程语言支持良好，且性能稳定。
 * 
 * @author 朱晓川
 *
 */
public class JavaSeriaTest {
	
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		//序列化(对象->字节数据)
		People zhangsan  = new People("zhangsan") ;  
		ByteArrayOutputStream os = new ByteArrayOutputStream() ;
		ObjectOutputStream out = new ObjectOutputStream(os) ;
		out.writeObject(zhangsan) ;
		
		//反序列化(字节数据-> 对象)
		byte[] data = os.toByteArray() ;
		ByteArrayInputStream is = new ByteArrayInputStream(data) ;
		ObjectInputStream input = new ObjectInputStream(is) ;
		People people = (People)input.readObject() ;
		System.out.println(people.getName());  
	}
}
