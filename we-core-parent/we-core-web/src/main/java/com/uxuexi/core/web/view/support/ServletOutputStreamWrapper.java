/**
 * ServletOutputStreamWrapper.java
 * com.uxuexi.core.web.view.support
 * Copyright (c) 2014, 北京聚智未来科技有限公司版权所有.
*/

package com.uxuexi.core.web.view.support;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletOutputStream;

/**
 * ServletOutputStream的包装类
 * @author   庄君祥
 * @Date	 2013-12-24 	 
 */
public class ServletOutputStreamWrapper extends ServletOutputStream {
	private OutputStream stream;

	public ServletOutputStreamWrapper(final OutputStream stream) {
		this.stream = stream;
	}

	@Override
	public void write(final int b) throws IOException {
		stream.write(b);
	}

	@Override
	public void write(final byte b[]) throws IOException {
		stream.write(b);
	}

	@Override
	public void write(final byte b[], final int off, final int len) throws IOException {
		stream.write(b, off, len);
	}

	@Override
	public void flush() throws IOException {
		stream.flush();
		super.flush();
	}

	@Override
	public void close() throws IOException {
		stream.close();
		super.close();
	}
}
