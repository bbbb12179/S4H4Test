package com.synnex.adms.central.jobs;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

public class JavaJob extends Job {

	/**
	 * 
	 */
	private static final long serialVersionUID = -624582830833775520L;
	private byte[] resource;
	private String message = "";
	private String className = "";
	
	public JavaJob(JobType jobType){
		super(jobType);
	}
	@Override
	public String execute(String[] args) throws ClassNotFoundException,InstantiationException,IllegalAccessException,MalformedURLException,Exception{
		File file = new File("C:/");
		URL[] cp = {file.toURI().toURL()};
		URLClassLoader urlcl = new URLClassLoader(cp);
		Class clazz = urlcl.loadClass(className);
		clazz.newInstance();
			
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
	public void setClassName(String className) {
		this.className = className;
	}
	public String getClassName() {
		return className;
	}

}
