/**
 * XmlResultDT.java
 * com.uxuexi.core.web.view.support
 * Copyright (c) 2015, 北京聚智未来科技有限公司版权所有.
*/

package com.uxuexi.core.web.view.dt;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.Data;

/**
 * xml处理结果载体
 *
 * @author   庄君祥
 * @Date	 2015-5-19 	 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "root")
@Data
public class XmlResultDT {
	@XmlElement(required = true)
	private String result;
	@XmlElement(required = true)
	private String resultInfo;
}
