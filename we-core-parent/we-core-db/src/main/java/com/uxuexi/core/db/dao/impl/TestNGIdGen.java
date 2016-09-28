/**
 * TestNGIdGen.java
 * com.uxuexi.core.db.dao.impl
 * Copyright (c) 2014, 北京聚智未来科技有限公司版权所有.
*/

package com.uxuexi.core.db.dao.impl;

import com.uxuexi.core.common.util.RandomUtil;
import com.uxuexi.core.db.dao.IIdGen;

/**
 * 用于单元测试的主键生成策略
 *
 * @author   庄君祥
 * @Date	 2014-6-10 	 
 */
public class TestNGIdGen implements IIdGen {

	@Override
	public long getId() {
		int randomInt = 10000;
		return System.currentTimeMillis() * randomInt + RandomUtil.nextInt(randomInt);
	}

}
