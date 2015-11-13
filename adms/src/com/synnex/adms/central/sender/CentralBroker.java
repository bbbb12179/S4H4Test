package com.synnex.adms.central.sender;

import java.net.URI;
import org.apache.activemq.broker.BrokerService;
import org.apache.activemq.broker.TransportConnector;
import org.apache.log4j.Logger;

public class CentralBroker {

	public static final int DEFAULT_INACTIVE_TIMEOUT = 120000;
	public static final String BROKER_VERSION = "v20150916";

	final Logger log =  Logger.getLogger(CentralBroker.class);
	private int inactiveTimeout;
	
	
	public CentralBroker(int inactiveTimeout) {
		this.inactiveTimeout = inactiveTimeout;
	}
	
	public static void main(String[] args) throws Exception {
		int inactiveTimeout = DEFAULT_INACTIVE_TIMEOUT;
		CentralBroker centralBroker = new CentralBroker(inactiveTimeout);
		centralBroker.start();
	}
	
	
	public void start() throws Exception {
		BrokerService broker = new BrokerService();
		TransportConnector connector = new TransportConnector();
		String uri = "tcp://192.168.212.101:7777?keepAlive=true&tcpNoDelay=true&socketBufferSize=131072&ioBufferSize=16384&trace=false&soTimeout=180000&connectionTimeout=60000&wireFormat.maxInactivityDuration=" + DEFAULT_INACTIVE_TIMEOUT+"&wireFormat.cacheEnabled=true&wireFormat.cacheSize=4096";
		
		connector.setUri(new URI(uri));
		connector.setName("CENTER");
		connector.setEnableStatusMonitor(true);
		
		broker.setBrokerName("CENTER");
		broker.getManagementContext().setConnectorPort(10000);//改變jmx port
		broker.setUseJmx(true);
		broker.addConnector(connector);
		broker.setAdvisorySupport(false);
		broker.setPersistent(false);
		broker.start();

	}

}
