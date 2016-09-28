/**
 * UploadUtil.java
 * com.uxuexi.core.web.util
 * Copyright (c) 2014, 北京聚智未来科技有限公司版权所有.
*/

package com.uxuexi.core.web.util;

import java.io.File;
import java.io.IOException;

import org.joda.time.DateTime;
import org.nutz.lang.Files;

import com.uxuexi.core.common.exception.impl.ParamException;
import com.uxuexi.core.common.util.DateTimeUtil;
import com.uxuexi.core.common.util.ExceptionUtil;
import com.uxuexi.core.common.util.FileUtil;
import com.uxuexi.core.common.util.RandomUtil;
import com.uxuexi.core.common.util.StringUtil;
import com.uxuexi.core.common.util.Util;
import com.uxuexi.core.web.config.ConfigKey;
import com.uxuexi.core.web.config.KvConfig;
import com.uxuexi.core.web.util.support.IUpload;

/**
 * 文件上传工具类
 *
 * @author   庄君祥
 * @Date	 2014-4-16 	 
 */
public class UploadUtil {

	/**
	 * 上传文件
	 * <p>
	 * 基于nutz的FilePool
	 * <p>
	 * 根据目录和文件名，copy并且rename
	 *
	 * @param file 已上传的文件
	 * @param fileName 文件名
	*/
	public static File upload(final File file, final String fileName) {
		File otherFile = new File(fileName);
		try {
			Files.copyFile(file, otherFile);
		} catch (IOException e) {
			throw new ParamException("上传失败");
		}
		return otherFile;
	}

	/**
	 * 把文件上传到服务器
	 * <p>
	 * 上传的结构主要分为：
	 * <ul>
	 * 		<li>根目录——kvConfig</li>
	 * 		<li>业务模块目录--upload</li>
	 * 		<li>结构目录——foldPath，如果不存在，则取时间当目录结构</li>
	 * 		<li>文件名——fileName，如果不存在，则取随机文件名</li>
	 * </ul>
	 *
	 * @param kvConfig 配置文件
	 * @param file 需要上传到服务器的文件
	 * @param upload 上传的接口
	 * @param foldPath 结构目录
	 * @param fileName 文件名
	 * @return 上传到服务器的文件
	*/
	public static File upload(final KvConfig kvConfig, final File file, final IUpload upload, final String foldPath,
			final String fileName) {
		return upload(file, fetchNewFilePath(kvConfig, file, upload, foldPath, fileName));
	}

	private static String fetchNewFilePath(final KvConfig kvConfig, final File file, final IUpload upload,
			final String foldPath, final String fileName) {
		ExceptionUtil.checkEmpty(kvConfig, "配置文件不允许为空");
		ExceptionUtil.checkEmpty(upload, "上传的类型不允许为空");
		DateTime dt = DateTimeUtil.now();
		StringBuilder sb = new StringBuilder(kvConfig.getValue(ConfigKey.FILE_SERVER)).append(File.separator);
		if (!Util.isEmpty(upload)) {
			sb.append(upload.foldName()).append(File.separator);
		}
		String realFoldPath = "";
		if (Util.isEmpty(foldPath)) {
			realFoldPath = new StringBuilder().append(dt.getYear()).append(File.separator).append(dt.getMonthOfYear())
					.append(File.separator).append(dt.getDayOfMonth()).append(File.separator).toString();
		} else {
			realFoldPath = foldPath;
		}
		sb.append(realFoldPath);

		String realFileName = "";
		if (Util.isEmpty(fileName) && Util.isEmpty(file)) {
			throw ExceptionUtil.pEx("文件或者文件名必须有一个");
		}
		if (Util.isEmpty(fileName)) {
			realFileName = new StringBuilder().append(RandomUtil.uu16()).append(".").append(FileUtil.getSuffix(file))
					.toString();
		} else {
			realFileName = fileName;
		}
		sb.append(realFileName);
		return StringUtil.path2Web(sb.toString());
	}

