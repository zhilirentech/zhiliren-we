package com.xiaoka.template.service.server;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BootStrap {

	public static void main(String[] args) throws Exception {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
				new String[] { "META-INF/spring/applicationContext.xml" });

		System.out.println("服务启动成功======ok");
		context.start();
		synchronized (BootStrap.class) {
			while (true)
				try {
					BootStrap.class.wait();
				} catch (Throwable e) {
					e.printStackTrace();
				}
		}

	}

}