package com.xiaoka.template.service.consumer;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.xiaoka.template.service.TemplateService;

public class DemoAction {
    
    private TemplateService templateService;

    public void setTemplateService(TemplateService templateService) {
		this.templateService = templateService;
	}

	public void start() throws Exception {
        for (int i = 0; i < Integer.MAX_VALUE; i ++) {
            try {
            	String hello = templateService.sayHello("world" + i);
                System.out.println("[" + new SimpleDateFormat("HH:mm:ss").format(new Date()) + "] " + hello);
            } catch (Exception e) {
                e.printStackTrace();
            }
            Thread.sleep(2000);
        }
	}

}