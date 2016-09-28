/**
 * ImageUtil.java
 * cn.vko.core.utils
 * Copyright (c) 2011, 北京微课创景教育科技有限公司版权所有.
*/

package com.uxuexi.core.common.util;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.nutz.lang.Files;

import com.uxuexi.core.common.interfaces.IZoom;

/**
 * 图片工具类
 * <p>
 * 用于图片剪切和缩放
 * @author   庄君祥
 * @Date	 2011-11-8	 
 * @version  5.0.2
 */
public final class ImageUtil {
	/**
	 * 裁剪页面
	 * @param srcFile 源图片
	 * @param destFile 目的图片
	 * @param x x位置
	 * @param y y位置
	 * @param width 宽度
	 * @param height 高度
	 * @param formatName 图片格式化类别
	 */
	public static void cut(final File srcFile, final String destFile, final int x, final int y, final int width,
			final int height, final String formatName) {
		BufferedImage image = read(srcFile);

		// 如果源图片的宽度或高度小于目标图片的宽度或高度，则直接返回出错
		if (image.getWidth() < width || image.getHeight() < height) {
			throw ExceptionUtil.pEx("图片宽度或高度小于设定的宽高！");
		}
		BufferedImage destImage = image.getSubimage(x, y, width, height);
		write(destImage, destFile, formatName);
	}

	/**
	 * 读取图片文件至流
	 * @param srcFile 图片文件路径
	 * @return 图片流
	 */
	private static BufferedImage read(final File srcFile) {
		BufferedImage image = null;
		try {
			image = ImageIO.read(srcFile);
		} catch (IOException e) {
			throw ExceptionUtil.pEx("图片读取错误！", e);
		}
		if (image == null) {
			throw ExceptionUtil.pEx("图片读取错误！");
		}
		return image;
	}

	/**
	 * 根据传进来的类型压缩成不同规格
	 * <p>
	 * en必需是实现IZoom，而且必需是对应的枚举，如果不是枚举的话，也不会进行
	 *
	 * @param file 文件
	 * @param en 枚举类型
	*/
	public static <T extends IZoom> void zoom(final File file, final Class<T> en) {
		if (en == null) {
			return;
		}
		if (!en.isEnum()) {
			return;
		}
		String destFile = file.getAbsolutePath();
		T[] arr = en.getEnumConstants();
		int indexOf = destFile.indexOf(".");
		String absoluteFile = destFile.substring(0, indexOf);
		String suffix = destFile.substring(indexOf + 1);
		for (T one : arr) {
			if (one == null) {
				continue;
			}
			zoom(file, absoluteFile + "_" + one.type() + "." + suffix, one.width(), one.height(), suffix);
		}
	}

	/**
	 * 图片变焦
	 * @param srcFile 源文件路径
	 * @param destFile 变焦后的文件路径
	 * @param width 变焦后的宽度
	 * @param height 变焦后的高度
	 * @param formatName 模板名
	 */
	public static void zoom(final File srcFile, final String destFile, final int width, final int height,
			final String formatName) {
		BufferedImage image = read(srcFile);
		BufferedImage destImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB); // 缩放图像
		Image zoomedImage = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
		Graphics g = destImage.getGraphics();
		g.drawImage(zoomedImage, 0, 0, null); // 绘制目标图
		g.dispose();
		write(destImage, destFile, formatName);
	}

	/**
	 * 将图片流保存成文件
	 * @param image 流
	 * @param fileName 目标文件路径
	 * @param formatName 模板名
	 */
	private static void write(final BufferedImage image, final String fileName, final String formatName) {
		// 写文件
		FileOutputStream out = null;
		try {
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			ImageIO.write(image, formatName, bos);// 输出到bos
			Files.createFileIfNoExists(fileName);
			out = new FileOutputStream(fileName);
			out.write(bos.toByteArray());
		} catch (IOException e) {
			throw ExceptionUtil.pEx("将图片流保存成文件失败", e);
		} finally {
			try {
				if (out != null) {
					out.close();
				}
			} catch (IOException e) {
				throw ExceptionUtil.pEx("将图片流保存成文件失败", e);
			}
		}
	}
}
