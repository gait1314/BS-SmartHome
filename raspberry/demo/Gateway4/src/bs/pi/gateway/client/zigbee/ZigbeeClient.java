package bs.pi.gateway.client.zigbee;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import com.test.Debugger;

import bs.pi.gateway.client.port.PortClient;
import bs.pi.gateway.main.IClient;
import bs.pi.gateway.main.IConverter;
import bs.pi.gateway.main.IReceivedListener;
import bs.pi.gateway.main.IReceiver;
import bs.pi.gateway.main.ISender;
import bs.pi.gateway.msg.IMsg;
import bs.pi.gateway.msg.OtherZigbeeConnectedMsg;
import bs.pi.gateway.msg.OutSensorValuesComingMsg;
import bs.pi.gateway.msg.PortMsgReceivedMsg;
import bs.pi.gateway.msg.PortSendResponseMsg;
import bs.pi.gateway.msg.QueryZigbeeIsOnlineMsg;
import bs.pi.gateway.msg.ResponseToZigbeeOnlineQueryMsg;
import bs.pi.gateway.msg.SendPortMsgMsg;

public class ZigbeeClient implements IClient{

	public static final String DEFAULT_CFG_PATH = System.getProperty("user.dir")+System.getProperty("file.separator")+"zigbeeClientCfg.properties";
	private String cfgPath;
	private ZigbeeClientCfg cfg;
	private PortClient portClient;
	private ISender portSender;
	private ZigbeeSender zigbeeSender;
	private ZigbeeReceiver zigbeeReceiver;
	private IConverter converter;
	//监听器，用于捕捉硬件地址对应的网络地址
	private IReceivedListener receivedListener = new IReceivedListener() {
		@Override
		public void handleEvent(IMsg msg) {
			ArrayList<ZigbeeInfo> zigbeeInfoList =  cfg.getZigbeeInfoList();
			if(zigbeeInfoList == null || zigbeeInfoList.isEmpty())
				return;
			
			if(OtherZigbeeConnectedMsg.MSG_NAME.equals(msg.getName())){
				OtherZigbeeConnectedMsg connectedMsg = (OtherZigbeeConnectedMsg) msg;
				for(ZigbeeInfo info : zigbeeInfoList){
					if(Arrays.equals(connectedMsg.getIEEEAddr() , info.getIEEEAddr())){
						info.setNWKAddr(connectedMsg.getNWKAddr());
					}
				}
				
			}else if(QueryZigbeeIsOnlineMsg.MSG_NAME.equals(msg.getName())){
				QueryZigbeeIsOnlineMsg queryZigbeeIsOnlineMsg = (QueryZigbeeIsOnlineMsg) msg;
				if(queryZigbeeIsOnlineMsg.getSrcAddr() == null || queryZigbeeIsOnlineMsg.getSrcAddr().length != 2)
					return;
				ResponseToZigbeeOnlineQueryMsg responseToZigbeeOnlineQueryMsg = new ResponseToZigbeeOnlineQueryMsg();
				responseToZigbeeOnlineQueryMsg.setSrcAddr(queryZigbeeIsOnlineMsg.getSrcAddr());
				zigbeeSender.send(responseToZigbeeOnlineQueryMsg);
				/*
				OutSensorValuesComingMsg msg1 = new OutSensorValuesComingMsg();
				msg1.setTemperature(23.4f);
				msg1.setHumidity(50.2f);
				msg1.setHeat(60.4f);
				msg1.setDustConcentration(12.2f);
				msg1.setLightIntensity(33.6f);
				zigbeeSender.send(msg1);*/
			}
			
		}
	};
	
	public ZigbeeClient(String cfgPath, PortClient portClient){
		this.cfgPath = cfgPath;
		this.portClient = portClient;
	}
	
	@Override
	public void init() throws Exception {
		
		if(cfgPath == null)
			cfgPath = DEFAULT_CFG_PATH;
		cfg = new ZigbeeClientCfg(cfgPath);

		portClient.init();
		portClient.start();
		portSender = portClient.getSender();
		
		portSend(CodeGenerator.CMD_DEVICE_RESET);
		Thread.sleep(2000);
		portSend(CodeGenerator.CMD_STARTUP_WITHOUT_LAST_STATE);
		Thread.sleep(500);
		portSend(CodeGenerator.CMD_DEVICE_RESET);
		Thread.sleep(2000);
		portSend(CodeGenerator.chanlistCfg(cfg.getChannel()));
		Thread.sleep(500);
		portSend(CodeGenerator.PANIDCfg(cfg.getPanID()));
		Thread.sleep(500);
		portSend(CodeGenerator.deviceTypeCfg(cfg.getDeviceType()));
		Thread.sleep(500);
		portSend(CodeGenerator.CMD_ZDO_DIRECT_CB);
		Thread.sleep(500);
		portSend(CodeGenerator.appRegister(cfg.getAppReg()));
		Thread.sleep(500);
	}
	
	private PortSendResponseMsg portSend(byte[] data) throws Exception{
		SendPortMsgMsg portSendMsg = new SendPortMsgMsg();
		portSendMsg.setData(data);
		return (PortSendResponseMsg) portSender.send(portSendMsg);
	}

	@Override
	public void start() throws Exception {
		
		zigbeeReceiver = new ZigbeeReceiver(portClient.getReceiver(), converter);
		zigbeeReceiver.addReceivedListenr(receivedListener);//监听应将地址对应的网络地址
		zigbeeReceiver.start();
		
		portSend(CodeGenerator.CMD_STARTUP_FROM_APP);
		Thread.sleep(2000);
		
		zigbeeSender = new ZigbeeSender(portSender, converter, cfg);
	}

	@Override
	public void destroy() {
		portClient.destroy();
		zigbeeSender = null;
		zigbeeReceiver = null;
	}

	@Override
	public ISender getSender() {
		return zigbeeSender;
	}

	@Override
	public IReceiver getReceiver() {
		return zigbeeReceiver;
	}

	@Override
	public void setConverter(IConverter converter) {
		this.converter = converter;
	}
	
	public ZigbeeClientCfg getZigbeeClientCfg(){
		return cfg;
	}
}
