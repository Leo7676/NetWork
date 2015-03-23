package com.leo;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;

public class SendMsg_webchinese {
	public boolean sendMessage(String cellphone,String contents){
		HttpClient client = new HttpClient();
		PostMethod post = new PostMethod("http://gbk.sms.webchinese.cn");
		post.addRequestHeader("Content-Type",
				"application/x-www-form-urlencoded;charset=gbk");// 在头文件中设置转码
		NameValuePair[] data = { new NameValuePair("Uid", "leo76"),
				new NameValuePair("Key", "f9460892efb82ae834a1"),
				new NameValuePair("smsMob", cellphone),
				new NameValuePair("smsText", contents) };
		post.setRequestBody(data);

		try {
			client.executeMethod(post);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		Header[] headers = post.getResponseHeaders();
		int statusCode = post.getStatusCode();
//		System.out.println("statusCode:" + statusCode);
		for (Header h : headers) {
			System.out.println(h.toString());
		}
		String result=null;
		try {
			result = new String(post.getResponseBodyAsString().getBytes(
					"gbk"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
//		System.out.println(result); // 打印返回消息状态

		post.releaseConnection();
		if(statusCode==1){
			return true;
		}
		return false;
	}


}