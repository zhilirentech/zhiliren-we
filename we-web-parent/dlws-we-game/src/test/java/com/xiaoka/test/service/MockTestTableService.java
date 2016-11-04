/**
 * MockTestTableService.java
 * com.xiaoka.test.service
 * Copyright (c) 2016, 北京聚智未来科技有限公司版权所有.
*/

package com.xiaoka.test.service;

import org.nutz.ioc.loader.annotation.IocBean;

import com.xiaoka.game.common.service.BaseService;
import com.xiaoka.test.entity.TestEntity;

/**
 * TODO(这里用一句话描述这个类的作用)
 * <p>
 * TODO(这里描述这个类补充说明 – 可选)
 *
 * @author   wangsen
 * @Date	 2016年8月25日 	 
 */
@IocBean(name = "mockTestTableService")
public class MockTestTableService extends BaseService<TestEntity> {

}
