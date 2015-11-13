package com.synnex.adms.central.jobs;

public class ScriptJob extends Job {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7891380484564545286L;
	private byte[] resource;
	private String message = "";
	
	public ScriptJob(JobType jobType){
		super(jobType);
	}
	@Override
	public String execute(String[] args) {
		// TODO Auto-generated method stub
		return null;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getMessage() {
		return message;
	}
	public void setResource(byte[] resource) {
		this.resource = resource;
	}
	public byte[] getResource() {
		return resource;
	}

}
