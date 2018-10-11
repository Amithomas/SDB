package com.equifax.dashboardportal.model;

public class ServicesRunning {
	//private String Protocol;
	private String servername;
	private String servicename;
	//private String Address;
	private String ip;
	private String servertype;
	private String State;
	//private String PID;
	private String groupName;
	private String port;
	public String getServername() {
		return servername;
	}
	public void setServername(String servername) {
		this.servername = servername;
	}
	public String getServicename() {
		return servicename;
	}
	public void setServicename(String servicename) {
		this.servicename = servicename;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getServertype() {
		return servertype;
	}
	public void setServertype(String servertype) {
		this.servertype = servertype;
	}
	public String getState() {
		return State;
	}
	public void setState(String state) {
		State = state;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public String getPort() {
		return port;
	}
	public void setPort(String port) {
		this.port = port;
	}
	
	
	
	
	

}
