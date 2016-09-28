/**
 * Md5Util.java
 * com.uxuexi.core.common.util
 * Copyright (c) 2014, 北京聚智未来科技有限公司版权所有.
*/

package com.uxuexi.core.common.util;

import static com.uxuexi.core.common.util.Util.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;

import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * md5工具类
 *
 * @author   庄君祥
 * @Date	 2014-5-26 	 
 */
public final class Md5Util {
	private Md5Util() {
	}

	/**
	* Logger for this class
	*/
	private static final Logger logger = LoggerFactory.getLogger(Md5Util.class);

	/**
	 * Md5加密
	 * @param message 待加密的数据
	 * @return 加密过后的数据
	 */
	public static String md5(final String message) {
		if (isEmpty(message)) {
			return "";
		}
		return DigestUtils.md5Hex(message);
	}

	/**
	 * 计算文件的md5
	 *
	 * @param file 文件
	 * @return md5
	 */
	public static String getFileMd5(final File file) {
		if (file == null || !file.exists() || !file.isFile()) {
			return "";
		}
		FileInputStream in = null;
		try {
			MessageDigest digest = MessageDigest.getInstance("MD5");
			in = new FileInputStream(file);
			int len;
			byte buffer[] = new byte[1024];
			while ((len = in.read(buffer, 0, 1024)) != -1) {
				digest.update(buffer, 0, len);
			}
			BigInteger bigInt = new BigInteger(1, digest.digest());
			return bigInt.toString(16);
		} catch (Exception e) {
			logger.error("计算文件md5出错", e);
			return "";
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					logger.error("计算文件md5出错。关闭文件流失败", e);
				}
			}
		}
	}
}
