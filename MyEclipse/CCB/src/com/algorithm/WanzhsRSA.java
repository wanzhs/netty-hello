package com.algorithm;

import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.interfaces.DSAPrivateKey;
import java.security.interfaces.DSAPublicKey;
import java.security.interfaces.ECPrivateKey;
import java.security.interfaces.ECPublicKey;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import org.apache.commons.codec.binary.Hex;





public class WanzhsRSA {
	private static String src="wanzhs nanyan couple rsa";
	private static String src1="wanzhs nanyan couple rsa";
	public static void main(String[] args) {
//		jdkRSA();
//		jdkDSA();
		jdkECDSA();
	}
	public static void jdkECDSA() {
		try {
			//初始化密钥
			KeyPairGenerator keyPairGenerator=KeyPairGenerator.getInstance("EC");
			keyPairGenerator.initialize(256);
			KeyPair keyPair=keyPairGenerator.generateKeyPair();
			ECPublicKey ecPublicKey=(ECPublicKey)keyPair.getPublic();
			ECPrivateKey ecPrivateKey=(ECPrivateKey)keyPair.getPrivate();
			
			// 执行签名
			PKCS8EncodedKeySpec pkcs8EncodedKeySpec=new PKCS8EncodedKeySpec(ecPrivateKey.getEncoded());
			KeyFactory keyFactory=KeyFactory.getInstance("EC");
			PrivateKey privateKey=keyFactory.generatePrivate(pkcs8EncodedKeySpec);
			Signature signature=Signature.getInstance("SHA1withECDSA");
			signature.initSign(privateKey);
			signature.update(src.getBytes());
			byte[] result=signature.sign();
			System.out.println("jdk ecdsa sign:"+Hex.encodeHexString(result));
			System.out.println("jdk ecdsa sign:"+new String(Hex.encodeHex(result)));

			
			// 验证签名
			X509EncodedKeySpec x509EncodedKeySpec=new X509EncodedKeySpec(ecPublicKey.getEncoded());
			keyFactory=KeyFactory.getInstance("EC");
			PublicKey publicKey=keyFactory.generatePublic(x509EncodedKeySpec);
			signature=Signature.getInstance("SHA1withECDSA");
			signature.initVerify(publicKey);
			signature.update(src1.getBytes());
			boolean bool=signature.verify(result);
			System.out.println("jdk ecdsa verify:"+bool);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
	public static void jdkDSA() {
		try {
			// 初始化密钥
			KeyPairGenerator keyPairGenerator=KeyPairGenerator.getInstance("DSA");
			keyPairGenerator.initialize(512);
			KeyPair keyPair=keyPairGenerator.generateKeyPair();
			DSAPublicKey dsaPublicKey=(DSAPublicKey)keyPair.getPublic();
			DSAPrivateKey dsaPrivateKey=(DSAPrivateKey)keyPair.getPrivate();

			// 执行签名
			PKCS8EncodedKeySpec pkcs8EncodedKeySpec=new PKCS8EncodedKeySpec(dsaPrivateKey.getEncoded());
			KeyFactory keyFactory=KeyFactory.getInstance("DSA");
			PrivateKey privateKey=keyFactory.generatePrivate(pkcs8EncodedKeySpec);
			Signature signature=Signature.getInstance("SHA1withDSA");
			signature.initSign(privateKey);
			signature.update(src.getBytes());
			byte[] result=signature.sign();
			System.out.println("jdk dsa sign:"+Hex.encodeHexString(result));
			System.out.println(result.length);
			
			// 验证签名
			X509EncodedKeySpec x509EncodedKeySpec=new X509EncodedKeySpec(dsaPublicKey.getEncoded());
			keyFactory=KeyFactory.getInstance("DSA");
			PublicKey publicKey=keyFactory.generatePublic(x509EncodedKeySpec);
			signature=Signature.getInstance("SHA1withDSA");
			signature.initVerify(publicKey);
			signature.update(src1.getBytes());
			boolean bool=signature.verify(result);
			System.out.println("jdk dsa verify:"+bool);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	public static void jdkRSA() {
		try {
			//初始化密钥
			KeyPairGenerator keyPairGenerator=KeyPairGenerator.getInstance("RSA");
			keyPairGenerator.initialize(512);
			KeyPair keyPair=keyPairGenerator.generateKeyPair();
			RSAPublicKey rsaPublicKey=(RSAPublicKey)keyPair.getPublic();
			RSAPrivateKey rsaPrivateKey=(RSAPrivateKey)keyPair.getPrivate();
			
			//执行签名
			PKCS8EncodedKeySpec pkcs8EncodedKeySpec=new PKCS8EncodedKeySpec(rsaPrivateKey.getEncoded());
			KeyFactory keyFactory=KeyFactory.getInstance("RSA");
			PrivateKey privateKey=keyFactory.generatePrivate(pkcs8EncodedKeySpec);
			Signature signature=Signature.getInstance("MD5withRSA");
			signature.initSign(privateKey);
			signature.update(src.getBytes());
			byte[] result=signature.sign();
			System.out.println(privateKey+":"+rsaPrivateKey);
			System.out.println("jdk rsa sign : " + Hex.encodeHexString(result));
			
			//验证签名
			X509EncodedKeySpec x509EncodedKeySpec=new X509EncodedKeySpec(rsaPublicKey.getEncoded());
			keyFactory=KeyFactory.getInstance("RSA");
			PublicKey publicKey=keyFactory.generatePublic(x509EncodedKeySpec);
			signature=Signature.getInstance("MD5withRSA");
			signature.initVerify(publicKey);
			signature.update(src1.getBytes());
			boolean bool=signature.verify(result);
			System.out.println("jdk rsa verify: "+bool);
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
