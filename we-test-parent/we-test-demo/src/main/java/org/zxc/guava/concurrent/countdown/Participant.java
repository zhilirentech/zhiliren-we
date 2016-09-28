package org.zxc.guava.concurrent.countdown;

import java.util.concurrent.TimeUnit;

public class Participant implements Runnable{
	
	/**
	 * 通过持有会议引用，通知会议主办方自己的到会信息
	 */
 	private VideoConference conference; 
 
    private String name;  
      
    public Participant(VideoConference conference,String name){  
        this.conference = conference;  
        this.name = name;  
    }  
	
    /**
     * 等待随机时间之后到会
     */
    @Override  
    public void run() {  
        long duration = (long)(Math.random()*10);  
        try {  
            TimeUnit.SECONDS.sleep(duration);  
        } catch (InterruptedException e) {  
            e.printStackTrace();  
        }  
        conference.arrive(name);  
    }  
}
