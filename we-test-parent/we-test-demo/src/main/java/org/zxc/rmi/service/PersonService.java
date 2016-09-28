package org.zxc.rmi.service;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import org.zxc.rmi.bean.PersonEntity;

public interface PersonService extends Remote {
	
	/*
	 * 如果RMI注册工具在4500端口运行，则地址应为："rmi://127.0.0.1:4500/PersonService" 
	 * 另外我们已经同时假定了我们的服务端和RMI注册工具是运行在同一台机器上的。否则需要修改host地址。
	 */
	String URL = "rmi://127.0.0.1/PersonService" ;  
	
	public List<PersonEntity> getList() throws RemoteException ; 
	
}
