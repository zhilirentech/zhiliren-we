package org.zxc.guava.concurrent.countdown;

import java.util.concurrent.CountDownLatch;

/**
 * 会议需要等所有人都到齐之后才能开始，是使用CounDownLatch的典型场景
 * @author 朱晓川
 *
 */
public class VideoConference implements Runnable{ 
	
	
	/**
	 * 1，CountDownLatch 机制不是用来保护共享资源或者临界区。它是用来同步一个或者多个执行多个任务的线程。它只能使用一次。
	 * 2，一旦CountDownLatch的计数器到达0，任何对它的方法的调用都是无效的。如果你想再次同步，你必须创建新的对象。
	 */
	private final CountDownLatch latch;   
	
	/**
	 * 创建一个视频会议的时候设置与会人数
	 */
    public VideoConference(int number){  
        latch = new CountDownLatch(number);  
    }  
     
    /**
     * 每到会一个人就执行计数--
     */
    public void arrive(String name){  
        System.out.println(name+" has arrived.");  
        latch.countDown();  
        System.out.println("VideoConference:Waiting for "+latch.getCount());  
    }  
    
    /**
     * 会议主流程
     */
    @Override  
    public void run() {  
        System.out.println("VideoConference:Initialization:"+latch.getCount());  
          
        try {  
            latch.await();  
            System.out.printf("VideoConference: All the participants have come\n");  
            System.out.printf("VideoConference: Let's start...\n");  
  
        } catch (InterruptedException e) {  
            e.printStackTrace();  
        }  
    }  

}
