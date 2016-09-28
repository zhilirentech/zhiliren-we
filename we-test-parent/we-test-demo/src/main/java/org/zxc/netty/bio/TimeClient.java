package org.zxc.netty.bio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.uxuexi.core.common.util.Util;

public class TimeClient {

	private static Logger logger = LoggerFactory.getLogger(TimeClient.class);

	public static void main(String[] args) {
		int port = 8080;
		if (!Util.isEmpty(args)) {
			port = Integer.valueOf(args[0]);
		}

		Socket client = null;
		BufferedReader in = null;
		PrintWriter out = null;
		try {
			client = new Socket("127.0.0.1", port);
			in = new BufferedReader(new InputStreamReader(client.getInputStream()));
			out = new PrintWriter(client.getOutputStream(), true);

			out.println("QUERY TIME ORDER");
			logger.info("Send order to server succeed " + port);
			String resp = in.readLine();
			logger.info("Now is : " + resp);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
				in = null;
			}

			if (out != null) {
				out.close();
				out = null;
			}

			if (client != null) {
				try {
					client.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
				client = null;
			}
		}
	}
}
