/**
 * ResponseWrapper.java
 * com.uxuexi.core.web.view.support
 * Copyright (c) 2014, 北京聚智未来科技有限公司版权所有.
*/

package com.uxuexi.core.web.view.support;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

import org.nutz.lang.stream.StringOutputStream;

/**
 * response 对象的包装类
 * @author   庄君祥
 * @Date	 2013-12-24 	 
 */
public class ResponseWrapper extends HttpServletResponseWrapper {
	private ServletOutputStream servletOutputStream;
	private StringBuilder content;
	private PrintWriter writer;

	public ResponseWrapper(final HttpServletResponse response) {
		super(response);
		content = new StringBuilder();
		this.servletOutputStream = new ServletOutputStreamWrapper(new StringOutputStream(content));
	}

	@Override
	public ServletOutputStream getOutputStream() {
		return servletOutputStream;
	}

	@Override
	public PrintWriter getWriter() throws IOException {
		if (writer == null) {
			writer = new PrintWriter(new OutputStreamWriter(servletOutputStream, getCharacterEncoding()), true);
		}
		return writer;
	}

	@Override
	public void flushBuffer() throws IOException {
		flush();
		super.flushBuffer();
	}

	public void flush() throws IOException {
		if (writer != null) {
			writer.flush();
		}
		servletOutputStream.flush();
	}

	@Override
	public void finalize() throws Throwable {
		super.finalize();
		if (writer != null) {
			writer.close();
		}
		servletOutputStream.close();
	}

	public String getContent() throws Throwable {
		flush();
		return content.toString();
	}
}
