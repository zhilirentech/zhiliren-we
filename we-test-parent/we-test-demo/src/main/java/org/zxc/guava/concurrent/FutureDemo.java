package org.zxc.guava.concurrent;

import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;

/**
 * 异步任务
 */
public class FutureDemo {

	public static void main(String[] args) {
		ListeningExecutorService executorService = MoreExecutors.listeningDecorator(Executors.newCachedThreadPool());
		
		//异步任务
		final ListenableFuture<Integer> listenableFuture = executorService.submit(new Callable<Integer>() {
			@Override
			public Integer call() throws Exception {
				System.out.println("async do something .. in Thread:" + Thread.currentThread().getName());
				TimeUnit.SECONDS.sleep(3);
				return 7;
			}
		});
		
		Futures.addCallback(listenableFuture, new FutureCallback<Integer>() {
			//监听异步任务是否执行完毕,执行完成的时候做一些事
			@Override
			public void onSuccess(Integer result) {
				System.out.println("Thread:" + Thread.currentThread().getName() +" get listenable future's result with callback " + result);
			}

			@Override
			public void onFailure(Throwable t) {
				t.printStackTrace();
			}
		});
		
		//同步获取调用结果
		try {
			System.out.println("get call result in thread :" + Thread.currentThread().getName()); 
			System.out.println(listenableFuture.get());
		} catch (Exception e) {
			e.printStackTrace();
		} 
		System.out.println("end of mainmethod in thread :" + Thread.currentThread().getName()); 

	}

}
