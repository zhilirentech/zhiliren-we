package org.zxc.rpc.service.impl;

import org.zxc.rpc.service.HelloService;

public class HelloServiceImp implements HelloService {

	@Override
	public String sayHello(String arg) {
		if("hello".equals(arg)){
			return "hello" ;
		}else{
			return "bye bye" ;
		}
	}

}
