/**
 * JaxbUtil.java
 * cn.vko.jaxb.util
 * Copyright (c) 2013, 北京微课创景教育科技有限公司版权所有.
*/

package com.uxuexi.core.web.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Writer;

import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.PropertyException;
import javax.xml.bind.Unmarshaller;

import org.nutz.lang.stream.StringInputStream;
import org.nutz.mvc.Mvcs;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.uxuexi.core.common.util.ExceptionUtil;

/**
 * xml与JavaBean转换工具类
 * 
 * @author   赵立伟
 * @author   庄君祥
 * @Date	 2013-8-22 	 
 * @version  5.1.0
 */
public class JaxbUtil {
	/**
	* Logger for this class
	*/
	private static final Logger logger = LoggerFactory.getLogger(JaxbUtil.class);

	private JaxbUtil() {
		throw new UnsupportedOperationException();
	}

	/**
	 * 初始化序列化工具
	 *
	 * @param jc 容器
	 * @return 工具
	 * @throws JAXBException 初始化出错
	 * @throws PropertyException 配置参数出错
	 */
	private static Marshaller initMarshaller(final JAXBContext jc) throws JAXBException, PropertyException {
		Marshaller m = jc.createMarshaller();
		m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		m.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
		return m;
	}

	/**
	 * 初始化jaxb容器
	 *
	 * @param classesToBeBound 对象的构造工厂
	 * @return 容器
	 * @throws JAXBException 初始化出错
	 * @throws DataException 参数为null
	 */
	private static JAXBContext initContext(final Class<?> classesToBeBound) throws JAXBException {
		if (classesToBeBound == null) {
			throw ExceptionUtil.pEx("初始化jaxb容器时，对象的类型为null");
		}
		return JAXBContext.newInstance(classesToBeBound);
	}

	/**
	 * 将xml流反序列化成对象
	 *
	 * @param classesToBeBound 对象的构造工厂
	 * @param xmlStream xml流
	 * @return 对象
	 * @throws JAXBException 反序列化出错
	 */
	private static Object unmarshal(final Class<?> classesToBeBound, final InputStream xmlStream) throws JAXBException {
		JAXBContext jc = initContext(classesToBeBound);
		Unmarshaller u = jc.createUnmarshaller();
		return u.unmarshal(xmlStream);
	}

	/**
	 * 将xml流反序列化成对象
	 *
	 * @param classesToBeBound 对象的构造工厂
	 * @param content 反序列化内容
	 * @return 对象
	 * @throws JAXBException 反序列化出错
	 */
	public static Object unmarshal(final Class<?> classesToBeBound, final String content) throws JAXBException {
		InputStream xmlStream = new StringInputStream(content);
		logger.debug("unmarshal(Class<?>, String) - InputStream xmlStream=" + xmlStream); //$NON-NLS-1$
		return unmarshal(classesToBeBound, xmlStream);
	}

	/**
	 * 将对象序列化成xml流，并写入OutputStream
	 * <p>
	 * <font color='red'><b>慎用!!!!````<br/>
	 * 切记只能有web项目中运用，因为他必须经过nutFiler才会有注入</b></font>
	 * 
	 * @param classesToBeBound 对象的构造工厂
	 * @param obj 序列化前的对象
	 * @throws JAXBException 序列化出错
	 * @throws IOException 
	 */
	public static void marshal(final Class<?> classesToBeBound, final Object obj) throws JAXBException, IOException {
		marshal(classesToBeBound, obj, Mvcs.getResp());
	}

	/**
	 * 将对象序列化成xml流，并写入OutputStream
	 *
	 * @param classesToBeBound 对象的构造工厂
	 * @param obj 序列化前的对象
	 * @param response HttpServletResponse对象
	 * @throws JAXBException 序列化出错
	 * @throws IOException 
	 */
	public static void marshal(final Class<?> classesToBeBound, final Object obj, final HttpServletResponse response)
			throws JAXBException, IOException {
		JAXBContext jc = initContext(classesToBeBound);
		Marshaller m = initMarshaller(jc);
		m.marshal(obj, response.getOutputStream());
	}

	/**
	 * 将对象序列化成xml流，并写入OutputStream
	 *
	 * @param classesToBeBound 对象的构造工厂
	 * @param obj 序列化前的对象
	 * @param stream 列化的xml流
	 * @throws JAXBException 序列化出错
	 */
	public static void marshal(final Class<?> classesToBeBound, final Object obj, final OutputStream stream)
			throws JAXBException {
		JAXBContext jc = initContext(classesToBeBound);
		Marshaller m = initMarshaller(jc);
		m.marshal(obj, stream);
	}

	/**
	 * 将对象序列化成xml流，并写入Writer
	 *
	 * @param classesToBeBound 对象的构造工厂
	 * @param obj 序列化前的对象
	 * @param writer 写入流
	 * @throws JAXBException 序列化出错
	 */
	public static void marshal(final Class<?> classesToBeBound, final Object obj, final Writer writer)
			throws JAXBException {
		JAXBContext jc = initContext(classesToBeBound);
		Marshaller m = initMarshaller(jc);
		m.marshal(obj, writer);
	}
}
