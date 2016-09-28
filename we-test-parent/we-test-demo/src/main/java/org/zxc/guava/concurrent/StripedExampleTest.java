package org.zxc.guava.concurrent;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import com.google.common.base.Stopwatch;
import com.google.common.collect.Lists;

import com.uxuexi.core.common.util.RandomUtil;

public class StripedExampleTest {

	private ExecutorService executorService = Executors.newCachedThreadPool();
	private int numberThreads = 300;
	
	private CountDownLatch startSignal = new CountDownLatch(1);
	private CountDownLatch endSignal = new CountDownLatch(numberThreads);
	private Stopwatch stopwatch = Stopwatch.createUnstarted();
	private ConcurrentWorker worker = new ConcurrentWorker();
	
	private static final boolean USE_STRIPES = true;
	
	private static final boolean NO_STRIPES = false;
	
	private static final int POSSIBLE_TASKS_PER_THREAD = 10;
	
	private List<String> data = Lists.newArrayList();

	public static void main(String[] args) throws Exception {
		StripedExampleTest driver = new StripedExampleTest();
	    driver.createData();
	    driver.runStripedExample();
	    driver.reset();
	    driver.runNonStripedExample();
	    driver.shutdown();
	}
	
	public void createData(){
		for(int i = 0 ; i < 10;i++){
			String randStr = RandomUtil.uu16() ;
			data.add(randStr) ;
		}
	}
	
	public void reset(){
		data.clear();
	}
	
	public String getValue(int index){
		if(index < data.size())
			return data.get(index);
		return "" ;
	}
	
	public void shutdown(){
		//TODO
	}

	private void runStripedExample() throws InterruptedException {
	    runExample(worker, USE_STRIPES, "Striped work");
	}

	private void runNonStripedExample() throws InterruptedException {
	    runExample(worker, NO_STRIPES, "Non-Striped work");
	}

	private void runExample(final ConcurrentWorker worker, final boolean isStriped, String type) throws InterruptedException {
	    for (int i = 0; i < numberThreads; i++) {
	        final String url = getValue(i % POSSIBLE_TASKS_PER_THREAD);
	        
	        executorService.submit(new Callable<Void>() {
	            @Override
	            public Void call() throws Exception {
	                startSignal.await();
	                if (isStriped) {
	                    worker.stripedConcurrentAccess(url); 
	                } else {
	                    worker.nonStripedConcurrentAccess(url);
	                }
	                endSignal.countDown();
	                return null;
	            }
	        });
	    }
	    stopwatch.start();
	    startSignal.countDown();
	    endSignal.await();
	    stopwatch.stop();
	    System.out.println("Time for" + type + " work [" + stopwatch.elapsed(TimeUnit.MILLISECONDS) + "] millis");
	}
	
}
