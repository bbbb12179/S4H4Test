package com.synnex.adms.central.sender;
import java.io.Serializable;
import java.util.List;
import java.util.Scanner;

import javax.jms.Connection;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

import com.synnex.adms.central.jobs.Job;



public class TaskLauncher {
	
	private String centralBrokerIp = "192.168.212.101";
	private String centralBrokerPort = "7777";
	private String nodeQueueName = "clientEngine";
	
	public static void main(String[] args) throws Exception {
//		Scanner scanner = new Scanner(System.in);
//		System.out.println("請輸入QueneName:");
//		QueueName = scanner.nextLine();
		
		TaskLauncher taskLauncher = new TaskLauncher();
		new Thread(new Command(taskLauncher)).start();

	}
	
	public void sendMessage(String message) throws JMSException{
		//send message to local queue in central broker
		ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://"+centralBrokerIp+":"+centralBrokerPort);
        Connection connection = connectionFactory.createConnection();
        connection.start();

        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Destination destination = session.createQueue(nodeQueueName);
        MessageProducer producer = session.createProducer(destination);
        producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
        
        TextMessage textMessage = session.createTextMessage(message);     
        producer.send(textMessage);

        session.close();
        connection.close();
	}
	
	public void sendJob(List<Job> jobList) throws JMSException{
		//send message to local queue in central broker
		ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://"+centralBrokerIp+":"+centralBrokerPort);
        Connection connection = connectionFactory.createConnection();
        connection.start();

        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Destination destination = session.createQueue(nodeQueueName);
        MessageProducer producer = session.createProducer(destination);
        producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
        
        ObjectMessage message = session.createObjectMessage((Serializable) jobList); 
        producer.send(message);

        session.close();
        connection.close();
	}
}