	/**
	 * 上传文件
	 * <p>
	 * 生成以时间为结构目录的随机名文件
	 *
	 * @param kvConfig 配置文件
	 * @param file 需要上传到服务器的文件
	 * @param upload 上传的接口
	 * @return 服务器的文件
	 * @see #upload(KvConfig, File, IUpload, String, String)
	*/
	public static File upload(final KvConfig kvConfig, final File file, final IUpload upload) {
		return upload(kvConfig, file, upload, null, null);
	}

	/**
	 * 把文件上传到服务器
	 * <p>
	 * 上传的结构主要分为：
	 * <ul>
	 * 		<li>根目录——kvConfig</li>
	 * 		<li>业务模块目录--upload</li>
	 * 		<li>结构目录——foldPath，如果不存在，则取时间当目录结构</li>
	 * 		<li>文件名——fileName，如果不存在，则取随机文件名</li>
	 * </ul>
	 * TODO File和fileName不允许都为空
	 *
	 * @param kvConfig 配置文件
	 * @param file 需要上传到服务器的文件
	 * @param upload 上传的接口
	 * @param foldPath 结构目录
	 * @param fileName 文件名
	 * @return 上传到服务器的文件相对路径，即排除静态域名的路径，比如:http://static.xxx.com/files/user/face/2014/7/21/1.jpg 最终返回的是 /files/user/face/2014/7/21/1.jpg
	*/
	public static String uploadWithPath(final KvConfig kvConfig, final File file, final IUpload upload,
			final String foldPath, final String fileName) {
		String newFilePath = fetchNewFilePath(kvConfig, file, upload, foldPath, fileName);
		if (!Util.isEmpty(file)) {
			upload(file, newFilePath);
		}
		return getRelationPath(newFilePath, kvConfig);
	}

	/**
	 * 上传文件
	 * <p>
	 * 生成以时间为结构目录的随机名文件
	 *
	 * @param kvConfig 配置文件
	 * @param file 需要上传到服务器的文件
	 * @param upload 上传的接口
	 * @return 上传到服务器的文件相对路径
	 * @see {@link #uploadWithPath(KvConfig, File, IUpload, String, String)}
	*/
	public static String uploadWithPath(final KvConfig kvConfig, final File file, final IUpload upload) {
		return uploadWithPath(kvConfig, file, upload, null, null);
	}

	/**
	 * 上传文件
	 * <p>
	 * 基于nutz的FilePool
	 * <p>
	 * 根据目录和文件名，copy并且rename
	 * @param file 已上传的文件
	 * @param fileName 文件名称
	 * @param foldPath 文件的相对资源的目录结构
	 *
	 * @see StaticFolder
	*/
	public static String uploadWithPath(final KvConfig kvConfig, final File file, final String fileName) {
		File otherFile = upload(file, fileName);
		return getRelationPath(otherFile, kvConfig);
	}

	/**
	 * 获取服务器绝对路径
	 *
	 * @param config 配置文件
	 * @return 服务器绝对路径
	*/
	private static String getFileServer(final KvConfig config) {
		return config.getValue(ConfigKey.FILE_SERVER);
	}

	/**
	 * 获取相对路径
	 * <p>
	 * 相对于去除静态域名的路径
	 *
	 * @param file 文件
	 * @param kvConfig 配置对象
	 * @return 上传到服务器的文件相对路径，即排除静态域名的路径，比如:http://static.xxx.com/files/user/face/2014/7/21/1.jpg 最终返回的是 /files/user/face/2014/7/21/1.jpg
	*/
	public static String getRelationPath(final File file, final KvConfig kvConfig) {
		return getRelationPath(file.getPath(), kvConfig);
	}

	/**
	 * 获取相对路径
	 * <p>
	 * 相对于去除静态域名的路径
	 *
	 * @param file 文件
	 * @param kvConfig 配置对象
	 * @return 上传到服务器的文件相对路径，即排除静态域名的路径，比如:http://static.xxx.com/files/user/face/2014/7/21/1.jpg 最终返回的是 /files/user/face/2014/7/21/1.jpg
	*/
	public static String getRelationPath(final String path, final KvConfig kvConfig) {
		if (Util.isEmpty(path)) {
			return "";
		}
		File f = new File(getFileServer(kvConfig));
		return StringUtil.removeLeft(StringUtil.path2Web(path), StringUtil.path2Web(f.getPath()));
	}
}
