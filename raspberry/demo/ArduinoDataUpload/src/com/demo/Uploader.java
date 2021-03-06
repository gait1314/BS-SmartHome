package com.demo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

public class Uploader {

	public static void uplaod(String value){
		try {
			System.out.println("upload...");
			
			//url
			String url = "http://119.29.36.20/dgutlink/api/device/21/sensor/13/datapoint";
			//post请求
			HttpPost httpPost = new HttpPost(url);
			//添加请求头
			httpPost.addHeader("APIKEY", "6000000620879617");
			//设置请求参数
			//HttpParams params = new BasicHttpParams();
			//params.setIntParameter("value", 123);
			//httpPost.setParams(params);
			//JSONObject paramJson=new JSONObject();
		   // paramJson.put("value","321");
			//httpPost.setEntity(new StringEntity(paramJson.toString(), Charset.forName("UTF-8")));
			
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("value", value);
			
			List<NameValuePair> pairs = null;
		    if (params != null && !params.isEmpty()) {
		        pairs = new ArrayList<NameValuePair>(params.size());
		        for (String key : params.keySet()) {
		            pairs.add(new BasicNameValuePair(key, params.get(key).toString()));
		        }
		    }
		    if (pairs != null && pairs.size() > 0) {
		        httpPost.setEntity(new UrlEncodedFormEntity(pairs, "UTF-8"));
		    }
			httpPost.setEntity(new UrlEncodedFormEntity(pairs, "UTF-8"));
		
			//执行请求
			HttpClient client = HttpClients.createDefault();
			HttpResponse response =  client.execute(httpPost);
			//打印结果
			System.out.println(EntityUtils.toString(response.getEntity()));
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
