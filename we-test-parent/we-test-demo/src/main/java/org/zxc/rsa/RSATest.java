package org.zxc.rsa;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class RSATest {
	
	private static final String CHARSET = "UTF-8" ;
	
	private String publicK;   
	private String privateK;   
	
	/**
	 * 使用jdk生成RSA公钥和私钥，并进行签名和验证,
	 * 支付宝使用openssl的方式生成公钥和私钥，
	 * 目前有768位被破解的案例，因此使用1024size
	 */
	public static void main(String[] args) {
		
	}
	
	@Test  
    public void test() throws Exception {  
        System.err.println("公钥加密——私钥解密");  
        String inputStr = "abc";  
        byte[] data = inputStr.getBytes();  
  
        byte[] encodedData = RSA.encryptByPublicKey(data, publicK);  
  
        byte[] decodedData = RSA.decryptByPrivateKey(encodedData,privateK);  
  
        String outputStr = new String(decodedData);  
        System.err.println("加密前: " + inputStr + "\n\r" + "解密后: " + outputStr);  
        Assert.assertEquals(inputStr, outputStr); 
        
        System.err.println("私钥加密——公钥解密");  
        String content = "sign";   
        byte[] bytes = content.getBytes();  
  
        byte[] encData = RSA.encryptByPrivateKey(bytes, privateK);  
        byte[] decData = RSA.decryptByPublicKey(encData, publicK);
  
        String output = new String(decData);  
        System.err.println("加密前: " + content + "\n\r" + "解密后: " + output);  
        Assert.assertEquals(content, output); 
    }  
	
	@BeforeTest
	public void before(){
		try {
			KeyDto dto = RSA.getKeyPair() ;
			privateK = dto.getPrivateKey() ;
			publicK = dto.getPublicKey() ;
			System.out.println("privateKey:" + privateK);
			System.out.println("publicKey:" + publicK);
			
			//发送方使用私钥进行签名
			String content = "a=1&b=2" ;
			String sign = RSA.sign(content, privateK, CHARSET) ;
			System.out.println("sign:" + sign); 
			
			//接收方使用公钥进行签名验证
			boolean verify = RSA.verify(content, sign,publicK, CHARSET); 
			Assert.assertTrue(verify); 
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
