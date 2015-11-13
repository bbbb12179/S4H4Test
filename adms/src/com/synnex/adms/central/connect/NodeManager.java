package com.synnex.adms.central.connect;

import java.util.concurrent.ConcurrentHashMap;

import javax.jms.Connection;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.Session;

import org.apache.activemq.ActiveMQConnectionFactory;

import com.synnex.adms.central.console.MessageManager;
import com.synnex.adms.central.sender.MessageReceiver;
import com.synnex.adms.central.tools.VariableValidator;


public class NodeManager {
	private static ConcurrentHashMap<String, Connection> nodeMap = new ConcurrentHashMap<String, Connection>();
	private static final int DEFAULT_INACTIVE_TIMEOUT = 120000;
	private MessageManager msgMGR = MessageManager.getInstance();
	
	public NodeManager(){		
	}
	
	public boolean addNode(String nodeName, String ip, int port){
		return this.addNode(nodeName, ip, port, DEFAULT_INACTIVE_TIMEOUT);
	}
	
	public boolean addNode(String nodeName, String ip, int port, int inactiveTimeout){
		VariableValidator validator = new VariableValidator();
		boolean success = false;
		if(validator.validIP(ip) == true && validator.validPort(port) == true && !NodeManager.nodeMap.containsKey(nodeName)){
			String url = "failover:(tcp://"+ip+":"+port+"?keepAlive=true&tcpNoDelay=true&socketBufferSize=131072&ioBufferSize=16384&trace=false&soTimeout=180000&connectionTimeout=60000&wireFormat.maxInactivityDuration=" +inactiveTimeout+ ")?initialReconnectDelay=10&maxReconnectDelay=360000&maxReconnectAttempts=120";
			ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(url);			
			connectionFactory.setCopyMessageOnSend(false);
	        Connection connection;
			try {
				connection = connectionFactory.createConnection();
				connection.start();
				Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);   
		        Destination destination = session.createQueue("CENTER");
		        MessageConsumer consumer = session.createConsumer(destination);

		        MessageReceiver rcv = new MessageReceiver();		        
		        consumer.setMessageListener(rcv);
		        msgMGR.connectAgentResponseMessage(nodeName, rcv);
		        NodeManager.nodeMap.put(nodeName, connection);
		        success = true;
			} catch (JMSException e) {
				e.printStackTrace();
				success = false;
			}	        
		}
		return success;		
	}
	
	public boolean deleteNode(String nodeName, String ip, int port){
		VariableValidator validator = new VariableValidator();
		boolean success = false;
		if(validator.validIP(ip) == true && validator.validPort(port) == true && NodeManager.nodeMap.get(nodeName)!=null){
			try {
				NodeManager.nodeMap.get(nodeName).close();
		        msgMGR.disConnectAgentResponseMessage(nodeName);
		        NodeManager.nodeMap.remove(nodeName);
		        success = true;
			} catch (JMSException e) {
				e.printStackTrace();
				success = false;
			}	        
		}
		return success;		
	}
					
}
