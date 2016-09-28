/**
 * MkdirTest.java
 * org.zxc.hadoop.dfs
 * Copyright (c) 2016, 北京科技有限公司版权所有.
*/

package org.zxc.hadoop.dfs;

import java.net.URI;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.BlockLocation;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hdfs.DistributedFileSystem;
import org.apache.hadoop.hdfs.protocol.DatanodeInfo;
import org.testng.annotations.Test;

import com.uxuexi.core.common.util.ConvertUtil;

/**
 * 测试HDFS文件操作
 *
 * @author   朱晓川
 * @Date	 2016年8月25日 	 
 */
public class FSTest {

	/**
	 * HDFS的地址，确保与core-site.xml中fs.default.name的地址一致
	 */
	private static final String HDFS_URI = "hdfs://nn.hadoop.com:9000";

	//	/**
	//	 * 安全模式可能会运行失败
	//	 * 可执行:bin/hadoop dfsadmin -safemode leave
	//	 * 离开安全模式
	//	 */
	//	@Test
	//	public void testMkdir() throws Exception {
	//		FileSystem fs = FileSystem.get(new URI(HDFS_URI), new Configuration());
	//		fs.mkdirs(new Path("/upload"));
	//		fs.close();
	//	}

	//	@Test
	//	public void testUpload() throws Exception {
	//		FileSystem fs = FileSystem.get(new URI(HDFS_URI), new Configuration());
	//		FSDataOutputStream out = fs.create(new Path("/upload/test"));
	//		Files.copy(new File("D:/dev/word.txt"), out);
	//		//		IOUtils.copyBytes(new FileInputStream(new File("D:/dev/word.txt")), out, 2048,true);
	//		fs.close();
	//	}
	//
	@Test
	public void testDelete() throws Exception {
		FileSystem fs = FileSystem.get(new URI(HDFS_URI), new Configuration());
		fs.delete(new Path("/output"), true);
		fs.close();
	}

	@Test
	public void testNodeCount() throws Exception {
		FileSystem fs = FileSystem.get(new URI(HDFS_URI), new Configuration());
		DistributedFileSystem dfs = ConvertUtil.cast(fs, DistributedFileSystem.class);
		DatanodeInfo[] dataNodeStats = dfs.getDataNodeStats();
		for (DatanodeInfo info : dataNodeStats) {
			System.out.println(info.getInfoAddr());
		}

		//取文件的块存储信息
		Path path = new Path("/upload/test");
		FileStatus fileStatus = dfs.getFileStatus(path);
		BlockLocation[] fileBlockLocations = dfs.getFileBlockLocations(fileStatus, 0, fileStatus.getLen());
		for (BlockLocation blockLocation : fileBlockLocations) {
			String[] hosts = blockLocation.getHosts();
			System.out.println(hosts[0]);
		}

		fs.close();
	}

}
