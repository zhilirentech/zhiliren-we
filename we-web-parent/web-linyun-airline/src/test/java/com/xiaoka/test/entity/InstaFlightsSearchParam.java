/**
 * InstaFlightsSearchParam.java
 * com.xiaoka.test.entity
 * Copyright (c) 2016, 北京科技有限公司版权所有.
*/

package com.xiaoka.test.entity;

import lombok.Data;

/**
 * TODO(这里用一句话描述这个类的作用)
 * <p>
 * TODO(这里描述这个类补充说明 – 可选)
 *
 * @author   朱晓川
 * @Date	 2016年11月15日 	 
 */
@Data
public class InstaFlightsSearchParam {

	/**
	 * 起飞机场(IATA3字代码),必须
	 */
	private String origin;

	/**
	 * 降落机场(IATA3字代码),必须
	 */
	private String destination;

	/**
	 * 出发日期,必须
	 */
	private String departuredate;

	/**
	 * 返程日期，可选 
	 */
	private String returndate;

	/**
	 * ，可选 
	 */
	private String includedcarriers;

	/**
	 * ，可选 
	 */
	private String excludedcarriers;

	/**
	 * ，可选 
	 */
	private String outboundflightstops;

	/**
	 * ，可选 
	 */
	private String inboundflightstops;

	/**
	 * ，可选 
	 */
	private String includedconnectpoints;

	/**
	 * ，可选 
	 */
	private String excludedconnectpoints;

	/**
	 * ，可选 
	 */
	private String outboundstopduration;

	/**
	 * ，可选 
	 */
	private String inboundstopduration;

	//TODO

}
