package org.zxc.rmi.server;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

import org.zxc.rmi.service.PersonService;
import org.zxc.rmi.service.impl.PersonServiceImpl;

public class RmiServer {
	public static void main(String[] args) {
		try {  
            PersonService personService = new PersonServiceImpl();  
            /*
             * 注册服务
             * 可以不用在控制台上开启RMI的注册程序，1099是RMI服务监视的默认端口
             */
            LocateRegistry.createRegistry(1099);  
            //注册通讯路径  
            Naming.rebind(PersonService.URL, personService);  
            System.out.println("Server Start!");  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
	}
}	
