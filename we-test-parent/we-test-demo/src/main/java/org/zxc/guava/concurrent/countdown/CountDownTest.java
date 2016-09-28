package org.zxc.guava.concurrent.countdown;

public class CountDownTest {
	
	public static void main(String[] args) {
		VideoConference conference = new VideoConference(10);  
        Thread threadConference = new Thread(conference);  
        threadConference.start();  
        for(int i=0;i<10;i++){  
            Participant p = new Participant(conference, "Participant"+i);  
            Thread t = new Thread(p);  
            t.start();  
        }  
	}
	
}
