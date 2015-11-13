package com.synnex.adms.central.console.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.synnex.adms.central.connect.NodeManager;
import com.synnex.adms.central.console.service.InteractiveConsoleService;
import com.synnex.adms.central.model.Node;

@RestController
public class InteractiveConsoleController {

	private final Logger log = LoggerFactory
			.getLogger(InteractiveConsoleController.class);
	
	@Autowired
	private InteractiveConsoleService interactiveConsoleService;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView index(ModelAndView model) {

		List nodeList = interactiveConsoleService.getNodeList();
		model.setViewName("acie");
		model.addObject("nodeList", nodeList);
		
		return model;
	}

	@RequestMapping(value = "/connectNode", method = RequestMethod.GET)
	public void connectNode(
			@RequestParam(value = "connectNodeArray", required = false) String connectNodeArray) {

		try {
			JSONParser jsonParser = new JSONParser();
			JSONArray jsonArray;

			jsonArray = (JSONArray) jsonParser.parse(connectNodeArray);

			for (int i = 0; i < jsonArray.size(); i++) {
				JSONObject jsonObject = (JSONObject) jsonArray.get(i);
				String nodeQueueName = (String) jsonObject.get("nodeQueueName");
				String nodeIp = (String) jsonObject.get("nodeIp");
				Integer nodePort = Integer.parseInt((String) jsonObject
						.get("nodePort"));

				boolean connectStatus = interactiveConsoleService.addNode(
						nodeQueueName, nodeIp, nodePort);
				if (connectStatus) {
					log.info("connect to :" + jsonObject.get("nodeId") + ","
							+ nodeQueueName + "," + nodeIp + "," + nodePort);
				}
			}

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@RequestMapping(value = "/disConnectNode", method = RequestMethod.POST)
	public void disConnectNode(
			@RequestParam(value = "disConnectNodeArray", required = false) String disConnectNodeArray) {

		try {
			JSONParser jsonParser = new JSONParser();
			JSONArray jsonArray = (JSONArray) jsonParser
					.parse(disConnectNodeArray);
			for (int i = 0; i < jsonArray.size(); i++) {
				JSONObject jsonObject = (JSONObject) jsonArray.get(i);
				Integer nodeId = Integer.parseInt((String) jsonObject
						.get("nodeId"));
				String nodeQueueName = (String) jsonObject.get("nodeQueueName");
				String nodeIp = (String) jsonObject.get("nodeIp");
				Integer nodePort = Integer.parseInt((String) jsonObject
						.get("nodePort"));
				boolean disConnectStatus = interactiveConsoleService
						.deleteNode(nodeQueueName, nodeIp, nodePort);
				if (disConnectStatus) {
					log.info("disconnect to :" + nodeIp + ","
							+ nodeQueueName + "," + nodeIp + "," + nodePort);
				}
				
				Node node = new Node();
				node.setNodeId(nodeId);
				node.setNodeIp(nodeIp);
				node.setNodePort(nodePort.toString());
				node.setNodeQueueName(nodeQueueName);
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	@RequestMapping(value = "/test2", method = RequestMethod.GET)
	public ModelAndView test(ModelAndView model,
			@RequestParam(value = "data", required = false) String data) {
		
		model.setViewName("acie2");

		return model;
	}
	
	@RequestMapping(value = "/test", method = RequestMethod.POST)
	public ModelAndView test(ModelAndView model,
			@RequestParam(value = "checkboxArray", required = false) List checkboxArray,
			@RequestParam(value = "nodeIdArray", required = false) List nodeIdArray,
			@RequestParam(value = "nodeQueueNameArray", required = false) List nodeQueueNameArray,
			@RequestParam(value = "nodeIpArray", required = false) List nodeIpArray,
			@RequestParam(value = "nodePortArray", required = false) List nodePortArray
			) {
		
		List nodeList = new ArrayList();
		for(int i=0;i<checkboxArray.size();i++){
			if(checkboxArray.get(i).equals("Y")){
				Node node = new Node();
				node.setNodeId(Integer.parseInt(nodeIdArray.get(i).toString()));
				node.setNodeIp(nodeIpArray.get(i).toString());
				node.setNodeQueueName(nodeQueueNameArray.get(i).toString());
				node.setNodePort(nodePortArray.get(i).toString());
				nodeList.add(node);
			}
		}
		model.setViewName("acie2");
		model.addObject("nodeList", nodeList);

		return model;
	}

}