package com.zigbeee;

import java.util.Arrays;

import com.demo.MyPortClient;

public class Main {

	public static void main(String[] args) {
		
		Zigbee zigbee = new Zigbee();
		MyPortClient client = new MyPortClient();
		zigbee.setPortClient(client);
		zigbee.init();
		zigbee.start();
		
		//testZBCG1();
	}
	
	public static void testZBCG1(){
		
		System.out.print("chanlistCfg : ");
		print(ZBCG.chanlistCfg(ZBCG.DEFAULT_CHANNEL));
		
		System.out.print("PANIDCfg : ");
		print(ZBCG.PANIDCfg(ZBCG.DEFAULT_PANID));
		
		System.out.print("deviceTypeCfg : ");
		print(ZBCG.deviceTypeCfg(ZBCG.DEVICE_TYPE_COORDINATOR));
		
		System.out.print("appRegister : ");
		ZBAppReg reg = new ZBAppReg();
		reg.setEndpoint((byte) 0x78);
		print(ZBCG.appRegister(reg));
	
	}
	public static void testZBCG(){
		byte[] bs1 = {0x00, 0x01, 0x02, 0x03};
		byte[] bs2 = ZBCG.chanlistCfg(bs1);
		System.out.print("chanlistCfg : ");
		print(bs2);
		
		byte[] bs3 = {0x01, 0x02};
		byte[] bs4 = ZBCG.PANIDCfg(bs3);
		System.out.print("PANIDCfg : ");
		print(bs4);
		
		byte[] bs5 = ZBCG.deviceTypeCfg((byte) 0x01);
		System.out.print("deviceTypeCfg : ");
		print(bs5);
		
		ZBAppReg reg = new ZBAppReg();
		byte[] bs6 = ZBCG.appRegister(reg);
		System.out.print("appRegister : ");
		print(bs6);
		
		ZBPacketSend packet = new ZBPacketSend();
		byte[] bs01 = {0x01, 0x02};
		packet.setDstAddr(bs01);
		packet.setDstEndpoint((byte)0x03);
		packet.setSrcEndpoint((byte)0x04);
		byte[] bs02 = {0x05, 0x06};
		packet.setClusterID(bs02);
		packet.setOptions((byte)0x07);
		packet.setRadius((byte)0x08);
		packet.setTransID((byte)0x09);
		byte[] bs03 = {0x01, 0x02, 0x03};
		packet.setData(bs03);
		byte[] bs7 = ZBCG.packetSend(packet);
		System.out.print("packetSend : ");
		print(bs7);
	}
	
	public static void print(byte[] bs){
		for(byte b : bs){
			System.out.printf("%x", b);
			System.out.print(',');
		}
		System.out.println();
	}

}
