/**
 * BaseServiceMock.java
 * com.uxuexi.core.db.service
 * Copyright (c) 2015, 北京聚智未来科技有限公司版权所有.
*/

package com.uxuexi.core.db.mock.service;

import org.nutz.ioc.loader.annotation.IocBean;

import com.uxuexi.core.db.mock.entity.MockEntity4Base;
import com.uxuexi.core.db.service.EntityBaseService;

/**
 * TODO(这里用一句话描述这个类的作用)
 * <p>
 * TODO(这里描述这个类补充说明 – 可选)
 *
 * @author   庄君祥
 * @Date	 2015年10月14日 	 
 */
@IocBean(fields = { "dbDao" })
public class BaseMockService extends EntityBaseService<MockEntity4Base> {

}
