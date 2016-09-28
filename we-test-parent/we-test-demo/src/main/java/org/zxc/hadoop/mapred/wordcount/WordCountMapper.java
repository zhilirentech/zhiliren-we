/**
 * WordCountMapper.java
 * org.zxc.hadoop.mapred.wordcount
 * Copyright (c) 2016, 北京科技有限公司版权所有.
*/

package org.zxc.hadoop.mapred.wordcount;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import com.google.common.base.Splitter;

/**
 * TODO(这里用一句话描述这个类的作用)
 * <p>
 * TODO(这里描述这个类补充说明 – 可选)
 *
 * @author   朱晓川
 * @Date	 2016年8月27日 	 
 */
public class WordCountMapper extends Mapper<LongWritable, Text, Text, IntWritable> {

	@Override
	protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, Text, IntWritable>.Context context)
			throws IOException, InterruptedException {
		String string = value.toString();

		Iterable<String> words = Splitter.on(" ").split(string);
		for (String word : words) {
			context.write(new Text(word), new IntWritable(1));
		}

	}

}
