package com.xiaoka.template.common.constants;

/**
 * 通用常量
 * @author 朱晓川
 *
 */
public class CommonConstants {

	/**超级管理员*/
	public static final String SUPER_ADMIN = "ccigmall";

	/**数据状态:@see DataStatusEnum*/
	/**数据状态-启用中*/
	public static final Integer DATA_STATUS_VALID = 1;

	/**数据状态-冻结*/
	public static final Integer DATA_STATUS_INVALID = 0;

	/**数据状态-删除*/
	public static final Integer DATA_STATUS_DELETE = 2;

	/**销售渠道类型-代理-代码，不可更改*/
	public static final String SALES_CHANNEL_PROXY_TYPECODE = "0001";

	/**销售渠道类型-非代理-代码，不可更改*/
	public static final String SALES_CHANNEL_NONPROXY_TYPECODE = "0002";

	/**项目字符编码*/
	public static final String CHARACTER_ENCODING_PROJECT = "UTF-8";

	/**
	 * 验证码-session key
	 */
	public static final String CONFIRMCODE = "confirmcode";

}
