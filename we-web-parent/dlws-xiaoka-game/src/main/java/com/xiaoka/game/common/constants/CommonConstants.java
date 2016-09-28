package com.xiaoka.game.common.constants;


/**
 * 通用常量
 * @author 朱晓川
 *
 */
public class CommonConstants {

	/**超级管理员*/
	public static final String SUPER_ADMIN = "admin";

	/**数据状态:@see DataStatusEnum*/
	/**数据状态-启用中*/
	public static final Integer DATA_STATUS_VALID = 1;

	/**数据状态-冻结*/
	public static final Integer DATA_STATUS_INVALID = 0;

	/**数据状态-删除*/
	public static final Integer DATA_STATUS_DELETE = 2;

	/**项目字符编码*/
	public static final String CHARACTER_ENCODING_PROJECT = "UTF-8";

	/**
	 * 验证码-session key
	 */
	public static final String CONFIRMCODE = "confirmcode";
	/**
	 * 微信顾客用户Id-session key
	 */
	public static final String CUSTOMER_ID = "customerId";
	/**
	 * 微信顾客用户openId-session key
	 */
	public static final String WEIXIN_OPEN_ID = "openId";
	/**
	 * 用户Id-session key
	 */
	public static final String T_USER_KEY = "tUserKey";
	
	/**万能登录验证码*/
	public static final String UNIVERSAL_CODE = "0225" ;
	
	/**
	 * 图片服务器地址
	 */
	public static final String IMAGES_SERVER_ADDR = "http://images.xiaoka360.com/";
	
	/**手机号正则表达式*/
	public static final String REGEX_TELEPHONE = "^(0|86|17951)?(13[0-9]|15[012356789]|17[678]|18[0-9]|14[57])[0-9]{8}$" ;
	/**机器人openId前缀*/
	public static final String XIAOKA_ROBOT_PREFIX = "xiaoka_robot_prefix_";
}
