package org.zxc.seria;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import com.caucho.hessian.io.HessianInput;
import com.caucho.hessian.io.HessianOutput;

public class HessionSeriaTest {
	
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		//序列化(对象->字节数据)
		People zhangsan  = new People("zhangsan") ;   
		ByteArrayOutputStream os = new ByteArrayOutputStream() ;
		HessianOutput out = new HessianOutput(os) ;
		out.writeObject(zhangsan) ;
		
		//反序列化(字节数据-> 对象)
		byte[] data = os.toByteArray() ;
		ByteArrayInputStream is = new ByteArrayInputStream(data) ; 
		HessianInput input = new HessianInput(is) ;
		People people = (People)input.readObject() ;
		System.out.println(people.getName()); 
	}
}
