package com.synnex.adms.central.console;

import com.synnex.adms.central.connect.NodeManager;
import com.synnex.adms.central.sender.MessageReceiver;
import com.synnex.adms.central.sender.MessageReceiverVO;

public class InteractiveConsole {
	MessageManager msgMGR = MessageManager.getInstance();
	
	public InteractiveConsole(){
		NodeManager nodeManager = new NodeManager();
//		nodeManager.addNode("TPFQAGT", "192.168.208.247", 8888);
//		nodeManager.addNode("PEWGAGT", "c", 8888);
		//nodeManager.addNode("UQWQAGT", "10.103.152.10", 8888);
//		nodeManager.addNode("NZWQAGT", "10.161.0.8", 8888);
		nodeManager.addNode("clientEngine", "192.168.212.101", 6666);
	}
	
	public static void main(String[] args) {
		InteractiveConsole console = new InteractiveConsole();				
		MessageManager msgMGR = MessageManager.getInstance();
		while(1 == 1){						
			try {
				Thread.sleep(1000);
				MessageReceiver rcv = msgMGR.getNodeMessage("NZWQAGT");
				MessageReceiverVO vo = rcv.getMessageReceiverVO();
				if(vo != null){
					System.out.println(vo.getText());
					System.out.println("***************************");
					System.out.println(vo.getCmdMessage());
					System.out.println("***************************");
					System.out.println(vo.getJobMessage());
					System.out.println("***************************");
				}
			} catch (InterruptedException e) {

				e.printStackTrace();
			}
		}

	}

}
