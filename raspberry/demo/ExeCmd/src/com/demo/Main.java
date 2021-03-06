package com.demo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import com.port.MyPortClient;
import com.zigbeee.ZBPacketSend;
import com.zigbeee.Zigbee;



public class Main {

	public static void main(String[] args) throws Exception {
		
		deleteCmd();
		sendCmd();
//		getCmd();
		//String[] strs = "123456".split("-");
		//System.out.println(strs.length);
		
		/*
		Zigbee zigbee = new Zigbee();
		MyPortClient client = new MyPortClient();
		zigbee.setPortClient(client);
		zigbee.init();
		zigbee.start();
		
		HttpCmdGetter getter = new HttpCmdGetter();
		getter.setUrl("http://119.29.36.20/dgutlink/api/device/21/command");
		getter.setApiKey("6000000620879617");
		
		while(true){
			System.out.println("begin get cmd");
			String[] results = getter.getHttpCmd();
			if(results == null || results.length != 2){
				System.out.println("app msg format error");
			}else{
				System.out.println("got a good cmd");
				System.out.print(results[0]+"-"+results[1]+"\n");
				ZBPacketSend packet = new ZBPacketSend();
				byte[] bs1 = {(byte) 0xff, (byte) 0xff};
				packet.setDstAddr(bs1);
				byte b = HexStringToBinary(results[0])[0];
				packet.setDstEndpoint(b);
				packet.setSrcEndpoint((byte)0x78);
				byte[] bs2 = {(byte) 0x00, (byte) 0x00};
				packet.setClusterID(bs2);
				packet.setOptions((byte)0x00);
				packet.setRadius((byte)0x00);
				packet.setTransID((byte)0x00);
				packet.setData(HexStringToBinary(results[1]));
				zigbee.sendPacket(packet);
				System.out.println("zigbee send a packet");
			}
			
			Thread.currentThread().sleep(2500);
		}*/
	}
	
	public static byte[] HexStringToBinary(String hexString){
		
		String hexStr =  "0123456789ABCDEF";
		
		//hexString的长度对2取整，作为bytes的长度
		int len = hexString.length()/2;
		byte[] bytes = new byte[len];   
		byte high = 0;//字节高四位
		byte low = 0;//字节低四位

		for(int i=0;i<len;i++){
			//右移四位得到高位
			high = (byte)((hexStr.indexOf(hexString.charAt(2*i)))<<4);
			low = (byte)hexStr.indexOf(hexString.charAt(2*i+1));
			bytes[i] = (byte) (high|low);//高地位做或运算
		}
		return bytes;
	}
	
	public static void sendCmd(){
		try {
			//url
			String url = "http://119.29.36.20/dgutlink/api/device/21/command";
			//post请求
			HttpPost httpPost = new HttpPost(url);
			//添加请求头
			httpPost.addHeader("APIKEY", "6000000620879617");
		/*	
			Map<String, Object> params = new HashMap<String, Object>();
			JSONObject msg = new JSONObject();
			msg.put("cmd", "TestCmd");
			msg.put("appID", 0xf1);
			params.put("command", msg.toString());
			
			List<NameValuePair> pairs = new ArrayList<NameValuePair>(1);
			pairs.add(new BasicNameValuePair("command", msg.toString()));*/
			
			List<NameValuePair> pairs = new ArrayList<NameValuePair>(1);
			pairs.add(new BasicNameValuePair("command", "closeSwitch1"));
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
	
	public static void getCmd(){
		try{
			//url
			String url = "http://119.29.36.20/dgutlink/api/device/21/command";
			//post请求
			HttpGet httpPost = new HttpGet(url);
			//添加请求头
			httpPost.addHeader("APIKEY", "6000000620879617");
			
			//执行请求
			HttpClient client = HttpClients.createDefault();
			HttpResponse response =  client.execute(httpPost);
			//打印结果
			System.out.println(EntityUtils.toString(response.getEntity()));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void deleteCmd(){
		try{
			//url
			String url = "http://119.29.36.20/dgutlink/api/device/21/command";
			//post请求
			HttpDelete httpDelete = new HttpDelete(url);
			//添加请求头
			httpDelete.addHeader("APIKEY", "6000000620879617");
			
			//执行请求
			HttpClient client = HttpClients.createDefault();
			HttpResponse response =  client.execute(httpDelete);
			//打印结果
			System.out.println(EntityUtils.toString(response.getEntity()));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}