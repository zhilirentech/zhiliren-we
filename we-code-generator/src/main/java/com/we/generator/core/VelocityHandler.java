package com.we.generator.core;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.nio.charset.Charset;

import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.nutz.lang.Files;
import org.nutz.lang.Streams;
import org.nutz.log.Log;
import org.nutz.log.Logs;

/**
 * 代码生成器
 * <p>
 * 负责读取Velocity模板生成代码
 */
public class VelocityHandler {

	private static final Log log = Logs.get();

	public void writeToFile(VelocityContext context, String tplPath, File file, boolean cover) throws IOException {
		if (file.exists() && !cover) {
			log.debug("file " + file + " exists, skipped");
			return;
		}

		String content = writeContent(context, tplPath);
		file.getParentFile().mkdirs();
		Files.write(file, content.getBytes(Charset.forName("utf8")));
	}

	/**得到模板输出的内容*/
	private String writeContent(VelocityContext context, String tplPath) throws IOException {
		StringWriter writer = new StringWriter();

		//读取模板内容
		log.debug("load velocity template from : [" + tplPath + "]");
		InputStream ins = ClassLoader.getSystemResourceAsStream(tplPath);
		byte[] data = Streams.readBytes(ins);
		String template = new String(data, Charset.forName("utf8"));

		VelocityEngine engine = new VelocityEngine();
		engine.setProperty("runtime.references.strict", false);
		engine.init();
		engine.evaluate(context, writer, "generator", template);
		return writer.toString();
	}
}
