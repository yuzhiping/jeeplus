package com.jeeplus.weixin.utils;

import java.io.IOException;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

public class SendsmsUtil {

	
	 private static String Url = "http://106.ihuyi.cn/webservice/sms.php?method=Submit";
 
	 
     public static void Send(String mobile,String content) {
		
		HttpClient client = new HttpClient(); 
		PostMethod method = new PostMethod(Url); 
			
		//client.getParams().setContentCharset("GBK");		
		client.getParams().setContentCharset("UTF-8");
		method.setRequestHeader("ContentType","application/x-www-form-urlencoded;charset=UTF-8");
 
		//System.out.println(mobile);
 

		NameValuePair[] data = {//提交短信
			    new NameValuePair("account","cf_botann"), //"用户名"
			    new NameValuePair("password", MD5Util.Md5("BTKJ0307") ), //密码可以使用明文密码或使用32位MD5加密
			    new NameValuePair("mobile", mobile), //"手机号码"
			    new NameValuePair("content", content),
		};
		
		method.setRequestBody(data);		
		
		
		try {
			client.executeMethod(method);	
			
			String SubmitResult =method.getResponseBodyAsString();
					
			//System.out.println(SubmitResult);

			Document doc = DocumentHelper.parseText(SubmitResult); 
			Element root = doc.getRootElement();


			String code = root.elementText("code");	
			String msg = root.elementText("msg");	
			String smsid = root.elementText("smsid");	
			
			
			System.out.println(code);
			System.out.println(msg);
			System.out.println(smsid);
						
			 if("2".equals(code)){
				System.out.println("短信提交成功");
			}
			
		} catch (HttpException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		
	}
	
     public static void main(String[] args) {
    		SendsmsUtil.Send("18046049715","您的验证码是：" + 123456 + "。请不要把验证码泄露给其他人。");
 
	}
}
