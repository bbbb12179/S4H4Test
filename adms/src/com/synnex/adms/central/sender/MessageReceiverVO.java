package com.synnex.adms.central.sender;

public class MessageReceiverVO {
	private String text = "";
	private String cmdMessage ="";	
	private String jobMessage = "";
	
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getCmdMessage() {
		return cmdMessage;
	}
	public void setCmdMessage(String cmdMessage) {
		this.cmdMessage = cmdMessage;
	}
	public String getJobMessage() {
		return jobMessage;
	}
	public void setJobMessage(String jobMessage) {
		this.jobMessage = jobMessage;
	}	
	
	
}
