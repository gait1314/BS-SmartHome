package com.zigbeee;

public class ZBPacketSend {

	private byte[] dstAddr = new byte[2];
	private byte dstEndpoint;
	private byte srcEndpoint;
	private byte[] clusterID = new byte[2];
	private byte transID;
	private byte options;
	private byte radius;
	private byte[] data = new byte[0];

	public byte[] getDstAddr() {
		return dstAddr;
	}

	public void setDstAddr(byte[] dstAddr) {
		this.dstAddr = dstAddr.clone();
	}

	public byte getDstEndpoint() {
		return dstEndpoint;
	}

	public void setDstEndpoint(byte dstEndpoint) {
		this.dstEndpoint = dstEndpoint;
	}

	public byte getSrcEndpoint() {
		return srcEndpoint;
	}

	public void setSrcEndpoint(byte srcEndpoint) {
		this.srcEndpoint = srcEndpoint;
	}

	public byte[] getClusterID() {
		return clusterID;
	}

	public void setClusterID(byte[] clusterID) {
		this.clusterID = clusterID.clone();
	}

	public byte getTransID() {
		return transID;
	}

	public void setTransID(byte transID) {
		this.transID = transID;
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

	public byte[] getData() {
		return data;
	}

	public void setData(byte[] data) {
		this.data = data.clone();
	}
}
