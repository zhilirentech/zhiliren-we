/**
 * IZoom.java
 * com.uxuexi.core.web.util.support
 * Copyright (c) 2014, 北京聚智未来科技有限公司版权所有.
*/

package com.uxuexi.core.common.interfaces;

/**
 * 标识不同规格的图片
 * <p>
 * 必需使用枚举来实现这个接口
 *
 * @author   庄君祥
 * @Date	 2014-7-21 	 
 */
public interface IZoom {
	/**
	 * 宽度
	 * <p>
	 * 单位是像素
	 *
	 * @return 像素
	*/
	public int width();

	public int height();

	public String type();
}
