package org.zxc.rmi.service.impl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.LinkedList;
import java.util.List;

import org.zxc.rmi.bean.PersonEntity;
import org.zxc.rmi.service.PersonService;

public class PersonServiceImpl extends UnicastRemoteObject implements PersonService {
	
	private static final long serialVersionUID = 1L;
	
	//这个实现必须有一个显式的构造函数，并且要抛出一个RemoteException异常  
	public PersonServiceImpl() throws RemoteException {  
        super();  
    } 

	@Override
	public List<PersonEntity> getList() throws RemoteException {
		System.out.println("getList start !");  
        List<PersonEntity> personList=new LinkedList<PersonEntity>();  
          
        PersonEntity person1=new PersonEntity();  
        person1.setAge(25);  
        person1.setId(0);  
        person1.setName("Leslie");  
        personList.add(person1);  
          
        PersonEntity person2=new PersonEntity();  
        person2.setAge(25);  
        person2.setId(1);  
        person2.setName("Rose");  
        personList.add(person2);  
        
        return personList;  
	}

}
