package com.synnex.adms.central.sender;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.jms.JMSException;

import com.synnex.adms.central.jobs.JavaJob;
import com.synnex.adms.central.jobs.Job;
import com.synnex.adms.central.jobs.JobType;
import com.synnex.adms.central.jobs.ScriptJob;



public class Command implements Runnable{

	TaskLauncher taskLauncher;
	List<Job> jobList = new ArrayList<Job>();
	
	public Command(TaskLauncher taskLauncher){
		this.taskLauncher = taskLauncher;
	}
	
	public void run() {
		
//		newListener();
//		updateListener();
//		testJob();
//		executeJavaClass2();
//		executeJavaClass();
//		newH2();
//		updateH2();
		
		
		try {
			/*
			File file = new File("F:\\ListenerDeploy\\pmq\\pmq.zip");
	        byte[] data = new byte[(int)file.length()];
	        FileInputStream fileInputStream = new FileInputStream(file);
	        fileInputStream.read(data);
	        fileInputStream.close();
	        */			
			
			ScriptJob listenerJob = new ScriptJob(JobType.script);
			/*
			listenerJob.setQueueName("WILLIAMQ");
			listenerJob.setJobAction("newListener");
			listenerJob.setResource(data);
			listenerJob.addScript("dir c:\\");			
			listenerJob.addScript("echo end");
			*/
			listenerJob.setQueueName("test");
			listenerJob.setJobAction("test");
			listenerJob.addScript("dir  c:\\temp");	
			listenerJob.addScript("dir");			
			listenerJob.addScript("echo end");
			
			
			jobList.add(listenerJob);
			taskLauncher.sendJob(jobList);
			
			
		} catch (Exception e) {

			e.printStackTrace();
		}
		
		//互動式CMD，測試時自行打開
//		Scanner scanner = new Scanner(System.in);
//		while(true){
//			String command = scanner.nextLine();
//			try {
//				central.sendMessage(command);
//			} catch (JMSException e) {
//				e.printStackTrace();
//			}      	
//		}		
		
		
	}
	
	
	public void newListener(){
		try {
			ScriptJob listenerJob = new ScriptJob(JobType.script);
			
			String queueName="TPFQ0S1";
			String resourceFileName="TPFQ0S1";
			
			File file = new File("C:\\Users\\jonaslee\\Desktop\\Printer工作區\\listenerAutoDeployResource\\TPFQ0S1.zip");
	        byte[] data = new byte[(int)file.length()];
	        FileInputStream fileInputStream = new FileInputStream(file);
	        fileInputStream.read(data);
	        fileInputStream.close();
	        
//	        listenerJob.setScriptResetFlag("Y");
//	        listenerJob.setJobResetFlag("Y");
	        listenerJob.setQueueName(queueName);
	        listenerJob.setResourceFileName(resourceFileName);
	        listenerJob.setJobAction("newListener");
	        listenerJob.setResource(data);
	        listenerJob.addScript("set PATH=%PATH%;C:\\Program Files\\7-Zip\\");
			listenerJob.addScript("mkdir C:\\"+queueName);
			listenerJob.addScript("7z x @RESOURCE -oC:\\"+queueName+" -y");
			listenerJob.addScript("del C:\\"+resourceFileName+".zip");
			listenerJob.addScript("C:\\"+queueName+"\\bin\\install.bat");
			listenerJob.addScript("net start "+queueName);
			listenerJob.addScript("echo end");
			
			jobList.add(listenerJob);
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}      	
	}
	
	
	public void updateListener(){
		try {
			ScriptJob listenerJob = new ScriptJob(JobType.script);
			
			String queueName="TPFQ0S1";
			String resourceFileName="TPFQ0S1";
			
			File file = new File("C:\\Users\\jonaslee\\Desktop\\Printer工作區\\listenerAutoDeployResource\\TPFQ0S1.zip");
	        byte[] data = new byte[(int)file.length()];
	        FileInputStream fileInputStream = new FileInputStream(file);
	        fileInputStream.read(data);
	        fileInputStream.close();
	        
	        listenerJob.setQueueName(queueName);
	        listenerJob.setResourceFileName(resourceFileName);
	        listenerJob.setJobAction("updateListener");
	        listenerJob.setResource(data);
//	        listenerJob.setScriptResetFlag("Y");
//	        listenerJob.setJobResetFlag("Y");
	        listenerJob.addScript("set PATH=%PATH%;C:\\Program Files\\7-Zip\\");
	        listenerJob.addScript("C:\\"+queueName+"\\bin\\uninstall.bat");
	        listenerJob.addScript("RD /S /Q C:\\backup\\"+queueName);
	        listenerJob.addScript("move /Y C:\\"+queueName+" C:\\backup\\"+queueName);
			listenerJob.addScript("mkdir C:\\"+queueName);
			listenerJob.addScript("7z x C:\\"+resourceFileName+".zip -oC:\\"+queueName+" -y");
			listenerJob.addScript("del C:\\"+resourceFileName+".zip");
			listenerJob.addScript("C:\\"+queueName+"\\bin\\install.bat");
			listenerJob.addScript("net start "+queueName);
			listenerJob.addScript("echo end");
			
			jobList.add(listenerJob);
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}      	
	}
	
