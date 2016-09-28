/**
 * XmlView.java
 * cn.vko.webfjcm.view
 * Copyright (c) 2013, 北京微课创景教育科技有限公司版权所有.
*/

package com.uxuexi.core.web.view;

import java.io.ByteArrayOutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.nutz.lang.Mirror;
import org.nutz.mvc.View;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.uxuexi.core.common.util.BeanUtil;
import com.uxuexi.core.common.util.ExceptionUtil;
import com.uxuexi.core.common.util.Util;
import com.uxuexi.core.web.util.JaxbUtil;
import com.uxuexi.core.web.util.RequestUtil;
import com.uxuexi.core.web.view.dt.XmlResultDT;

/**
 * 将对象转换为XML视图
 * <p>
 * 只是为在线辅导工具提供服务，如果需要其他的xml，请自行实现
 * 
 * @author   zhuangjunxiang
 * @author   wangsen
 * @Date	 2013-8-26 	 
 */
public class XmlTransferView implements View {
	private static final Logger logger = LoggerFactory.getLogger(XmlTransferView.class);

	@Override
	public void render(final HttpServletRequest req, final HttpServletResponse resp, final Object obj) throws Throwable {
		if (null == obj) {
			throw ExceptionUtil.pEx("参数为null");
		}
		//TODO 如果它不是javabean，则抛异常，unsupportExceitpion
		Object o = obj;
		Mirror<Object> me = Mirror.me(o);
		try {
			//如果是字符串
			if (obj instanceof String) {
				//构建下个结果bean
				XmlResultDT r = new XmlResultDT();
				r.setResult("Success");
				r.setResultInfo(String.valueOf(obj));
				o = r;
			} else {
				//TODO 添加属性，增加判断
				if (BeanUtil.contain(o, "result")) {
					me.setValue(o, "result", "Success");
				}
				if (BeanUtil.contain(o, "resultInfo")) {
					me.setValue(o, "resultInfo", "成功");
				}
				//如果是一个javaBean，给它设置result和resultInfo
			}
		} catch (Exception e) {
			//如果是一个javaBean，给它设置result和resultInfo
			me.setValue(o, "result", "Error");
			me.setValue(o, "resultInfo", e.getMessage());
			logger.error(e.getMessage());

		}

		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		JaxbUtil.marshal(Util.first(o).getClass(), o, outputStream);
		logger.debug("返回的xml内容" + outputStream);

		String content = new String(outputStream.toByteArray(), "UTF-8");
		RequestUtil.safeWrite(resp, content, "text/xml");
	}
}
