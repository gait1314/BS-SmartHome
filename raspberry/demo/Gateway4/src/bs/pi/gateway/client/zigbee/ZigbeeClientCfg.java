package bs.pi.gateway.client.zigbee;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Properties;

import com.test.Debugger;

import bs.pi.gateway.assist.Tool;

public class ZigbeeClientCfg {
	
	public final static String K_CHANNEL = "channel";
	public final static String K_PANID = "PANID";
	public final static String K_DEVICE_TYPE = "deviceType";
	
	public final static String K_CLUSTER_ID = "clusterID";
	public final static String K_OPTIONS = "options";
	public final static String K_RADIUS = "radius";
	
	public final static String K_OTHER_ZIGBEE_COUNT = "oZigbeeCount";
	public final static String K_OTHER_ZIGBEE_IEEEADDR = "oZigbeeIEEEAddr";
	public final static String K_APP_ID_S = "appIDs";
	
	public final static byte DEVICE_TYPE_COORDINATOR = 0x00;
	public final static byte DEVICE_TYPE_ROUTE = 0x01;
	public final static byte DEVICE_TYPE_END_DEVICE = 0x02;
	
	public final static byte[] DEFAULT_CHANNEL = {0x00, 0x08, 0x00, 0x00};
	public final static byte[] DEFAULT_PANID = {0x34, 0x12};
	public final static byte DEFAULT_DEVICE_TYPE = DEVICE_TYPE_ROUTE;
	
	public final static byte[] DEFAULT_CLUSTER_ID = {0x00, 0x00};
	public final static byte DEFAULT_OPTIONS = 0x00;
	public final static byte DEFAULT_RADIUS = 0x00;
	
	private byte[] channel;	//信道
	private byte[] panID;	//网络号
	private byte deviceType;	//zigbee设备类型
	private ZigbeeAppReg appReg;

	private byte[] clusterID;
	private byte options;
	private byte radius;

	private ArrayList<ZigbeeInfo> zigbeeInfoList;
	
	public ZigbeeClientCfg(){
		channel = DEFAULT_CHANNEL;
		panID = DEFAULT_PANID;
		deviceType = DEFAULT_DEVICE_TYPE;
		
		clusterID = DEFAULT_CLUSTER_ID;
		options = DEFAULT_OPTIONS;
		radius = DEFAULT_RADIUS;
		
		appReg = new ZigbeeAppReg();
	}
	
	public ZigbeeClientCfg(String cfgPath) throws Exception{
		loadCfg(cfgPath);
	}
	
	public void loadCfg(String cfgPath) throws Exception{
		Properties properties = new Properties();
		properties.load(new FileInputStream(cfgPath));
		channel = Tool.strToBytes(properties.getProperty(K_CHANNEL));
		panID = Tool.strToBytes(properties.getProperty(K_PANID));
		deviceType = Tool.strToByte(properties.getProperty(K_DEVICE_TYPE));
		
		clusterID = Tool.strToBytes(properties.getProperty(K_CLUSTER_ID));
		options = Tool.strToByte(properties.getProperty(K_OPTIONS));
		radius = Tool.strToByte(properties.getProperty(K_RADIUS));
		
		appReg = new ZigbeeAppReg(properties);
		
		int count = Integer.parseInt(properties.getProperty(K_OTHER_ZIGBEE_COUNT));
		if(count > 0){
			zigbeeInfoList = new ArrayList<ZigbeeInfo>();
			for(int i=1;i<(count+1);i++){
				byte[] IEEEAddr = Tool.strToBytes(properties.getProperty(K_OTHER_ZIGBEE_IEEEADDR+i));
				String appIDsStr = properties.getProperty(K_APP_ID_S+i);
				String[] appIDsStrs = appIDsStr.split(",");
				ArrayList<Byte> appIDs = new ArrayList<Byte>();
				for(int j=0;j<appIDsStrs.length;j++){
					byte appID = Tool.strToByte(appIDsStrs[j]);
					appIDs.add(appID);
				}
				ZigbeeInfo info = new ZigbeeInfo(IEEEAddr, null, appIDs);
				zigbeeInfoList.add(info);
			}
		}
	}
	
	public byte[] getChannel() {
		return channel;
	}

	public void setChannel(byte[] channel) {
		this.channel = channel;
	}

	public byte[] getPanID() {
		return panID;
	}

	public void setPanID(byte[] panID) {
		this.panID = panID;
	}

	public byte getDeviceType() {
		return deviceType;
	}

	public void setDeviceType(byte deviceType) {
		this.deviceType = deviceType;
	}

	public ZigbeeAppReg getAppReg() {
		return appReg;
	}

	public void setAppReg(ZigbeeAppReg appReg) {
		this.appReg = appReg;
	}

	public byte[] getClusterID() {
		return clusterID;
	}

	public void setClusterID(byte[] clusterID) {
		this.clusterID = clusterID;
	}

	public byte getOptions() {
		return options;
	}

	public void setOptions(byte options) {
		this.options = options;
	}

	public byte getRadius() {
		return radius;
	}

	public void setRadius(byte radius) {
		this.radius = radius;
	}

	public ArrayList<ZigbeeInfo> getZigbeeInfoList() {
		return zigbeeInfoList;
	}

	public void setZigbeeInfoList(ArrayList<ZigbeeInfo> zigbeeInfoList) {
		this.zigbeeInfoList = zigbeeInfoList;
	}
}
