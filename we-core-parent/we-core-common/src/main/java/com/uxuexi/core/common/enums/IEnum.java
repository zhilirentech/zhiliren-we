/**
 * IEnum.java
 * com.uxuexi.common.enums
 * Copyright (c) 2014, 北京聚智未来科技有限公司版权所有.
*/

package com.uxuexi.core.common.enums;

import com.uxuexi.core.common.util.EnumUtil;

/**
 * 自己实现的enum接口
 * <p>
 * 标识枚举，方便获取和转换枚举
 * 
 * @author   庄君祥
 * @Date	 2014-4-3 
 * @see 	 EnumUtil
 */
public interface IEnum {
	/**
	 * 获取唯一标示
	 *
	 * @return 唯一标示
	 */
	public String key();

	/**
	 * 获取显示值
	 *
	 * @return 显示值
	 */
	public String value();
}
