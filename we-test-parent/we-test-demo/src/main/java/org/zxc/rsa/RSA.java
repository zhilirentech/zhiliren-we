
package org.zxc.rsa;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.Cipher;

/**
 * 
 * 1 发送方使用私钥进行签名.
 * 2 接收方使用公钥进行签名验证.
 * 3 因此作为接收方需要得到发送方的公钥(支付宝上传公钥).
 * <p>
 * 注:RSA加密明文最大长度117字节，解密要求密文最大长度为128字节
 *   因为加密算法的padding部分（11位）也需要一起进行加密,比如PKCS
 * @author 朱晓川
 *
 */
public class RSA{
	
	/**秘钥生成算法*/
	public static final String KEY_ALGORITHM = "RSA";  

	
	/**
	 * 签名算法,可以使用MD5withRSA或者SHA1WithRSA
	 * 支付宝选择使用SHA1WithRSA
	 */
	public static final String  SIGN_ALGORITHMS = "SHA1WithRSA";
	
	
	 /** *//** 
     * RSA最大加密明文大小 
     */  
    private static final int MAX_ENCRYPT_BLOCK = 117; 
    
    private static final int KEY_SIZE = 1024;  
      
    /** *//** 
     * RSA最大解密密文大小 
     */  
    private static final int MAX_DECRYPT_BLOCK = 128;
    
