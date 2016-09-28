package org.zxc.seria.protostuff.tcp;

import java.io.OutputStream;
import java.net.Socket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.zxc.seria.protostuff.bean.LeeBean;
import org.zxc.seria.protostuff.util.SerializationUtil;

public class ProtoStuffTcpClient {
	
	private static Logger logger = LoggerFactory.getLogger(ProtoStuffTcpClient.class) ;
	
	public static void main(String[] args) throws Exception {
		LeeBean lee = new LeeBean() ; 
		lee.setId(1) ;
		lee.setName("zalepisces") ; 
		byte[] data = SerializationUtil.serialize(lee) ; 
       
		logger.info("client-length : " + data.length) ;
    	Socket socket = new Socket("127.0.0.1",8082) ;
    	OutputStream out = socket.getOutputStream() ;
    	out.write(data) ;
    	
    	socket.close() ;
	}
}
