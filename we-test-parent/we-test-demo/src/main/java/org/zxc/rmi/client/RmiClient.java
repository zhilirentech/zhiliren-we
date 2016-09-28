package org.zxc.rmi.client;

import java.rmi.Naming;
import java.util.List;

import org.zxc.rmi.bean.PersonEntity;
import org.zxc.rmi.service.PersonService;

public class RmiClient {
	
	public static void main(String[] args) {
		try{  
            //调用远程对象，注意RMI路径与接口必须与服务器配置一致   
            PersonService service = (PersonService)Naming.lookup(PersonService.URL);  
            List<PersonEntity> personList = service.getList();  
            for(PersonEntity person : personList){   
                System.out.println("ID:"+person.getId()+" Age:"+person.getAge()+" Name:"+person.getName());  
            }  
        }catch(Exception ex){  
            ex.printStackTrace();  
        }  
	}
	
}
