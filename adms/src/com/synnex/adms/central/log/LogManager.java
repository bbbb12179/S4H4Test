package com.synnex.adms.central.log;

import org.apache.log4j.Logger;
//import noNamespace.JobMessageDocument.JobMessage;

public class LogManager{
	private static LogManager logManager = new LogManager();	
	private static Logger log =  Logger.getLogger(LogManager.class);	//

		
	private LogManager(){		
	}
	
	public static LogManager getInstance(){
		return LogManager.logManager;
	}
	
	

}
