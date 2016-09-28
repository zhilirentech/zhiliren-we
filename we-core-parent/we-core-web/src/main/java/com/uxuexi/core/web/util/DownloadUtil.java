package com.uxuexi.core.web.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;

import com.uxuexi.core.common.util.ExceptionUtil;
import com.uxuexi.core.common.util.FileUtil;
import com.uxuexi.core.common.util.Util;

/**
 * 下载方法
 * @author  lq
 * @Date	 2014-7-15
 */
public class DownloadUtil {

	public static HttpServletResponse download(final HttpServletResponse response, final String path) throws Exception {
		return download(response, path, null);
	}

	public static HttpServletResponse download(final HttpServletResponse response, final String path,
			final String fileName) throws Exception {
		File file = new File(path);
		String fn = fileName;
		if (Util.isEmpty(fileName)) {
			fn = file.getName();
		} else {
			if (fileName.indexOf(".") == -1) {
				fn = fileName + "." + FileUtil.getSuffix(file.getName());
			}
		}
		InputStream fis = new BufferedInputStream(new FileInputStream(path));
		byte[] buffer = new byte[fis.available()];
		fis.read(buffer);
		fis.close();
		response.reset();
		response.addHeader("Content-Disposition", "attachment;filename="
				+ new String(fn.getBytes("gb2312"), "ISO8859-1"));
		response.addHeader("Content-Length", "" + file.length());
		OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
		response.setContentType("application/octet-stream");
		toClient.write(buffer);
		toClient.flush();
		toClient.close();
		return response;
	}

	public static HttpServletResponse viewWord(final HttpServletResponse response, final String path) throws Exception {
		try {
			File file = new File(path);
			String filename = file.getName();
			InputStream fis = new BufferedInputStream(new FileInputStream(path));
			byte[] buffer = new byte[fis.available()];
			fis.read(buffer);
			fis.close();
			response.reset();
			response.addHeader("Content-Disposition", "inline;filename="
					+ new String(filename.getBytes("gb2312"), "ISO8859-1"));
			OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
			response.setContentType("application/pdf");
			toClient.write(buffer);
			toClient.flush();
			toClient.close();
		} catch (Exception e) {
			throw ExceptionUtil.bEx("文件损坏，预览失败");
		}
		return response;
	}

	public HttpServletResponse viewTxt(final HttpServletResponse response, final String path) throws Exception {
		try {
			BufferedReader bis = null;
			File file = new File(path);
			InputStream in = new FileInputStream(file);
			bis = new BufferedReader(new InputStreamReader(in));
			StringBuffer buf = new StringBuffer();
			String temp;
			while ((temp = bis.readLine()) != null) {
				buf.append(temp);
				response.getWriter().write(temp);
				if (buf.length() >= 1000) {
					break;
				}
			}
			bis.close();
		} catch (Exception e) {
			throw ExceptionUtil.bEx("文件损坏，预览失败");
		}
		return response;
	}
}
