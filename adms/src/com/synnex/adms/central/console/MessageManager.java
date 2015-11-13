package com.synnex.adms.central.console;

import java.util.concurrent.ConcurrentHashMap;

import com.synnex.adms.central.sender.MessageReceiver;

public class MessageManager {
	private static MessageManager msgMGR = new MessageManager();
	private ConcurrentHashMap<String, MessageReceiver> rcvmsgMap = new ConcurrentHashMap<String, MessageReceiver>();

	private MessageManager(){		
	}
	
	public static MessageManager getInstance(){
		return MessageManager.msgMGR;
	}
	
	public void connectAgentResponseMessage(String nodeName, MessageReceiver receiver){
		this.rcvmsgMap.put(nodeName, receiver);
	}
	
	public void disConnectAgentResponseMessage(String nodeName){
		this.rcvmsgMap.remove(nodeName);
	}
	
	public MessageReceiver getNodeMessage(String nodeName){
		return this.rcvmsgMap.get(nodeName);
	}
	
}
