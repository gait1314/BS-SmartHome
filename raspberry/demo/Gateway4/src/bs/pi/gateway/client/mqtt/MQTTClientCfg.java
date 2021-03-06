package bs.pi.gateway.client.mqtt;

import java.io.FileInputStream;
import java.util.Properties;

import org.fusesource.mqtt.client.QoS;
import org.fusesource.mqtt.client.Topic;

public class MQTTClientCfg {

	public final static String KEY_HOST = "host";
	public final static String KEY_PORT = "port";
	public final static String KEY_USERNAME = "username";
	public final static String KEY_PASSWORD = "password";
	public final static String KEY_TOPICS = "topics";
	public final static String KEY_QOSES = "qoses";
	
	private String host;
	private int port;
	private String username;
	private String password;
	private Topic[] topics;
	
	public MQTTClientCfg(String cfgPath) throws Exception{
		Properties properties = new Properties();
		properties.load(new FileInputStream(cfgPath));
		host = properties.getProperty(KEY_HOST);
		port = Integer.parseInt(properties.getProperty(KEY_PORT));
		username = properties.getProperty(KEY_USERNAME);
		password = properties.getProperty(KEY_PASSWORD);
		
		String[] topicsStr = properties.getProperty(KEY_TOPICS).split(",");
		String[] qosesStr = properties.getProperty(KEY_QOSES).split(",");
		topics = new Topic[topicsStr.length];
		for(int i=0; i<topicsStr.length; i++){
			String str = "0";
			if(i < qosesStr.length)
				str = qosesStr[i];
			int qosInt = Integer.parseInt(str.trim());
			QoS qos;
			if(qosInt == 2)
				qos = QoS.EXACTLY_ONCE;
			else if(qosInt == 1)
				qos = QoS.AT_LEAST_ONCE;
			else {
				qos = QoS.AT_MOST_ONCE;
			}
			topics[i] = new Topic(topicsStr[i], qos);
		}
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Topic[] getTopics() {
		return topics;
	}

	public void setTopics(Topic[] topics) {
		this.topics = topics;
	}
}