	public void newH2(){
		try {
			ScriptJob h2Job = new ScriptJob(JobType.script);
			
			File file = new File("C:\\Users\\jonaslee\\Desktop\\Printer工作區\\h2AutoInstallResource\\h2.zip");
	        byte[] data = new byte[(int)file.length()];
	        FileInputStream fileInputStream = new FileInputStream(file);
	        fileInputStream.read(data);
	        fileInputStream.close();
			
	        String resourceFileName="h2";
			String sqlFileName = "backup.sql";
			
			h2Job.setJobAction("newH2");
			h2Job.setResourceFileName(resourceFileName);
//			h2Job.setScriptResetFlag("Y");
			h2Job.setJobResetFlag("Y");
			h2Job.setResource(data);
			h2Job.addScript("set PATH=%PATH%;C:\\Program Files\\7-Zip\\");
			h2Job.addScript("7z x C:\\h2.zip -oC:\\h2 -y");
			h2Job.addScript("del C:\\h2.zip");
			h2Job.addScript("C:\\h2\\service\\1_install_service.bat");
			h2Job.addScript("C:\\h2\\service\\2_start_service.bat");
			h2Job.addScript("C:\n cd C:\\h2\\bin\n java -cp h2.jar org.h2.tools.RunScript -url \"jdbc:h2:tcp://localhost/~/pmq/pmqdb\" -script \".\\backup\\" +sqlFileName + "\" -user sa");
			h2Job.addScript("C:\\h2\\service\\3_start_browser.bat");
			h2Job.addScript("echo end");
			
			jobList.add(h2Job); 
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}      	
	}
	
	public void updateH2(){
		try {
			ScriptJob h2Job = new ScriptJob(JobType.script);
			File file = new File("C:\\Users\\jonaslee\\Desktop\\Printer工作區\\h2AutoInstallResource\\bin\\update\\update_Y151005.zip");
	        byte[] data = new byte[(int)file.length()];
	        FileInputStream fileInputStream = new FileInputStream(file);
	        fileInputStream.read(data);
	        fileInputStream.close();
	        
	        String resourceFileName="update_Y151005";
			String sqlFileName = "update_Y151005.sql";
			
			h2Job.setResourceFileName(resourceFileName);
			h2Job.setJobAction("updateH2");
	//		h2Job.setScriptResetFlag("Y");
	//		h2Job.setJobResetFlag("Y");
			h2Job.setResource(data);
			h2Job.addScript("set PATH=%PATH%;C:\\Program Files\\7-Zip\\");
			h2Job.addScript("7z x C:\\update_Y151005.zip -oC:\\h2\\bin\\update -y");
			h2Job.addScript("del C:\\update_Y151005.zip");
			h2Job.addScript("C:\n cd C:\\h2\\bin\n java -cp h2.jar org.h2.tools.RunScript -url \"jdbc:h2:tcp://localhost/~/pmq/pmqdb\" -script \".\\update\\" +sqlFileName + "\" -user sa");
			
			h2Job.addScript("echo end");
			
			jobList.add(h2Job);      
		
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}      	
	}
	
	public void testJob(){
		ScriptJob listenerJob = new ScriptJob(JobType.script);
	
	    listenerJob.setJobResetFlag("Y");
		//listenerJob.setQueueName(queueName);
		listenerJob.setJobAction("testJob");
		listenerJob.addScript("echo 1");
		listenerJob.addScript("echo 2");
		listenerJob.addScript("echo 3");
		listenerJob.addScript("echo 4");
		listenerJob.addScript("echo 5");
		listenerJob.addScript("echo 6");
		listenerJob.addScript("echo 7");
		listenerJob.addScript("echo end");
		
		jobList.add(listenerJob);      	
	}
	
	public void executeJavaClass(){
		try {
			JavaJob javaJob = new JavaJob(JobType.java);
			
			
			File file = new File("D:\\PrinterAutoDeployWorkSpace\\adms_NEW(Y151023)\\adms\\build\\classes\\CheckListenerVersion.class");
	        byte[] data = new byte[(int)file.length()];
	        FileInputStream fileInputStream = new FileInputStream(file);
	        fileInputStream.read(data);
	        fileInputStream.close();
	        
			javaJob.setJobAction("executeJavaClass");
			javaJob.setResource(data);
			javaJob.setResourceFileName("CheckListenerVersion");
			javaJob.setClassName("CheckListenerVersion");
			
			jobList.add(javaJob);    
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}      	
	}
	
	public void executeJavaClass2(){
		try {
			JavaJob javaJob = new JavaJob(JobType.java);
			
			
			File file = new File("D:\\PrinterAutoDeployWorkSpace\\adms_NEW(Y151023)\\adms\\build\\classes\\CheckListenerVersion2.class");
	        byte[] data = new byte[(int)file.length()];
	        FileInputStream fileInputStream = new FileInputStream(file);
	        fileInputStream.read(data);
	        fileInputStream.close();
	        
//	        javaJob.setJobResetFlag("Y");
			javaJob.setJobAction("executeJavaClass2");
			javaJob.setResource(data);
			javaJob.setResourceFileName("CheckListenerVersion2");
		//	javaJob.setClassName("CheckListenerVersion2");
			
			jobList.add(javaJob);    
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}      	
	}
}
