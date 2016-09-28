/**
 * ImageTranUtil.java
 * com.uxuexi.core.web.util
 * Copyright (c) 2014, 北京聚智未来科技有限公司版权所有.
*/

package com.uxuexi.core.web.util;

import com.uxuexi.core.web.config.ConfigKey;
import com.uxuexi.core.web.config.KvConfig;

/**
 * 图片转换工具
 *
 * @author   庄君祥
 * @Date	 2014-12-11 	 
 */
public class ImageTranUtil {

	/**
	 * 给图片前面添加静态域名
	 *
	 * @param kvConfig 配置文件
	 * @param src 需要替换的字符串
	 * @return 已经替换的字符串
	*/
	public static String addDomain(KvConfig kvConfig, String src) {
		return src.replaceAll("src\\s*=\\\"([^h][^t][^t][^p][^:][^/][^/].*?)\\\"",
				"src=\\\"" + kvConfig.getValue(ConfigKey.STATIC) + "$1\\\"");
	}

}
