/**
 * FileUtil.java
 * com.uxuexi.util
 * Copyright (c) 2012, 北京聚智未来科技有限公司版权所有.
*/

package com.uxuexi.core.common.util;

import static com.uxuexi.core.common.util.Util.*;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.joda.time.DateTime;
import org.nutz.lang.Files;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 文件工具类
 * 

 * @author   庄君祥
 * @Date	 2012-11-20 	
 */
public final class FileUtil {

	private static final Logger logger = LoggerFactory.getLogger(FileUtil.class);

	/**根据文件路径，获取一个BufferedReader实例*/
	public static BufferedReader getBufferedReader(final String filePath, final String charset)
			throws UnsupportedEncodingException {
		File f = new File(filePath);
		return getBufferedReader(f, charset);
	}

	/**根据路径，获取一个BufferedReader实例
	 * @throws UnsupportedEncodingException */
	public static BufferedReader getBufferedReader(final File file, final String charset)
			throws UnsupportedEncodingException {
		BufferedReader reader = null;

		reader = new BufferedReader(new InputStreamReader(getInputStream(file), charset));
		return reader;
	}

	/**根据文件路径，获取一个BufferedWriter实例*/
	public static BufferedWriter getBufferedWriter(final String filePath) {
		File f = new File(filePath);
		return getBufferedWriter(f);
	}

	/**根据文件，获取一个BufferedWriter实例*/
	public static BufferedWriter getBufferedWriter(final File file) {
		BufferedWriter writer = null;
		writer = new BufferedWriter(new OutputStreamWriter(getOutputStream(file)));
		return writer;
	}

	/**根据文件路径获取一个输出流*/
	public static OutputStream getOutputStream(final String filePath) {
		File f = new File(filePath);
		return getOutputStream(f);
	}

