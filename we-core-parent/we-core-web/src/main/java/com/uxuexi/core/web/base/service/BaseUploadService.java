package com.uxuexi.core.web.base.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Map;

import com.uxuexi.core.common.util.FileUtil;
import com.uxuexi.core.common.util.Util;
import com.uxuexi.core.web.chain.support.WapResult;

public abstract class BaseUploadService implements UploadService {

	/**
	 * 上传图片
	 * @param inStream   文件流  
	 * @param file_ext_name   文件扩展名
	 * @param dest_filename   指定文件名
	 * @return
	 */
	@Override
	public abstract String uploadImage(InputStream inStream, String file_ext_name, String dest_filename);

	/**用于ajax上传图片，返回地址*/
	@Override
	public Map<String, Object> ajaxUploadImage(File image) {
		InputStream fis = null;
		try {
			if (Util.isEmpty(image)) {
				return WapResult.error("请选择文件", null);
			}

			String fileName = image.getName();
			String suffix = FileUtil.getSuffix(fileName);
			fis = new FileInputStream(image);

			String url = uploadImage(fis, suffix, null);
			return WapResult.success("上传成功", url);
		} catch (Exception e) {
			e.printStackTrace();
			return WapResult.error("上传失败", null);
		}
	}
}
