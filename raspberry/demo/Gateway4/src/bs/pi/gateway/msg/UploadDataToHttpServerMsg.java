package bs.pi.gateway.msg;


public class UploadDataToHttpServerMsg implements IMsg {

	public final static String MSG_NAME = "UploadDataToHttpServerMsg";
	
	//这里反的sensorID要与平台的sensorID一致
	public final static short SENSOR_ID_IN_TEMPERATURE 	= 20;
	public final static short SENSOR_ID_IN_HUMIDITY 	= 21;
	public final static short SENSOR_ID_IN_HEAT 		= 22;
	public final static short SENSOR_ID_OUT_TEMPERATURE = 26;
	public final static short SENSOR_ID_OUT_HUMIDITY 	= 27;
	public final static short SENSOR_ID_OUT_HEAT 		= 28;
	public final static short SENSOR_ID_SOLID_HUMIDITY 	= 23;
	public final static short SENSOR_ID_DUST_DENSITY 	= 24;
	public final static short SENSOR_ID_LIGHT_INTENSITY = 25;
	
	private short sensorID;
	private float sensorValue;
	
	@Override
	public String getName() {
		return MSG_NAME;
	}

	public short getSensorID() {
		return sensorID;
	}

	public void setSensorID(short sensorID) {
		this.sensorID = sensorID;
	}

	public float getSensorValue() {
		return sensorValue;
	}

	public void setSensorValue(float sensorValue) {
		this.sensorValue = sensorValue;
	}
}
