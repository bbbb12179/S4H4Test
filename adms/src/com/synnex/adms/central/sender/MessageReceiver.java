package com.synnex.adms.central.sender;

import java.util.concurrent.LinkedTransferQueue;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import javax.jms.TextMessage;

import noNamespace.CmdMessageDocument;
import noNamespace.JobMessageDocument;


public class MessageReceiver implements MessageListener{
	private LinkedTransferQueue <MessageReceiverVO> buffer = new LinkedTransferQueue <MessageReceiverVO>();
	
	public MessageReceiver(){	
	}
		

	@Override
	public void onMessage(Message message) {
		MessageReceiverVO msgVO = new MessageReceiverVO();
		
		if(message instanceof TextMessage){
			TextMessage textMessage = (TextMessage) message;		
			try {				
				msgVO.setText(textMessage.getText());				
			} catch (JMSException e) {
				e.printStackTrace();
			}
		}else if(message instanceof ObjectMessage){
			ObjectMessage objectMessage = (ObjectMessage) message;
			
			try {
	        	if(objectMessage.getObject() instanceof CmdMessageDocument){
	        		CmdMessageDocument cmdMessageDocument = (CmdMessageDocument) objectMessage.getObject();	        		
	        		msgVO.setCmdMessage(cmdMessageDocument.toString());
	        		
	        	}else if(objectMessage.getObject() instanceof JobMessageDocument){
	        		JobMessageDocument jobMessageDocument = (JobMessageDocument) objectMessage.getObject();
	        		//JobMessage jobMessage = jobMessageDocument.getJobMessage();	        		
	        		msgVO.setJobMessage(jobMessageDocument.toString());
	        	}
				
			} catch (JMSException e) {

				e.printStackTrace();
			}
		}
		buffer.put(msgVO);
	}
	
	public MessageReceiverVO getMessageReceiverVO(){
		return this.buffer.poll();
	}
	
	public static void main(String[] args){
		LinkedTransferQueue <String> buffer = new LinkedTransferQueue<String>();
		buffer.add("A");
		buffer.add("B");
		buffer.add("C");
		System.out.println(buffer);
		while(buffer.isEmpty() == false){
			System.out.println(buffer.poll());
		}		
		
	}
}