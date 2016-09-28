/**
 * EncryptUtil.java
 * com.uxuexi.core.utils
 * Copyright (c) 2011, 北京聚智未来科技有限公司版权所有.
*/

package com.uxuexi.core.common.util;

import static com.uxuexi.core.common.util.ExceptionUtil.*;
import static com.uxuexi.core.common.util.Util.*;

import java.io.UnsupportedEncodingException;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

import org.apache.commons.codec.binary.Base64;

/**
 * 加解密工具类
 * <p>
 * DES算法、md5
 * @author   庄君祥

 * @Date	 2014-4-3  
 */
public abstract class EncryptUtil {

	public static final String PASSWORD_CRYPT_KEY = "abcdefgh";

	/**
	 * DES加密
	 * @param message 待加密的数据
	 * @param password 密钥
	 * @return 密文
	 */
	public static String encrypt(final String message, final String password) {
		try {
			if (isEmpty(message) || isEmpty(password)) {
				return "";
			}
			SecureRandom random = new SecureRandom();
			DESKeySpec desKey = new DESKeySpec(password.getBytes("UTF-8"));
			//创建一个密匙工厂，然后用它把DESKeySpec转换成  
			SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
			SecretKey securekey = keyFactory.generateSecret(desKey);
			//Cipher对象实际完成加密操作  
			Cipher cipher = Cipher.getInstance("DES");
			//用密匙初始化Cipher对象  
			cipher.init(Cipher.ENCRYPT_MODE, securekey, random);
			//现在，获取数据并加密  
			//正式执行加密操作  
			return new String(Base64.encodeBase64(cipher.doFinal(message.getBytes("UTF-8")), false));
		} catch (Exception e) {
			throw pEx("DES加密失败", e);
		}
	}

	/**
	 * 使用默认密钥的加密
	 * @param message 待加密的数据
	 * @return 密文
	 */
	public static String encrypt(final String message) {
		return encrypt(message, PASSWORD_CRYPT_KEY);
	}

	/**
	 * DES解密
	 * @param message 待解密的数据
	 * @param password 密钥
	 * @return 解密文
	 */
	public static String decrypt(final String message, final String password) {
		try {
			if (isEmpty(message) || isEmpty(password)) {
				return "";
			}
			byte[] decodeValue = Base64.decodeBase64(message.getBytes("UTF-8"));
			// DES算法要求有一个可信任的随机数源  
			SecureRandom random = new SecureRandom();
			// 创建一个DESKeySpec对象  
			DESKeySpec desKey = new DESKeySpec(password.getBytes("UTF-8"));
			// 创建一个密匙工厂  
			SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
			// 将DESKeySpec对象转换成SecretKey对象  
			SecretKey securekey = keyFactory.generateSecret(desKey);
			// Cipher对象实际完成解密操作  
			Cipher cipher = Cipher.getInstance("DES");
			// 用密匙初始化Cipher对象  
			cipher.init(Cipher.DECRYPT_MODE, securekey, random);
			// 真正开始解密操作  
			return new String(cipher.doFinal(decodeValue), "UTF-8");
		} catch (Exception e) {
			throw pEx("DES解密失败", e);
		}
	}

	/**
	 * 使用默认密钥的DES解密
	 * @param message 待解密的数据
	 * @return 解密文
	 */
	public static String decrypt(final String message) {
		return decrypt(message, PASSWORD_CRYPT_KEY);
	}

	public static String encode(final String message) {
		if (Util.isEmpty(message)) {
			return "";
		}

		try {
			return Base64.encodeBase64String(message.getBytes("UTF-8"));
		} catch (UnsupportedEncodingException e) {
			return "";
		}
	}

	public static String decode(final String message) {
		if (Util.isEmpty(message)) {
			return "";
		}
		byte[] b = Base64.decodeBase64(message);
		return new String(b);
	}
}
