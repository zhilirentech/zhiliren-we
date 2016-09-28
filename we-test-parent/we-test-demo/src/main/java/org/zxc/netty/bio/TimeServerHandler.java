package org.zxc.netty.bio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TimeServerHandler implements Runnable {

	private Socket socket;

	private static Logger logger = LoggerFactory
			.getLogger(TimeServerHandler.class);

	public TimeServerHandler(Socket socket) {
		this.socket = socket;
	}

	@Override
	public void run() {
		BufferedReader in = null;
		PrintWriter out = null;

		try {
			in = new BufferedReader(new InputStreamReader(
					socket.getInputStream()));
			out = new PrintWriter(socket.getOutputStream(), true);
			String cuurentTime = null;
			String body = null;

			while (true) {
				body = in.readLine();
				if (null == body) {
					break;
				}
				logger.info("the time server receive order : " + body);

				cuurentTime = "QUERY TIME ORDER".equalsIgnoreCase(body) ? new Date(
						System.currentTimeMillis()).toString() : "BAD ORDER";
				out.println(cuurentTime) ;
			}

		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if(in != null){
				try {
					in.close() ;
				} catch (IOException e) {
					e.printStackTrace();
				}
				in = null ;
			}
			
			if(out != null){
				out.close() ;
				out = null ;
			}
			
			if(socket != null){
				try {
					socket.close() ;
				} catch (IOException e) {
					e.printStackTrace();
				}
				socket = null ;
			}
		}

	}

}
