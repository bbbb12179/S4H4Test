package com.synnex.adms.central.tools;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class VariableValidator {

	private Pattern pattern;
	private Matcher matcher;

	private static final String IPADDRESS_PATTERN = "^([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\."
			+ "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\."
			+ "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\."
			+ "([01]?\\d\\d?|2[0-4]\\d|25[0-5])$";

	public VariableValidator() {
		pattern = Pattern.compile(IPADDRESS_PATTERN);
	}

	/**
	 * Validate ip address with regular expression
	 * 
	 * @param ip
	 *            ip address for validation
	 * @return true valid ip address, false invalid ip address
	 */
	public boolean validIP(final String ip) {
		matcher = pattern.matcher(ip);
		return matcher.matches();
	}
	
	
	public boolean validPort(final int port) {
		boolean flag = false;
		if(port > 0 && port < 65536){
			flag = true;
		}
		return flag;
	}
}