	/**根据文件获取一个输出流*/
	public static OutputStream getOutputStream(final File file) {
		OutputStream os = null;
		try {
			os = new FileOutputStream(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return os;
	}

	/**根据文件路径获取一个输入流*/
	public static InputStream getInputStream(final String filePath) {
		File f = new File(filePath);
		return getInputStream(f);
	}

	/**根据文件获取一个输入流*/
	public static InputStream getInputStream(final File f) {
		InputStream is = null;
		try {
			is = new FileInputStream(f);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return is;
	}

	/**
	 * 逐行读取指定路径文件的每一行，返回list集合，集合的每个元素就是文件的每一行,
	 * 若该行trim以后为空字符串，则抛弃
	 */
	public static List<String> getLineList(final String filePath, final String charset) {
		List<String> list = new ArrayList<String>();
		getLineCollection(filePath, charset, list);
		return list;
	}

	/**
	 * 逐行读取指定路径文件的每一行，返回set集合，集合的每个元素就是文件的每一行,
	 * 若该行trim以后为空字符串，则抛弃
	 */
	public static Set<String> getLineSet(final String filePath, final String charset) {
		Set<String> set = new HashSet<String>();
		getLineCollection(filePath, charset, set);
		return set;
	}

	/**
	 * 校验文件是否存在
	 * 
	 * @param file 文件路径
	 * @return 存在为真。如果路径为空，则为假
	 */
	public static boolean isExist(final String file) {
		if (isEmpty(file)) {
			return false;
		}
		File f = new File(file);
		return f.exists();
	}

	/**  
	 * 拷贝文件到指定文件,如果指定文件不存在,自动生成文件; 如果指定文件存在,则自动覆盖该文件. 该方法能自动捕获异常并输出(使用log4j)  
	 *   
	 * @param srcFileName 源文件  
	 * @param tagFileName 目标文件(指定文件)  
	 */
	public static boolean copyFile(final String srcFileName, final String tagFileName) {
		File out = new File(tagFileName);
		if (!out.exists()) {
			try {
				out.createNewFile();
			} catch (IOException e) {
				logger.error("错误:文件" + tagFileName + "创建失败!", e);
				return false;
			}
		}
		FileInputStream inputStream = null;
		FileChannel inChannel = null;
		FileChannel outChannel = null;
		FileOutputStream outputStream = null;
		boolean isOk = false;
		try {
			File in = new File(srcFileName);
			inputStream = new FileInputStream(in);
			inChannel = inputStream.getChannel();
			long inputSize = inChannel.size();
			MappedByteBuffer inBuffer = inChannel.map(FileChannel.MapMode.READ_ONLY, 0, inputSize);

			// 使用通道方式复制文件   
			outputStream = new FileOutputStream(out);
			outChannel = outputStream.getChannel();
			outChannel.write(inBuffer);
			isOk = true;
			// 关闭相关对象   
		} catch (FileNotFoundException e) {
			logger.error("错误:文件" + srcFileName + "未找到!", e);
		} catch (IOException e2) {
			logger.error("错误:文件操作出错!", e2);
		} finally {
			if (inChannel != null && inChannel.isOpen()) {
				try {
					inChannel.close();
				} catch (IOException e) {
					logger.error("错误:文件流关闭失败!", e);
				}
			}
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException e) {
					logger.error("错误:文件流关闭失败!", e);
				}
			}
			if (outChannel != null && outChannel.isOpen()) {
				try {
					outChannel.close();
				} catch (IOException e) {
					logger.error("错误:文件流关闭失败!", e);
				}
			}
			if (outputStream != null) {
				try {
					outputStream.close();
				} catch (IOException e) {
					logger.error("错误:文件流关闭失败!", e);
				}
			}
		}
		return isOk;
	}

	/**
	 * 创建文件夹
	 * 
	 * @param dir 文件夹路径
	 * @return 是否创建成功
	 */
	public static boolean makeDir(final File dir) {
		if (null == dir || dir.exists()) {
			return false;
		}
		return dir.mkdirs();
	}

	/**
	 * 创建新文件
	 *  
	 * @param f 文件路径
	 * @return 是否创建成功
	 */
	public static boolean createNewFile(final File f) {
		if (null == f) {
			return false;
		}
		if (f.exists()) {
			f.delete();
		}
		makeDir(f.getParentFile());
		try {
			return f.createNewFile();
		} catch (IOException e) {
			logger.error("创建文件出错", e);
			return false;
		}
	}

	/**
	 * 删除文件
	 *  
	 * @param file 文件路径
	 * @return 是否删除成功
	 */
	public static boolean deleteFile(final String file) {
		File f = new File(file);
		return f.delete();
	}

	/**
	 * 获取路径中的文件名
	 * 
	 * @param pathName	路径
	 * @return			文件名
	*/
	public static String getFileName(final String pathName) {
		if (pathName.lastIndexOf("\\") > -1) {
			return pathName.substring(pathName.lastIndexOf("\\") + 1);
		} else if (pathName.lastIndexOf("/") > -1) {
			return pathName.substring(pathName.lastIndexOf("/") + 1);
		}
		return pathName;
	}

	public static String getRandomFileName() {
		DateTime dt = DateTimeUtil.now();
		StringBuilder rdFoldPath = new StringBuilder();
		rdFoldPath.append(dt.getYear()).append(File.separator).append(dt.getMonthOfYear()).append(File.separator)
				.append(dt.getDayOfMonth()).append(File.separator).append(RandomUtil.uu16());
		return rdFoldPath.toString();
	}

	/**
	 * 获取路径中的路径
	 * 
	 * @param pathName  	路径
	 * @return 	路径
	*/
	public static String getFilePath(final String pathName) {
		if (pathName.indexOf(File.separator) > -1) {
			return pathName.substring(0, pathName.lastIndexOf(File.separator));
		}
		return "";
	}

	/**
	 * 考虑到性能，使用该方法创建一个输入流
	 * 
	 * @param f   File 实例
	 * @return 输入流
	 */
	public static BufferedInputStream bis(final File f) {
		if (f.exists()) {
			try {
				return new BufferedInputStream(new FileInputStream(f));
			} catch (FileNotFoundException e) {
				//never happen!
			}
		}
		return null;
	}

	/**
	 * 考虑到性能，使用该方法创建一个输出流
	 * 
	 * @param f   File 实例
	 * @return 输出流
	 */
	public static BufferedOutputStream bos(final File f) {
		if (f.exists()) {
			try {
				return new BufferedOutputStream(new FileOutputStream(f));
			} catch (FileNotFoundException e) {
				//never happen!
			}
		}
		return null;
	}

	/**
	 * 获取文件后缀
	 *
	 * @param file 文件路径
	 * @return 后缀
	 */
	public static String getSuffix(final String file) {
		if (isEmpty(file)) {
			return "";
		}
		return Files.getSuffixName(file);
	}

	/**
	 * 获取文件后缀
	 *
	 * @param file 文件路径
	 * @return 后缀
	 */
	public static String getSuffix(final File file) {
		if (isEmpty(file)) {
			return "";
		}
		return Files.getSuffixName(file);
	}

	private static void getLineCollection(final String filePath, final String charset,
			final Collection<String> collection) {
		try {
			BufferedReader reader = getBufferedReader(filePath, charset);
			String line = "";
			while ((line = reader.readLine()) != null) {
				collection.add(line);
			}
			reader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
