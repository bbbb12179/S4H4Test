package com.synnex.adms.central.model;

import java.io.Serializable;

public class Node implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4977552368102701196L;
	
	private int nodeId;
	private String nodeQueueName;
	private String nodeIp;
	private String nodePort;
	
	public int getNodeId() {
		return nodeId;
	}
	public void setNodeId(int nodeId) {
		this.nodeId = nodeId;
	}
	public String getNodeQueueName() {
		return nodeQueueName;
	}
	public void setNodeQueueName(String nodeQueueName) {
		this.nodeQueueName = nodeQueueName;
	}
	public String getNodeIp() {
		return nodeIp;
	}
	public void setNodeIp(String nodeIp) {
		this.nodeIp = nodeIp;
	}
	public String getNodePort() {
		return nodePort;
	}
	public void setNodePort(String nodePort) {
		this.nodePort = nodePort;
	}
	
	
	
	
}
