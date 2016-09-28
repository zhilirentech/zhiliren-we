/**
 * WordCount.java
 * org.zxc.hadoop.mapred.wordcount
 * Copyright (c) 2016, 北京科技有限公司版权所有.
*/

package org.zxc.hadoop.mapred.wordcount;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.zxc.hadoop.conf.HadoopConfig;

import com.hp.hpl.sparta.Text;

/**
 * TODO(这里用一句话描述这个类的作用)
 * <p>
 * TODO(这里描述这个类补充说明 – 可选)
 *
 * @author   朱晓川
 * @Date	 2016年8月27日 	 
 */
public class WordCount {

	public static void main(String[] args) throws Exception {
		Configuration conf = new Configuration();

		//创建作业任务
		Job job = Job.getInstance(conf, "wordcount");
		job.setJarByClass(WordCount.class);//入口
		job.setMapperClass(WordCountMapper.class); //map任务类
		job.setReducerClass(WordCountReducer.class);//reduce任务类

		//输出类型
		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(IntWritable.class);
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(IntWritable.class);

		//设置map数据输入
		FileInputFormat.setInputPaths(job, new Path(HadoopConfig.HDFS_URI + "/wordcout"));
		FileOutputFormat.setOutputPath(job, new Path(HadoopConfig.HDFS_URI + "/output"));

		/*
		 * 提交作业
		 * 可以使用submit()提交
		 * 也可以使用waitForCompletion提交
		 */
		boolean waitForCompletion = job.waitForCompletion(true);
		if (!waitForCompletion) {
			System.err.println("job fail");
		}
	}

}
