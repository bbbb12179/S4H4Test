package com.synnex.adms.central.sender;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;

import javax.jms.Connection;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import javax.jms.Session;
import javax.jms.TextMessage;

import noNamespace.CmdMessageDocument;
import noNamespace.JobMessageDocument;
import noNamespace.JobMessageDocument.JobMessage;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.log4j.Logger;

import com.synnex.adms.central.connect.NodeManager;

public class CentralListener implements MessageListener {
	private static Logger log =  Logger.getLogger(CentralListener.class);
	public static final int DEFAULT_INACTIVE_TIMEOUT = 120000;
	private String cmdMessage ="";
	
	public CentralListener(int inactiveTimeout) throws Exception {
		
		//listener for centralListener queue in clientEngineBroker
		ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory("failover:(tcp://192.168.208.247:8888?keepAlive=true&tcpNoDelay=true&socketBufferSize=131072&ioBufferSize=16384&trace=false&soTimeout=180000&connectionTimeout=60000&wireFormat.maxInactivityDuration=" +inactiveTimeout+ ")?initialReconnectDelay=10&maxReconnectDelay=360000&maxReconnectAttempts=120"); 
		connectionFactory.setCopyMessageOnSend(false);
        Connection connection = connectionFactory.createConnection();
        connection.start();
      
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);   
        Destination destination = session.createQueue("CENTER");
        MessageConsumer consumer = session.createConsumer(destination);
        
        consumer.setMessageListener(this);
	}

	public static void main(String[] args) throws Exception {
		//int inactiveTimeout = DEFAULT_INACTIVE_TIMEOUT;
		//new CentralListener(inactiveTimeout);
		
		NodeManager nodeManager = new NodeManager();
//		nodeManager.addNode("TPFQAGT", "192.168.208.247", 8888);
//		nodeManager.addNode("PEWGAGT", "10.160.9.26", 8888);
		//nodeManager.addNode("UQWQAGT", "10.103.152.10", 8888);
//		nodeManager.addNode("NZWQAGT", "10.161.0.8", 8888);
		nodeManager.addNode("clientEngine", "192.168.212.101", 6666);
	}

	@Override
	public void onMessage(Message message) {
		if(message instanceof TextMessage){
			TextMessage textMessage = (TextMessage) message;
	        String text = "";
		
			try {
				text = textMessage.getText();
				System.out.println(text);
			} catch (JMSException e) {
				e.printStackTrace();
			}
		}else if(message instanceof ObjectMessage){
			ObjectMessage objectMessage = (ObjectMessage) message;
			
			try {
	        	if(objectMessage.getObject() instanceof CmdMessageDocument){
	        		CmdMessageDocument cmdMessageDocument = (CmdMessageDocument) objectMessage.getObject();
	        		cmdMessage += "\n" + cmdMessageDocument.toString();
	        		Writer wt = new FileWriter("log/cmdMessage.log");
					PrintWriter pw = new PrintWriter(wt); 
		        	pw.println(cmdMessage);
		        	pw.flush();
		        	pw.close();
	        	}else if(objectMessage.getObject() instanceof JobMessageDocument){
	        		JobMessageDocument jobMessageDocument = (JobMessageDocument) objectMessage.getObject();
	        		JobMessage jobMessage = jobMessageDocument.getJobMessage();  		
	        		log.info("\n" + jobMessageDocument.toString());
	        	}
				
			} catch (JMSException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
}
