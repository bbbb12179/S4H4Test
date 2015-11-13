package com.synnex.adms.central.jobs;

import java.io.Serializable;
import java.net.MalformedURLException;
import java.util.ArrayList;


public abstract class Job implements Serializable{	
	private static final long serialVersionUID = -9157082799203602946L;
	private JobType jobtype;
	private byte[] resource;
	private ArrayList<String> scriptList = new ArrayList<String>();
	private JobStatus status = JobStatus.PREPARE;
	private String queueName = null;
	private String resourceFileName = null;
	private String jobName = null;
	private String jobAction = null;
	private String scriptResetFlag = "";
	private String jobResetFlag = "";
	
	public Job(JobType jobtype){
		this.jobtype = jobtype;
	}
	
	public Job(){
		
	}
	
	
	public abstract String execute(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, MalformedURLException, Exception;

	public JobType getJobType() {
		return jobtype;
	}
	
	public void setJobType(JobType type){
		this.jobtype = type;
	}
	
	public String[] getScript() {
		return this.scriptList.toArray(new String[this.scriptList.size()]);
	}

	public void addScript(String script){
		this.scriptList.add(script);
	}

	public JobStatus getStatus() {
		return status;
	}

	public void setStatus(JobStatus status) {
		this.status = status;
	}

	public void setResource(byte[] resource) {
		this.resource = resource;
	}

	public byte[] getResource() {
		return resource;
	}

	public String getQueueName() {
		return queueName;
	}

	public void setQueueName(String queueName) {
		this.queueName = queueName;
	}

	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	public String getScriptResetFlag() {
		return scriptResetFlag;
	}

	public void setScriptResetFlag(String scriptResetFlag) {
		this.scriptResetFlag = scriptResetFlag;
	}

	public String getJobResetFlag() {
		return jobResetFlag;
	}

	public void setJobResetFlag(String jobResetFlag) {
		this.jobResetFlag = jobResetFlag;
	}

	public void setJobAction(String jobAction) {
		this.jobAction = jobAction;
	}

	public String getJobAction() {
		return jobAction;
	}

	public void setResourceFileName(String resourceFileName) {
		this.resourceFileName = resourceFileName;
	}

	public String getResourceFileName() {
		return resourceFileName;
	}
}
