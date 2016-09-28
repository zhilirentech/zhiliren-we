package org.zxc.seria.protostuff.tcp;

import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.zxc.seria.protostuff.bean.LeeBean;
import org.zxc.seria.protostuff.util.SerializationUtil;

public class ProtoStuffTcpServer {
	
	private static Logger logger = LoggerFactory.getLogger(ProtoStuffTcpServer.class) ;
	
	public static void main(String[] args) throws Exception { 
		ServerSocket server = new ServerSocket(8082);
		while (true) {
			Socket client = server.accept();
			try {
				//从输入流读取字节数组
				InputStream input = client.getInputStream(); 
				int count = 0;
				while (count == 0) {
					count = input.available();
				}
				byte[] data = new byte[count];
				input.read(data) ;
				
				LeeBean lee = SerializationUtil.deserialize(data, LeeBean.class) ;
				logger.info("length : " + data.length) ;
				logger.info("id     : " + lee.getId()) ;
				logger.info("name   : " + lee.getName()) ; 
				
				input.close();
			} catch (Exception ex) {
				ex.printStackTrace();
			} finally {
				client.close();
			}
		}
	}
}