    /**
     * 获取RSA 公钥和私钥对
     */
    public static KeyDto getKeyPair(){ 
    	KeyDto dto = new KeyDto() ;
    	KeyPairGenerator generator;
		try {
			generator = KeyPairGenerator.getInstance(KEY_ALGORITHM);
			generator.initialize(KEY_SIZE); 
			KeyPair keyPair = generator.generateKeyPair() ;   
			PrivateKey privateKey = keyPair.getPrivate() ; 
			PublicKey publicKey = keyPair.getPublic(); 
			
			dto.setPrivateKey(Base64.encode(privateKey.getEncoded()));
			dto.setPublicKey(Base64.encode(publicKey.getEncoded()));
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return dto ;
    }
	
	// ================================================签名====================================================
	
	/**
	* RSA签名
	* <p>
	* 参数私钥为Base64编码,返回的签名值也是Base64编码后的
	* @param content 待签名数据
	* @param privateKey 私钥
	* @param input_charset 编码格式
	* @return 签名值
	*/
	public static String sign(String content, String privateKey, String input_charset)
	{
        try 
        {
        	PKCS8EncodedKeySpec priPKCS8 	= new PKCS8EncodedKeySpec( Base64.decode(privateKey) ); 
        	KeyFactory keyf 				= KeyFactory.getInstance(KEY_ALGORITHM);
        	PrivateKey priKey 				= keyf.generatePrivate(priPKCS8);

            java.security.Signature signature = java.security.Signature.getInstance(SIGN_ALGORITHMS);
            signature.initSign(priKey);
            signature.update( content.getBytes(input_charset) );
            byte[] signed = signature.sign();
            
            return Base64.encode(signed);
        }
        catch (Exception e) 
        {
        	e.printStackTrace();
        }
        
        return null;
    }
	
	/**
	* RSA验签名检查
	* @param content 待签名数据
	* @param sign 签名值
	* @param public_key 公钥
	* @param input_charset 编码格式
	* @return 布尔值
	*/
	public static boolean verify(String content, String sign, String public_key, String input_charset)
	{
		try 
		{
			KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
	        byte[] encodedKey = Base64.decode(public_key);
	        PublicKey pubKey = keyFactory.generatePublic(new X509EncodedKeySpec(encodedKey));

		
			java.security.Signature signature = java.security.Signature
			.getInstance(SIGN_ALGORITHMS);
		
			signature.initVerify(pubKey);
			signature.update( content.getBytes(input_charset) );
		
			boolean bverify = signature.verify( Base64.decode(sign) );
			return bverify;
			
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		
		return false;
	}
	
	 //================================================加密====================================================
	
	/**
	 * 公钥加密
	 * @param content  BASE64密文
	 * @param key      BASE64公钥
	 */
	public static byte[] encryptByPublicKey(String content, String key)throws Exception {   
        Key publicKey = getPublicKey(key) ;   
        return edcrypt(content, publicKey, Cipher.ENCRYPT_MODE);  
    }
	
	/**
	 * 公钥加密
	 * @param data  密文
	 * @param key   BASE64公钥
	 */
	public static byte[] encryptByPublicKey(byte[] data, String key)throws Exception {  
        Key publicKey = getPublicKey(key) ;   
        return edcrypt(data, publicKey, Cipher.ENCRYPT_MODE);  
    }
	  
    /**
     * 私钥加密
     * @param content BASE64密文
     * @param key  BASE64私钥
     */
    public static byte[] encryptByPrivateKey(String content, String key)throws Exception { 
        Key privateKey = getPrivateKey(key);   
        return edcrypt(content, privateKey, Cipher.ENCRYPT_MODE);  
    }
    
    /**
     * 私钥加密
     * @param data 密文
     * @param key  BASE64私钥
     */ 
    public static byte[] encryptByPrivateKey(byte[] data, String key)throws Exception { 
        Key privateKey = getPrivateKey(key);   
        return edcrypt(data, privateKey, Cipher.ENCRYPT_MODE);  
    } 
    
  //================================================解密====================================================
    
    /**
     * 公钥解密
     * @param data 密文
     * @param key  BASE64公钥
     */
    public static byte[] decryptByPublicKey(byte[] data, String key)throws Exception {   
    	Key publicKey = getPublicKey(key) ; 
    	return edcrypt(data, publicKey, Cipher.DECRYPT_MODE);   
    } 
    
    /**
     * 公钥解密
     * @param content BASE64密文 
     * @param key  BASE64公钥
     */
    public static byte[] decryptByPublicKey(String content, String key)throws Exception {   
    	Key publicKey = getPublicKey(key) ; 
    	return edcrypt(content, publicKey, Cipher.DECRYPT_MODE);   
    }
    
    /**
     * 私钥解密
     * @param data 密文
     * @param key  BASE64私钥
     */
    public static byte[] decryptByPrivateKey(byte[] data, String key)throws Exception {   
    	Key privateKey = getPrivateKey(key); 
    	return edcrypt(data, privateKey, Cipher.DECRYPT_MODE);   
    }
    
    /**
     * 私钥解密
     * @param content BASE64密文
     * @param key  BASE64私钥
     */
    public static byte[] decryptByPrivateKey(String content, String key)throws Exception {    
    	Key privateKey = getPrivateKey(key); 
    	return edcrypt(content, privateKey, Cipher.DECRYPT_MODE);   
    }
    
    //private methods
    
    /**
     * 对BASE64字符串数据进行加密/解密 
     */
    private static byte[] edcrypt(String content, Key key,int opmode) throws Exception {
        byte[] data = Base64.decode(content) ;
        return edcrypt(data, key, opmode); 
    }
	
	/**
	* 对二进制数据进行加密/解密
	* <p>
	* @param data 明文/密文
	* @param key 公钥/私钥
	* @param opmode 加密/解密模式
	* @return 加密或者解密后的二进制数据
	*/
	private static byte[] edcrypt(byte[] data, Key key,int opmode) throws Exception {
        Cipher cipher = Cipher.getInstance(KEY_ALGORITHM);
        cipher.init(opmode, key);

        InputStream ins = new ByteArrayInputStream(data);
        ByteArrayOutputStream writer = new ByteArrayOutputStream(); 
        
        //RSA加密明文最大长度117字节，解密要求密文最大长度为128字节
        int len = MAX_ENCRYPT_BLOCK ; 
        if(Cipher.DECRYPT_MODE == opmode){
        	len = MAX_DECRYPT_BLOCK ;
        }
        byte[] buf = new byte[len];
        int bufl;

        while ((bufl = ins.read(buf)) != -1) {
            byte[] block = null;

            if (buf.length == bufl) {
                block = buf;
            } else {
                block = new byte[bufl];
                for (int i = 0; i < bufl; i++) {
                    block[i] = buf[i];
                }
            }
            writer.write(cipher.doFinal(block));
        }
        return writer.toByteArray();
    }

	
	/**
	* 得到私钥
	* @param key 密钥字符串（经过base64编码）
	* @throws Exception
	*/
	private static PrivateKey getPrivateKey(String key) throws Exception {
		byte[] keyBytes;
		keyBytes = Base64.decode(key);
		
		PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);
		KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
		PrivateKey privateKey = keyFactory.generatePrivate(keySpec);
		
		return privateKey;
	}
	
	/**
	* 得到公钥
	* @param key 密钥字符串（经过base64编码）
	* @throws Exception
	*/
	private static PublicKey getPublicKey(String key) throws Exception {
		byte[] keyBytes;
		keyBytes = Base64.decode(key);
		
		X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(keyBytes);  
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);  
        PublicKey publicKey = keyFactory.generatePublic(x509KeySpec);  
		
		return publicKey;
	}
	
}
