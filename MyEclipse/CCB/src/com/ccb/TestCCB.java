package com.ccb;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.LinkedHashMap;
import java.util.Map;


/**
 * 互联网被扫请求加密算法
 * @author John
 * 商户编号：105701000001081
 * 柜台号：004165094
 * 操作员号：105701000001081-123
 *
 */
public class TestCCB {
	public static void main(String[] args) throws Exception{
		//验证入口
		URL url=new URL("https://ibsbjstar.ccb.com.cn/CCBIS/B2CMainPlat_00_ENPAY");
		//待加密字符串
		Map<String, Object> srcParams=new LinkedHashMap<>();
		srcParams.put("MERFLAG", "1");
		srcParams.put("MERCHANTID", "105701000001081");
		srcParams.put("POSID", "004165094");
//		srcParams.put("TERMNO1", "");
//		srcParams.put("TERMNO2", "");
		srcParams.put("BRANCHID", "110000000");
		srcParams.put("ORDERID", "105000000000000123456");
		srcParams.put("QRCODE", "CCB9991234567");
		srcParams.put("AMOUNT", "0.01");
		srcParams.put("TXCODE", "PAY100");
		//待传递属性
		Map<String, Object> properties=new LinkedHashMap<>();
		properties.put("MERCHANTID", "105701000001081");
		properties.put("POSID", "004165094");
		properties.put("BRANCHID", "110000000");
		
		//商户密钥后30位

		String pubkey = "fba31890669ad519bac275b9020111";
		
		testCCB(url,pubkey, srcParams,properties);
	}
	
	public static String testCCB(URL url,String pubkey,Map<String,Object> srcParams,Map<String,Object> properties) throws Exception{
		//获取待加密字符串
		StringBuilder postData=new StringBuilder();
		for(Map.Entry<String, Object> param:srcParams.entrySet()) {
			if(postData.length()!=0) postData.append("&");
			postData.append(param.getKey());
			postData.append("=");
			postData.append(String.valueOf(param.getValue()));
			
		}
		System.out.println("数据加密前："+postData);
		
		//数据加密对象获取
		COM.CCB.EnDecryptAlgorithm. MCipherEncryptor ccbEncryptor = 
				new COM.CCB.EnDecryptAlgorithm. MCipherEncryptor (pubkey);
		//执行加密
		String ccbParam = ccbEncryptor. doEncrypt(postData.toString());
		System.out.println("数据加密后："+ccbParam);
		
		//获取建行请求对象连接
		HttpURLConnection connection=(HttpURLConnection)url.openConnection();
		//设置请求方式，默认GET
		connection.setRequestMethod("POST");
		//设置请求属性
		connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
		for(Map.Entry<String, Object> param:properties.entrySet()) {
			connection.setRequestProperty(URLEncoder.encode(param.getKey(), "UTF-8"),URLEncoder.encode( param.getValue().toString(), "UTF-8"));			
		}
		connection.setRequestProperty(URLEncoder.encode("ccbParam", "UTF-8"),URLEncoder.encode(ccbParam, "UTF-8"));
		// 连接
		connection.connect();
		
		//请求结果处理
		String msg = "";
		int code=connection.getResponseCode();
		System.out.println("响应码："+code);
		if (code==200) {
			BufferedReader reader=new BufferedReader(new InputStreamReader(connection.getInputStream(),"GB2312"));
			String line;
			while((line=reader.readLine())!=null) {
				msg +=line+"\n";
			}
			reader.close();
			System.out.println(msg);
		}
		
		return msg;
	}

}
