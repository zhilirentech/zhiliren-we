/**
 * 
 * 
 * 
 * 
 * 授权:
 * 遵循OAuth 2.0开放授权
 * POST /v2/auth/token
 * account:brandorsabre
 * passwd:zxcv1234
 * 
 */
package com.xiaoka.test.entity;


/**
 * 1,构造客户端ID
 * 形如:V1:mq2kolgs7tunkpe0:DEVCENTER:EXT
 * 2,Base64编码(Client_ID:Secret),Base64编码你的密码password 
 *   用冒号拼接，得到形如encodedId:encodedPasswd的值，对此值再次进行Base64编码得到用于获取
 *   access_token的凭据
 * 3，获取access_token
 * 
 * 4,使用access_token调用查询航班的API
 * 
 * 国际航空3字母代码
 * SYX(三亚)
 * BKK(曼谷)
 * 例:GET https://api.sabre.com/v1/shop/flights/cheapest/fares/DFW
 * @author   朱晓川
 * @Date	 2016年10月26日 	 
 */
public class TestAPI {

	/**
	 * API环境分为测试环境和生产环境，测试的时候使用测试环境
	 */
	private static final String test_environment = "https://api.test.sabre.com";

	private static final String prod_environment = "https://api.sabre.com";

	private static final String AUTH_URI = "/v2/auth/token";

	private static final String Client_ID = "V1:mq2kolgs7tunkpe0:DEVCENTER:EXT";

	private static final String Secret = "satULB47";

	private static final String PASSWD = "zxcv1234";

	private static final String contentType = "application/x-www-form-urlencoded";

	private static final String payload = "grant_type=client_credentials";

	/**
	 * 获取access_token
	 */
	public static String getAccessToken() {

		return null;
	}

	/**
	 * GET /v1/shop/flights/cheapest/fares/{destination} 
	 * 
	 * 
	 */
	public static void flightTo() {

	}

}
