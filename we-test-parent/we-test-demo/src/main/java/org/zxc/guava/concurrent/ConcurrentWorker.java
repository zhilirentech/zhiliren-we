package org.zxc.guava.concurrent;

import java.util.concurrent.Semaphore;

import com.google.common.util.concurrent.Striped;

public class ConcurrentWorker {
	
	//带stripd的信号量
	private Striped<Semaphore> stripedSemaphores = Striped.semaphore(10,3);
 
	//直接使用信号量
	private Semaphore semaphore = new Semaphore(3);

	public void stripedConcurrentAccess(String url) throws Exception{
		Semaphore stripedSemaphore = stripedSemaphores.get(url);
		stripedSemaphore.acquire();
		try{
			//Access restricted resource here
			Thread.sleep(25);
		}finally{
			stripedSemaphore.release();
		}
	}

	public void nonStripedConcurrentAccess(String url) throws Exception{
		semaphore.acquire();
		try{
			//Access restricted resource here
			Thread.sleep(25);
		}finally{
			semaphore.release();
		}
	}
}
