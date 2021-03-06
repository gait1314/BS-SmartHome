package bs.pi.gateway.client.http;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Properties;

public class HttpClientCfg {
	
	public final static String KEY_SERVICE_URL = "serviceUrl";
	public final static String KEY_DEVICE_ID = "deviceID";
	public final static String KEY_API_KEY = "apiKey";
	public final static String KEY_GET_DEVICE_CMD_TASK_INTERVAL = "getDeviceCmdTaskInterval";
	
	private String serviceUrl;
	private String deviceID;
	private String apiKey;
	private int getCmdTaskInterval;
	
	public HttpClientCfg(){
		
	}
	
	public HttpClientCfg(String cfgPath) throws Exception{
		loadCfg(cfgPath);
	}
	
	public void loadCfg(String cfgPath) throws Exception{
		Properties properties = new Properties();
		properties.load(new FileInputStream(cfgPath));
		serviceUrl = properties.getProperty(KEY_SERVICE_URL);
		deviceID = properties.getProperty(KEY_DEVICE_ID);
		apiKey = properties.getProperty(KEY_API_KEY);
		getCmdTaskInterval = Integer.parseInt(properties.getProperty(KEY_GET_DEVICE_CMD_TASK_INTERVAL));
	}

	public String getServiceUrl() {
		return serviceUrl;
	}
	public void setServiceUrl(String serviceUrl) {
		this.serviceUrl = serviceUrl;
	}
	public String getDeviceID() {
		return deviceID;
	}
	public void setDeviceID(String deviceID) {
		this.deviceID = deviceID;
	}
	public String getApiKey() {
		return apiKey;
	}
	public void setApiKey(String apiKey) {
		this.apiKey = apiKey;
	}

	public int getGetCmdTaskInterval() {
		return getCmdTaskInterval;
	}

	public void setGetCmdTaskInterval(int getCmdTaskInterval) {
		this.getCmdTaskInterval = getCmdTaskInterval;
	}
	
}
