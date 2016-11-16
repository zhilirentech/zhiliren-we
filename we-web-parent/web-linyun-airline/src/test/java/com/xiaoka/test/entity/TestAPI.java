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

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.SocketTimeoutException;
import java.security.GeneralSecurityException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLException;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.conn.ConnectionPoolTimeoutException;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContextBuilder;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.conn.ssl.X509HostnameVerifier;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;
import org.nutz.log.Log;
import org.nutz.log.Logs;

import com.xiaoka.test.sign.Base64;

/**
 * 1,构造客户端ID
 * 形如:V1:mq2kolgs7tunkpe0:DEVCENTER:EXT
 * 2,Base64编码(Client_ID),Base64编码你的密码password 
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

	//打log用
	private static Log log = Logs.getLog(TestAPI.class);;

	/**
	 * API环境分为测试环境和生产环境，测试的时候使用测试环境
	 */
	private static final String test_environment = "https://api.test.sabre.com";

	private static final String prod_environment = "https://api.sabre.com";

	private static final String AUTH_URI = "/v2/auth/token";

	private static final String Client_ID = "V1:mq2kolgs7tunkpe0:DEVCENTER:EXT";

	private static final String Secret = "satULB47";

	private static final String contentType = "application/x-www-form-urlencoded";

	private static final String CHARSET = "UTF-8";

	private static final String access_token = "T1RLAQLg4m0XHuhQ97LhThcLl7RQKvKKlRDWP7z/jQUIAPTUHWY7XevTAADApSyBquywCFXpXvwilxDM9BpeRo7EHogqX/1lcfyOBikJcj7VlfxStQMTn0LSZ4BMPskVhpbf0XqYBrGwPnqo0Qo+Fd89dZUhHPU3BulLXPNv7SkTFenjz6BluL9uMDz+UVlx1fAYZm9IG6vqyq6ciwM4vcR/Hjv1PKy0xGdyCUNyKmYC8s+bYZKS8pbzSqPDxyxsCTv7y11u41ZuBrGHVrJaWc4LRk8PkXjBj6cPHBA6IpGKRtZ7G3ACGTF4070a";

	private static PoolingHttpClientConnectionManager connMgr;
	private static RequestConfig requestConfig;
	private static final int MAX_TIMEOUT = 10000;

	static {
		// 设置连接池  
		connMgr = new PoolingHttpClientConnectionManager();
		// 设置连接池大小  
		connMgr.setMaxTotal(100);
		connMgr.setDefaultMaxPerRoute(connMgr.getMaxTotal());

		RequestConfig.Builder configBuilder = RequestConfig.custom();
		// 设置连接超时  
		configBuilder.setConnectTimeout(MAX_TIMEOUT);
		// 设置读取超时  
		configBuilder.setSocketTimeout(MAX_TIMEOUT);
		// 设置从连接池获取连接实例的超时  
		configBuilder.setConnectionRequestTimeout(MAX_TIMEOUT);
		// 在提交请求之前 测试连接是否可用  
		configBuilder.setStaleConnectionCheckEnabled(true);

		//设置代理抓包
		requestConfig = configBuilder.build();
	}

	public static void main(String[] args) throws UnsupportedEncodingException {
		//		getAccessToken();
		flightTo();
	}

	/**
	 * 获取access_token
	 * @throws UnsupportedEncodingException 
	 * @throws Exception 
	 */
	public static String getAccessToken() throws UnsupportedEncodingException {
		String encodedCid = Base64.encode((Client_ID).getBytes(CHARSET));
		String encodedSecret = Base64.encode(Secret.getBytes(CHARSET));
		log.info("encodedCid:" + encodedCid);
		log.info("encodedSecret:" + encodedSecret);

		String authorization = Base64.encode((encodedCid + ":" + encodedSecret).getBytes(CHARSET));
		//		String authorization = "Basic " + credential;

		String authUrl = test_environment + AUTH_URI;

		String result = null;
		HttpPost httpPost = new HttpPost(authUrl);
		httpPost.addHeader("Authorization", "Basic " + authorization);//必须是第一个参数,而且Basic空格是必须的:(
		httpPost.addHeader("Content-Type", contentType);

		//得指明使用UTF-8编码，否则到API服务器XML的中文不能被成功识别
		StringEntity postEntity = new StringEntity("grant_type=client_credentials", CHARSET);
		httpPost.setEntity(postEntity);

		log.info("executing request " + httpPost.getRequestLine());
		Header[] heads = httpPost.getAllHeaders();
		for (Header h : heads) {
			log.info("header name[" + h.getName() + "] value[" + h.getValue() + "]");
		}

		result = httpsPost(httpPost);

		return result;
	}

	private static String httpsPost(HttpPost httpPost) {
		String result = "";
		try {
			//ssl连接,sabre接口必须走https
			CloseableHttpClient httpClient = HttpClients.custom().setSSLSocketFactory(createSSLConnSocketFactory())
					.setConnectionManager(connMgr).setDefaultRequestConfig(requestConfig).build();
			HttpResponse response = httpClient.execute(httpPost);

			int statusCode = response.getStatusLine().getStatusCode();
			log.info("statusCode:" + statusCode);

			HttpEntity entity = response.getEntity();
			result = EntityUtils.toString(entity, "UTF-8");
			log.info(result);
		} catch (ConnectionPoolTimeoutException e) {
			log.error("http get throw ConnectionPoolTimeoutException(wait time out)");

		} catch (ConnectTimeoutException e) {
			log.error("http get throw ConnectTimeoutException");

		} catch (SocketTimeoutException e) {
			log.error("http get throw SocketTimeoutException");

		} catch (Exception e) {
			log.error("http get throw Exception");

		} finally {
			httpPost.abort();
		}
		return result;
	}

	private static String httpsGet(HttpGet httpGet) {
		String result = "";
		try {
			//ssl连接,sabre接口必须走https
			CloseableHttpClient httpClient = HttpClients.custom().setSSLSocketFactory(createSSLConnSocketFactory())
					.setConnectionManager(connMgr).setDefaultRequestConfig(requestConfig).build();
			HttpResponse response = httpClient.execute(httpGet);

			int statusCode = response.getStatusLine().getStatusCode();
			log.info("statusCode:" + statusCode);

			HttpEntity entity = response.getEntity();
			result = EntityUtils.toString(entity, "UTF-8");
			log.info(result);
		} catch (ConnectionPoolTimeoutException e) {
			log.error("http get throw ConnectionPoolTimeoutException(wait time out)");

		} catch (ConnectTimeoutException e) {
			log.error("http get throw ConnectTimeoutException");

		} catch (SocketTimeoutException e) {
			log.error("http get throw SocketTimeoutException");

		} catch (Exception e) {
			log.error("http get throw Exception");

		} finally {
			httpGet.abort();
		}
		return result;
	}

	/**
	 * 1输入目的地直接查询(调试通过)
	 * GET /v1/shop/flights/cheapest/fares/{destination}
	 * 
	 * 2复杂查询
	 * A     /v1/shop/flights
	 * B     /v1/shop/flights/tags/{tagid}
	 * A接口指定参数enabletagging=true的时候会返回tagid
	 * 
	 * BJS   北京
	 * SDY   悉尼
	 */
	public static String flightTo() {
		String searchUrl = test_environment + "/v1/shop/flights?origin=PEK&destination=SYD&departuredate=2016-11-30";
		//		String searchUrl = test_environment + "/v1/shop/flights/cheapest/fares/BJS";

		String result = null;
		HttpGet httpget = new HttpGet(searchUrl);
		httpget.addHeader("Authorization", "Bearer " + access_token);

		log.info("executing request " + httpget.getRequestLine());
		Header[] heads = httpget.getAllHeaders();
		for (Header h : heads) {
			log.info("header name[" + h.getName() + "] value[" + h.getValue() + "]");
		}
		result = httpsGet(httpget);
		return result;
	}

	/** 
	 * 创建SSL安全连接 
	 * 
	 * @return 
	 */
	private static SSLConnectionSocketFactory createSSLConnSocketFactory() {
		SSLConnectionSocketFactory sslsf = null;
		try {
			SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(null, new TrustStrategy() {
				@Override
				public boolean isTrusted(X509Certificate[] chain, String authType) throws CertificateException {
					return true;
				}
			}).build();

			sslsf = new SSLConnectionSocketFactory(sslContext, new X509HostnameVerifier() {
				@Override
				public boolean verify(String arg0, SSLSession arg1) {
					return true;
				}

				@Override
				public void verify(String host, SSLSocket ssl) throws IOException {
				}

				@Override
				public void verify(String host, X509Certificate cert) throws SSLException {
				}

				@Override
				public void verify(String host, String[] cns, String[] subjectAlts) throws SSLException {
				}
			});
		} catch (GeneralSecurityException e) {
			e.printStackTrace();
		}
		return sslsf;
	}

}
