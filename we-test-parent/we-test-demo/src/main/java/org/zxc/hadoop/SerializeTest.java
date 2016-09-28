/**
 * Test.java
 * org.zxc.hadoop
 * Copyright (c) 2016, 北京聚智未来科技有限公司版权所有.
*/

package org.zxc.hadoop;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Writable;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * TODO(这里用一句话描述这个类的作用)
 * <p>
 * TODO(这里描述这个类补充说明 – 可选)
 *
 * @author   朱晓川
 * @Date	 2016年8月20日 	 
 */
public class SerializeTest {

	@Test
	public static void testSeraialize() throws IOException {
		IntWritable intWritable = new IntWritable(163);
		byte[] data = serailize(intWritable);
		Assert.assertEquals(data.length, 4);

		IntWritable newWritable = new IntWritable();
		deserailize(newWritable, data);
		Assert.assertEquals(newWritable.get(), 163);
	}

	public static byte[] deserailize(Writable writable, byte[] bytes) throws IOException {
		ByteArrayInputStream in = new ByteArrayInputStream(bytes);
		DataInputStream dataInput = new DataInputStream(in);
		writable.readFields(dataInput);
		dataInput.close();
		return bytes;
	}

	public static byte[] serailize(Writable writable) throws IOException {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		DataOutputStream dataOutput = new DataOutputStream(out);
		writable.write(dataOutput);
		dataOutput.close();
		return out.toByteArray();

	}

}
