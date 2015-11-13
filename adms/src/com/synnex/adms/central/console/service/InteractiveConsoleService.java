package com.synnex.adms.central.console.service;

import java.util.ArrayList;
import java.util.List;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.synnex.adms.central.connect.NodeManager;
import com.synnex.adms.central.console.dao.InteractiveConsoleDao;

@Transactional
@Service
public class InteractiveConsoleService {

	private static final Logger log = LoggerFactory.getLogger(InteractiveConsoleService.class);
	
	@Autowired
	private InteractiveConsoleDao interactiveConsoleDao;


	public List getNodeList() {
		List nodeList = new ArrayList();
		try{
			nodeList = interactiveConsoleDao.queryNodeList();
		}catch(Exception e){
			log.error(e.getMessage());
		}
		return nodeList;
	}
	
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public boolean addNode(String nodeQueueName, String nodeIp, int nodePort){
		boolean connectStatus = false;
		try{
			NodeManager nodeManager = new NodeManager();
			connectStatus = nodeManager.addNode(nodeQueueName, nodeIp, nodePort);
		}catch(Exception e){
			log.error(e.getMessage());
		}
		return connectStatus;
	}
	
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public boolean deleteNode(String nodeQueueName, String nodeIp, int nodePort){
		boolean disConnectStatus = false;
		try{
			NodeManager nodeManager = new NodeManager();
			disConnectStatus = nodeManager.deleteNode(nodeQueueName, nodeIp, nodePort);
		}catch(Exception e){
			log.error(e.getMessage());
		}
		return disConnectStatus;
	}

	

}