package com.xiaoka.game.common.module;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Filters;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.baidu.ueditor.ActionEnter;
import com.uxuexi.core.common.util.Util;
import com.xiaoka.game.common.constants.CommonConstants;
import com.xiaoka.game.common.service.UploadService;
import com.xiaoka.game.common.util.UploaderUtil;


@IocBean
@Filters
@At("/ueditor")
public class UeditorUploadModule {

	protected Logger logger = LoggerFactory.getLogger(UeditorUploadModule.class);
	
	@Inject
	private UploadService qiniuUploadService ;  

	@At
	@Ok("json")
	public String imageUpload(HttpServletRequest request, HttpServletResponse response, @Param("action")String action) throws Exception {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		if (Util.isEmpty(action)) {
			return "ueditor:action should not be null";
		}

		//1单张上传
		String result = "";
		if (action.equals("uploadimage")) {
			Uploader uploader = new Uploader(request,qiniuUploadService);
			uploader.upload();
			String origFileName = uploader.getOriginalName();
			String url = uploader.getUrl();

			//判断文件名是否为空。为空set null值
			if (null != url) {
				result = "{\"state\": \"SUCCESS\",\"title\": \"\",\"original\":\"" + origFileName + "\",\"type\": \""
						+ uploader.getType() + "\",\"url\":\"" + CommonConstants.IMAGES_SERVER_ADDR + url + "\",\"size\": \"" + uploader.getSize() + "\"}";
			} else {
				result = "{\"state\": \"Server is Error!\"}";
			}
		}

		//2复制黏贴多张图片
		else if (action.equals("catchimage")) {
			String[] source = request.getParameterValues("linkUp[]");

			StringBuffer buffer = new StringBuffer();
			buffer.append("{\"state\": \"SUCCESS\", list: [");
			for (int i = 0; i < source.length; i++) {
				try {
					URL url = new URL(source[i]);

					HttpURLConnection uc = (HttpURLConnection) url.openConnection();
					uc.setDoInput(true);//设置是否要从 URL 连接读取数据,默认为true  
					uc.connect();

					String fileExt = UploaderUtil.getFileExt(source[i]);
					String newUrl = qiniuUploadService.uploadImage(uc.getInputStream(), fileExt, null);

					if (i == source.length - 1) {

						buffer.append("{\"state\": \"SUCCESS\",\"title\": \"\",\"source\":\"" + source[i]
								+ "\",\"type\": \"" + fileExt + "\",\"url\":\"" + CommonConstants.IMAGES_SERVER_ADDR + newUrl + "\",\"size\": \""
								+ uc.getContentLength() + "\"}]}");
					} else {
						buffer.append("{\"state\": \"SUCCESS\",\"title\": \"\",\"source\":\"" + source[i]
								+ "\",\"type\": \"" + fileExt + "\",\"url\":\"" + CommonConstants.IMAGES_SERVER_ADDR + newUrl + "\",\"size\": \""
								+ uc.getContentLength() + "\"},");
					}
				} catch (Exception e) {
					logger.error("文件上传失败！" + e.getMessage(), e);
				}
			}
			result = buffer.toString();
		}
		//3，读取后台配置信息
		else if (action.equals("config")) {
			try {
				request.setCharacterEncoding("utf-8");
				response.setHeader("Content-Type", "text/html");
				String rootPath = request.getRealPath("/");
				result = new ActionEnter(request, rootPath).exec();
			} catch (IOException e) {
				logger.error("ueditor:" + e.getMessage(), e);
			}
		}
		return result;
	}

}
