package com.ccb;

import java.net.URL;
import java.util.LinkedHashMap;
import java.util.Map;


/**
 * 互联网被扫请求加密算法
 * @author John
 *
 */
public class SocketPay {
	public static void main(String[] args) throws Exception{
		//验证入口
		URL url=new URL("https://ibsbjstar.ccb.com.cn/CCBIS/B2CMainPlat_00_ENPAY");
		//待加密字符串
		Map<String, Object> src=new LinkedHashMap<>();
		src.put("POSID", "004165094");
		src.put("BRANCHID", "105701000001081-123");
		src.put("ORDERID", "A01201008160001");
		src.put("PAYMENT", "1.00");
		src.put("CURCODE", "01");
		src.put("REMARK1", "");
		src.put("REMARK2", "");
		src.put("SUCCESS", "Y");
		String pkey="30819c300d06092a864886f70d010101050003818a003081860281807f7e06318918e4b40e0544af801b065e85b182866bdc1d2b2400c31c9ae64016ec349ba7682352b19372898c693895bbd84e71f03a32c59ee6e39011db3e80b567a413b4ecd76ebc0e82834606fdb0d595264bead63791b650d17cc9d5a4885dc913294d020bdc011e5c14f1d5d60d585528b3c29e1f6280394120a99ac6d979020111";
		String sign="0dc3052ac8050056a835110f8ce47c20aa475cba6003655c5fb0dd60414aef71473a0eb4e14943df19f4aa065aa2322afae7971be25a6ec941f7c4a8fb9fefc74d7584eed7388be0c4d1f7cf05855eb9fb940c3d9c08e067fcb9226804b196532087cca8c1fad2478dda08f694b7a9712ed756bcd648f4f23dcb384a6d22db3b";
		checkSign(pkey, sign, src);
	}
	public static void  checkSign(String pkey,String sign,Map<String, Object> src) {
		CCBSign.RSASig rsaSig=new CCBSign.RSASig();
		rsaSig.setPublicKey(pkey);
		StringBuilder srcParams = new StringBuilder();
		for(Map.Entry<String, Object> param:src.entrySet()) {
			if(srcParams.length()!=0) srcParams.append("&");
			srcParams.append(param.getKey());
			srcParams.append("=");
			srcParams.append(String.valueOf(param.getValue()));
			
		}
		System.out.println("参数："+srcParams.toString());
		System.out.println(rsaSig.generateSigature(srcParams.toString()));
		
		boolean result=rsaSig.verifySigature(sign, srcParams.toString());
		System.out.println("verify:"+result);
		
	}
}